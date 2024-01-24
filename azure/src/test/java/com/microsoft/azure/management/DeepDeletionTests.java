package com.microsoft.azure.management;

import com.microsoft.azure.management.compute.VirtualMachine;
import com.microsoft.azure.management.compute.VirtualMachineUpdate;
import com.microsoft.azure.management.compute.implementation.VirtualMachineInner;
import com.microsoft.azure.management.network.Network;
import com.microsoft.azure.management.network.NetworkInterface;
import com.microsoft.azure.management.network.PublicIPAddress;
import com.microsoft.azure.management.network.SecurityRuleAccess;
import com.microsoft.azure.management.network.SecurityRuleDirection;
import com.microsoft.azure.management.network.SecurityRuleProtocol;
import com.microsoft.azure.management.network.Subnet;
import com.microsoft.azure.management.network.implementation.SecurityRuleInner;
import com.microsoft.azure.management.resources.DeploymentMode;
import com.microsoft.azure.management.resources.GenericResource;
import com.microsoft.azure.management.resources.ResourceGroup;
import com.microsoft.azure.management.resources.core.TestBase;
import com.microsoft.azure.management.resources.core.TestUtilities;
import com.microsoft.azure.management.resources.fluentcore.arm.Region;
import com.microsoft.azure.management.resources.implementation.GenericResourceInner;
import com.microsoft.rest.LogLevel;
import com.microsoft.rest.RestClient;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
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

    private String deploymentCreateTemplates = "{\n" +
        "    \"$schema\": \"https://schema.management.azure.com/schemas/2019`-`04-01/deploymentParameters.json#\",\n" +
        "    \"contentVersion\": \"1.0.0.0\",\n" +
        "    \"parameters\": {\n" +
        "        \"location\": {\n" +
        "            \"type\": \"String\"\n" +
        "        },\n" +
        "        \"networkInterfaceName\": {\n" +
        "            \"type\": \"String\"\n" +
        "        },\n" +
        "        \"networkSecurityGroupName\": {\n" +
        "            \"type\": \"String\"\n" +
        "        },\n" +
        "        \"networkSecurityGroupRules\": {\n" +
        "            \"type\": \"Array\"\n" +
        "        },\n" +
        "        \"subnetName\": {\n" +
        "            \"type\": \"String\"\n" +
        "        },\n" +
        "        \"virtualNetworkName\": {\n" +
        "            \"type\": \"String\"\n" +
        "        },\n" +
        "        \"addressPrefixes\": {\n" +
        "            \"type\": \"Array\"\n" +
        "        },\n" +
        "        \"subnets\": {\n" +
        "            \"type\": \"Array\"\n" +
        "        },\n" +
        "        \"publicIpAddressName\": {\n" +
        "            \"type\": \"String\"\n" +
        "        },\n" +
        "        \"publicIpAddressType\": {\n" +
        "            \"type\": \"String\"\n" +
        "        },\n" +
        "        \"publicIpAddressSku\": {\n" +
        "            \"type\": \"String\"\n" +
        "        },\n" +
        "        \"pipDeleteOption\": {\n" +
        "            \"type\": \"String\"\n" +
        "        },\n" +
        "        \"virtualMachineName\": {\n" +
        "            \"type\": \"String\"\n" +
        "        },\n" +
        "        \"virtualMachineComputerName\": {\n" +
        "            \"type\": \"String\"\n" +
        "        },\n" +
        "        \"osDiskType\": {\n" +
        "            \"type\": \"String\"\n" +
        "        },\n" +
        "        \"osDiskDeleteOption\": {\n" +
        "            \"type\": \"String\"\n" +
        "        },\n" +
        "        \"virtualMachineSize\": {\n" +
        "            \"type\": \"String\"\n" +
        "        },\n" +
        "        \"nicDeleteOption\": {\n" +
        "            \"type\": \"String\"\n" +
        "        },\n" +
        "        \"hibernationEnabled\": {\n" +
        "            \"type\": \"Bool\"\n" +
        "        },\n" +
        "        \"adminUsername\": {\n" +
        "            \"type\": \"String\"\n" +
        "        },\n" +
        "        \"adminPublicKey\": {\n" +
        "            \"type\": \"SecureString\"\n" +
        "        },\n" +
        "        \"patchMode\": {\n" +
        "            \"type\": \"String\"\n" +
        "        },\n" +
        "        \"rebootSetting\": {\n" +
        "            \"type\": \"String\"\n" +
        "        },\n" +
        "        \"securityType\": {\n" +
        "            \"type\": \"String\"\n" +
        "        },\n" +
        "        \"secureBoot\": {\n" +
        "            \"type\": \"Bool\"\n" +
        "        },\n" +
        "        \"vTPM\": {\n" +
        "            \"type\": \"Bool\"\n" +
        "        }\n" +
        "    },\n" +
        "    \"variables\": {\n" +
        "        \"nsgId\": \"[resourceId(resourceGroup().name, 'Microsoft.Network/networkSecurityGroups', parameters('networkSecurityGroupName'))]\",\n" +
        "        \"vnetName\": \"[parameters('virtualNetworkName')]\",\n" +
        "        \"vnetId\": \"[resourceId(resourceGroup().name,'Microsoft.Network/virtualNetworks', parameters('virtualNetworkName'))]\",\n" +
        "        \"subnetRef\": \"[concat(variables('vnetId'), '/subnets/', parameters('subnetName'))]\"\n" +
        "    },\n" +
        "    \"resources\": [\n" +
        "        {\n" +
        "            \"type\": \"Microsoft.Network/networkInterfaces\",\n" +
        "            \"apiVersion\": \"2022-11-01\",\n" +
        "            \"name\": \"[parameters('networkInterfaceName')]\",\n" +
        "            \"location\": \"[parameters('location')]\",\n" +
        "            \"dependsOn\": [\n" +
        "                \"[concat('Microsoft.Network/networkSecurityGroups/', parameters('networkSecurityGroupName'))]\",\n" +
        "                \"[concat('Microsoft.Network/virtualNetworks/', parameters('virtualNetworkName'))]\",\n" +
        "                \"[concat('Microsoft.Network/publicIpAddresses/', parameters('publicIpAddressName'))]\"\n" +
        "            ],\n" +
        "            \"properties\": {\n" +
        "                \"ipConfigurations\": [\n" +
        "                    {\n" +
        "                        \"name\": \"ipconfig1\",\n" +
        "                        \"properties\": {\n" +
        "                            \"subnet\": {\n" +
        "                                \"id\": \"[variables('subnetRef')]\"\n" +
        "                            },\n" +
        "                            \"privateIPAllocationMethod\": \"Dynamic\",\n" +
        "                            \"publicIpAddress\": {\n" +
        "                                \"id\": \"[resourceId(resourceGroup().name, 'Microsoft.Network/publicIpAddresses', parameters('publicIpAddressName'))]\",\n" +
        "                                \"properties\": {\n" +
        "                                    \"deleteOption\": \"[parameters('pipDeleteOption')]\"\n" +
        "                                }\n" +
        "                            }\n" +
        "                        }\n" +
        "                    }\n" +
        "                ],\n" +
        "                \"networkSecurityGroup\": {\n" +
        "                    \"id\": \"[variables('nsgId')]\"\n" +
        "                }\n" +
        "            }\n" +
        "        },\n" +
        "        {\n" +
        "            \"type\": \"Microsoft.Network/networkSecurityGroups\",\n" +
        "            \"apiVersion\": \"2019-02-01\",\n" +
        "            \"name\": \"[parameters('networkSecurityGroupName')]\",\n" +
        "            \"location\": \"[parameters('location')]\",\n" +
        "            \"properties\": {\n" +
        "                \"securityRules\": \"[parameters('networkSecurityGroupRules')]\"\n" +
        "            }\n" +
        "        },\n" +
        "        {\n" +
        "            \"type\": \"Microsoft.Network/virtualNetworks\",\n" +
        "            \"apiVersion\": \"2021-05-01\",\n" +
        "            \"name\": \"[parameters('virtualNetworkName')]\",\n" +
        "            \"location\": \"[parameters('location')]\",\n" +
        "            \"properties\": {\n" +
        "                \"addressSpace\": {\n" +
        "                    \"addressPrefixes\": \"[parameters('addressPrefixes')]\"\n" +
        "                },\n" +
        "                \"subnets\": \"[parameters('subnets')]\"\n" +
        "            }\n" +
        "        },\n" +
        "        {\n" +
        "            \"type\": \"Microsoft.Network/publicIpAddresses\",\n" +
        "            \"apiVersion\": \"2020-08-01\",\n" +
        "            \"name\": \"[parameters('publicIpAddressName')]\",\n" +
        "            \"location\": \"[parameters('location')]\",\n" +
        "            \"sku\": {\n" +
        "                \"name\": \"[parameters('publicIpAddressSku')]\"\n" +
        "            },\n" +
        "            \"properties\": {\n" +
        "                \"publicIpAllocationMethod\": \"[parameters('publicIpAddressType')]\"\n" +
        "            }\n" +
        "        },\n" +
        "        {\n" +
        "            \"type\": \"Microsoft.Compute/virtualMachines\",\n" +
        "            \"apiVersion\": \"2022-11-01\",\n" +
        "            \"name\": \"[parameters('virtualMachineName')]\",\n" +
        "            \"location\": \"[parameters('location')]\",\n" +
        "            \"dependsOn\": [\n" +
        "                \"[concat('Microsoft.Network/networkInterfaces/', parameters('networkInterfaceName'))]\"\n" +
        "            ],\n" +
        "            \"properties\": {\n" +
        "                \"hardwareProfile\": {\n" +
        "                    \"vmSize\": \"[parameters('virtualMachineSize')]\"\n" +
        "                },\n" +
        "                \"storageProfile\": {\n" +
        "                    \"osDisk\": {\n" +
        "                        \"createOption\": \"fromImage\",\n" +
        "                        \"managedDisk\": {\n" +
        "                            \"storageAccountType\": \"[parameters('osDiskType')]\"\n" +
        "                        },\n" +
        "                        \"deleteOption\": \"[parameters('osDiskDeleteOption')]\"\n" +
        "                    },\n" +
        "                    \"imageReference\": {\n" +
        "                        \"publisher\": \"canonical\",\n" +
        "                        \"offer\": \"0001-com-ubuntu-server-focal\",\n" +
        "                        \"sku\": \"20_04-lts-gen2\",\n" +
        "                        \"version\": \"latest\"\n" +
        "                    }\n" +
        "                },\n" +
        "                \"networkProfile\": {\n" +
        "                    \"networkInterfaces\": [\n" +
        "                        {\n" +
        "                            \"id\": \"[resourceId('Microsoft.Network/networkInterfaces', parameters('networkInterfaceName'))]\",\n" +
        "                            \"properties\": {\n" +
        "                                \"deleteOption\": \"[parameters('nicDeleteOption')]\"\n" +
        "                            }\n" +
        "                        }\n" +
        "                    ]\n" +
        "                },\n" +
        "                \"additionalCapabilities\": {\n" +
        "                    \"hibernationEnabled\": false\n" +
        "                },\n" +
        "                \"osProfile\": {\n" +
        "                    \"computerName\": \"[parameters('virtualMachineComputerName')]\",\n" +
        "                    \"adminUsername\": \"[parameters('adminUsername')]\",\n" +
        "                    \"linuxConfiguration\": {\n" +
        "                        \"disablePasswordAuthentication\": true,\n" +
        "                        \"ssh\": {\n" +
        "                            \"publicKeys\": [\n" +
        "                                {\n" +
        "                                    \"path\": \"[concat('/home/', parameters('adminUsername'), '/.ssh/authorized_keys')]\",\n" +
        "                                    \"keyData\": \"[parameters('adminPublicKey')]\"\n" +
        "                                }\n" +
        "                            ]\n" +
        "                        },\n" +
        "                        \"patchSettings\": {\n" +
        "                            \"patchMode\": \"[parameters('patchMode')]\",\n" +
        "                            \"automaticByPlatformSettings\": {\n" +
        "                                \"rebootSetting\": \"[parameters('rebootSetting')]\"\n" +
        "                            }\n" +
        "                        }\n" +
        "                    }\n" +
        "                },\n" +
        "                \"securityProfile\": {\n" +
        "                    \"securityType\": \"[parameters('securityType')]\",\n" +
        "                    \"uefiSettings\": {\n" +
        "                        \"secureBootEnabled\": \"[parameters('secureBoot')]\",\n" +
        "                        \"vTpmEnabled\": \"[parameters('vTPM')]\"\n" +
        "                    }\n" +
        "                }\n" +
        "            }\n" +
        "        }\n" +
        "    ],\n" +
        "    \"outputs\": {\n" +
        "        \"adminUsername\": {\n" +
        "            \"type\": \"String\",\n" +
        "            \"value\": \"[parameters('adminUsername')]\"\n" +
        "        }\n" +
        "    }\n" +
        "}";

    private String updateTemplate = "{\n" +
            "    \"$schema\": \"https://schema.management.azure.com/schemas/2019-04-01/deploymentTemplate.json#\",\n" +
            "    \"contentVersion\": \"1.0.0.1\",\n" +
            "    \"parameters\": {\n" +
            "        \"location\": {\n" +
            "            \"type\": \"String\"\n" +
            "        },\n" +
            "        \"networkInterfaceName\": {\n" +
            "            \"type\": \"String\"\n" +
            "        },\n" +
            "        \"subnetId\": {\n" +
            "            \"type\": \"String\"\n" +
            "        },\n" +
            "        \"publicIpAddressName\": {\n" +
            "            \"type\": \"String\"\n" +
            "        },\n" +
            "        \"pipDeleteOption\": {\n" +
            "            \"type\": \"String\"\n" +
            "        }\n" +
            "    },\n" +
            "    \"variables\": {},\n" +
            "    \"resources\": [\n" +
            "        {\n" +
            "            \"type\": \"Microsoft.Network/networkInterfaces\",\n" +
            "            \"apiVersion\": \"2022-11-01\",\n" +
            "            \"name\": \"[parameters('networkInterfaceName')]\",\n" +
            "            \"location\": \"[parameters('location')]\",\n" +
            "            \"properties\": {\n" +
            "                \"ipConfigurations\": [\n" +
            "                    {\n" +
            "                        \"name\": \"ipconfig1\",\n" +
            "                        \"properties\": {\n" +
            "                            \"subnet\": {\n" +
            "                                \"id\": \"[parameters('subnetId')]\"\n" +
            "                            },\n" +
            "                            \"privateIPAllocationMethod\": \"Dynamic\",\n" +
            "                            \"publicIpAddress\": {\n" +
            "                                \"id\": \"[resourceId(resourceGroup().name, 'Microsoft.Network/publicIpAddresses', parameters('publicIpAddressName'))]\",\n" +
            "                                \"properties\": {\n" +
            "                                    \"deleteOption\": \"[parameters('pipDeleteOption')]\"\n" +
            "                                }\n" +
            "                            }\n" +
            "                        }\n" +
            "                    }\n" +
            "                ]\n" +
            "            }\n" +
            "        }\n" +
            "    ],\n" +
            "    \"outputs\": {}\n" +
            "}";

    @Test
    public void testUpdateDeleteOptions() throws IOException {
        Region region = Region.US_WEST;
        ResourceGroup resourceGroup = azure.resourceGroups().define(rgName).withRegion(region).create();
        // scenario1
        // step 1: create VM with ip address delete options "DELETE"
        // step 2: delete VM, verify ip address got deleted
        // scenario1, step 1: create VM with ip address delete options "DELETE"
        VirtualMachine vm1 = createVmWithPipDeleteOptionDelete(region, resourceGroup);
        assert azure.publicIPAddresses().listByResourceGroup(resourceGroup.name()).size() == 1;
        // scenario1, step 2: delete VM, verify ip address got deleted
        String networkId = vm1.getPrimaryNetworkInterface().primaryIPConfiguration().networkId();
        azure.virtualMachines().deleteById(vm1.id());
        assert azure.publicIPAddresses().listByResourceGroup(resourceGroup.name()).size() == 0;
        // clean up resources
        try {
            azure.networks().deleteById(networkId);
        } catch (Exception e) {
            // NO-OP
        }

        // scenario2
        // step 1: create VM with ip address delete option "DELETE"
        // step 2: update VM with deployment template, setting ip address delete option to "Detach"
        // step 3: delete VM, verify ip address not deleted
        // scenario2, step 1: create VM with ip address delete option "DELETE"
        VirtualMachine vm2 = createVmWithPipDeleteOptionDelete(region, resourceGroup);
        assert azure.publicIPAddresses().listByResourceGroup(resourceGroup.name()).size() == 1;

        // scenario2, step 2: update VM with deployment template, setting ip address delete option to "Detach"
        VirtualMachineInner vmInner = vm2.inner();
        NetworkInterface networkInterface = azure.networkInterfaces().getById(vmInner.networkProfile().networkInterfaces().iterator().next().id());
        Map<String, Object> updateParameters = new HashMap<>();
        setParameter(updateParameters, "location", region.toString());
        setParameter(updateParameters, "networkInterfaceName", networkInterface.name());
        setParameter(updateParameters, "subnetId", networkInterface.primaryIPConfiguration().getNetwork().subnets().get("default").inner().id());
        setParameter(updateParameters, "publicIpAddressName", networkInterface.primaryIPConfiguration().getPublicIPAddress().name());
        setParameter(updateParameters, "pipDeleteOption", "Detach");

        String dp2 = generateRandomResourceName("dp", 15);
        azure.deployments().define(dp2)
            .withExistingResourceGroup(resourceGroup)
            .withTemplate(updateTemplate)
            .withParameters(updateParameters)
            .withMode(DeploymentMode.INCREMENTAL)
            .create();

        // scenario2, step 3: delete VM, verify ip address not deleted
        azure.virtualMachines().deleteById(vm2.id());

        assert azure.publicIPAddresses().listByResourceGroup(resourceGroup.name()).size() == 1;
    }

    @Test
    public void testUpdateWithSDK() throws IOException {
        Region region = Region.US_EAST;
        ResourceGroup resourceGroup = azure.resourceGroups().define(rgName).withRegion(region).create();
        // step 1: create VM with ip address delete options "DELETE"
        // step 2: update VM with Track1 SDK, ip address delete option "Detach"
        // step 3: delete VM, verify ip address not deleted
        VirtualMachine vm1 = createVmWithPipDeleteOptionDelete(region, resourceGroup);
        assert azure.publicIPAddresses().listByResourceGroup(rgName).size() == 1;

        // Mandatory, since default api version is too low and there's no deleteOption configuration
        String networkApiVersion = "2023-05-01";
        String computeApiVersion = "2023-07-01";

        GenericResource vmGeneric = azure.genericResources().getById(vm1.id(), computeApiVersion);
        Map<String, Object> vmProperties = (Map<String, Object>) vmGeneric.properties();
        Map<String, Object> networkProfile = (Map<String, Object>) vmProperties.get("networkProfile");
        List<Map<String, Object>> networkInterfaces = (List<Map<String, Object>>) networkProfile.get("networkInterfaces");
        Map<String, Object> vmPropertiesUpdate = new HashMap<>();
        Map<String, Object> networkProfileUpdate = new HashMap<>();
        vmPropertiesUpdate.put("networkProfile", networkProfileUpdate);
        List<Map<String, Object>> nicConfigurationsUpdate = new ArrayList<>();
        networkProfileUpdate.put("networkInterfaceConfigurations", nicConfigurationsUpdate);
        networkProfileUpdate.put("networkApiVersion", networkApiVersion);
        for (Map<String, Object> nicConfiguration : networkInterfaces) {
            String nicId = (String) nicConfiguration.get("id");
            GenericResource nic = azure.genericResources().getById(nicId, networkApiVersion);

            Map<String, Object> nicProperties = (Map<String, Object>) nic.properties();
            List<Map<String, Object>> ipConfigurations = (List<Map<String, Object>>) nicProperties.get("ipConfigurations");

            Map<String, Object> nicConfigurationUpdate = new HashMap<>();
            nicConfigurationsUpdate.add(nicConfigurationUpdate);
            Map<String, Object> nicPropertiesUpdate = new HashMap<>();
            nicConfigurationUpdate.put("properties", nicPropertiesUpdate);
            nicConfigurationUpdate.put("name", nic.name());
            List<Map<String, Object>> ipConfigurationsUpdate = new ArrayList<>();
            nicPropertiesUpdate.put("ipConfigurations", ipConfigurationsUpdate);
            for (Map<String, Object> ipConfiguration : ipConfigurations) {
                Map<String, Object> ipConfigurationProperties = (Map<String, Object>) ipConfiguration.get("properties");
                Map<String, Object> pipConfiguration = (Map<String, Object>) ipConfigurationProperties.get("publicIPAddress");

                // update parameters
                Map<String, Object> ipConfigurationUpdate = new HashMap<>();
                ipConfigurationsUpdate.add(ipConfigurationUpdate);
                ipConfigurationUpdate.put("name", ipConfiguration.get("name"));
//                ipConfigurationUpdate.put("id", ipConfiguration.get("id"));
                Map<String, Object> ipConfigurationPropertiesUpdate = new HashMap<>();
                ipConfigurationUpdate.put("properties", ipConfigurationPropertiesUpdate);
                Map<String, Object> pipConfigurationUpdate = new HashMap<>();
                ipConfigurationPropertiesUpdate.put("publicIPAddressConfiguration", pipConfigurationUpdate);
                Map<String, Object> pipConfigurationPropertiesUpdate = new HashMap<>();
                pipConfigurationUpdate.put("properties", pipConfigurationPropertiesUpdate);
//                pipConfigurationUpdate.put("id", pipConfiguration.get("id"));
                pipConfigurationUpdate.put("name", pipConfiguration.get("name"));
                pipConfigurationPropertiesUpdate.put("deleteOption", "Detach");
            }
        }

        azure.genericResources().manager().inner().resources().update(rgName, "Microsoft.Compute", "", "virtualMachines", vm1.name(), computeApiVersion, new GenericResourceInner().withProperties(vmPropertiesUpdate));

        PublicIPAddress publicIPAddress = vm1.getPrimaryPublicIPAddress();
        System.out.println(publicIPAddress.ipAllocationMethod());

        GenericResource nic1Generic = azure.genericResources().getById(vm1.getPrimaryNetworkInterface().id(), networkApiVersion);

        Map<String, Object> nic1Properties = (Map<String, Object>) nic1Generic.properties();
        List<Map<String, Object>> ipConfigurations = (List<Map<String, Object>>) nic1Properties.get("ipConfigurations");
        for (Map<String, Object> ipConfiguration : ipConfigurations) {
            Map<String, Object> ipConfigurationProperties = (Map<String, Object>) ipConfiguration.get("properties");
            Map<String, Object> pipConfiguration = (Map<String, Object>) ipConfigurationProperties.get("publicIPAddress");
            Map<String, Object> pipConfigurationProperties = (Map<String, Object>) pipConfiguration.get("properties");

            // make sure subnet is not overwritten
            Map<String, Object> subnet = (Map<String, Object>) ipConfigurationProperties.get("subnet");
            assert subnet != null;
            String deleteOption = (String) pipConfigurationProperties.get("deleteOption");
            // verify delete option is updated
            assert "Detach".equals(deleteOption);
        }

        azure.virtualMachines().deleteById(vm1.id());
        assert azure.publicIPAddresses().listByResourceGroup(rgName).size() == 1;
    }

    @Test
    public void testUpdateWithExistingSDK() {
        String resourceGroupName = "rg-xiaofei";
        String vmName = "test2023122101";
        String networkApiVersion = "2023-05-01";
        String computeApiVersion = "2023-07-01";

        VirtualMachine vm = azure.virtualMachines().getByResourceGroup(resourceGroupName, vmName);
        GenericResource vmGeneric = azure.genericResources().getById(vm.id(), computeApiVersion);
        Map<String, Object> vmProperties = (Map<String, Object>) vmGeneric.properties();
        Map<String, Object> networkProfile = (Map<String, Object>) vmProperties.get("networkProfile");
        List<Map<String, Object>> networkInterfaces = (List<Map<String, Object>>) networkProfile.get("networkInterfaces");
        Map<String, Object> vmPropertiesUpdate = new HashMap<>();
        Map<String, Object> networkProfileUpdate = new HashMap<>();
        vmPropertiesUpdate.put("networkProfile", networkProfileUpdate);
        List<Map<String, Object>> nicConfigurationsUpdate = new ArrayList<>();
        networkProfileUpdate.put("networkInterfaceConfigurations", nicConfigurationsUpdate);
        networkProfileUpdate.put("networkApiVersion", networkApiVersion);
        for (Map<String, Object> nicConfiguration : networkInterfaces) {
            String nicId = (String) nicConfiguration.get("id");
            GenericResource nic = azure.genericResources().getById(nicId, networkApiVersion);

            Map<String, Object> nicProperties = (Map<String, Object>) nic.properties();
            List<Map<String, Object>> ipConfigurations = (List<Map<String, Object>>) nicProperties.get("ipConfigurations");

            Map<String, Object> nicConfigurationUpdate = new HashMap<>();
            nicConfigurationsUpdate.add(nicConfigurationUpdate);
            Map<String, Object> nicPropertiesUpdate = new HashMap<>();
            nicConfigurationUpdate.put("properties", nicPropertiesUpdate);
            nicConfigurationUpdate.put("name", nic.name());
            List<Map<String, Object>> ipConfigurationsUpdate = new ArrayList<>();
            nicPropertiesUpdate.put("ipConfigurations", ipConfigurationsUpdate);
            for (Map<String, Object> ipConfiguration : ipConfigurations) {
                Map<String, Object> ipConfigurationProperties = (Map<String, Object>) ipConfiguration.get("properties");
                Map<String, Object> pipConfiguration = (Map<String, Object>) ipConfigurationProperties.get("publicIPAddress");

                // update parameters
                Map<String, Object> ipConfigurationUpdate = new HashMap<>();
                ipConfigurationsUpdate.add(ipConfigurationUpdate);
                ipConfigurationUpdate.put("name", ipConfiguration.get("name"));
//                ipConfigurationUpdate.put("id", ipConfiguration.get("id"));
                Map<String, Object> ipConfigurationPropertiesUpdate = new HashMap<>();
                ipConfigurationUpdate.put("properties", ipConfigurationPropertiesUpdate);
                Map<String, Object> pipConfigurationUpdate = new HashMap<>();
                ipConfigurationPropertiesUpdate.put("publicIPAddressConfiguration", pipConfigurationUpdate);
                Map<String, Object> pipConfigurationPropertiesUpdate = new HashMap<>();
                pipConfigurationUpdate.put("properties", pipConfigurationPropertiesUpdate);
//                pipConfigurationUpdate.put("id", pipConfiguration.get("id"));
                pipConfigurationUpdate.put("name", pipConfiguration.get("name"));
                pipConfigurationPropertiesUpdate.put("deleteOption", "Detach");
            }
        }

        azure.genericResources().manager().inner().resources().update(resourceGroupName, "Microsoft.Compute", "", "virtualMachines", vm.name(), computeApiVersion, new GenericResourceInner().withProperties(vmPropertiesUpdate));
        PublicIPAddress publicIPAddress = vm.getPrimaryPublicIPAddress();
        System.out.println(publicIPAddress.ipAllocationMethod());

        GenericResource nic1Generic = azure.genericResources().getById(vm.getPrimaryNetworkInterface().id(), networkApiVersion);

        Map<String, Object> nic1Properties = (Map<String, Object>) nic1Generic.properties();
        List<Map<String, Object>> ipConfigurations = (List<Map<String, Object>>) nic1Properties.get("ipConfigurations");
        for (Map<String, Object> ipConfiguration : ipConfigurations) {
            Map<String, Object> ipConfigurationProperties = (Map<String, Object>) ipConfiguration.get("properties");
            Map<String, Object> pipConfiguration = (Map<String, Object>) ipConfigurationProperties.get("publicIPAddress");
            Map<String, Object> pipConfigurationProperties = (Map<String, Object>) pipConfiguration.get("properties");

            // make sure subnet is not overwritten
            Map<String, Object> subnet = (Map<String, Object>) ipConfigurationProperties.get("subnet");
            assert subnet != null;
            String deleteOption = (String) pipConfigurationProperties.get("deleteOption");
            // verify delete option is updated
            assert "Detach".equals(deleteOption);
        }
    }

    private String createAndUpdateTemplate = "{\n" +
            "\t\"$schema\": \"https://schema.management.azure.com/schemas/2019-04-01/deploymentTemplate.json#\",\n" +
            "\t\"contentVersion\": \"1.0.0.0\",\n" +
            "\t\"parameters\":{\n" +
            "        \"pipDeleteOption\": {\n" +
            "            \"type\": \"string\"\n" +
            "        }\n" +
            "\t},\n" +
            "\t\"variables\": {\n" +
            "\t\t\"vnetID\": \"[resourceId('Microsoft.Network/virtualNetworks', 'vnet1')]\",\n" +
            "\t\t\"pipID\": \"[resourceId('Microsoft.Network/publicIPAddresses', 'pip1')]\",\n" +
            "\t\t\"subnet1id\": \"[concat(variables('vnetID'),'/subnets/subnet1')]\"\n" +
            "\t},\n" +
            "\t\"resources\": [\n" +
            "\t\t{\n" +
            "\t\t\t\"name\": \"nsg1\",\n" +
            "\t\t\t\"type\": \"Microsoft.Network/networkSecurityGroups\",\n" +
            "\t\t\t\"apiVersion\": \"2023-05-01\",\n" +
            "\t\t\t\"location\": \"[resourceGroup().location]\",\n" +
            "\t\t\t\"properties\": {\n" +
            "\t\t\t\t\"securityRules\": [\n" +
            "\t\t\t\t\t{\n" +
            "\t\t\t\t\t\t\"name\": \"test-port-allow-pro\",\n" +
            "\t\t\t\t\t\t\"properties\": {\n" +
            "\t\t\t\t\t\t\t\"description\": \"\",\n" +
            "\t\t\t\t\t\t\t\"protocol\": \"Tcp\",\n" +
            "\t\t\t\t\t\t\t\"sourcePortRange\": \"*\",\n" +
            "\t\t\t\t\t\t\t\"destinationPortRange\": \"*\",\n" +
            "\t\t\t\t\t\t\t\"sourceAddressPrefix\": \"*\",\n" +
            "\t\t\t\t\t\t\t\"destinationAddressPrefix\": \"*\",\n" +
            "\t\t\t\t\t\t\t\"access\": \"Allow\",\n" +
            "\t\t\t\t\t\t\t\"priority\": 121,\n" +
            "\t\t\t\t\t\t\t\"direction\": \"Inbound\"\n" +
            "\t\t\t\t\t\t}\n" +
            "\t\t\t\t\t}\n" +
            "\t\t\t\t]\n" +
            "\t\t\t}\n" +
            "\t\t},\n" +
            "\t\t{\n" +
            "\t\t\t\"name\": \"vnet1\",\n" +
            "\t\t\t\"comments\": \"Virtual Network\",\n" +
            "\t\t\t\"type\": \"Microsoft.Network/virtualNetworks\",\n" +
            "\t\t\t\"apiVersion\": \"2023-05-01\",\n" +
            "\t\t\t\"location\": \"[resourceGroup().location]\",\n" +
            "\t\t\t\"dependsOn\": [\n" +
            "\t\t\t\t\"[resourceId('Microsoft.Network/networkSecurityGroups', 'nsg1')]\"\n" +
            "\t\t\t],\n" +
            "\t\t\t\"properties\": {\n" +
            "\t\t\t\t\"addressSpace\": {\n" +
            "\t\t\t\t\t\"addressPrefixes\": [\n" +
            "\t\t\t\t\t\t\"10.0.0.0/16\"\n" +
            "\t\t\t\t\t]\n" +
            "\t\t\t\t},\n" +
            "\t\t\t\t\"subnets\": [\n" +
            "\t\t\t\t\t{\n" +
            "\t\t\t\t\t\t\"name\": \"subnet1\",\n" +
            "\t\t\t\t\t\t\"properties\": {\n" +
            "\t\t\t\t\t\t\t\"addressPrefix\": \"10.0.0.0/24\",\n" +
            "\t\t\t\t\t\t\t\"networkSecurityGroup\": {\n" +
            "\t\t\t\t\t\t\t\t\"id\": \"[resourceId('Microsoft.Network/networkSecurityGroups', 'nsg1')]\"\n" +
            "\t\t\t\t\t\t\t}\n" +
            "\t\t\t\t\t\t}\n" +
            "\t\t\t\t\t}\n" +
            "\t\t\t\t]\n" +
            "\t\t\t}\n" +
            "\t\t},\n" +
            "\t\t{\n" +
            "\t\t\t\"name\": \"pip1\",\n" +
            "\t\t\t\"type\": \"Microsoft.Network/publicIPAddresses\",\n" +
            "\t\t\t\"location\": \"[resourceGroup().location]\",\n" +
            "\t\t\t\"apiVersion\": \"2023-05-01\",\n" +
            "\t\t\t\"sku\": {\n" +
            "\t\t\t\t\"name\": \"Standard\"\n" +
            "\t\t\t},\n" +
            "\t\t\t\"properties\": {\n" +
            "\t\t\t\t\"publicIPAllocationMethod\": \"Static\"\n" +
            "\t\t\t}\n" +
            "\t\t},\n" +
            "\t\t{\n" +
            "\t\t\t\"name\": \"nic1\",\n" +
            "\t\t\t\"type\": \"Microsoft.Network/networkInterfaces\",\n" +
            "\t\t\t\"location\": \"[resourceGroup().location]\",\n" +
            "\t\t\t\"apiVersion\": \"2023-05-01\",\n" +
            "\t\t\t\"dependsOn\": [\n" +
            "\t\t\t\t\"[variables('vnetID')]\",\n" +
            "\t\t\t\t\"[variables('pipID')]\"\n" +
            "\t\t\t],\n" +
            "\t\t\t\"properties\": {\n" +
            "\t\t\t\t\"primary\": true,\n" +
            "\t\t\t\t\"ipConfigurations\": [\n" +
            "\t\t\t\t\t{\n" +
            "\t\t\t\t\t\t\"name\": \"ipConfig2\",\n" +
            "\t\t\t\t\t\t\"properties\": {\n" +
            "\t\t\t\t\t\t\t\"primary\": false,\n" +
            "\t\t\t\t\t\t\t\"subnet\": {\n" +
            "\t\t\t\t\t\t\t\t\"id\": \"[variables('subnet1id')]\"\n" +
            "\t\t\t\t\t\t\t},\n" +
            "\t\t\t\t\t\t\t\"publicIPAddress\": {\n" +
            "\t\t\t\t\t\t\t\t\"id\": \"[variables('pipID')]\",\n" +
            "\t\t\t\t\t\t\t\t\"properties\": {\n" +
            "\t\t\t\t\t\t\t\t\t\"deleteOption\": \"[parameters('pipDeleteOption')]\"\n" +
            "\t\t\t\t\t\t\t\t}\n" +
            "\t\t\t\t\t\t\t}\n" +
            "\t\t\t\t\t\t}\n" +
            "\t\t\t\t\t}\n" +
            "\t\t\t\t]\n" +
            "\t\t\t}\n" +
            "\t\t}\n" +
            "\t]\n" +
            "}";

    @Test
    public void createAndUpdateNicWithPipDeleteOption() throws IOException {
        Map<String, Object> parameters = new HashMap<>();
        // create with "pipDeleteOption" "Delete"
        setParameter(parameters, "pipDeleteOption", "Delete");
        azure.deployments().define(generateRandomResourceName("dp", 15))
                .withNewResourceGroup(rgName, Region.US_EAST)
                .withTemplate(createAndUpdateTemplate)
                .withParameters(parameters)
                .withMode(DeploymentMode.INCREMENTAL)
                .create();

        setParameter(parameters, "pipDeleteOption", "Detach");
        // update with "pipDeleteOption" "Detach"
        azure.deployments().define(generateRandomResourceName("dp", 15))
                .withExistingResourceGroup(rgName)
                .withTemplate(createAndUpdateTemplate)
                .withParameters(parameters)
                .withMode(DeploymentMode.INCREMENTAL)
                .create();
    }

    private VirtualMachine createVmWithPipDeleteOptionDelete(Region region, ResourceGroup resourceGroup) throws IOException {

        String vnetName = generateRandomResourceName("vnet", 15);
        String subnetName = "default";
        Network network = azure.networks()
            .define(vnetName)
            .withRegion(region)
            .withExistingResourceGroup(resourceGroup)
            .withAddressSpace("10.0.0.0/16")
            .withSubnet(subnetName, "10.0.0.0/24")
            .create();

        Subnet subnet = network.subnets().get(subnetName);
        String publicIpAddressName = generateRandomResourceName("pip", 15);
        String vmName = generateRandomResourceName("vm", 15);

        // create VM with deployment template
        SecurityRuleInner rule = new SecurityRuleInner().withName("SSH").withPriority(300).withProtocol(SecurityRuleProtocol.TCP).withAccess(SecurityRuleAccess.ALLOW).withDirection(SecurityRuleDirection.INBOUND).withSourceAddressPrefix("*").withSourcePortRange("*").withDestinationAddressPrefix("*").withDestinationPortRange("22");

        Map<String, Object> parameters = new HashMap<>();
        setParameter(parameters, "location", region.toString());
        setParameter(parameters, "networkInterfaceName", generateRandomResourceName("nic", 15));
        setParameter(parameters, "nicDeleteOption", "Delete");
        setParameter(parameters, "networkSecurityGroupName", generateRandomResourceName("nsg", 15));
        setParameter(parameters, "networkSecurityGroupRules", Collections.singletonList(rule));
        setParameter(parameters, "addressPrefixes", Collections.singletonList("10.0.0.0/16"));
        setParameter(parameters, "subnetName", "default");
        setParameter(parameters, "subnets", Collections.singletonList(subnet.inner()));
        setParameter(parameters, "virtualNetworkName", generateRandomResourceName("vnet", 15));
        setParameter(parameters, "publicIpAddressName", publicIpAddressName);
        setParameter(parameters, "publicIpAddressType", "Static");
        setParameter(parameters, "publicIpAddressSku", "Standard");
        setParameter(parameters, "pipDeleteOption", "Delete"); // pipDeleteOption=Delete
        setParameter(parameters, "virtualMachineName", vmName);
        setParameter(parameters, "virtualMachineComputerName", vmName);
        setParameter(parameters, "osDiskType", "Standard_LRS");
        setParameter(parameters, "osDiskDeleteOption", "Delete");
        setParameter(parameters, "virtualMachineSize", "Standard_B1s");
        setParameter(parameters, "adminUsername", "azureuser");
        setParameter(parameters, "adminPublicKey", TestUtilities.createSshPublicKey());
        setParameter(parameters, "hibernationEnabled", false);
        setParameter(parameters, "patchMode", "AutomaticByPlatform");
        setParameter(parameters, "rebootSetting", "IfRequired");
        setParameter(parameters, "secureBoot", true);
        setParameter(parameters, "securityType", "TrustedLaunch");
        setParameter(parameters, "vTPM", true);

        String dp1 = generateRandomResourceName("dp", 15);
        azure.deployments().define(dp1)
            .withNewResourceGroup(rgName, region)
            .withTemplate(deploymentCreateTemplates)
            .withParameters(parameters)
            .withMode(DeploymentMode.COMPLETE)
            .create();

        return azure.virtualMachines().getByResourceGroup(resourceGroup.name(), vmName);
    }

    private static void setParameter(Map<String, Object> parameters, final String name, final Object value) {
        parameters.put(name, new HashMap<String, Object>(){{this.put("value", value);}});
    }
}
