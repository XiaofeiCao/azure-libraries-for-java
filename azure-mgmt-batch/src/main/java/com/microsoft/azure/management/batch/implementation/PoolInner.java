/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for
 * license information.
 *
 * Code generated by Microsoft (R) AutoRest Code Generator.
 */

package com.microsoft.azure.management.batch.implementation;

import org.joda.time.DateTime;
import com.microsoft.azure.management.batch.PoolProvisioningState;
import com.microsoft.azure.management.batch.AllocationState;
import com.microsoft.azure.management.batch.DeploymentConfiguration;
import com.microsoft.azure.management.batch.ScaleSettings;
import com.microsoft.azure.management.batch.AutoScaleRun;
import com.microsoft.azure.management.batch.InterNodeCommunicationState;
import com.microsoft.azure.management.batch.NetworkConfiguration;
import com.microsoft.azure.management.batch.TaskSchedulingPolicy;
import java.util.List;
import com.microsoft.azure.management.batch.UserAccount;
import com.microsoft.azure.management.batch.MetadataItem;
import com.microsoft.azure.management.batch.StartTask;
import com.microsoft.azure.management.batch.CertificateReference;
import com.microsoft.azure.management.batch.ApplicationPackageReference;
import com.microsoft.azure.management.batch.ResizeOperationStatus;
import com.microsoft.azure.management.batch.MountConfiguration;
import com.microsoft.azure.management.batch.BatchPoolIdentity;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.microsoft.rest.serializer.JsonFlatten;
import com.microsoft.azure.ProxyResource;

/**
 * Contains information about a pool.
 */
@JsonFlatten
public class PoolInner extends ProxyResource {
    /**
     * The display name for the pool.
     * The display name need not be unique and can contain any Unicode
     * characters up to a maximum length of 1024.
     */
    @JsonProperty(value = "properties.displayName")
    private String displayName;

    /**
     * The last modified time of the pool.
     * This is the last time at which the pool level data, such as the
     * targetDedicatedNodes or autoScaleSettings, changed. It does not factor
     * in node-level changes such as a compute node changing state.
     */
    @JsonProperty(value = "properties.lastModified", access = JsonProperty.Access.WRITE_ONLY)
    private DateTime lastModified;

    /**
     * The creation time of the pool.
     */
    @JsonProperty(value = "properties.creationTime", access = JsonProperty.Access.WRITE_ONLY)
    private DateTime creationTime;

    /**
     * The current state of the pool.
     * Possible values include: 'Succeeded', 'Deleting'.
     */
    @JsonProperty(value = "properties.provisioningState", access = JsonProperty.Access.WRITE_ONLY)
    private PoolProvisioningState provisioningState;

    /**
     * The time at which the pool entered its current state.
     */
    @JsonProperty(value = "properties.provisioningStateTransitionTime", access = JsonProperty.Access.WRITE_ONLY)
    private DateTime provisioningStateTransitionTime;

    /**
     * Whether the pool is resizing.
     * Possible values include: 'Steady', 'Resizing', 'Stopping'.
     */
    @JsonProperty(value = "properties.allocationState", access = JsonProperty.Access.WRITE_ONLY)
    private AllocationState allocationState;

    /**
     * The time at which the pool entered its current allocation state.
     */
    @JsonProperty(value = "properties.allocationStateTransitionTime", access = JsonProperty.Access.WRITE_ONLY)
    private DateTime allocationStateTransitionTime;

    /**
     * The size of virtual machines in the pool. All VMs in a pool are the same
     * size.
     * For information about available sizes of virtual machines for Cloud
     * Services pools (pools created with cloudServiceConfiguration), see Sizes
     * for Cloud Services
     * (https://azure.microsoft.com/documentation/articles/cloud-services-sizes-specs/).
     * Batch supports all Cloud Services VM sizes except ExtraSmall. For
     * information about available VM sizes for pools using images from the
     * Virtual Machines Marketplace (pools created with
     * virtualMachineConfiguration) see Sizes for Virtual Machines (Linux)
     * (https://azure.microsoft.com/documentation/articles/virtual-machines-linux-sizes/)
     * or Sizes for Virtual Machines (Windows)
     * (https://azure.microsoft.com/documentation/articles/virtual-machines-windows-sizes/).
     * Batch supports all Azure VM sizes except STANDARD_A0 and those with
     * premium storage (STANDARD_GS, STANDARD_DS, and STANDARD_DSV2 series).
     */
    @JsonProperty(value = "properties.vmSize")
    private String vmSize;

