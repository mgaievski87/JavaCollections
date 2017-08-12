package com.javarush.task.task32.task3210;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

/* 
Используем RandomAccessFile
*/

public class Solution {
    public static void main(String... args) {
        String fileName;
        int position;
        String text;
        String textFromFile;
        if(args.length != 3 || args[0] == null || args[1] == null || args[2] == null)
            return;
        fileName = args[0];
        position = Integer.parseInt(args[1]);
        text = args[2];

        try {
            RandomAccessFile randomAccessFile = new RandomAccessFile(fileName,"rw");

            randomAccessFile.seek(position);

            byte[] buffer = new byte[text.length()];

            randomAccessFile.read(buffer,0,text.length());

            randomAccessFile.seek(randomAccessFile.length());

            if(convertByteToString(buffer).equals(text))

                randomAccessFile.write("true".getBytes());

            else

                randomAccessFile.write("false".getBytes());

            randomAccessFile.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static String convertByteToString(byte readBytes[]){
        return new String(readBytes);
    }
}
