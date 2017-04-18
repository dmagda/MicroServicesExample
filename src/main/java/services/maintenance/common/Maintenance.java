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

import java.io.Serializable;
import java.util.Date;
import org.apache.ignite.cache.query.annotations.QuerySqlField;

/**
 * POJO for maintenance records.
 */
public class Maintenance implements Serializable {
    private int vehicleId;

    private Date date;

    public Maintenance(int vehicleId, Date date) {
        this.vehicleId = vehicleId;
        this.date = date;
    }

    public int getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(int vehicleId) {
        this.vehicleId = vehicleId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override public String toString() {
        return "Maintenance{" +
            "vehicleId=" + vehicleId +
            ", date=" + date +
            '}';
    }
}