    /**
     * This property describes how the pool nodes will be deployed - using
     * Cloud Services or Virtual Machines.
     * Using CloudServiceConfiguration specifies that the nodes should be
     * creating using Azure Cloud Services (PaaS), while
     * VirtualMachineConfiguration uses Azure Virtual Machines (IaaS).
     */
    @JsonProperty(value = "properties.deploymentConfiguration")
    private DeploymentConfiguration deploymentConfiguration;

    /**
     * The number of compute nodes currently in the pool.
     */
    @JsonProperty(value = "properties.currentDedicatedNodes", access = JsonProperty.Access.WRITE_ONLY)
    private Integer currentDedicatedNodes;

    /**
     * The number of low-priority compute nodes currently in the pool.
     */
    @JsonProperty(value = "properties.currentLowPriorityNodes", access = JsonProperty.Access.WRITE_ONLY)
    private Integer currentLowPriorityNodes;

    /**
     * Settings which configure the number of nodes in the pool.
     */
    @JsonProperty(value = "properties.scaleSettings")
    private ScaleSettings scaleSettings;

    /**
     * The results and errors from the last execution of the autoscale formula.
     * This property is set only if the pool automatically scales, i.e.
     * autoScaleSettings are used.
     */
    @JsonProperty(value = "properties.autoScaleRun", access = JsonProperty.Access.WRITE_ONLY)
    private AutoScaleRun autoScaleRun;

    /**
     * Whether the pool permits direct communication between nodes.
     * This imposes restrictions on which nodes can be assigned to the pool.
     * Enabling this value can reduce the chance of the requested number of
     * nodes to be allocated in the pool. If not specified, this value defaults
     * to 'Disabled'. Possible values include: 'Enabled', 'Disabled'.
     */
    @JsonProperty(value = "properties.interNodeCommunication")
    private InterNodeCommunicationState interNodeCommunication;

    /**
     * The network configuration for the pool.
     */
    @JsonProperty(value = "properties.networkConfiguration")
    private NetworkConfiguration networkConfiguration;

    /**
     * The number of task slots that can be used to run concurrent tasks on a
     * single compute node in the pool.
     * The default value is 1. The maximum value is the smaller of 4 times the
     * number of cores of the vmSize of the pool or 256.
     */
    @JsonProperty(value = "properties.taskSlotsPerNode")
    private Integer taskSlotsPerNode;

    /**
     * How tasks are distributed across compute nodes in a pool.
     * If not specified, the default is spread.
     */
    @JsonProperty(value = "properties.taskSchedulingPolicy")
    private TaskSchedulingPolicy taskSchedulingPolicy;

    /**
     * The list of user accounts to be created on each node in the pool.
     */
    @JsonProperty(value = "properties.userAccounts")
    private List<UserAccount> userAccounts;

    /**
     * A list of name-value pairs associated with the pool as metadata.
     * The Batch service does not assign any meaning to metadata; it is solely
     * for the use of user code.
     */
    @JsonProperty(value = "properties.metadata")
    private List<MetadataItem> metadata;

    /**
     * A task specified to run on each compute node as it joins the pool.
     * In an PATCH (update) operation, this property can be set to an empty
     * object to remove the start task from the pool.
     */
    @JsonProperty(value = "properties.startTask")
    private StartTask startTask;

