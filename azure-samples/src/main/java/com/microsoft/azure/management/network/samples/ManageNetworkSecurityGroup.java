/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for
 * license information.
 */

package com.microsoft.azure.management.network.samples;

import com.microsoft.azure.management.Azure;
import com.microsoft.azure.management.compute.KnownLinuxVirtualMachineImage;
import com.microsoft.azure.management.compute.VirtualMachine;
import com.microsoft.azure.management.compute.VirtualMachineSizeTypes;
import com.microsoft.azure.management.network.Network;
import com.microsoft.azure.management.network.NetworkInterface;
import com.microsoft.azure.management.network.NetworkSecurityGroup;
import com.microsoft.azure.management.network.SecurityRuleProtocol;
import com.microsoft.azure.management.resources.fluentcore.arm.Region;
import com.microsoft.azure.management.resources.fluentcore.utils.SdkContext;
import com.microsoft.azure.management.samples.SSHShell;
import com.microsoft.azure.management.samples.Utils;
import com.microsoft.rest.LogLevel;

import java.io.File;
import java.util.Date;
import java.util.List;

/**
 * Azure Network sample for managing network security groups -
 *  - Create a network security group for the front end of a subnet
 *  - Create a network security group for the back end of a subnet
 *  - Create Linux virtual machines for the front end and back end
 *  -- Apply network security groups
 *  - List network security groups
 *  - Update a network security group.
 */

public final class ManageNetworkSecurityGroup {

