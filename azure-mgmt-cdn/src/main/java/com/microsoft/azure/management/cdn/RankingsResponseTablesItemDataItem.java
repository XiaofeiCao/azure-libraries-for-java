/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for
 * license information.
 *
 * Code generated by Microsoft (R) AutoRest Code Generator.
 */

package com.microsoft.azure.management.cdn;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The RankingsResponseTablesItemDataItem model.
 */
public class RankingsResponseTablesItemDataItem {
    /**
     * The name property.
     */
    @JsonProperty(value = "name")
    private String name;

    /**
     * The metrics property.
     */
    @JsonProperty(value = "metrics")
    private List<RankingsResponseTablesItemDataItemMetricsItem> metrics;

    /**
     * Get the name value.
     *
     * @return the name value
     */
    public String name() {
        return this.name;
    }

    /**
     * Set the name value.
     *
     * @param name the name value to set
     * @return the RankingsResponseTablesItemDataItem object itself.
     */
    public RankingsResponseTablesItemDataItem withName(String name) {
        this.name = name;
        return this;
    }

    /**
     * Get the metrics value.
     *
     * @return the metrics value
     */
    public List<RankingsResponseTablesItemDataItemMetricsItem> metrics() {
        return this.metrics;
    }

    /**
     * Set the metrics value.
     *
     * @param metrics the metrics value to set
     * @return the RankingsResponseTablesItemDataItem object itself.
     */
    public RankingsResponseTablesItemDataItem withMetrics(List<RankingsResponseTablesItemDataItemMetricsItem> metrics) {
        this.metrics = metrics;
        return this;
    }

}
