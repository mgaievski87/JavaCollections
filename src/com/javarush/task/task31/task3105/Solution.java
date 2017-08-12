package com.javarush.task.task31.task3105;

import java.io.*;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/* 
Добавление файла в архив
*/
public class Solution {
    static String fileName;
    static String zipName;
    static File file;
    static HashMap<String, ByteArrayOutputStream> zipContent = new HashMap<>();
    public static void main(String[] args) throws IOException {
        if(args.length != 2 || args[0] == null || args[1] == null){
            System.out.println("Invalid arguments!");
            return;
        }
        fileName = args[0];
        zipName = args[1];
        file = new File(fileName);
        try(ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(zipName))){
            ZipEntry zipEntry;
            while ((zipEntry = zipInputStream.getNextEntry()) != null){
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                byte[] buffer = new byte[1024];
                int count = 0;
                while ((count = zipInputStream.read(buffer)) != -1){
                    byteArrayOutputStream.write(buffer,0,count);
                }
                zipContent.put(zipEntry.getName(),byteArrayOutputStream);

            }

        }

        try(ZipOutputStream zipOutputStream = new ZipOutputStream(new FileOutputStream(zipName))){

            for(Map.Entry<String,ByteArrayOutputStream> entry : zipContent.entrySet()){
                if(entry.getKey().substring(entry.getKey().lastIndexOf("/")+1).equals(file.getName())) continue;



                    zipOutputStream.putNextEntry(new ZipEntry(entry.getKey()));
                    zipOutputStream.write(entry.getValue().toByteArray());

            }

            zipOutputStream.putNextEntry(new ZipEntry("new/" + file.getName()));
            Files.copy(file.toPath(),zipOutputStream);

        }



    }

}
