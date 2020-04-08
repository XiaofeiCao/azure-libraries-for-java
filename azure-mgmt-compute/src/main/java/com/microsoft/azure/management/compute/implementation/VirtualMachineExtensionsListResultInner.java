/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for
 * license information.
 *
 * Code generated by Microsoft (R) AutoRest Code Generator.
 */

package com.microsoft.azure.management.compute.implementation;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The List Extension operation response.
 */
public class VirtualMachineExtensionsListResultInner {
    /**
     * The list of extensions.
     */
    @JsonProperty(value = "value")
    private List<VirtualMachineExtensionInner> value;

    /**
     * Get the list of extensions.
     *
     * @return the value value
     */
    public List<VirtualMachineExtensionInner> value() {
        return this.value;
    }

    /**
     * Set the list of extensions.
     *
     * @param value the value value to set
     * @return the VirtualMachineExtensionsListResultInner object itself.
     */
    public VirtualMachineExtensionsListResultInner withValue(List<VirtualMachineExtensionInner> value) {
        this.value = value;
        return this;
    }

}