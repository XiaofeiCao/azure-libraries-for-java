/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for
 * license information.
 */
package com.azure.management.storage.implementation;

import com.azure.core.http.rest.PagedFlux;
import com.azure.core.http.rest.PagedIterable;
import com.azure.management.storage.Usage;
import com.azure.management.storage.Usages;
import com.azure.management.storage.models.UsagesInner;

/**
 * The implementation of {@link Usages}.
 */
class UsagesImpl
        implements
        Usages {

    private final StorageManager manager;

    UsagesImpl(StorageManager storageManager) {
        this.manager = storageManager;
    }

    @Override
    public StorageManager getManager() {
        return this.manager;
    }

    @Override
    public PagedIterable<Usage> list() {
        return null;
    }

    @Override
    public PagedFlux<Usage> listAsync() {
        return null;
    }

    @Override
    public UsagesInner getInner() {
        return this.getManager().getInner().usages();
    }
}