/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for
 * license information.
 *
 * Code generated by Microsoft (R) AutoRest Code Generator.
 */

package com.microsoft.azure.management.trafficmanager.implementation;

import com.microsoft.azure.management.trafficmanager.EndpointStatus;
import com.microsoft.azure.management.trafficmanager.EndpointMonitorStatus;
import java.util.List;
import com.microsoft.azure.management.trafficmanager.EndpointPropertiesSubnetsItem;
import com.microsoft.azure.management.trafficmanager.EndpointPropertiesCustomHeadersItem;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.microsoft.rest.serializer.JsonFlatten;

/**
 * Class representing a Traffic Manager endpoint.
 */
@JsonFlatten
public class EndpointInner extends ProxyResourceInner {
    /**
     * The Azure Resource URI of the of the endpoint. Not applicable to
     * endpoints of type 'ExternalEndpoints'.
     */
    @JsonProperty(value = "properties.targetResourceId")
    private String targetResourceId;

    /**
     * The fully-qualified DNS name or IP address of the endpoint. Traffic
     * Manager returns this value in DNS responses to direct traffic to this
     * endpoint.
     */
    @JsonProperty(value = "properties.target")
    private String target;

    /**
     * The status of the endpoint. If the endpoint is Enabled, it is probed for
     * endpoint health and is included in the traffic routing method. Possible
     * values include: 'Enabled', 'Disabled'.
     */
    @JsonProperty(value = "properties.endpointStatus")
    private EndpointStatus endpointStatus;

    /**
     * The weight of this endpoint when using the 'Weighted' traffic routing
     * method. Possible values are from 1 to 1000.
     */
    @JsonProperty(value = "properties.weight")
    private Long weight;

    /**
     * The priority of this endpoint when using the 'Priority' traffic routing
     * method. Possible values are from 1 to 1000, lower values represent
     * higher priority. This is an optional parameter.  If specified, it must
     * be specified on all endpoints, and no two endpoints can share the same
     * priority value.
     */
    @JsonProperty(value = "properties.priority")
    private Long priority;

    /**
     * Specifies the location of the external or nested endpoints when using
     * the 'Performance' traffic routing method.
     */
    @JsonProperty(value = "properties.endpointLocation")
    private String endpointLocation;

    /**
     * The monitoring status of the endpoint. Possible values include:
     * 'CheckingEndpoint', 'Online', 'Degraded', 'Disabled', 'Inactive',
     * 'Stopped'.
     */
    @JsonProperty(value = "properties.endpointMonitorStatus")
    private EndpointMonitorStatus endpointMonitorStatus;

    /**
     * The minimum number of endpoints that must be available in the child
     * profile in order for the parent profile to be considered available. Only
     * applicable to endpoint of type 'NestedEndpoints'.
     */
    @JsonProperty(value = "properties.minChildEndpoints")
    private Long minChildEndpoints;

    /**
     * The list of countries/regions mapped to this endpoint when using the
     * 'Geographic' traffic routing method. Please consult Traffic Manager
     * Geographic documentation for a full list of accepted values.
     */
    @JsonProperty(value = "properties.geoMapping")
    private List<String> geoMapping;

    /**
     * The list of subnets, IP addresses, and/or address ranges mapped to this
     * endpoint when using the 'Subnet' traffic routing method. An empty list
     * will match all ranges not covered by other endpoints.
     */
    @JsonProperty(value = "properties.subnets")
    private List<EndpointPropertiesSubnetsItem> subnets;

    /**
     * List of custom headers.
     */
    @JsonProperty(value = "properties.customHeaders")
    private List<EndpointPropertiesCustomHeadersItem> customHeaders;

    /**
     * Get the Azure Resource URI of the of the endpoint. Not applicable to endpoints of type 'ExternalEndpoints'.
     *
     * @return the targetResourceId value
     */
    public String targetResourceId() {
        return this.targetResourceId;
    }

    /**
     * Set the Azure Resource URI of the of the endpoint. Not applicable to endpoints of type 'ExternalEndpoints'.
     *
     * @param targetResourceId the targetResourceId value to set
     * @return the EndpointInner object itself.
     */
    public EndpointInner withTargetResourceId(String targetResourceId) {
        this.targetResourceId = targetResourceId;
        return this;
    }

    /**
     * Get the fully-qualified DNS name or IP address of the endpoint. Traffic Manager returns this value in DNS responses to direct traffic to this endpoint.
     *
     * @return the target value
     */
    public String target() {
        return this.target;
    }

    /**
     * Set the fully-qualified DNS name or IP address of the endpoint. Traffic Manager returns this value in DNS responses to direct traffic to this endpoint.
     *
     * @param target the target value to set
     * @return the EndpointInner object itself.
     */
    public EndpointInner withTarget(String target) {
        this.target = target;
        return this;
    }

    /**
     * Get the status of the endpoint. If the endpoint is Enabled, it is probed for endpoint health and is included in the traffic routing method. Possible values include: 'Enabled', 'Disabled'.
     *
     * @return the endpointStatus value
     */
    public EndpointStatus endpointStatus() {
        return this.endpointStatus;
    }

    /**
     * Set the status of the endpoint. If the endpoint is Enabled, it is probed for endpoint health and is included in the traffic routing method. Possible values include: 'Enabled', 'Disabled'.
     *
     * @param endpointStatus the endpointStatus value to set
     * @return the EndpointInner object itself.
     */
    public EndpointInner withEndpointStatus(EndpointStatus endpointStatus) {
        this.endpointStatus = endpointStatus;
        return this;
    }

