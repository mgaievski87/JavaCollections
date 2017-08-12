package com.javarush.task.task31.tests;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;

/**
 * Created by Maxim on 7/13/2017.
 */
public class ShowDirectoryContent {
    static ArrayList<Path> fileList = new ArrayList<>();
    public static void main(String[] args) throws IOException{
        Path directoryName;
        System.out.println("Enter the directory:");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        directoryName = Paths.get(bufferedReader.readLine());
        Files.walkFileTree(directoryName,new MyFileVisitor());
        for(Path path : fileList){
            System.out.println(path);
        }
    }
    public static class MyFileVisitor extends SimpleFileVisitor<Path>{
        @Override
        public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
            //fileList.add(dir);
            return FileVisitResult.CONTINUE;
        }

        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
            if(file.getFileName().toString().endsWith(".zip"))
            System.out.println(file);
            //fileList.add(file);

            return FileVisitResult.CONTINUE;
        }
    }
}
