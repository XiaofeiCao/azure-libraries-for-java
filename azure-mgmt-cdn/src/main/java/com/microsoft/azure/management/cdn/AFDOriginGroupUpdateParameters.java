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
 * AFDOrigin group properties needed for origin group creation or update.
 */
@JsonFlatten
public class AFDOriginGroupUpdateParameters {
    /**
     * Load balancing settings for a backend pool.
     */
    @JsonProperty(value = "properties.loadBalancingSettings")
    private LoadBalancingSettingsParameters loadBalancingSettings;

    /**
     * Health probe settings to the origin that is used to determine the health
     * of the origin.
     */
    @JsonProperty(value = "properties.healthProbeSettings")
    private HealthProbeParameters healthProbeSettings;

    /**
     * Time in minutes to shift the traffic to the endpoint gradually when an
     * unhealthy endpoint comes healthy or a new endpoint is added. Default is
     * 10 mins. This property is currently not supported.
     */
    @JsonProperty(value = "properties.trafficRestorationTimeToHealedOrNewEndpointsInMinutes")
    private Integer trafficRestorationTimeToHealedOrNewEndpointsInMinutes;

    /**
     * The JSON object that contains the properties to determine origin health
     * using real requests/responses. This property is currently not supported.
     */
    @JsonProperty(value = "properties.responseBasedAfdOriginErrorDetectionSettings")
    private ResponseBasedOriginErrorDetectionParameters responseBasedAfdOriginErrorDetectionSettings;

    /**
     * Whether to allow session affinity on this host. Valid options are
     * 'Enabled' or 'Disabled'. Possible values include: 'Enabled', 'Disabled'.
     */
    @JsonProperty(value = "properties.sessionAffinityState")
    private EnabledState sessionAffinityState;

    /**
     * Get load balancing settings for a backend pool.
     *
     * @return the loadBalancingSettings value
     */
    public LoadBalancingSettingsParameters loadBalancingSettings() {
        return this.loadBalancingSettings;
    }

    /**
     * Set load balancing settings for a backend pool.
     *
     * @param loadBalancingSettings the loadBalancingSettings value to set
     * @return the AFDOriginGroupUpdateParameters object itself.
     */
    public AFDOriginGroupUpdateParameters withLoadBalancingSettings(LoadBalancingSettingsParameters loadBalancingSettings) {
        this.loadBalancingSettings = loadBalancingSettings;
        return this;
    }

    /**
     * Get health probe settings to the origin that is used to determine the health of the origin.
     *
     * @return the healthProbeSettings value
     */
    public HealthProbeParameters healthProbeSettings() {
        return this.healthProbeSettings;
    }

    /**
     * Set health probe settings to the origin that is used to determine the health of the origin.
     *
     * @param healthProbeSettings the healthProbeSettings value to set
     * @return the AFDOriginGroupUpdateParameters object itself.
     */
    public AFDOriginGroupUpdateParameters withHealthProbeSettings(HealthProbeParameters healthProbeSettings) {
        this.healthProbeSettings = healthProbeSettings;
        return this;
    }

    /**
     * Get time in minutes to shift the traffic to the endpoint gradually when an unhealthy endpoint comes healthy or a new endpoint is added. Default is 10 mins. This property is currently not supported.
     *
     * @return the trafficRestorationTimeToHealedOrNewEndpointsInMinutes value
     */
    public Integer trafficRestorationTimeToHealedOrNewEndpointsInMinutes() {
        return this.trafficRestorationTimeToHealedOrNewEndpointsInMinutes;
    }

    /**
     * Set time in minutes to shift the traffic to the endpoint gradually when an unhealthy endpoint comes healthy or a new endpoint is added. Default is 10 mins. This property is currently not supported.
     *
     * @param trafficRestorationTimeToHealedOrNewEndpointsInMinutes the trafficRestorationTimeToHealedOrNewEndpointsInMinutes value to set
     * @return the AFDOriginGroupUpdateParameters object itself.
     */
    public AFDOriginGroupUpdateParameters withTrafficRestorationTimeToHealedOrNewEndpointsInMinutes(Integer trafficRestorationTimeToHealedOrNewEndpointsInMinutes) {
        this.trafficRestorationTimeToHealedOrNewEndpointsInMinutes = trafficRestorationTimeToHealedOrNewEndpointsInMinutes;
        return this;
    }

    /**
     * Get the JSON object that contains the properties to determine origin health using real requests/responses. This property is currently not supported.
     *
     * @return the responseBasedAfdOriginErrorDetectionSettings value
     */
    public ResponseBasedOriginErrorDetectionParameters responseBasedAfdOriginErrorDetectionSettings() {
        return this.responseBasedAfdOriginErrorDetectionSettings;
    }

    /**
     * Set the JSON object that contains the properties to determine origin health using real requests/responses. This property is currently not supported.
     *
     * @param responseBasedAfdOriginErrorDetectionSettings the responseBasedAfdOriginErrorDetectionSettings value to set
     * @return the AFDOriginGroupUpdateParameters object itself.
     */
    public AFDOriginGroupUpdateParameters withResponseBasedAfdOriginErrorDetectionSettings(ResponseBasedOriginErrorDetectionParameters responseBasedAfdOriginErrorDetectionSettings) {
        this.responseBasedAfdOriginErrorDetectionSettings = responseBasedAfdOriginErrorDetectionSettings;
        return this;
    }

    /**
     * Get whether to allow session affinity on this host. Valid options are 'Enabled' or 'Disabled'. Possible values include: 'Enabled', 'Disabled'.
     *
     * @return the sessionAffinityState value
     */
    public EnabledState sessionAffinityState() {
        return this.sessionAffinityState;
    }

    /**
     * Set whether to allow session affinity on this host. Valid options are 'Enabled' or 'Disabled'. Possible values include: 'Enabled', 'Disabled'.
     *
     * @param sessionAffinityState the sessionAffinityState value to set
     * @return the AFDOriginGroupUpdateParameters object itself.
     */
    public AFDOriginGroupUpdateParameters withSessionAffinityState(EnabledState sessionAffinityState) {
        this.sessionAffinityState = sessionAffinityState;
        return this;
    }

}