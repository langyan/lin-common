/**
 * 
 */
package com.lin.common.caffeine;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import com.github.benmanes.caffeine.cache.Caffeine;

/**
 * @author michaellin
 *
 */
public class CaffeineExample {

    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        // LoadingCache<String, List<String>> napsAircrafts = Caffeine.newBuilder().expireAfterWrite(48, TimeUnit.HOURS)
        // .refreshAfterWrite(30, TimeUnit.MINUTES).build(key -> findNapsAircraft(key));
        // napsAircrafts.invalidate(key);

        com.github.benmanes.caffeine.cache.Cache<String, String> cache =
            Caffeine.newBuilder().initialCapacity(100).maximumSize(1000).expireAfterWrite(4, TimeUnit.MINUTES).build();

        cache.put("1", "1");
        cache.invalidate("1");
        System.out.println(cache.getIfPresent("1"));

        // napsAircrafts.invalidateAll(keys);
    }

    public static List<String> findNapsAircraft(String key) {
        return Arrays.asList(key);
    }

}
