package com.javarush.task.task33.task3310;

import com.javarush.task.task33.task3310.strategy.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Maxim on 8/21/2017.
 */
public class Solution {
    public static void main(String[] args) {
        testStrategy(new HashMapStorageStrategy(), 10_000);

        testStrategy(new OurHashMapStorageStrategy(), 10_000);

        testStrategy(new FileStorageStrategy(), 10);

        testStrategy(new OurHashBiMapStorageStrategy(), 10_000);

        testStrategy(new HashBiMapStorageStrategy(), 10_000);

        testStrategy(new DualHashBidiMapStorageStrategy(), 10_000);



        //System.out.println(Paths.get("").toAbsolutePath());

     /*  FileBucket fileBucket = new FileBucket();
        Entry entryChild = new Entry(456,789L,"entryChild",null);
        Entry entry = new Entry(123,456L,"entry", entryChild);
        fileBucket.putEntry(entry);
        Entry deserializedEntry = fileBucket.getEntry();
        System.out.println(deserializedEntry);*/

    }

    public static Set<Long> getIds(Shortener shortener, Set<String> strings){
        Set<Long> ids = new HashSet<>();
        for(String string : strings){
            ids.add(shortener.getId(string));
        }
        return ids;
    }

    public static Set<String> getStrings(Shortener shortener, Set<Long> keys){
        Set<String> strings = new HashSet<>();
        for(Long key : keys){
            strings.add(shortener.getString(key));
        }
        return strings;
    }

    public static void testStrategy(StorageStrategy strategy, long elementsNumber){
        Date startTime;
        Date nowTime;
        Long timeElapsed;

        Helper.printMessage(strategy.getClass().getSimpleName());
        //Generating a Set of strings for testing
        Set<String> strings = new HashSet<>();
        for (int i = 0; i < elementsNumber; i++) {
            strings.add(Helper.generateRandomString());
        }

        //Creating a Shortener based on the Strategy
        Shortener shortener = new Shortener(strategy);

        //Measuring the time elapsed for the strategy for getIds

        startTime = new Date();

        Set<Long> ids = getIds(shortener, strings);

        nowTime = new Date();

        timeElapsed = nowTime.getTime() - startTime.getTime();

        Helper.printMessage("Time needed for getting ids:" + timeElapsed);


        //Measuring the time elapsed for the strategy for getStrings

        startTime = new Date();

        Set<String> returnedStrings = getStrings(shortener, ids);

        nowTime = new Date();

        timeElapsed = nowTime.getTime() - startTime.getTime();

        Helper.printMessage("Time needed for getting strings:" + timeElapsed);

        // Checking if returned strings equals to the original ones.

        if(returnedStrings.size() != strings.size())
            Helper.printMessage("Тест не пройден.");
        else {
            if(returnedStrings.containsAll(strings))
                Helper.printMessage("Тест пройден.");
        }
    }

}
