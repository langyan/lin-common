package com.lin.common.leetcode;

import java.util.HashMap;
import java.util.LinkedHashMap;

public class LRUCache {

    private HashMap<Integer, Integer> hashMap;

    public LRUCache(int capacity) {
        this.hashMap = new LinkedHashMap<>(capacity, 0.75F, true);
    }

    public int get(int key) {

        return hashMap.get(key);
    }

    public void put(int key, int value) {
        hashMap.put(key, value);
    }

}
