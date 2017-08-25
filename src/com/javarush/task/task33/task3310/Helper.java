package com.javarush.task.task33.task3310;

import java.math.BigInteger;
import java.security.SecureRandom;

/**
 * Created by Maxim on 8/21/2017.
 */
public class Helper {

    public static String generateRandomString(){
        SecureRandom random = new SecureRandom();
        BigInteger bigInteger = new BigInteger(130,random);
        return bigInteger.toString(32);
    }
    public static void printMessage(String message){
        System.out.println(message);
    }
}
