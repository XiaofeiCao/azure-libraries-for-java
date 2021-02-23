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
 * Defines values for HeaderAction.
 */
public final class HeaderAction extends ExpandableStringEnum<HeaderAction> {
    /** Static value Append for HeaderAction. */
    public static final HeaderAction APPEND = fromString("Append");

    /** Static value Overwrite for HeaderAction. */
    public static final HeaderAction OVERWRITE = fromString("Overwrite");

    /** Static value Delete for HeaderAction. */
    public static final HeaderAction DELETE = fromString("Delete");

    /**
     * Creates or finds a HeaderAction from its string representation.
     * @param name a name to look for
     * @return the corresponding HeaderAction
     */
    @JsonCreator
    public static HeaderAction fromString(String name) {
        return fromString(name, HeaderAction.class);
    }

    /**
     * @return known HeaderAction values
     */
    public static Collection<HeaderAction> values() {
        return values(HeaderAction.class);
    }
}
