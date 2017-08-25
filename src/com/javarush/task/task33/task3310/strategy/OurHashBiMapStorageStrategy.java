package com.javarush.task.task33.task3310.strategy;

import java.util.HashMap;

/**
 * Created by Maxim on 8/23/2017.
 */
public class OurHashBiMapStorageStrategy implements StorageStrategy {

    HashMap<Long, String> k2v = new HashMap<>();

    HashMap<String, Long> v2k = new HashMap<>();

    @Override
    public boolean containsKey(Long key) {
        return k2v.containsKey(key);
    }

    @Override
    public boolean containsValue(String value) {
        return v2k.containsKey(value);
    }

    @Override
    public void put(Long key, String value) {
        k2v.put(key, value);
        v2k.put(value, key);
    }

    @Override
    public Long getKey(String value) {
        return v2k.get(value);
    }

    @Override
    public String getValue(Long key) {
        return k2v.get(key);
    }
}
