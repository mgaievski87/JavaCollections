package com.javarush.task.task33.task3308;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Maxim on 8/1/2017.
 */
@XmlRootElement
@XmlType(name = "shop")
public class Shop {

    public Goods goods;
    @XmlElement(name = "count")
    public int count;
    @XmlElement(name = "profit")
    public double profit;
    @XmlElement(name = "secretData")
    public String[] secretData;
    @XmlType(name = "goods")
    public static class Goods{
        public List<String> names = new ArrayList<>();

        @Override
        public String toString() {
            return "Goods{" +
                    "names=" + names +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "Shop{" +
                "\ngoods=" + goods +
                ",\ncount=" + count +
                ",\nprofit=" + profit +
                ",\nsecretData=" + Arrays.toString(secretData) +
                '}';
    }
}
