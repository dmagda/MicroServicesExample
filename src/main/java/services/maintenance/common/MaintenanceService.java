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
package services.maintenance.common;

import java.util.Date;
import java.util.List;
import org.apache.ignite.IgniteServices;
import org.apache.ignite.services.Service;

/**
 * Maintenance Service interface which defines service specific methods that are visible to every cluster node that is
 * going to interact with the service using {@link IgniteServices} API. In general, the interface is not only used
 * by service implementations but also needed for the nodes that will talk to the service by means of service proxy -
 * {@link IgniteServices#serviceProxy(String, Class, boolean)}.
 */
public interface MaintenanceService extends Service
{
    /**
     * Schedules vehicle maintenance to the nearest available date.
     *
     * @param vehicleId Vehicle unique ID.
     *
     * @return The date of the appointment.
     */
    public Date scheduleVehicleMaintenance(int vehicleId);

    /**
     * Get all maintenance records for a vehicle.
     *
     * @param vehicleId Vehicle unique ID.
     *
     * @return Maintenance records.
     */
    public List<Maintenance> getMaintenanceRecords(int vehicleId);
}
