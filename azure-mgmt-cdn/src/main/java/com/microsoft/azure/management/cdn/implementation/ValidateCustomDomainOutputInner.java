/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for
 * license information.
 *
 * Code generated by Microsoft (R) AutoRest Code Generator.
 */

package com.microsoft.azure.management.cdn.implementation;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Output of custom domain validation.
 */
public class ValidateCustomDomainOutputInner {
    /**
     * Indicates whether the custom domain is valid or not.
     */
    @JsonProperty(value = "customDomainValidated", access = JsonProperty.Access.WRITE_ONLY)
    private Boolean customDomainValidated;

    /**
     * The reason why the custom domain is not valid.
     */
    @JsonProperty(value = "reason", access = JsonProperty.Access.WRITE_ONLY)
    private String reason;

    /**
     * Error message describing why the custom domain is not valid.
     */
    @JsonProperty(value = "message", access = JsonProperty.Access.WRITE_ONLY)
    private String message;

    /**
     * Get indicates whether the custom domain is valid or not.
     *
     * @return the customDomainValidated value
     */
    public Boolean customDomainValidated() {
        return this.customDomainValidated;
    }

    /**
     * Get the reason why the custom domain is not valid.
     *
     * @return the reason value
     */
    public String reason() {
        return this.reason;
    }

    /**
     * Get error message describing why the custom domain is not valid.
     *
     * @return the message value
     */
    public String message() {
        return this.message;
    }

}
