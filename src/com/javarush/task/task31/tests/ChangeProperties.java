package com.javarush.task.task31.tests;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

/**
 * Created by Maxim on 7/15/2017.
 */
public class ChangeProperties {
    public static void main(String[] args) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            Path fileName = Paths.get(reader.readLine());
            //File file = new File(reader.readLine());

            Properties properties = new Properties();
            properties.load(new FileInputStream(fileName.toFile()));
            /*for(String key : properties.stringPropertyNames()){
                System.out.println(key + " = " + properties.getProperty(key));
            }*/
            properties.setProperty("NAME","Nicolay");
            System.out.println(properties.getProperty("NAME"));


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
