package com.javarush.task.task31.task3113;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

/* 
Что внутри папки?
*/
public class Solution {

    static long totalFolders = 0;
    static long totalFiles = 0;
    static long totalSize = 0;


    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        Path directoryName = Paths.get(reader.readLine());

        reader.close();

        if (!Files.isDirectory(directoryName)) {
            System.out.println(directoryName.toAbsolutePath().toString() + " - не папка");
            return;
        }

        Files.walkFileTree(directoryName, new MyFileVisitor());


        System.out.println("Всего папок - " + (totalFolders-1));

        System.out.println("Всего файлов - " + totalFiles);

        System.out.println("Общий размер - " + totalSize);


    }
    public static class MyFileVisitor extends SimpleFileVisitor<Path>{
        @Override
        public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
            totalFolders++;
            return FileVisitResult.CONTINUE;
        }

        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
            totalFiles++;
            totalSize += attrs.size();
            return FileVisitResult.CONTINUE;
        }
    }
}