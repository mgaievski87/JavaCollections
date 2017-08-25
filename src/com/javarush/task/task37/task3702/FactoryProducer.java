package com.javarush.task.task37.task3702;

import com.javarush.task.task37.task3702.female.FemaleFactory;
import com.javarush.task.task37.task3702.male.MaleFactory;

/**
 * Created by Maxim on 8/18/2017.
 */
public class FactoryProducer {
    public static enum HumanFactoryType{
        MALE, FEMALE
    }
    public static AbstractFactory getFactory(HumanFactoryType factoryType){
        if(factoryType == HumanFactoryType.MALE)
            return new MaleFactory();
        if(factoryType == HumanFactoryType.FEMALE)
            return new FemaleFactory();
        else
            return null;
    }
}