    /**
     * Get the weight of this endpoint when using the 'Weighted' traffic routing method. Possible values are from 1 to 1000.
     *
     * @return the weight value
     */
    public Long weight() {
        return this.weight;
    }

    /**
     * Set the weight of this endpoint when using the 'Weighted' traffic routing method. Possible values are from 1 to 1000.
     *
     * @param weight the weight value to set
     * @return the EndpointInner object itself.
     */
    public EndpointInner withWeight(Long weight) {
        this.weight = weight;
        return this;
    }

    /**
     * Get the priority of this endpoint when using the 'Priority' traffic routing method. Possible values are from 1 to 1000, lower values represent higher priority. This is an optional parameter.  If specified, it must be specified on all endpoints, and no two endpoints can share the same priority value.
     *
     * @return the priority value
     */
    public Long priority() {
        return this.priority;
    }

    /**
     * Set the priority of this endpoint when using the 'Priority' traffic routing method. Possible values are from 1 to 1000, lower values represent higher priority. This is an optional parameter.  If specified, it must be specified on all endpoints, and no two endpoints can share the same priority value.
     *
     * @param priority the priority value to set
     * @return the EndpointInner object itself.
     */
    public EndpointInner withPriority(Long priority) {
        this.priority = priority;
        return this;
    }

    /**
     * Get specifies the location of the external or nested endpoints when using the 'Performance' traffic routing method.
     *
     * @return the endpointLocation value
     */
    public String endpointLocation() {
        return this.endpointLocation;
    }

    /**
     * Set specifies the location of the external or nested endpoints when using the 'Performance' traffic routing method.
     *
     * @param endpointLocation the endpointLocation value to set
     * @return the EndpointInner object itself.
     */
    public EndpointInner withEndpointLocation(String endpointLocation) {
        this.endpointLocation = endpointLocation;
        return this;
    }

    /**
     * Get the monitoring status of the endpoint. Possible values include: 'CheckingEndpoint', 'Online', 'Degraded', 'Disabled', 'Inactive', 'Stopped'.
     *
     * @return the endpointMonitorStatus value
     */
    public EndpointMonitorStatus endpointMonitorStatus() {
        return this.endpointMonitorStatus;
    }

    /**
     * Set the monitoring status of the endpoint. Possible values include: 'CheckingEndpoint', 'Online', 'Degraded', 'Disabled', 'Inactive', 'Stopped'.
     *
     * @param endpointMonitorStatus the endpointMonitorStatus value to set
     * @return the EndpointInner object itself.
     */
    public EndpointInner withEndpointMonitorStatus(EndpointMonitorStatus endpointMonitorStatus) {
        this.endpointMonitorStatus = endpointMonitorStatus;
        return this;
    }

    /**
     * Get the minimum number of endpoints that must be available in the child profile in order for the parent profile to be considered available. Only applicable to endpoint of type 'NestedEndpoints'.
     *
     * @return the minChildEndpoints value
     */
    public Long minChildEndpoints() {
        return this.minChildEndpoints;
    }

    /**
     * Set the minimum number of endpoints that must be available in the child profile in order for the parent profile to be considered available. Only applicable to endpoint of type 'NestedEndpoints'.
     *
     * @param minChildEndpoints the minChildEndpoints value to set
     * @return the EndpointInner object itself.
     */
    public EndpointInner withMinChildEndpoints(Long minChildEndpoints) {
        this.minChildEndpoints = minChildEndpoints;
        return this;
    }

    /**
     * Get the list of countries/regions mapped to this endpoint when using the 'Geographic' traffic routing method. Please consult Traffic Manager Geographic documentation for a full list of accepted values.
     *
     * @return the geoMapping value
     */
    public List<String> geoMapping() {
        return this.geoMapping;
    }

    /**
     * Set the list of countries/regions mapped to this endpoint when using the 'Geographic' traffic routing method. Please consult Traffic Manager Geographic documentation for a full list of accepted values.
     *
     * @param geoMapping the geoMapping value to set
     * @return the EndpointInner object itself.
     */
    public EndpointInner withGeoMapping(List<String> geoMapping) {
        this.geoMapping = geoMapping;
        return this;
    }

    /**
     * Get the list of subnets, IP addresses, and/or address ranges mapped to this endpoint when using the 'Subnet' traffic routing method. An empty list will match all ranges not covered by other endpoints.
     *
     * @return the subnets value
     */
    public List<EndpointPropertiesSubnetsItem> subnets() {
        return this.subnets;
    }

    /**
     * Set the list of subnets, IP addresses, and/or address ranges mapped to this endpoint when using the 'Subnet' traffic routing method. An empty list will match all ranges not covered by other endpoints.
     *
     * @param subnets the subnets value to set
     * @return the EndpointInner object itself.
     */
    public EndpointInner withSubnets(List<EndpointPropertiesSubnetsItem> subnets) {
        this.subnets = subnets;
        return this;
    }

    /**
     * Get list of custom headers.
     *
     * @return the customHeaders value
     */
    public List<EndpointPropertiesCustomHeadersItem> customHeaders() {
        return this.customHeaders;
    }

    /**
     * Set list of custom headers.
     *
     * @param customHeaders the customHeaders value to set
     * @return the EndpointInner object itself.
     */
    public EndpointInner withCustomHeaders(List<EndpointPropertiesCustomHeadersItem> customHeaders) {
        this.customHeaders = customHeaders;
        return this;
    }

}