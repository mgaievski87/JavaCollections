package com.javarush.task.task36.task3610;

import java.io.Serializable;
import java.util.*;

public class MyMultiMap<K, V> extends HashMap<K, V> implements Cloneable, Serializable {
    static final long serialVersionUID = 123456789L;
    private HashMap<K, List<V>> map;
    private int repeatCount;

    public MyMultiMap(int repeatCount) {
        this.repeatCount = repeatCount;
        map = new HashMap<>();
    }

    @Override
    public int size() {
        int valuesCount = 0;
        for(Map.Entry<K,List<V>> entry : map.entrySet())
            valuesCount += entry.getValue().size();
        return valuesCount;
    }

    @Override
    public V put(K key, V value) {
        if(map.containsKey(key)){
            List<V> values = new ArrayList<>();
            values.addAll(map.get(key));
            if(values.size() == repeatCount){
                values.remove(0);
            }
                values.add(value);
                map.put(key,values);

            return values.size() > 1 ? values.get(values.size()-2) : null;

        }
        else {
            map.put(key, Arrays.asList(value));
            return null;
        }
    }

    @Override
    public V remove(Object key) {
        if(map.containsKey(key)){
            List<V> values = new ArrayList<>();
               values.addAll(map.get(key));
            if(values.size() > 0){
                V removedValue = values.remove(0);
                map.put((K)key, values);
                if(values.size() == 0)
                    map.remove(key);
                return removedValue;
            }
            else{
                map.remove(key);
                return null;
            }
        }
        else
            return null;
    }

    @Override
    public Set<K> keySet() {
        return map.keySet();
    }

    @Override
    public Collection<V> values() {
        ArrayList<V> valuesList = new ArrayList<>();
        for(Map.Entry<K,List<V>> entry : map.entrySet()){
            valuesList.addAll(entry.getValue());
        }
        return valuesList;
    }

    @Override
    public boolean containsKey(Object key) {
        return map.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return values().contains(value);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("{");
        for (Map.Entry<K, List<V>> entry : map.entrySet()) {
            sb.append(entry.getKey());
            sb.append("=");
            for (V v : entry.getValue()) {
                sb.append(v);
                sb.append(", ");
            }
        }
        String substring = sb.substring(0, sb.length() - 2);
        return substring + "}";
    }
}