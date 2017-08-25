package com.javarush.task.task33.task3310.tests;

import com.javarush.task.task33.task3310.Helper;
import com.javarush.task.task33.task3310.Shortener;
import com.javarush.task.task33.task3310.strategy.HashBiMapStorageStrategy;
import com.javarush.task.task33.task3310.strategy.HashMapStorageStrategy;
import org.junit.Assert;
import org.junit.Test;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Maxim on 8/24/2017.
 */
public class SpeedTest extends Assert {

    public long getTimeForGettingIds(Shortener shortener, Set<String> strings, Set<Long> ids){
        Date start = new Date();
        for(String string : strings)
            ids.add(shortener.getId(string));
        Date finish = new Date();
        return finish.getTime() - start.getTime();
    }

    public long getTimeForGettingStrings(Shortener shortener, Set<Long> ids, Set<String> strings){
        Date start = new Date();
        for(Long id : ids)
            strings.add(shortener.getString(id));
        Date finish = new Date();
        return finish.getTime() - start.getTime();
    }

    @Test
    public void testHashMapStorage(){
        HashMapStorageStrategy hashMapStorageStrategy = new HashMapStorageStrategy();
        Shortener shortener1 = new Shortener(hashMapStorageStrategy);
        HashBiMapStorageStrategy hashBiMapStorageStrategy = new HashBiMapStorageStrategy();
        Shortener shortener2 = new Shortener(hashBiMapStorageStrategy);
        Set<String> origStrings = new HashSet<>();
        for (int i = 0; i < 10000; i++) {
            origStrings.add(Helper.generateRandomString());
        }

        Set<Long> ids = new HashSet<>();

        long timeForGettingIdsHashMap = getTimeForGettingIds(shortener1, origStrings, ids);
        long timeForGettingIdsHashBiMap = getTimeForGettingIds(shortener2, origStrings, ids);

        assertTrue("Time for getting Ids with HashMapStorageStrategy should be greater than with HashBiMapStorageStrategy: ",
                timeForGettingIdsHashMap > timeForGettingIdsHashBiMap);

        long timeForGettingStringsHashMap = getTimeForGettingStrings(shortener1, ids, new HashSet<>());
        long timeForGettingStringsHashBiMap = getTimeForGettingStrings(shortener2, ids, new HashSet<>());

        assertEquals("Time for getting Strings with HashMapStorageStrategy should be almost the same as with HashBiMapStorageStrategy: ",
                timeForGettingStringsHashMap, timeForGettingStringsHashBiMap, 30);

    }

}
