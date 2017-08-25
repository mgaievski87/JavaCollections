package com.javarush.task.task36.task3601;

import java.util.List;

/**
 * Created by Maxim on 8/18/2017.
 */
public class Model {
    public List<String> getStringDataList() {
        return new Service().getData();
    }

}
