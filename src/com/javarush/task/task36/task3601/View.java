package com.javarush.task.task36.task3601;

/**
 * Created by Maxim on 8/18/2017.
 */
public class View {
    public void fireEventShowData() {
        System.out.println(new Controller().onDataListShow());
    }
}
