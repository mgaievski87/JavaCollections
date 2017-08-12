package com.javarush.task.task32.task3204;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/* 
Генератор паролей
*/
public class Solution {
    public static void main(String[] args) {
        ByteArrayOutputStream password = getPassword();
        System.out.println(password.toString());
    }

    public static ByteArrayOutputStream getPassword() {
        String alphabeth = "abcdefghijklmnopqrstuvwxyz";
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int index = (int)(Math.random() * 26);
        byteArrayOutputStream.write(alphabeth.charAt(index));
        index = (int)(Math.random() * 26);
        byteArrayOutputStream.write(alphabeth.toUpperCase().charAt(index));
        try {
            for (int i = 0; i < 6; i++) {
                index = (int)(Math.random() * 10);
                byteArrayOutputStream.write(Integer.toString(index).getBytes());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return byteArrayOutputStream;
    }
}