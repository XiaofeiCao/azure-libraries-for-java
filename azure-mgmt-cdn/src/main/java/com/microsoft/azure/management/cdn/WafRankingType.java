/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for
 * license information.
 *
 * Code generated by Microsoft (R) AutoRest Code Generator.
 */

package com.microsoft.azure.management.cdn;

import java.util.Collection;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.microsoft.rest.ExpandableStringEnum;

/**
 * Defines values for WafRankingType.
 */
public final class WafRankingType extends ExpandableStringEnum<WafRankingType> {
    /** Static value action for WafRankingType. */
    public static final WafRankingType ACTION = fromString("action");

    /** Static value ruleGroup for WafRankingType. */
    public static final WafRankingType RULE_GROUP = fromString("ruleGroup");

    /** Static value ruleId for WafRankingType. */
    public static final WafRankingType RULE_ID = fromString("ruleId");

    /** Static value userAgent for WafRankingType. */
    public static final WafRankingType USER_AGENT = fromString("userAgent");

    /** Static value clientIp for WafRankingType. */
    public static final WafRankingType CLIENT_IP = fromString("clientIp");

    /** Static value url for WafRankingType. */
    public static final WafRankingType URL = fromString("url");

    /** Static value country for WafRankingType. */
    public static final WafRankingType COUNTRY = fromString("country");

    /** Static value ruleType for WafRankingType. */
    public static final WafRankingType RULE_TYPE = fromString("ruleType");

    /**
     * Creates or finds a WafRankingType from its string representation.
     * @param name a name to look for
     * @return the corresponding WafRankingType
     */
    @JsonCreator
    public static WafRankingType fromString(String name) {
        return fromString(name, WafRankingType.class);
    }

    /**
     * @return known WafRankingType values
     */
    public static Collection<WafRankingType> values() {
        return values(WafRankingType.class);
    }
}
