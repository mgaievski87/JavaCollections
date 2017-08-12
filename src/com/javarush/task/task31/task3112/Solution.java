package com.javarush.task.task31.task3112;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/* 
Загрузчик файлов
*/
public class Solution {

    public static void main(String[] args) throws IOException {
        //Path passwords = downloadFile("https://www.amigo.com/ship/secretPassword.txt", Paths.get("D:/MyDownloads"));
        Path passwords = downloadFile("https://opensource.apple.com/source/vim/vim-6/vim/runtime/doc/filetype.txt", Paths.get("D:/MyDownloads"));

        for (String line : Files.readAllLines(passwords)) {
            System.out.println(line);
        }
    }

    public static Path downloadFile(String urlString, Path downloadDirectory) throws IOException {

        URL url = new URL(urlString);

        InputStream inputStream = url.openStream();

        Path tempFile = Files.createTempFile("temp-",".tmp");


        Files.copy(inputStream,tempFile);

        String fileName = urlString.substring(urlString.lastIndexOf("/"));

        Files.move(tempFile, Paths.get(downloadDirectory.toString() + fileName));

        return Paths.get(downloadDirectory.toString()+fileName);
    }
}
