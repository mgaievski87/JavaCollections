package com.javarush.task.task36.task3605;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;

/* 
Использование TreeSet
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        TreeSet<Character> treeSet = new TreeSet<>();
        if(args.length > 0 && args[0] != null){
            Path filePath = Paths.get(args[0]);
            List<String> lines = Files.readAllLines(filePath, Charset.defaultCharset());
            for(String line : lines){
                char[] symbols = line.toLowerCase().toCharArray();
                for(char symbol : symbols){
                    if(alphabet.indexOf(symbol) != -1){
                        treeSet.add(symbol);
                    }
                }
            }
            Iterator<Character> iterator = treeSet.iterator();
            int i = 0;
            while (iterator.hasNext() && i < 5){
                System.out.print(iterator.next().charValue());
                i++;
            }

        }


    }
}
