/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for
 * license information.
 *
 * Code generated by Microsoft (R) AutoRest Code Generator.
 */

package com.microsoft.azure.management.cosmosdb;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Cosmos DB SQL userDefinedFunction resource object.
 */
public class SqlUserDefinedFunctionResource {
    /**
     * Name of the Cosmos DB SQL userDefinedFunction.
     */
    @JsonProperty(value = "id", required = true)
    private String id;

    /**
     * Body of the User Defined Function.
     */
    @JsonProperty(value = "body")
    private String body;

    /**
     * Get name of the Cosmos DB SQL userDefinedFunction.
     *
     * @return the id value
     */
    public String id() {
        return this.id;
    }

    /**
     * Set name of the Cosmos DB SQL userDefinedFunction.
     *
     * @param id the id value to set
     * @return the SqlUserDefinedFunctionResource object itself.
     */
    public SqlUserDefinedFunctionResource withId(String id) {
        this.id = id;
        return this;
    }

    /**
     * Get body of the User Defined Function.
     *
     * @return the body value
     */
    public String body() {
        return this.body;
    }

    /**
     * Set body of the User Defined Function.
     *
     * @param body the body value to set
     * @return the SqlUserDefinedFunctionResource object itself.
     */
    public SqlUserDefinedFunctionResource withBody(String body) {
        this.body = body;
        return this;
    }

}
