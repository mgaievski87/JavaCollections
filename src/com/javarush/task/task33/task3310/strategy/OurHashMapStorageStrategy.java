package com.javarush.task.task33.task3310.strategy;

import java.util.HashMap;

/**
 * Created by Maxim on 8/22/2017.
 */
public class OurHashMapStorageStrategy implements StorageStrategy{

    static final int DEFAULT_INITIAL_CAPACITY = 16;
    static final float DEFAULT_LOAD_FACTOR = 0.75f;

    Entry[] table = new Entry[DEFAULT_INITIAL_CAPACITY];

    int size;
    int threshold = (int)(DEFAULT_INITIAL_CAPACITY * DEFAULT_LOAD_FACTOR);
    float loadFactor = DEFAULT_LOAD_FACTOR;


    int hash(Long key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }

    int indexFor(int hash, int length){
        return (length-1) & hash;
    }


    Entry getEntry(Long key){
        Entry first, e;
        int hash = (key == null) ? 0 : hash(key);
        if (table != null && table.length > 0 &&
                (first = table[indexFor(hash,table.length)]) != null) {
            if (first.hash == hash && // always check first node
                    (first.key == key || (key != null && key.equals(first.key))))
                return first;
            if ((e = first.next) != null) {

                do {
                    if (e.hash == hash &&
                            ((first.key = e.key) == key || (key != null && key.equals(first.key))))
                        return e;
                } while ((e = e.next) != null);
            }
        }

        return null;
    }

    void resize(int newCapacity){
        Entry[] oldTable = table;
        int oldCapacity = oldTable.length;
        if (oldCapacity == (1 << 30)) {
            threshold = Integer.MAX_VALUE;
            return;
        }
        Entry[] newTable = new Entry[newCapacity];
        transfer(newTable);
        table = newTable;
        threshold = (int)Math.min(newCapacity * loadFactor, (1 << 30) + 1);


    }

    void transfer(Entry[] newTable){
        int newCapacity = newTable.length;
        for (Entry e : table) {
            while(e != null) {
                //Entry next = e.next;
                int i = indexFor(e.hash, newCapacity);
                //e.next = newTable[i];
                newTable[i] = e;
                e = e.next;
            }
        }
    }

    void addEntry(int hash, Long key, String value, int bucketIndex){
        if ((size >= threshold) && (null != table[bucketIndex])) {
            resize(2 * table.length);
            hash = (null != key) ? hash(key) : 0;
            bucketIndex = indexFor(hash, table.length);
        }
        createEntry(hash, key, value, bucketIndex);
    }

    public void createEntry(int hash, Long key, String value, int bucketIndex) {
        Entry e = table[bucketIndex];
        table[bucketIndex] = new Entry(hash, key, value, e);
        size++;
    }

    @Override
    public boolean containsKey(Long key) {
        return getEntry(key) != null;
    }

    @Override
    public boolean containsValue(String value) {
        if(value == null)
        return false;
        for(Entry entry: table)
            for(Entry e = entry; e != null; e = e.next){
                if(value.equals(e.value))
                    return true;
            }
        return false;
    }

    @Override
    public void put(Long key, String value) {
        addEntry(hash(key),key,value,indexFor(hash(key),table.length));

    }

    @Override
    public Long getKey(String value) {
        if(value == null)
            return 0L;
        for (Entry entry : table) {
            for (Entry e = entry; e != null; e = e.next)
                if (value.equals(e.value))
                    return e.getKey();
        }
        return null;
    }

    @Override
    public String getValue(Long key) {
        return getEntry(key) == null ? null : getEntry(key).getValue();
    }
}
