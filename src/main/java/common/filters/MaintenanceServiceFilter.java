/*
 *  Licensed to the Apache Software Foundation (ASF) under one or more
 *  contributor license agreements.  See the NOTICE file distributed with
 *  this work for additional information regarding copyright ownership.
 *  The ASF licenses this file to You under the Apache License, Version 2.0
 *  (the "License"); you may not use this file except in compliance with
 *  the License.  You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package common.filters;

import org.apache.ignite.cluster.ClusterNode;
import org.apache.ignite.lang.IgnitePredicate;
import org.apache.ignite.services.ServiceConfiguration;

/**
 * The filter that has to be set in {@link ServiceConfiguration} of Maintenance Service.
 * </p>
 * It will be called at the deployment time of Maintenance Service and will define a subset of the nodes where the
 * service will be deployed or might be deployed in general - Maintenance Service Nodes.
 * </p>
 * The same filter will be called every time the cluster topology changes which happens when a new cluster node joins
 * the cluster or an old one leaves it. When it's executed for the new joined node then depending on the filter's
 * execution result the node might be considered as a Maintenance Service Node and an instance of the service may be
 * deployed there depending on how many service instances have to be deployed cluster wide.
 */
public class MaintenanceServiceFilter implements IgnitePredicate<ClusterNode>{
    /**
     * Checks if {@code node} needs to be considered as a Maintenance Service Node.
     *
     * @param node Cluster node instance.
     *
     * @return {@code true} if the node has to be considered as Maintenance Service Node, {@code false} otherwise.
     */
    public boolean apply(ClusterNode node) {
        Boolean dataNode = node.attribute("maintenance.service.node");

        return dataNode != null && dataNode;
    }
}
