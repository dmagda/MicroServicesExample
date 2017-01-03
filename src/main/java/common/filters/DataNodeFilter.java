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

/**
 * The filter that has to be set in every cache configuration.
 * </p>
 * It will be called upon a cache startup to define a subset of the cluster nodes where cache data
 * will be stored - Data Nodes.
 * </p>
 * The same filter will be called every time the cluster topology changes which happens when a new cluster node joins
 * the cluster or an old one leaves it. When it's executed for the new joined node then depending on the
 * filter's execution result the node will be considered as a Data Node and a part of cached data will be rebalanced
 * on it or the node will be rejected as a Data Node candidate.
 */
public class DataNodeFilter implements IgnitePredicate<ClusterNode>{
    /**
     * Checks if {@code node} needs to be considered as a Data Node.
     *
     * @param node Cluster node instance.
     *
     * @return {@code true} if the node has to be considered as Data Node, {@code false} otherwise.
     */
    public boolean apply(ClusterNode node) {
        Boolean dataNode = node.attribute("data.node");

        return dataNode != null && dataNode;
    }
}