    /**
     * The list of certificates to be installed on each compute node in the
     * pool.
     * For Windows compute nodes, the Batch service installs the certificates
     * to the specified certificate store and location. For Linux compute
     * nodes, the certificates are stored in a directory inside the task
     * working directory and an environment variable AZ_BATCH_CERTIFICATES_DIR
     * is supplied to the task to query for this location. For certificates
     * with visibility of 'remoteUser', a 'certs' directory is created in the
     * user's home directory (e.g., /home/{user-name}/certs) and certificates
     * are placed in that directory.
     */
    @JsonProperty(value = "properties.certificates")
    private List<CertificateReference> certificates;

    /**
     * The list of application packages to be installed on each compute node in
     * the pool.
     * Changes to application package references affect all new compute nodes
     * joining the pool, but do not affect compute nodes that are already in
     * the pool until they are rebooted or reimaged. There is a maximum of 10
     * application package references on any given pool.
     */
    @JsonProperty(value = "properties.applicationPackages")
    private List<ApplicationPackageReference> applicationPackages;

    /**
     * The list of application licenses the Batch service will make available
     * on each compute node in the pool.
     * The list of application licenses must be a subset of available Batch
     * service application licenses. If a license is requested which is not
     * supported, pool creation will fail.
     */
    @JsonProperty(value = "properties.applicationLicenses")
    private List<String> applicationLicenses;

    /**
     * Contains details about the current or last completed resize operation.
     */
    @JsonProperty(value = "properties.resizeOperationStatus", access = JsonProperty.Access.WRITE_ONLY)
    private ResizeOperationStatus resizeOperationStatus;

    /**
     * A list of file systems to mount on each node in the pool.
     * This supports Azure Files, NFS, CIFS/SMB, and Blobfuse.
     */
    @JsonProperty(value = "properties.mountConfiguration")
    private List<MountConfiguration> mountConfiguration;

    /**
     * The type of identity used for the Batch Pool.
     * The type of identity used for the Batch Pool.
     */
    @JsonProperty(value = "identity")
    private BatchPoolIdentity identity;

    /**
     * The ETag of the resource, used for concurrency statements.
     */
    @JsonProperty(value = "etag", access = JsonProperty.Access.WRITE_ONLY)
    private String etag;

    /**
     * Get the display name need not be unique and can contain any Unicode characters up to a maximum length of 1024.
     *
     * @return the displayName value
     */
    public String displayName() {
        return this.displayName;
    }

    /**
     * Set the display name need not be unique and can contain any Unicode characters up to a maximum length of 1024.
     *
     * @param displayName the displayName value to set
     * @return the PoolInner object itself.
     */
    public PoolInner withDisplayName(String displayName) {
        this.displayName = displayName;
        return this;
    }

    /**
     * Get this is the last time at which the pool level data, such as the targetDedicatedNodes or autoScaleSettings, changed. It does not factor in node-level changes such as a compute node changing state.
     *
     * @return the lastModified value
     */
    public DateTime lastModified() {
        return this.lastModified;
    }

    /**
     * Get the creationTime value.
     *
     * @return the creationTime value
     */
    public DateTime creationTime() {
        return this.creationTime;
    }

    /**
     * Get possible values include: 'Succeeded', 'Deleting'.
     *
     * @return the provisioningState value
     */
    public PoolProvisioningState provisioningState() {
        return this.provisioningState;
    }

    /**
     * Get the provisioningStateTransitionTime value.
     *
     * @return the provisioningStateTransitionTime value
     */
    public DateTime provisioningStateTransitionTime() {
        return this.provisioningStateTransitionTime;
    }

    /**
     * Get possible values include: 'Steady', 'Resizing', 'Stopping'.
     *
     * @return the allocationState value
     */
    public AllocationState allocationState() {
        return this.allocationState;
    }

    /**
     * Get the allocationStateTransitionTime value.
     *
     * @return the allocationStateTransitionTime value
     */
    public DateTime allocationStateTransitionTime() {
        return this.allocationStateTransitionTime;
    }

