/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for
 * license information.
 *
 * Code generated by Microsoft (R) AutoRest Code Generator.
 */

package com.microsoft.azure.management.containerinstance;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The container Http Get settings, for liveness or readiness probe.
 */
public class ContainerHttpGet {
    /**
     * The path to probe.
     */
    @JsonProperty(value = "path")
    private String path;

    /**
     * The port number to probe.
     */
    @JsonProperty(value = "port", required = true)
    private int port;

    /**
     * The scheme. Possible values include: 'http', 'https'.
     */
    @JsonProperty(value = "scheme")
    private Scheme scheme;

    /**
     * The HTTP headers.
     */
    @JsonProperty(value = "httpHeaders")
    private HttpHeaders httpHeaders;

    /**
     * Get the path to probe.
     *
     * @return the path value
     */
    public String path() {
        return this.path;
    }

    /**
     * Set the path to probe.
     *
     * @param path the path value to set
     * @return the ContainerHttpGet object itself.
     */
    public ContainerHttpGet withPath(String path) {
        this.path = path;
        return this;
    }

    /**
     * Get the port number to probe.
     *
     * @return the port value
     */
    public int port() {
        return this.port;
    }

    /**
     * Set the port number to probe.
     *
     * @param port the port value to set
     * @return the ContainerHttpGet object itself.
     */
    public ContainerHttpGet withPort(int port) {
        this.port = port;
        return this;
    }

    /**
     * Get the scheme. Possible values include: 'http', 'https'.
     *
     * @return the scheme value
     */
    public Scheme scheme() {
        return this.scheme;
    }

    /**
     * Set the scheme. Possible values include: 'http', 'https'.
     *
     * @param scheme the scheme value to set
     * @return the ContainerHttpGet object itself.
     */
    public ContainerHttpGet withScheme(Scheme scheme) {
        this.scheme = scheme;
        return this;
    }

    /**
     * Get the HTTP headers.
     *
     * @return the httpHeaders value
     */
    public HttpHeaders httpHeaders() {
        return this.httpHeaders;
    }

    /**
     * Set the HTTP headers.
     *
     * @param httpHeaders the httpHeaders value to set
     * @return the ContainerHttpGet object itself.
     */
    public ContainerHttpGet withHttpHeaders(HttpHeaders httpHeaders) {
        this.httpHeaders = httpHeaders;
        return this;
    }

}
