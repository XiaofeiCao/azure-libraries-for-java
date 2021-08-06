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
 * Details about the connection between the Batch service and the endpoint.
 */
public class EndpointDetail {
    /**
     * The port an endpoint is connected to.
     */
    @JsonProperty(value = "port", access = JsonProperty.Access.WRITE_ONLY)
    private Integer port;

    /**
     * Get the port an endpoint is connected to.
     *
     * @return the port value
     */
    public Integer port() {
        return this.port;
    }

}