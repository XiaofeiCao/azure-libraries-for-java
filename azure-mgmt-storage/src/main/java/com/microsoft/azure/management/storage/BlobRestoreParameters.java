/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for
 * license information.
 *
 * Code generated by Microsoft (R) AutoRest Code Generator.
 */

package com.microsoft.azure.management.storage;

import org.joda.time.DateTime;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Blob restore parameters.
 */
public class BlobRestoreParameters {
    /**
     * Restore blob to the specified time.
     */
    @JsonProperty(value = "timeToRestore", required = true)
    private DateTime timeToRestore;

    /**
     * Blob ranges to restore.
     */
    @JsonProperty(value = "blobRanges", required = true)
    private List<BlobRestoreRange> blobRanges;

    /**
     * Get restore blob to the specified time.
     *
     * @return the timeToRestore value
     */
    public DateTime timeToRestore() {
        return this.timeToRestore;
    }

    /**
     * Set restore blob to the specified time.
     *
     * @param timeToRestore the timeToRestore value to set
     * @return the BlobRestoreParameters object itself.
     */
    public BlobRestoreParameters withTimeToRestore(DateTime timeToRestore) {
        this.timeToRestore = timeToRestore;
        return this;
    }

    /**
     * Get blob ranges to restore.
     *
     * @return the blobRanges value
     */
    public List<BlobRestoreRange> blobRanges() {
        return this.blobRanges;
    }

    /**
     * Set blob ranges to restore.
     *
     * @param blobRanges the blobRanges value to set
     * @return the BlobRestoreParameters object itself.
     */
    public BlobRestoreParameters withBlobRanges(List<BlobRestoreRange> blobRanges) {
        this.blobRanges = blobRanges;
        return this;
    }

}
