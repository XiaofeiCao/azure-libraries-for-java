package com.microsoft.azure.management;

import com.microsoft.azure.management.compute.KnownLinuxVirtualMachineImage;
import com.microsoft.azure.management.compute.VirtualMachine;
import com.microsoft.azure.management.compute.implementation.ComputeManager;
import com.microsoft.azure.management.compute.implementation.VirtualMachineInner;
import com.microsoft.azure.management.network.Network;
import com.microsoft.azure.management.network.NetworkInterface;
import com.microsoft.azure.management.network.NetworkSecurityRule;
import com.microsoft.azure.management.network.SecurityRuleAccess;
import com.microsoft.azure.management.network.SecurityRuleDirection;
import com.microsoft.azure.management.network.SecurityRuleProtocol;
import com.microsoft.azure.management.network.Subnet;
import com.microsoft.azure.management.network.implementation.SecurityRuleInner;
import com.microsoft.azure.management.network.implementation.SubnetInner;
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
            "    \"$schema\": \"https://schema.management.azure.com/schemas/2019-04-01/deploymentParameters.json#\",\n" +
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
            "    \"apiVersion\": \"2020-06-01\",\n" +
            "    \"type\": \"Microsoft.Resources/deployments\",\n" +
            "    \"name\": \"updateVNet\",\n" +
            "    \"properties\": {\n" +
            "        \"mode\": \"Incremental\",\n" +
            "        \"parameters\": {},\n" +
            "        \"template\": {\n" +
            "            \"$schema\": \"https://schema.management.azure.com/schemas/2019-04-01/deploymentTemplate.json#\",\n" +
            "            \"contentVersion\": \"1.0.0.1\",\n" +
            "            \"parameters\": {\n" +
            "                \"location\": {\n" +
            "                    \"type\": \"String\"\n" +
            "                },\n" +
            "                \"networkInterfaceName\": {\n" +
            "                    \"type\": \"String\"\n" +
            "                },\n" +
            "                \"subnetId\": {\n" +
            "                    \"type\": \"String\"\n" +
            "                },\n" +
            "                \"publicIpAddressName\": {\n" +
            "                    \"type\": \"String\"\n" +
            "                },\n" +
            "                \"pipDeleteOption\": {\n" +
            "                    \"type\": \"String\"\n" +
            "                }\n" +
            "            },\n" +
            "            \"variables\": {},\n" +
            "            \"resources\": [\n" +
            "                {\n" +
            "                    \"type\": \"Microsoft.Network/networkInterfaces\",\n" +
            "                    \"apiVersion\": \"2022-11-01\",\n" +
            "                    \"name\": \"[parameters('networkInterfaceName')]\",\n" +
            "                    \"location\": \"[parameters('location')]\",\n" +
            "                    \"properties\": {\n" +
            "                        \"ipConfigurations\": [\n" +
            "                            {\n" +
            "                                \"name\": \"ipconfig1\",\n" +
            "                                \"properties\": {\n" +
            "                                    \"subnet\": {\n" +
            "                                        \"id\": \"[parameters('subnetId')]\"\n" +
            "                                    },\n" +
            "                                    \"privateIPAllocationMethod\": \"Dynamic\",\n" +
            "                                    \"publicIpAddress\": {\n" +
            "                                        \"id\": \"[resourceId(resourceGroup().name, 'Microsoft.Network/publicIpAddresses', parameters('publicIpAddressName'))]\",\n" +
            "                                        \"properties\": {\n" +
            "                                            \"deleteOption\": \"[parameters('pipDeleteOption')]\"\n" +
            "                                        }\n" +
            "                                    }\n" +
            "                                }\n" +
            "                            }\n" +
            "                        ]\n" +
            "                    }\n" +
            "                }\n" +
            "            ],\n" +
            "            \"outputs\": {}\n" +
            "        }\n" +
            "    }\n" +
            "}";

    @Test
    public void testUpdateDeleteOptions() throws IOException {
        String createTemplate = "";
        String updateTemplate = "";
        Region region = Region.US_EAST;
        // Create with SDK, verify the deleteOptions is not taking effect
//        String vmName1 = generateRandomResourceName("vm", 15);
//        String nicName1 = generateRandomResourceName("nic", 15);
        String networkApiVersion = "2023-05-01";
        String computeApiVersion = "2023-07-01";
//        NetworkInterface nic1 = azure.networkInterfaces().define(nicName1)
//            .withRegion(region)
//            .withNewResourceGroup(rgName)
//            .withNewPrimaryNetwork("10.0.0.0/24")
//            .withPrimaryPrivateIPAddressDynamic()
//            .withNewPrimaryPublicIPAddress()
//            .create();
//
//        GenericResource nic1Generic = azure.genericResources().getById(nic1.id(), networkApiVersion);
//        Map<String, Object> nic1Properties = (Map<String, Object>) nic1Generic.properties();
//        List<Map<String, Object>> ipConfigurations = (List<Map<String, Object>>) nic1Properties.get("ipConfigurations");
//        for (Map<String, Object> ipConfiguration : ipConfigurations) {
//            Map<String, Object> ipConfigurationProperties = (Map<String, Object>) ipConfiguration.get("properties");
//            Map<String, Object> pipConfiguration = (Map<String, Object>) ipConfigurationProperties.get("publicIPAddress");
//            Map<String, Object> pipConfigurationProperties = (Map<String, Object>) pipConfiguration.get("properties");
//            if (pipConfigurationProperties == null) {
//                pipConfigurationProperties = new HashMap<>();
//                pipConfiguration.put("properties", pipConfigurationProperties);
//            }
//            pipConfigurationProperties.put("deleteOption", "Detach");
//            pipConfigurationProperties.put("publicIPAllocationMethod", "Static");
//        }
//
//        nic1Generic.update()
//            .withApiVersion(networkApiVersion)
//            .withProperties(nic1Properties)
//            .apply();
//
//        nic1Generic = azure.genericResources().getById(nic1.id(), networkApiVersion);
//        System.out.println(nic1Generic);

        String dp1 = generateRandomResourceName("dp", 15);
        Map<String, Object> parameters = new HashMap<>();
        String vmName1 = generateRandomResourceName("vm", 15);
        final String pipName1 = generateRandomResourceName("pip", 15);
        SecurityRuleInner rule = new SecurityRuleInner().withName("SSH").withPriority(300).withProtocol(SecurityRuleProtocol.TCP).withAccess(SecurityRuleAccess.ALLOW).withDirection(SecurityRuleDirection.INBOUND).withSourceAddressPrefix("*").withSourcePortRange("*").withDestinationAddressPrefix("*").withDestinationPortRange("22");
        SubnetInner subnetInner = new SubnetInner().withAddressPrefix("10.0.0.0/24").withName("default");
        String nicName = generateRandomResourceName("nic", 15);
        String vnetName = generateRandomResourceName("vnet", 15);

        setParameter(parameters, "location", region.toString());
        setParameter(parameters, "networkInterfaceName", nicName);
        setParameter(parameters, "nicDeleteOption", "Delete");
        setParameter(parameters, "networkSecurityGroupName", generateRandomResourceName("nsg", 15));
        setParameter(parameters, "networkSecurityGroupRules", Collections.singletonList(rule));
        setParameter(parameters, "addressPrefixes", Collections.singletonList("10.0.0.0/16"));
        setParameter(parameters, "subnetName", "default");
        setParameter(parameters, "subnets", Collections.singletonList(subnetInner));
        setParameter(parameters, "virtualNetworkName", vnetName);
        setParameter(parameters, "publicIpAddressName", pipName1);
        setParameter(parameters, "publicIpAddressType", "Static");
        setParameter(parameters, "publicIpAddressSku", "Standard");
        setParameter(parameters, "pipDeleteOption", "Delete");
        setParameter(parameters, "virtualMachineName", vmName1);
        setParameter(parameters, "virtualMachineComputerName", vmName1);
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


        Deployment deployment = azure.deployments().define(dp1)
                .withNewResourceGroup(rgName, region)
                .withTemplate(deploymentCreateTemplates)
                .withParameters(parameters)
                .withMode(DeploymentMode.COMPLETE)
                .create();
        assert azure.publicIPAddresses().listByResourceGroup(rgName).size() == 1;

        Network network = azure.networks().getByResourceGroup(rgName, vnetName);
        Subnet subnet = network.subnets().get("default");

        Map<String, Object> updateParameters = new HashMap<>();
        setParameter(updateParameters, "location", region.toString());
        setParameter(updateParameters, "networkInterfaceName", nicName);
        setParameter(updateParameters, "subnetId", subnet.inner().id());
        setParameter(updateParameters, "publicIpAddressName", pipName1);
        setParameter(updateParameters, "pipDeleteOption", "Detach");

        String dp2 = generateRandomResourceName("dp", 15);
        azure.deployments().define(dp2)
                .withNewResourceGroup(rgName, region)
                .withTemplate(updateTemplate)
                .withParameters(updateParameters)
                .withMode(DeploymentMode.INCREMENTAL)
                .create();

        azure.virtualMachines().deleteByResourceGroup(rgName, vmName1);

        assert azure.publicIPAddresses().listByResourceGroup(rgName).size() == 1;

        // Create with deployment templates, update with SDK, verify the deleteOptions is not updated.

        // Create with deployment templates, update with deployment templates, verify the deleteOptions is updated.
    }

    private static void setParameter(Map<String, Object> parameters, final String name, final Object value) {
        parameters.put(name, new HashMap<String, Object>(){{this.put("value", value);}});
    }
}
