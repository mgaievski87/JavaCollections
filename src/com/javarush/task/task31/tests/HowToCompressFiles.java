package com.javarush.task.task31.tests;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Created by Maxim on 7/13/2017.
 */
public class HowToCompressFiles {
    static File directoryName;
    public static void main(String[] args) throws Exception{

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        directoryName = new File(bufferedReader.readLine());
        bufferedReader.close();

        try{
            ZipOutputStream zipOutputStream = new ZipOutputStream(new FileOutputStream("Archive.zip"));
            doZip(directoryName,zipOutputStream);
            zipOutputStream.close();
        }
        catch (IOException ex){
            ex.printStackTrace();
        }

        // Code For compressing a single file:
        /*ZipOutputStream zipOutputStream = new ZipOutputStream(new FileOutputStream("File.zip"));
        zipOutputStream.putNextEntry(new ZipEntry(directoryName.getFileName().toString()));
        Files.copy(directoryName,zipOutputStream);

        zipOutputStream.close();*/
    }
    private static void doZip(File directory, ZipOutputStream zipOutputStream) throws IOException{
        for(File file : directory.listFiles()) {
            if (file.isDirectory()) {
                doZip(file, zipOutputStream);
            } else {
                String zipEntryName = Paths.get(HowToCompressFiles.directoryName.toURI()).relativize(Paths.get(file.toURI())).toString();
                zipOutputStream.putNextEntry(new ZipEntry(zipEntryName));

                write(new FileInputStream(file), zipOutputStream);

            }
        }
    }
    private static void write(InputStream in, OutputStream out) throws IOException{
        byte[] buffer = new byte[1024];
        int count;
        while ((count = in.read(buffer)) >= 0){
            out.write(buffer,0,count);
        }
        in.close();
    }
}
