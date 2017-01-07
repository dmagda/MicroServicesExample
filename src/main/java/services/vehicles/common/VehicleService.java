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
package services.vehicles.common;

import org.apache.ignite.IgniteServices;
import org.apache.ignite.services.Service;

/**
 * Vehicle Service interface which defines service specific methods that are visible to every cluster node that is
 * going to interact with the service using {@link IgniteServices} API. In general, the interface is not only used
 * by service implementations but also needed for the nodes that will talk to the service by means of service proxy -
 * {@link IgniteServices#serviceProxy(String, Class, boolean)}.
 */
public interface VehicleService extends Service {
    /** Service name */
    public static final String SERVICE_NAME = "VehicleService";

    /**
     * Calls the service to add a new vehicle.
     *
     * @param vehicleId Vehicle unique ID.
     * @param vehicle Vehicle instance to add.
     */
    public void addVehicle(int vehicleId, Vehicle vehicle);

    /**
     * Calls the service to get details for a specific vehicle.
     *
     * @param vehicleId Vehicle unique ID.
     */
    public Vehicle getVehicle(int vehicleId);

    /**
     * Calls the service to remove a specific vehicle.
     *
     * @param vehicleId Vehicle unique ID.
     */
    public void removeVehicle(int vehicleId);
}
