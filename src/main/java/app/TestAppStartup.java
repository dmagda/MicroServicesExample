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
package app;

import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.apache.ignite.IgniteException;
import org.apache.ignite.Ignition;
import services.maintenance.common.MaintenanceService;
import services.vehicles.common.Vehicle;
import services.vehicles.common.VehicleService;

/**
 * Created by dmagda on 1/6/17.
 */
public class TestAppStartup {
    /** Dummy vehicles' names */
    private static final String[] VEHICLES_NAMES = new String [] {
        "TOYOTA", "BMW", "MERCEDES", "HYUNDAI", "FORD"};

    /** Total number of vehicles. */
    public static int TOTAL_VEHICLES_NUMBER = 10;

    /**
     * Start up a testing application that connects to the cluster using an Ignite client node.
     *
     * The app will fill in the caches with sample data and call the services.
     *
     * @param args Command line arguments, none required.
     * @throws IgniteException If failed.
     */
    public static void main(String[] args) throws IgniteException {
        Ignite ignite = Ignition.start("config/client-node-config.xml");

        System.out.println("Client node has connected to the cluster");

        // Getting access to the vehicles cache. At least one Data Node has to be started before so that the cache
        // deployed there. To start a Data Node use DataNodeStartup class file or ignite.sh/bat script passing
        // data-node-config.xml to it.
        IgniteCache<Integer, Vehicle> vehiclesCache = ignite.cache("vehicles");

        Random rand = new Random();

        Calendar calendar = Calendar.getInstance();

        // Filling it vehicles cache with dummy data.
        for (int i = 0; i < TOTAL_VEHICLES_NUMBER; i++) {

            calendar.set(Calendar.MONTH, rand.nextInt(12));
            calendar.set(Calendar.YEAR, 2000 + rand.nextInt(17));

            vehiclesCache.put(i, new Vehicle(
                VEHICLES_NAMES[rand.nextInt(VEHICLES_NAMES.length)],
                calendar.getTime(),
                (double)(11000 + rand.nextInt(10000))
            ));
        }

        System.out.println("Filled in Vehicles cache. Entries number: " + vehiclesCache.size());

        // Getting access to a remotely deployed Vehicles Service using a service proxy. Proxy has to be used on the
        // nodes that don't have the service deployed locally.
        VehicleService vehicleService = ignite.services().serviceProxy(VehicleService.SERVICE_NAME,
            VehicleService.class, false);

        System.out.println("Getting info for a random vehicle using VehiclesService: " + vehicleService.getVehicle(
            rand.nextInt(TOTAL_VEHICLES_NUMBER)));

        MaintenanceService maintenanceService = ignite.services().serviceProxy(MaintenanceService.SERVICE_NAME,
            MaintenanceService.class, false);

        int vehicleId = rand.nextInt(TOTAL_VEHICLES_NUMBER);

        Date date = maintenanceService.scheduleVehicleMaintenance(vehicleId);

        System.out.println("Scheduled maintenance service [vehicleID=" + vehicleId + ", " + "date=" + date + ']');
    }
}
