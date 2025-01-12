/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for
 * license information.
 */

package com.microsoft.azure.management.samples;

import com.microsoft.azure.management.network.samples.*;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

public class NetworkSampleTests extends SamplesTestBase {

    @Test
    public void testManageNetworkPeeringInSameSubscription() {
        Assert.assertTrue(ManageNetworkPeeringInSameSubscription.runSample(azure));
    }

    @Test
    @Ignore("Get error `Cannot create more than 1 network watchers for this subscription in this region.` with test subscription")
    public void testVerifyNetworkPeeringWithNetworkWatcher() {
        Assert.assertTrue(VerifyNetworkPeeringWithNetworkWatcher.runSample(azure));
    }

    @Test
    public void testManageApplicationGateway() {
        if (isPlaybackMode()) {
            return;
        }
        Assert.assertTrue(ManageApplicationGateway.runSample(azure));
    }

    @Test
    public void testManageInternalLoadBalancer() {
        Assert.assertTrue(ManageInternalLoadBalancer.runSample(azure));
    }

    @Test
    public void testCreateSimpleInternetFacingLoadBalancer() {
        Assert.assertTrue(CreateSimpleInternetFacingLoadBalancer.runSample(azure));
    }

    @Test
    public void testManageInternetFacingLoadBalancer() {
        Assert.assertTrue(ManageInternetFacingLoadBalancer.runSample(azure));
    }

    @Test
    public void testManageIPAddress() {
        Assert.assertTrue(ManageIPAddress.runSample(azure));
    }

    @Test
    public void testManageNetworkInterface() {
        Assert.assertTrue(ManageNetworkInterface.runSample(azure));
    }

    @Test
    public void testManageNetworkSecurityGroup() {
        Assert.assertTrue(ManageNetworkSecurityGroup.runSample(azure));
    }

    @Test
    public void testManageSimpleApplicationGateway() {
        if (isPlaybackMode()) {
            return;
        }
        Assert.assertTrue(ManageSimpleApplicationGateway.runSample(azure));
    }

    @Test
    public void testManageVirtualMachinesInParallelWithNetwork() {
        Assert.assertTrue(ManageVirtualMachinesInParallelWithNetwork.runSample(azure));
    }

    @Test
    public void testManageVirtualNetwork() {
        Assert.assertTrue(ManageVirtualNetwork.runSample(azure));
    }

    @Test
    public void testManageVirtualNetworkAsync() {
        Assert.assertTrue(ManageVirtualNetworkAsync.runSample(azure));
    }

    @Test
    public void testManageVpnGatewaySite2SiteConnection() {
        Assert.assertTrue(ManageVpnGatewaySite2SiteConnection.runSample(azure));
    }

    @Test
    @Ignore("Need root certificate file and client certificate thumbprint to run the sample")
    public void testManageVpnGatewayPoint2SiteConnection() {
        Assert.assertTrue(ManageVpnGatewayPoint2SiteConnection.runSample(azure));
    }
}
