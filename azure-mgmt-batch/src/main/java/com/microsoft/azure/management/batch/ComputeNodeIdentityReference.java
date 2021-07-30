/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for
 * license information.
 *
 * Code generated by Microsoft (R) AutoRest Code Generator.
 */

package com.microsoft.azure.management.batch;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The reference to a user assigned identity associated with the Batch pool
 * which a compute node will use.
 */
public class ComputeNodeIdentityReference {
    /**
     * The ARM resource id of the user assigned identity.
     */
    @JsonProperty(value = "resourceId")
    private String resourceId;

    /**
     * Get the ARM resource id of the user assigned identity.
     *
     * @return the resourceId value
     */
    public String resourceId() {
        return this.resourceId;
    }

    /**
     * Set the ARM resource id of the user assigned identity.
     *
     * @param resourceId the resourceId value to set
     * @return the ComputeNodeIdentityReference object itself.
     */
    public ComputeNodeIdentityReference withResourceId(String resourceId) {
        this.resourceId = resourceId;
        return this;
    }

}
