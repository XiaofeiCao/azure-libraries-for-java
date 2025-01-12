/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for
 * license information.
 *
 * Code generated by Microsoft (R) AutoRest Code Generator.
 */

package com.microsoft.azure.management.compute;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Input for InstallPatches on a Linux VM, as directly received by the API.
 */
public class LinuxParameters {
    /**
     * The update classifications to select when installing patches for Linux.
     */
    @JsonProperty(value = "classificationsToInclude")
    private List<VMGuestPatchClassificationLinux> classificationsToInclude;

    /**
     * packages to include in the patch operation. Format:
     * packageName_packageVersion.
     */
    @JsonProperty(value = "packageNameMasksToInclude")
    private List<String> packageNameMasksToInclude;

    /**
     * packages to exclude in the patch operation. Format:
     * packageName_packageVersion.
     */
    @JsonProperty(value = "packageNameMasksToExclude")
    private List<String> packageNameMasksToExclude;

    /**
     * This is used as a maintenance run identifier for Auto VM Guest Patching
     * in Linux.
     */
    @JsonProperty(value = "maintenanceRunId")
    private String maintenanceRunId;

    /**
     * Get the update classifications to select when installing patches for Linux.
     *
     * @return the classificationsToInclude value
     */
    public List<VMGuestPatchClassificationLinux> classificationsToInclude() {
        return this.classificationsToInclude;
    }

    /**
     * Set the update classifications to select when installing patches for Linux.
     *
     * @param classificationsToInclude the classificationsToInclude value to set
     * @return the LinuxParameters object itself.
     */
    public LinuxParameters withClassificationsToInclude(List<VMGuestPatchClassificationLinux> classificationsToInclude) {
        this.classificationsToInclude = classificationsToInclude;
        return this;
    }

    /**
     * Get packages to include in the patch operation. Format: packageName_packageVersion.
     *
     * @return the packageNameMasksToInclude value
     */
    public List<String> packageNameMasksToInclude() {
        return this.packageNameMasksToInclude;
    }

    /**
     * Set packages to include in the patch operation. Format: packageName_packageVersion.
     *
     * @param packageNameMasksToInclude the packageNameMasksToInclude value to set
     * @return the LinuxParameters object itself.
     */
    public LinuxParameters withPackageNameMasksToInclude(List<String> packageNameMasksToInclude) {
        this.packageNameMasksToInclude = packageNameMasksToInclude;
        return this;
    }

    /**
     * Get packages to exclude in the patch operation. Format: packageName_packageVersion.
     *
     * @return the packageNameMasksToExclude value
     */
    public List<String> packageNameMasksToExclude() {
        return this.packageNameMasksToExclude;
    }

    /**
     * Set packages to exclude in the patch operation. Format: packageName_packageVersion.
     *
     * @param packageNameMasksToExclude the packageNameMasksToExclude value to set
     * @return the LinuxParameters object itself.
     */
    public LinuxParameters withPackageNameMasksToExclude(List<String> packageNameMasksToExclude) {
        this.packageNameMasksToExclude = packageNameMasksToExclude;
        return this;
    }

    /**
     * Get this is used as a maintenance run identifier for Auto VM Guest Patching in Linux.
     *
     * @return the maintenanceRunId value
     */
    public String maintenanceRunId() {
        return this.maintenanceRunId;
    }

    /**
     * Set this is used as a maintenance run identifier for Auto VM Guest Patching in Linux.
     *
     * @param maintenanceRunId the maintenanceRunId value to set
     * @return the LinuxParameters object itself.
     */
    public LinuxParameters withMaintenanceRunId(String maintenanceRunId) {
        this.maintenanceRunId = maintenanceRunId;
        return this;
    }

}
