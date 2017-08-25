package com.javarush.task.task33.task3310.strategy;

/**
 * Created by Maxim on 8/23/2017.
 */
public class FileStorageStrategy implements StorageStrategy {

    static final int DEFAULT_INITIAL_CAPACITY = 16;

    static final long DEFAULT_BUCKET_SIZE_LIMIT = 10000;

    int size;

    FileBucket[] table = new FileBucket[DEFAULT_INITIAL_CAPACITY];

    private long bucketSizeLimit = DEFAULT_BUCKET_SIZE_LIMIT;

    long maxBucketSize;

    public long getBucketSizeLimit() {
        return bucketSizeLimit;
    }

    public void setBucketSizeLimit(long bucketSizeLimit) {
        this.bucketSizeLimit = bucketSizeLimit;
    }


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
                (first = table[indexFor(hash,table.length)].getEntry()) != null) {
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
        FileBucket[] oldTable = table;
        int oldCapacity = oldTable.length;
        /*if (newCapacity > maxBucketSize) {
            newCapacity = maxBucketSize > Integer.MAX_VALUE ? Integer.MAX_VALUE : (int)maxBucketSize;

        }*/
        FileBucket[] newTable = new FileBucket[newCapacity];
        transfer(newTable);
        table = newTable;
        //threshold = (int)Math.min(newCapacity * loadFactor, (1 << 30) + 1);


    }

    void transfer(FileBucket[] newTable){
        int newCapacity = newTable.length;
        for (FileBucket fileBucket : table) {
            Entry e = fileBucket.getEntry();
            while(e != null) {
                int i = indexFor(e.hash, newCapacity);
                newTable[i].putEntry(e);
                e = e.next;
            }
        }
    }

    void addEntry(int hash, Long key, String value, int bucketIndex){
        if ((table[bucketIndex] != null) && (table[bucketIndex].getFileSize() >= bucketSizeLimit)) {
            resize(2 * table.length);
            hash = (null != key) ? hash(key) : 0;
            bucketIndex = indexFor(hash, table.length);
        }
        createEntry(hash, key, value, bucketIndex);
    }

    public void createEntry(int hash, Long key, String value, int bucketIndex) {
        FileBucket fileBucket;
        Entry e = null;
        if((fileBucket = table[bucketIndex]) != null) {
            e = fileBucket.getEntry();
        }
        table[bucketIndex] = new FileBucket();
        table[bucketIndex].putEntry(new Entry(hash, key, value, e));
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
        for(FileBucket fileBucket : table){
            if(fileBucket != null)
            for(Entry e = fileBucket.getEntry(); e != null; e = e.next){
                if(e.getValue() == value)
                    return true;
            }
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
        for (FileBucket fileBucket : table) {
            for (Entry e = fileBucket.getEntry(); e != null; e = e.next)
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
