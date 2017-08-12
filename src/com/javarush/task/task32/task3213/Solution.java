package com.javarush.task.task32.task3213;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

/* 
Шифр Цезаря
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        StringReader reader = new StringReader("Khoor Dpljr");
        System.out.println(decode(reader, -3));  //Hello Amigo

    }

    public static String decode(StringReader reader, int key) throws IOException {
        String alphabeth = "abcdefghijklmnopqrstuvwxyz";
        String decode = "";
        String codeString;
        int index;
        StringWriter stringWriter = new StringWriter();
        BufferedReader bufferedReader = new BufferedReader(reader);
        while ((codeString = bufferedReader.readLine()) != null){

            for(char ch : codeString.toCharArray()){
                if(Character.isUpperCase(ch))
                    alphabeth = alphabeth.toUpperCase();
                else
                    alphabeth = alphabeth.toLowerCase();
                if(alphabeth.indexOf(ch) == -1)
                    stringWriter.write(ch);
                else {
                    index = alphabeth.indexOf(ch) - 3;

                    if (index < 0) index += 26;
                    stringWriter.write(alphabeth.charAt(index));
                }
            }
        }
        decode += stringWriter.toString();
        stringWriter.close();

        return decode;
    }

}
