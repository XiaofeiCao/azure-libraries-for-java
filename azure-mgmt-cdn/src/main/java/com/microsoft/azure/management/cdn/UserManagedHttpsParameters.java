/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for
 * license information.
 *
 * Code generated by Microsoft (R) AutoRest Code Generator.
 */

package com.microsoft.azure.management.cdn;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;

/**
 * Defines the certificate source parameters using user's keyvault certificate
 * for enabling SSL.
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "certificateSource", defaultImpl = UserManagedHttpsParameters.class)
@JsonTypeName("AzureKeyVault")
public class UserManagedHttpsParameters extends CustomDomainHttpsParameters {
    /**
     * Defines the certificate source parameters using user's keyvault
     * certificate for enabling SSL.
     */
    @JsonProperty(value = "certificateSourceParameters", required = true)
    private KeyVaultCertificateSourceParameters certificateSourceParameters;

    /**
     * Get defines the certificate source parameters using user's keyvault certificate for enabling SSL.
     *
     * @return the certificateSourceParameters value
     */
    public KeyVaultCertificateSourceParameters certificateSourceParameters() {
        return this.certificateSourceParameters;
    }

    /**
     * Set defines the certificate source parameters using user's keyvault certificate for enabling SSL.
     *
     * @param certificateSourceParameters the certificateSourceParameters value to set
     * @return the UserManagedHttpsParameters object itself.
     */
    public UserManagedHttpsParameters withCertificateSourceParameters(KeyVaultCertificateSourceParameters certificateSourceParameters) {
        this.certificateSourceParameters = certificateSourceParameters;
        return this;
    }

}