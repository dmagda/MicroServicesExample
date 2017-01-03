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
package services.maintenance;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.cache.Cache;
import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteAtomicSequence;
import org.apache.ignite.IgniteCache;
import org.apache.ignite.cache.query.SqlQuery;
import org.apache.ignite.resources.IgniteInstanceResource;
import org.apache.ignite.services.ServiceContext;
import services.maintenance.common.Maintenance;
import services.maintenance.common.MaintenanceService;


/**
 * An implementation of {@link MaintenanceService} that will be deployed in the cluster.
 * </p>
 * The implementation stores maintenance records in a dedicated distributed cache deployed on Data Nodes.
 */
public class MaintenanceServiceImpl implements MaintenanceService {
    @IgniteInstanceResource
    private Ignite ignite;

    /** Reference to the cache. */
    private IgniteCache<Long, Maintenance> maintCache;

    /** Maintenance IDs generator */
    private IgniteAtomicSequence sequence;

    /** {@inheritDoc} */
    public void init(ServiceContext ctx) throws Exception {
        System.out.println("Initializing Maintenance Service on node:" + ignite.cluster().localNode());

        /**
         * It's assumed that the cache has already been deployed. To do that, make sure to start Data Nodes with
         * a respective cache configuration.
         */
        maintCache = ignite.cache("maintenance");

        /**
         * Instantiating the sequence that will be used for IDs generation.
         */
        sequence = ignite.atomicSequence("MaintenanceIds", 1, true);
    }

    /** {@inheritDoc} */
    public void execute(ServiceContext ctx) throws Exception {
        System.out.println("Executing Maintenance Service on node:" + ignite.cluster().localNode());

        // Some custom logic.
    }

    /** {@inheritDoc} */
    public void cancel(ServiceContext ctx) {
        System.out.println("Stopping Maintenance Service on node:" + ignite.cluster().localNode());

        // Some custom logic.
    }

    /** {@inheritDoc} */
    public Date scheduleVehicleMaintenance(int vehicleId) {
        Date date = new Date();

        maintCache.put(sequence.getAndIncrement(), new Maintenance(vehicleId, date));

        return date;
    }

    /** {@inheritDoc} */
    public List<Maintenance> getMaintenanceRecords(int vehicleId) {
        SqlQuery<Long, Maintenance> query = new SqlQuery<Long, Maintenance>(Maintenance.class,
            "WHERE vehicleId = ?").setArgs(vehicleId);

        List<Cache.Entry<Long, Maintenance>> res = maintCache.query(query).getAll();

        List<Maintenance> res2 = new ArrayList<Maintenance>(res.size());

        for (Cache.Entry<Long, Maintenance> entry : res)
            res2.add(entry.getValue());

        return res2;
    }
}