    /**
     * Get for information about available sizes of virtual machines for Cloud Services pools (pools created with cloudServiceConfiguration), see Sizes for Cloud Services (https://azure.microsoft.com/documentation/articles/cloud-services-sizes-specs/). Batch supports all Cloud Services VM sizes except ExtraSmall. For information about available VM sizes for pools using images from the Virtual Machines Marketplace (pools created with virtualMachineConfiguration) see Sizes for Virtual Machines (Linux) (https://azure.microsoft.com/documentation/articles/virtual-machines-linux-sizes/) or Sizes for Virtual Machines (Windows) (https://azure.microsoft.com/documentation/articles/virtual-machines-windows-sizes/). Batch supports all Azure VM sizes except STANDARD_A0 and those with premium storage (STANDARD_GS, STANDARD_DS, and STANDARD_DSV2 series).
     *
     * @return the vmSize value
     */
    public String vmSize() {
        return this.vmSize;
    }

    /**
     * Set for information about available sizes of virtual machines for Cloud Services pools (pools created with cloudServiceConfiguration), see Sizes for Cloud Services (https://azure.microsoft.com/documentation/articles/cloud-services-sizes-specs/). Batch supports all Cloud Services VM sizes except ExtraSmall. For information about available VM sizes for pools using images from the Virtual Machines Marketplace (pools created with virtualMachineConfiguration) see Sizes for Virtual Machines (Linux) (https://azure.microsoft.com/documentation/articles/virtual-machines-linux-sizes/) or Sizes for Virtual Machines (Windows) (https://azure.microsoft.com/documentation/articles/virtual-machines-windows-sizes/). Batch supports all Azure VM sizes except STANDARD_A0 and those with premium storage (STANDARD_GS, STANDARD_DS, and STANDARD_DSV2 series).
     *
     * @param vmSize the vmSize value to set
     * @return the PoolInner object itself.
     */
    public PoolInner withVmSize(String vmSize) {
        this.vmSize = vmSize;
        return this;
    }

    /**
     * Get using CloudServiceConfiguration specifies that the nodes should be creating using Azure Cloud Services (PaaS), while VirtualMachineConfiguration uses Azure Virtual Machines (IaaS).
     *
     * @return the deploymentConfiguration value
     */
    public DeploymentConfiguration deploymentConfiguration() {
        return this.deploymentConfiguration;
    }

    /**
     * Set using CloudServiceConfiguration specifies that the nodes should be creating using Azure Cloud Services (PaaS), while VirtualMachineConfiguration uses Azure Virtual Machines (IaaS).
     *
     * @param deploymentConfiguration the deploymentConfiguration value to set
     * @return the PoolInner object itself.
     */
    public PoolInner withDeploymentConfiguration(DeploymentConfiguration deploymentConfiguration) {
        this.deploymentConfiguration = deploymentConfiguration;
        return this;
    }

    /**
     * Get the currentDedicatedNodes value.
     *
     * @return the currentDedicatedNodes value
     */
    public Integer currentDedicatedNodes() {
        return this.currentDedicatedNodes;
    }

    /**
     * Get the currentLowPriorityNodes value.
     *
     * @return the currentLowPriorityNodes value
     */
    public Integer currentLowPriorityNodes() {
        return this.currentLowPriorityNodes;
    }

    /**
     * Get the scaleSettings value.
     *
     * @return the scaleSettings value
     */
    public ScaleSettings scaleSettings() {
        return this.scaleSettings;
    }

    /**
     * Set the scaleSettings value.
     *
     * @param scaleSettings the scaleSettings value to set
     * @return the PoolInner object itself.
     */
    public PoolInner withScaleSettings(ScaleSettings scaleSettings) {
        this.scaleSettings = scaleSettings;
        return this;
    }

    /**
     * Get this property is set only if the pool automatically scales, i.e. autoScaleSettings are used.
     *
     * @return the autoScaleRun value
     */
    public AutoScaleRun autoScaleRun() {
        return this.autoScaleRun;
    }

    /**
     * Get this imposes restrictions on which nodes can be assigned to the pool. Enabling this value can reduce the chance of the requested number of nodes to be allocated in the pool. If not specified, this value defaults to 'Disabled'. Possible values include: 'Enabled', 'Disabled'.
     *
     * @return the interNodeCommunication value
     */
    public InterNodeCommunicationState interNodeCommunication() {
        return this.interNodeCommunication;
    }

