package com.microsoft.azure.management;

import com.microsoft.azure.management.compute.KnownLinuxVirtualMachineImage;
import com.microsoft.azure.management.compute.VirtualMachine;
import com.microsoft.azure.management.compute.implementation.ComputeManager;
import com.microsoft.azure.management.compute.implementation.VirtualMachineInner;
import com.microsoft.azure.management.network.NetworkInterface;
import com.microsoft.azure.management.resources.Deployment;
import com.microsoft.azure.management.resources.DeploymentMode;
import com.microsoft.azure.management.resources.GenericResource;
import com.microsoft.azure.management.resources.core.TestBase;
import com.microsoft.azure.management.resources.core.TestUtilities;
import com.microsoft.azure.management.resources.fluentcore.arm.Region;
import com.microsoft.azure.management.resources.fluentcore.arm.models.GroupableResource;
import com.microsoft.azure.management.resources.fluentcore.model.Creatable;
import com.microsoft.azure.management.resources.fluentcore.model.HasInner;
import com.microsoft.rest.RestClient;
import org.junit.Test;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class DeepDeletionTests extends TestBase {
    private Azure azure;
    private String rgName;

    @Override
    protected void initializeClients(RestClient restClient, String defaultSubscription, String domain) {
        Azure.Authenticated azureAuthed = Azure.authenticate(restClient, defaultSubscription, domain);
        azure = azureAuthed.withSubscription(defaultSubscription);
        rgName = generateRandomResourceName("javamrg", 15);
    }

    @Override
    protected void cleanUpResources() {
        if (azure.resourceGroups().contain(rgName)) {
            azure.resourceGroups().beginDeleteByName(rgName);
        }
    }

    @Test
    public void testUpdateDeleteOptions() throws IOException {
        String createTemplate = "";
        String updateTemplate = "";
        Region region = Region.US_WEST;
        // Create with SDK, verify the deleteOptions is not taking effect
        String vmName1 = generateRandomResourceName("vm", 15);
        String nicName1 = generateRandomResourceName("nic", 15);
        String apiVersion = "2023-07-01";
        NetworkInterface nic1 = azure.networkInterfaces().define(nicName1)
            .withRegion(region)
            .withNewResourceGroup(rgName)
            .withNewPrimaryNetwork("10.0.0.0/24")
            .withPrimaryPrivateIPAddressDynamic()
            .withNewPrimaryPublicIPAddress()
            .create();

        GenericResource nic1Generic = azure.genericResources().getById(nic1.id(), apiVersion);
        Map<String, Object> nic1Properties = (Map<String, Object>) nic1Generic.properties();
        List<Map<String, Object>> ipConfigurations = (List<Map<String, Object>>) nic1Properties.get("ipConfigurations");
        for (Map<String, Object> ipConfiguration : ipConfigurations) {
            Map<String, Object> ipConfigurationProperties = (Map<String, Object>) ipConfiguration.get("properties");
            Map<String, Object> pipConfiguration = (Map<String, Object>) ipConfigurationProperties.get("publicIPAddress");
            Map<String, Object> pipConfigurationProperties = (Map<String, Object>) pipConfiguration.get("properties");
            pipConfigurationProperties.put("deleteOption", "Detach");
        }

        nic1Generic.update()
            .withApiVersion(apiVersion)
            .withProperties(nic1Properties)
            .apply();

        nic1Generic = azure.genericResources().getById(nic1.id(), apiVersion);
        System.out.println(nic1Generic);
//
//        VirtualMachine vm1 = azure
//            .virtualMachines()
//            .define(vmName1)
//            .withRegion(region)
//            .withNewResourceGroup(rgName)
//            .withExistingPrimaryNetworkInterface(nic1)
//            .withPopularLinuxImage(KnownLinuxVirtualMachineImage.UBUNTU_SERVER_16_04_LTS)
//            .withRootUsername("jvuser")
//            .withSsh(TestUtilities.createSshPublicKey())
//            .create();
//        GenericResource vm1Generic = azure.genericResources().getById(vm1.id(), apiVersion);
//        Map<String, Object> properties = (Map<String, Object>) vm1Generic.properties();
//        vm1Generic.update()
//            .withApiVersion(apiVersion)
//            .withProperties(properties)
//            .apply();
//
//        vm1Generic = azure.genericResources().getById(vm1.id(), apiVersion);
//
//        System.out.println(vm1Generic.properties());

        // Create with deployment templates, verify the deleteOptions is taking effect
//        String dp2 = generateRandomResourceName("dp", 15);
//        String vmName2 = generateRandomResourceName("vm", 15);
//        Deployment createdDeployment = azure.deployments()
//            .define(dp2)
//            .withNewResourceGroup(rgName, region)
//            .withTemplate(createTemplate)
//            .withParameters(null)
//            .withMode(DeploymentMode.COMPLETE)
//            .create();


        // Create with deployment templates, update with SDK, verify the deleteOptions is not updated.

        // Create with deployment templates, update with deployment templates, verify the deleteOptions is updated.
    }
}
