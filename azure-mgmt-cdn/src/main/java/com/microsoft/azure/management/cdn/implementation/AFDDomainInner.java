/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for
 * license information.
 *
 * Code generated by Microsoft (R) AutoRest Code Generator.
 */

package com.microsoft.azure.management.cdn.implementation;

import com.microsoft.azure.management.cdn.AFDDomainHttpsParameters;
import com.microsoft.azure.management.cdn.ResourceReference;
import com.microsoft.azure.management.cdn.AfdProvisioningState;
import com.microsoft.azure.management.cdn.DeploymentStatus;
import com.microsoft.azure.management.cdn.DomainValidationState;
import com.microsoft.azure.management.cdn.DomainValidationProperties;
import com.microsoft.azure.management.cdn.SystemData;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.microsoft.rest.serializer.JsonFlatten;
import com.microsoft.azure.ProxyResource;

/**
 * Friendly domain name mapping to the endpoint hostname that the customer
 * provides for branding purposes, e.g. www.contoso.com.
 */
@JsonFlatten
public class AFDDomainInner extends ProxyResource {
    /**
     * The configuration specifying how to enable HTTPS for the domain - using
     * AzureFrontDoor managed certificate or user's own certificate. If not
     * specified, enabling ssl uses AzureFrontDoor managed certificate by
     * default.
     */
    @JsonProperty(value = "properties.tlsSettings")
    private AFDDomainHttpsParameters tlsSettings;

    /**
     * Resource reference to the Azure DNS zone.
     */
    @JsonProperty(value = "properties.azureDnsZone")
    private ResourceReference azureDnsZone;

    /**
     * Provisioning status. Possible values include: 'Succeeded', 'Failed',
     * 'Updating', 'Deleting', 'Creating'.
     */
    @JsonProperty(value = "properties.provisioningState", access = JsonProperty.Access.WRITE_ONLY)
    private AfdProvisioningState provisioningState;

    /**
     * Possible values include: 'NotStarted', 'InProgress', 'Succeeded',
     * 'Failed'.
     */
    @JsonProperty(value = "properties.deploymentStatus", access = JsonProperty.Access.WRITE_ONLY)
    private DeploymentStatus deploymentStatus;

    /**
     * Provisioning substate shows the progress of custom HTTPS
     * enabling/disabling process step by step. DCV stands for
     * DomainControlValidation. Possible values include: 'Unknown',
     * 'Submitting', 'Pending', 'TimedOut', 'PendingRevalidation', 'Approved'.
     */
    @JsonProperty(value = "properties.domainValidationState", access = JsonProperty.Access.WRITE_ONLY)
    private DomainValidationState domainValidationState;

    /**
     * The host name of the domain. Must be a domain name.
     */
    @JsonProperty(value = "properties.hostName", required = true)
    private String hostName;

    /**
     * Values the customer needs to validate domain ownership.
     */
    @JsonProperty(value = "properties.validationProperties", access = JsonProperty.Access.WRITE_ONLY)
    private DomainValidationProperties validationProperties;

    /**
     * The systemData property.
     */
    @JsonProperty(value = "systemData", access = JsonProperty.Access.WRITE_ONLY)
    private SystemData systemData;

    /**
     * Get the configuration specifying how to enable HTTPS for the domain - using AzureFrontDoor managed certificate or user's own certificate. If not specified, enabling ssl uses AzureFrontDoor managed certificate by default.
     *
     * @return the tlsSettings value
     */
    public AFDDomainHttpsParameters tlsSettings() {
        return this.tlsSettings;
    }

    /**
     * Set the configuration specifying how to enable HTTPS for the domain - using AzureFrontDoor managed certificate or user's own certificate. If not specified, enabling ssl uses AzureFrontDoor managed certificate by default.
     *
     * @param tlsSettings the tlsSettings value to set
     * @return the AFDDomainInner object itself.
     */
    public AFDDomainInner withTlsSettings(AFDDomainHttpsParameters tlsSettings) {
        this.tlsSettings = tlsSettings;
        return this;
    }

    /**
     * Get resource reference to the Azure DNS zone.
     *
     * @return the azureDnsZone value
     */
    public ResourceReference azureDnsZone() {
        return this.azureDnsZone;
    }

    /**
     * Set resource reference to the Azure DNS zone.
     *
     * @param azureDnsZone the azureDnsZone value to set
     * @return the AFDDomainInner object itself.
     */
    public AFDDomainInner withAzureDnsZone(ResourceReference azureDnsZone) {
        this.azureDnsZone = azureDnsZone;
        return this;
    }

    /**
     * Get provisioning status. Possible values include: 'Succeeded', 'Failed', 'Updating', 'Deleting', 'Creating'.
     *
     * @return the provisioningState value
     */
    public AfdProvisioningState provisioningState() {
        return this.provisioningState;
    }

    /**
     * Get possible values include: 'NotStarted', 'InProgress', 'Succeeded', 'Failed'.
     *
     * @return the deploymentStatus value
     */
    public DeploymentStatus deploymentStatus() {
        return this.deploymentStatus;
    }

    /**
     * Get provisioning substate shows the progress of custom HTTPS enabling/disabling process step by step. DCV stands for DomainControlValidation. Possible values include: 'Unknown', 'Submitting', 'Pending', 'TimedOut', 'PendingRevalidation', 'Approved'.
     *
     * @return the domainValidationState value
     */
    public DomainValidationState domainValidationState() {
        return this.domainValidationState;
    }

    /**
     * Get the host name of the domain. Must be a domain name.
     *
     * @return the hostName value
     */
    public String hostName() {
        return this.hostName;
    }

    /**
     * Set the host name of the domain. Must be a domain name.
     *
     * @param hostName the hostName value to set
     * @return the AFDDomainInner object itself.
     */
    public AFDDomainInner withHostName(String hostName) {
        this.hostName = hostName;
        return this;
    }

    /**
     * Get values the customer needs to validate domain ownership.
     *
     * @return the validationProperties value
     */
    public DomainValidationProperties validationProperties() {
        return this.validationProperties;
    }

    /**
     * Get the systemData value.
     *
     * @return the systemData value
     */
    public SystemData systemData() {
        return this.systemData;
    }

}