    /**
     * Set this imposes restrictions on which nodes can be assigned to the pool. Enabling this value can reduce the chance of the requested number of nodes to be allocated in the pool. If not specified, this value defaults to 'Disabled'. Possible values include: 'Enabled', 'Disabled'.
     *
     * @param interNodeCommunication the interNodeCommunication value to set
     * @return the PoolInner object itself.
     */
    public PoolInner withInterNodeCommunication(InterNodeCommunicationState interNodeCommunication) {
        this.interNodeCommunication = interNodeCommunication;
        return this;
    }

    /**
     * Get the networkConfiguration value.
     *
     * @return the networkConfiguration value
     */
    public NetworkConfiguration networkConfiguration() {
        return this.networkConfiguration;
    }

    /**
     * Set the networkConfiguration value.
     *
     * @param networkConfiguration the networkConfiguration value to set
     * @return the PoolInner object itself.
     */
    public PoolInner withNetworkConfiguration(NetworkConfiguration networkConfiguration) {
        this.networkConfiguration = networkConfiguration;
        return this;
    }

    /**
     * Get the default value is 1. The maximum value is the smaller of 4 times the number of cores of the vmSize of the pool or 256.
     *
     * @return the taskSlotsPerNode value
     */
    public Integer taskSlotsPerNode() {
        return this.taskSlotsPerNode;
    }

    /**
     * Set the default value is 1. The maximum value is the smaller of 4 times the number of cores of the vmSize of the pool or 256.
     *
     * @param taskSlotsPerNode the taskSlotsPerNode value to set
     * @return the PoolInner object itself.
     */
    public PoolInner withTaskSlotsPerNode(Integer taskSlotsPerNode) {
        this.taskSlotsPerNode = taskSlotsPerNode;
        return this;
    }

    /**
     * Get if not specified, the default is spread.
     *
     * @return the taskSchedulingPolicy value
     */
    public TaskSchedulingPolicy taskSchedulingPolicy() {
        return this.taskSchedulingPolicy;
    }

    /**
     * Set if not specified, the default is spread.
     *
     * @param taskSchedulingPolicy the taskSchedulingPolicy value to set
     * @return the PoolInner object itself.
     */
    public PoolInner withTaskSchedulingPolicy(TaskSchedulingPolicy taskSchedulingPolicy) {
        this.taskSchedulingPolicy = taskSchedulingPolicy;
        return this;
    }

    /**
     * Get the userAccounts value.
     *
     * @return the userAccounts value
     */
    public List<UserAccount> userAccounts() {
        return this.userAccounts;
    }

    /**
     * Set the userAccounts value.
     *
     * @param userAccounts the userAccounts value to set
     * @return the PoolInner object itself.
     */
    public PoolInner withUserAccounts(List<UserAccount> userAccounts) {
        this.userAccounts = userAccounts;
        return this;
    }

    /**
     * Get the Batch service does not assign any meaning to metadata; it is solely for the use of user code.
     *
     * @return the metadata value
     */
    public List<MetadataItem> metadata() {
        return this.metadata;
    }

    /**
     * Set the Batch service does not assign any meaning to metadata; it is solely for the use of user code.
     *
     * @param metadata the metadata value to set
     * @return the PoolInner object itself.
     */
    public PoolInner withMetadata(List<MetadataItem> metadata) {
        this.metadata = metadata;
        return this;
    }

    /**
     * Get in an PATCH (update) operation, this property can be set to an empty object to remove the start task from the pool.
     *
     * @return the startTask value
     */
    public StartTask startTask() {
        return this.startTask;
    }

    /**
     * Set in an PATCH (update) operation, this property can be set to an empty object to remove the start task from the pool.
     *
     * @param startTask the startTask value to set
     * @return the PoolInner object itself.
     */
    public PoolInner withStartTask(StartTask startTask) {
        this.startTask = startTask;
        return this;
    }

