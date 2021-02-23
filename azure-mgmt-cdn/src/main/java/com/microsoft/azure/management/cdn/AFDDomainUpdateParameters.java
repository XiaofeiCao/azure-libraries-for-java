/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for
 * license information.
 *
 * Code generated by Microsoft (R) AutoRest Code Generator.
 */

package com.microsoft.azure.management.cdn;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.microsoft.rest.serializer.JsonFlatten;

/**
 * The domain JSON object required for domain creation or update.
 */
@JsonFlatten
public class AFDDomainUpdateParameters {
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
     * @return the AFDDomainUpdateParameters object itself.
     */
    public AFDDomainUpdateParameters withTlsSettings(AFDDomainHttpsParameters tlsSettings) {
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
     * @return the AFDDomainUpdateParameters object itself.
     */
    public AFDDomainUpdateParameters withAzureDnsZone(ResourceReference azureDnsZone) {
        this.azureDnsZone = azureDnsZone;
        return this;
    }

}
