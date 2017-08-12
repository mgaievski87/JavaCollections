package com.javarush.task.task32.task3201;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

/*
Запись в существующий файл
*/
public class Solution {
    public static void main(String... args) {
        String fileName;
        int position;
        String text;
        if(args.length != 3 || args[0] == null || args[1] == null || args[2] == null)
            return;
        fileName = args[0];
        position = Integer.parseInt(args[1]);
        text = args[2];
        try {
            RandomAccessFile randomAccessFile = new RandomAccessFile(fileName,"rw");

            if(position >= randomAccessFile.length())

                randomAccessFile.seek(randomAccessFile.length());

            else

                randomAccessFile.seek(position);

            randomAccessFile.write(text.getBytes());

            randomAccessFile.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