    /**
     * Main function which runs the actual sample.
     * @param azure instance of the azure client
     * @return true if sample runs successfully
     */
    public static boolean runSample(Azure azure) {
        final Region region = Region.US_WEST;
        final String frontEndNSGName = SdkContext.randomResourceName("fensg", 24);
        final String backEndNSGName = SdkContext.randomResourceName("bensg", 24);
        final String rgName = SdkContext.randomResourceName("rgNEMS", 24);
        final String vnetName = SdkContext.randomResourceName("vnet", 24);
        final String networkInterfaceName1 = SdkContext.randomResourceName("nic1", 24);
        final String networkInterfaceName2 = SdkContext.randomResourceName("nic2", 24);
        final String publicIPAddressLeafDNS1 = SdkContext.randomResourceName("pip1", 24);
        final String frontEndVMName = SdkContext.randomResourceName("fevm", 24);
        final String backEndVMName = SdkContext.randomResourceName("bevm", 24);
        final String userName = "tirekicker";
        try {
            final String sshKey = SSHShell.generateSSHKeys(null, null).getSshPublicKey();

            // Define a virtual network for VMs in this availability set

            System.out.println("Creating a virtual network ...");

            Network network = azure.networks().define(vnetName)
                    .withRegion(region)
                    .withNewResourceGroup(rgName)
                    .withAddressSpace("172.16.0.0/16")
                    .defineSubnet("Front-end")
                        .withAddressPrefix("172.16.1.0/24")
                        .attach()
                    .defineSubnet("Back-end")
                        .withAddressPrefix("172.16.2.0/24")
                        .attach()
                    .create();

            System.out.println("Created a virtual network: " + network.id());
            Utils.print(network);

            //============================================================
            // Create a network security group for the front end of a subnet
            // front end subnet contains two rules
            // - ALLOW-SSH - allows SSH traffic into the front end subnet
            // - ALLOW-WEB- allows HTTP traffic into the front end subnet

            System.out.println("Creating a security group for the front end - allows SSH and HTTP");
            NetworkSecurityGroup frontEndNSG = azure.networkSecurityGroups().define(frontEndNSGName)
                    .withRegion(region)
                    .withNewResourceGroup(rgName)
                    .defineRule("ALLOW-SSH")
                        .allowInbound()
                        .fromAnyAddress()
                        .fromAnyPort()
                        .toAnyAddress()
                        .toPort(22)
                        .withProtocol(SecurityRuleProtocol.TCP)
                        .withPriority(100)
                        .withDescription("Allow SSH")
                        .attach()
                    .defineRule("ALLOW-HTTP")
                        .allowInbound()
                        .fromAnyAddress()
                        .fromAnyPort()
                        .toAnyAddress()
                        .toPort(80)
                        .withProtocol(SecurityRuleProtocol.TCP)
                        .withPriority(101)
                        .withDescription("Allow HTTP")
                        .attach()
                    .create();

            System.out.println("Created a security group for the front end: " + frontEndNSG.id());
            Utils.print(frontEndNSG);


            //============================================================
            // Create a network security group for the back end of a subnet
            // back end subnet contains two rules
            // - ALLOW-SQL - allows SQL traffic only from the front end subnet
            // - DENY-WEB - denies all outbound internet traffic from the back end subnet

            System.out.println("Creating a security group for the front end - allows SSH and "
                    + "denies all outbound internet traffic  ");

            NetworkSecurityGroup backEndNSG = azure.networkSecurityGroups().define(backEndNSGName)
                    .withRegion(region)
                    .withExistingResourceGroup(rgName)
                    .defineRule("ALLOW-SQL")
                        .allowInbound()
                        .fromAddress("172.16.1.0/24")
                        .fromAnyPort()
                        .toAnyAddress()
                        .toPort(1433)
                        .withProtocol(SecurityRuleProtocol.TCP)
                        .withPriority(100)
                        .withDescription("Allow SQL")
                        .attach()
                    .defineRule("DENY-WEB")
                        .denyOutbound()
                        .fromAnyAddress()
                        .fromAnyPort()
                        .toAnyAddress()
                        .toAnyPort()
                        .withAnyProtocol()
                        .withDescription("Deny Web")
                        .withPriority(200)
                        .attach()
                    .create();

            System.out.println("Created a security group for the back end: " + backEndNSG.id());
            Utils.print(backEndNSG);

            System.out.println("Creating multiple network interfaces");
            System.out.println("Creating network interface 1");


            //========================================================
            // Create a network interface and apply the
            // front end network security group

            System.out.println("Creating a network interface for the front end");

            NetworkInterface networkInterface1 = azure.networkInterfaces().define(networkInterfaceName1)
                    .withRegion(region)
                    .withExistingResourceGroup(rgName)
                    .withExistingPrimaryNetwork(network)
                    .withSubnet("Front-end")
                    .withPrimaryPrivateIPAddressDynamic()
                    .withNewPrimaryPublicIPAddress(publicIPAddressLeafDNS1)
                    .withIPForwarding()
                    .withExistingNetworkSecurityGroup(frontEndNSG)
                    .create();

            System.out.println("Created network interface for the front end");

            Utils.print(networkInterface1);


            //========================================================
            // Create a network interface and apply the
            // back end network security group

            System.out.println("Creating a network interface for the back end");

            NetworkInterface networkInterface2 = azure.networkInterfaces().define(networkInterfaceName2)
                    .withRegion(region)
                    .withExistingResourceGroup(rgName)
                    .withExistingPrimaryNetwork(network)
                    .withSubnet("Back-end")
                    .withPrimaryPrivateIPAddressDynamic()
                    .withExistingNetworkSecurityGroup(backEndNSG)
                    .create();

            Utils.print(networkInterface2);


            //=============================================================
            // Create a virtual machine (for the front end)
            // with the network interface that has the network security group for the front end

            System.out.println("Creating a Linux virtual machine (for the front end) - "
                    + "with the network interface that has the network security group for the front end");

            Date t1 = new Date();

            VirtualMachine frontEndVM = azure.virtualMachines().define(frontEndVMName)
                    .withRegion(region)
                    .withExistingResourceGroup(rgName)
                    .withExistingPrimaryNetworkInterface(networkInterface1)
                    .withPopularLinuxImage(KnownLinuxVirtualMachineImage.UBUNTU_SERVER_16_04_LTS)
                    .withRootUsername(userName)
                    .withSsh(sshKey)
                    .withSize(VirtualMachineSizeTypes.fromString("Standard_D2a_v4"))
                    .create();

            Date t2 = new Date();
            System.out.println("Created Linux VM: (took "
                    + ((t2.getTime() - t1.getTime()) / 1000) + " seconds) " + frontEndVM.id());
            // Print virtual machine details
            Utils.print(frontEndVM);


            //=============================================================
            // Create a virtual machine (for the back end)
            // with the network interface that has the network security group for the back end

            System.out.println("Creating a Linux virtual machine (for the back end) - "
                    + "with the network interface that has the network security group for the back end");

            t1 = new Date();

            VirtualMachine backEndVM = azure.virtualMachines().define(backEndVMName)
                    .withRegion(region)
                    .withExistingResourceGroup(rgName)
                    .withExistingPrimaryNetworkInterface(networkInterface2)
                    .withPopularLinuxImage(KnownLinuxVirtualMachineImage.UBUNTU_SERVER_16_04_LTS)
                    .withRootUsername(userName)
                    .withSsh(sshKey)
                    .withSize(VirtualMachineSizeTypes.fromString("Standard_D2a_v4"))
                    .create();

            t2 = new Date();
            System.out.println("Created a Linux VM: (took "
                    + ((t2.getTime() - t1.getTime()) / 1000) + " seconds) " + backEndVM.id());
            Utils.print(backEndVM);


            //========================================================
            // List network security groups

            System.out.println("Walking through network security groups");
            List<NetworkSecurityGroup> networkSecurityGroups = azure.networkSecurityGroups().listByResourceGroup(rgName);

            for (NetworkSecurityGroup networkSecurityGroup: networkSecurityGroups) {
                Utils.print(networkSecurityGroup);
            }


            //========================================================
            // Update a network security group

            System.out.println("Updating the front end network security group to allow FTP");

            frontEndNSG.update()
                    .defineRule("ALLOW-FTP")
                        .allowInbound()
                        .fromAnyAddress()
                        .fromAnyPort()
                        .toAnyAddress()
                        .toPortRange(20, 21)
                        .withProtocol(SecurityRuleProtocol.TCP)
                        .withDescription("Allow FTP")
                        .withPriority(200)
                        .attach()
                    .apply();

            System.out.println("Updated the front end network security group");
            Utils.print(frontEndNSG);
            return true;
        } catch (Exception f) {

            System.out.println(f.getMessage());
            f.printStackTrace();

        } finally {

            try {
                System.out.println("Deleting Resource Group: " + rgName);
                azure.resourceGroups().deleteByName(rgName);
                System.out.println("Deleted Resource Group: " + rgName);
            } catch (NullPointerException npe) {
                System.out.println("Did not create any resources in Azure. No clean up is necessary");
            } catch (Exception g) {
                g.printStackTrace();
            }
        }
        return false;
    }

    /**
     * Main entry point.
     * @param args the parameters
     */
    public static void main(String[] args) {



        try {

            //=============================================================
            // Authenticate

            final File credFile = new File(System.getenv("AZURE_AUTH_LOCATION"));

            Azure azure = Azure.configure()
                    .withLogLevel(LogLevel.BASIC)
                    .authenticate(credFile)
                    .withDefaultSubscription();

            // Print selected subscription
            System.out.println("Selected subscription: " + azure.subscriptionId());

            runSample(azure);
        } catch (Exception e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
        }
    }

    private ManageNetworkSecurityGroup() {

    }
}