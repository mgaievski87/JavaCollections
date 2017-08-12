package com.javarush.task.task32.task3202;

import java.io.*;

/* 
Читаем из потока
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        StringWriter writer = getAllDataFromInputStream(new FileInputStream("D:/JavaProjects/JavaRushHomeWork/JavaRushTasks/4.JavaCollections/src/com/javarush/task/task32/task3202/testFile.log"));
        System.out.println(writer.toString());
    }

    public static StringWriter getAllDataFromInputStream(InputStream is) throws IOException {

            StringWriter stringWriter = new StringWriter();
            if(is == null)
            return new StringWriter();
            String inputLine = "";
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is));
            while ((inputLine = bufferedReader.readLine()) != null){

            stringWriter.write(inputLine);
            }

            return stringWriter;

    }
}