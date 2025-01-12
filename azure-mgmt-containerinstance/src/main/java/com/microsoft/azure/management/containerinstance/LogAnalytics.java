/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for
 * license information.
 *
 * Code generated by Microsoft (R) AutoRest Code Generator.
 */

package com.microsoft.azure.management.containerinstance;

import java.util.Map;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Container group log analytics information.
 */
public class LogAnalytics {
    /**
     * The workspace id for log analytics.
     */
    @JsonProperty(value = "workspaceId", required = true)
    private String workspaceId;

    /**
     * The workspace key for log analytics.
     */
    @JsonProperty(value = "workspaceKey", required = true)
    private String workspaceKey;

    /**
     * The log type to be used. Possible values include: 'ContainerInsights',
     * 'ContainerInstanceLogs'.
     */
    @JsonProperty(value = "logType")
    private LogAnalyticsLogType logType;

    /**
     * Metadata for log analytics.
     */
    @JsonProperty(value = "metadata")
    private Map<String, String> metadata;

    /**
     * The workspace resource id for log analytics.
     */
    @JsonProperty(value = "workspaceResourceId")
    private Map<String, String> workspaceResourceId;

    /**
     * Get the workspace id for log analytics.
     *
     * @return the workspaceId value
     */
    public String workspaceId() {
        return this.workspaceId;
    }

    /**
     * Set the workspace id for log analytics.
     *
     * @param workspaceId the workspaceId value to set
     * @return the LogAnalytics object itself.
     */
    public LogAnalytics withWorkspaceId(String workspaceId) {
        this.workspaceId = workspaceId;
        return this;
    }

    /**
     * Get the workspace key for log analytics.
     *
     * @return the workspaceKey value
     */
    public String workspaceKey() {
        return this.workspaceKey;
    }

    /**
     * Set the workspace key for log analytics.
     *
     * @param workspaceKey the workspaceKey value to set
     * @return the LogAnalytics object itself.
     */
    public LogAnalytics withWorkspaceKey(String workspaceKey) {
        this.workspaceKey = workspaceKey;
        return this;
    }

    /**
     * Get the log type to be used. Possible values include: 'ContainerInsights', 'ContainerInstanceLogs'.
     *
     * @return the logType value
     */
    public LogAnalyticsLogType logType() {
        return this.logType;
    }

    /**
     * Set the log type to be used. Possible values include: 'ContainerInsights', 'ContainerInstanceLogs'.
     *
     * @param logType the logType value to set
     * @return the LogAnalytics object itself.
     */
    public LogAnalytics withLogType(LogAnalyticsLogType logType) {
        this.logType = logType;
        return this;
    }

    /**
     * Get metadata for log analytics.
     *
     * @return the metadata value
     */
    public Map<String, String> metadata() {
        return this.metadata;
    }

    /**
     * Set metadata for log analytics.
     *
     * @param metadata the metadata value to set
     * @return the LogAnalytics object itself.
     */
    public LogAnalytics withMetadata(Map<String, String> metadata) {
        this.metadata = metadata;
        return this;
    }

    /**
     * Get the workspace resource id for log analytics.
     *
     * @return the workspaceResourceId value
     */
    public Map<String, String> workspaceResourceId() {
        return this.workspaceResourceId;
    }

    /**
     * Set the workspace resource id for log analytics.
     *
     * @param workspaceResourceId the workspaceResourceId value to set
     * @return the LogAnalytics object itself.
     */
    public LogAnalytics withWorkspaceResourceId(Map<String, String> workspaceResourceId) {
        this.workspaceResourceId = workspaceResourceId;
        return this;
    }

}
