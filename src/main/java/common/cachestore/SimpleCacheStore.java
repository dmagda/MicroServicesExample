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

package common.cachestore;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import javax.cache.Cache;
import javax.cache.integration.CacheLoaderException;
import javax.cache.integration.CacheWriterException;
import org.apache.ignite.binary.BinaryObject;
import org.apache.ignite.cache.store.CacheStoreAdapter;

/**
 * Simple cache store implementation that should persist data on disk. For the sake of the example, we use a dummy
 * store which is a concurrent hash map. Replace it with an actual persistent store like MySQl, MongoDB satisfying
 * your use case.
 *
 * Alternatively, instead of this sample class you can use the cache store implementation created for relational
 * databases, Hibernate, Cassandra and more:
 * https://apacheignite.readme.io/docs/persistent-store
 */
public class SimpleCacheStore extends CacheStoreAdapter<Long, BinaryObject> {
    /** Dummy store. Replace it with an real persistent store according to your use case. */
    private Map<Long, BinaryObject> storeImpl = new ConcurrentHashMap<Long, BinaryObject>();

    /** {@inheritDoc} */
    public BinaryObject load(Long key) throws CacheLoaderException {
        System.out.println(" >>> Getting Value From Cache Store: " + key);

        return storeImpl.get(key);
    }

    /** {@inheritDoc} */
    public void write(Cache.Entry<? extends Long, ? extends BinaryObject> entry) throws CacheWriterException {
        System.out.println(" >>> Writing Value To Cache Store: " + entry);

        storeImpl.put(entry.getKey(), entry.getValue());
    }

    /** {@inheritDoc} */
    public void delete(Object key) throws CacheWriterException {
        System.out.println(" >>> Removing Key From Cache Store: " + key);

        storeImpl.remove(key);
    }
}
