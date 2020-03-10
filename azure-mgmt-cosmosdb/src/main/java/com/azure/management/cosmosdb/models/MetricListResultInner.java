// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for
// license information.
// 
// Code generated by Microsoft (R) AutoRest Code Generator.

package com.azure.management.cosmosdb.models;

import com.azure.core.annotation.Immutable;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/**
 * The MetricListResult model.
 */
@Immutable
public final class MetricListResultInner {
    /*
     * The list of metrics for the account.
     */
    @JsonProperty(value = "value", access = JsonProperty.Access.WRITE_ONLY)
    private List<MetricInner> value;

    /**
     * Get the value property: The list of metrics for the account.
     * 
     * @return the value value.
     */
    public List<MetricInner> value() {
        return this.value;
    }
}