    /**
     * Get for Windows compute nodes, the Batch service installs the certificates to the specified certificate store and location. For Linux compute nodes, the certificates are stored in a directory inside the task working directory and an environment variable AZ_BATCH_CERTIFICATES_DIR is supplied to the task to query for this location. For certificates with visibility of 'remoteUser', a 'certs' directory is created in the user's home directory (e.g., /home/{user-name}/certs) and certificates are placed in that directory.
     *
     * @return the certificates value
     */
    public List<CertificateReference> certificates() {
        return this.certificates;
    }

    /**
     * Set for Windows compute nodes, the Batch service installs the certificates to the specified certificate store and location. For Linux compute nodes, the certificates are stored in a directory inside the task working directory and an environment variable AZ_BATCH_CERTIFICATES_DIR is supplied to the task to query for this location. For certificates with visibility of 'remoteUser', a 'certs' directory is created in the user's home directory (e.g., /home/{user-name}/certs) and certificates are placed in that directory.
     *
     * @param certificates the certificates value to set
     * @return the PoolInner object itself.
     */
    public PoolInner withCertificates(List<CertificateReference> certificates) {
        this.certificates = certificates;
        return this;
    }

    /**
     * Get changes to application package references affect all new compute nodes joining the pool, but do not affect compute nodes that are already in the pool until they are rebooted or reimaged. There is a maximum of 10 application package references on any given pool.
     *
     * @return the applicationPackages value
     */
    public List<ApplicationPackageReference> applicationPackages() {
        return this.applicationPackages;
    }

    /**
     * Set changes to application package references affect all new compute nodes joining the pool, but do not affect compute nodes that are already in the pool until they are rebooted or reimaged. There is a maximum of 10 application package references on any given pool.
     *
     * @param applicationPackages the applicationPackages value to set
     * @return the PoolInner object itself.
     */
    public PoolInner withApplicationPackages(List<ApplicationPackageReference> applicationPackages) {
        this.applicationPackages = applicationPackages;
        return this;
    }

    /**
     * Get the list of application licenses must be a subset of available Batch service application licenses. If a license is requested which is not supported, pool creation will fail.
     *
     * @return the applicationLicenses value
     */
    public List<String> applicationLicenses() {
        return this.applicationLicenses;
    }

    /**
     * Set the list of application licenses must be a subset of available Batch service application licenses. If a license is requested which is not supported, pool creation will fail.
     *
     * @param applicationLicenses the applicationLicenses value to set
     * @return the PoolInner object itself.
     */
    public PoolInner withApplicationLicenses(List<String> applicationLicenses) {
        this.applicationLicenses = applicationLicenses;
        return this;
    }

    /**
     * Get the resizeOperationStatus value.
     *
     * @return the resizeOperationStatus value
     */
    public ResizeOperationStatus resizeOperationStatus() {
        return this.resizeOperationStatus;
    }

    /**
     * Get this supports Azure Files, NFS, CIFS/SMB, and Blobfuse.
     *
     * @return the mountConfiguration value
     */
    public List<MountConfiguration> mountConfiguration() {
        return this.mountConfiguration;
    }

    /**
     * Set this supports Azure Files, NFS, CIFS/SMB, and Blobfuse.
     *
     * @param mountConfiguration the mountConfiguration value to set
     * @return the PoolInner object itself.
     */
    public PoolInner withMountConfiguration(List<MountConfiguration> mountConfiguration) {
        this.mountConfiguration = mountConfiguration;
        return this;
    }

    /**
     * Get the type of identity used for the Batch Pool.
     *
     * @return the identity value
     */
    public BatchPoolIdentity identity() {
        return this.identity;
    }

    /**
     * Set the type of identity used for the Batch Pool.
     *
     * @param identity the identity value to set
     * @return the PoolInner object itself.
     */
    public PoolInner withIdentity(BatchPoolIdentity identity) {
        this.identity = identity;
        return this;
    }

    /**
     * Get the ETag of the resource, used for concurrency statements.
     *
     * @return the etag value
     */
    public String etag() {
        return this.etag;
    }

}
