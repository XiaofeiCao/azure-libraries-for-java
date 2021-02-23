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
 * Defines contents of custom rules.
 */
public class CustomRuleList {
    /**
     * List of rules.
     */
    @JsonProperty(value = "rules")
    private List<CustomRule> rules;

    /**
     * Get list of rules.
     *
     * @return the rules value
     */
    public List<CustomRule> rules() {
        return this.rules;
    }

    /**
     * Set list of rules.
     *
     * @param rules the rules value to set
     * @return the CustomRuleList object itself.
     */
    public CustomRuleList withRules(List<CustomRule> rules) {
        this.rules = rules;
        return this;
    }

}
