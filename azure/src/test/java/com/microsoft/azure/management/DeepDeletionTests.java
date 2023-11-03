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
        String vnetName = "test2023110301-vnet";
        String resourceGroupName = "rg-xiaofei";
        String subnetName = "default";
        Network network = azure.networks().getByResourceGroup(resourceGroupName, vnetName);
        Subnet subnet = network.subnets().get(subnetName);
        String nicName = "test2023110301668";
        String publicIpAddressName = "test2023110301-ip";
        String pipDeleteOption = "Detach";


        Map<String, Object> updateParameters = new HashMap<>();
        setParameter(updateParameters, "location", region.toString());
        setParameter(updateParameters, "networkInterfaceName", nicName);
        setParameter(updateParameters, "subnetId", subnet.inner().id());
        setParameter(updateParameters, "publicIpAddressName", publicIpAddressName);
        setParameter(updateParameters, "pipDeleteOption", pipDeleteOption);

        String dp2 = generateRandomResourceName("dp", 15);
        azure.deployments().define(dp2)
                .withNewResourceGroup(resourceGroupName, region)
                .withTemplate(updateTemplate)
                .withParameters(updateParameters)
                .withMode(DeploymentMode.INCREMENTAL)
                .create();
    }

    private static void setParameter(Map<String, Object> parameters, final String name, final Object value) {
        parameters.put(name, new HashMap<String, Object>(){{this.put("value", value);}});
    }
}
