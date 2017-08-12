package com.javarush.task.task31.tests;

import com.sun.corba.se.impl.orbutil.ObjectUtility;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * Created by Maxim on 7/14/2017.
 */
public class HowToDecompressArchiveFileVisitor {
    public static void main(String[] args) {
        Path destination;
        try{

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            Path archive = Paths.get(bufferedReader.readLine());
            destination = Paths.get(archive.toString().substring(0, archive.toString().lastIndexOf(".zip")));
            Files.createDirectories(destination);

            ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(archive.toFile()));
            ZipEntry zipEntry;
            while ((zipEntry = zipInputStream.getNextEntry()) != null){
               /* if(zipEntry.isDirectory()){
                    new File(destination.toString(),zipEntry.getName()).mkdirs();
                }
                else{*/

                    Path file = Paths.get(zipEntry.getName());
                    //Checking if there is a subfolder. If yes, creating it.
                    if(file.getParent() != null)
                        Files.createDirectories(Paths.get(destination.toAbsolutePath() + "/" + file.getParent().toString()));
                    //Creating a file and writing to file
                    FileOutputStream fileOutputStream = new FileOutputStream(new File(destination.toString(),zipEntry.getName()));
                    write(zipInputStream, fileOutputStream);
                    fileOutputStream.close();
          //      }

           }
            zipInputStream.close();
        }
        catch(IOException ex){
            ex.printStackTrace();
        }
    }
    private  static  void write(InputStream inputStream, OutputStream outputStream) throws IOException{
        byte[] buffer = new byte[1024];
        int len;
        while ((len = inputStream.read(buffer)) != -1){
            outputStream.write(buffer,0,len);
        }
    }

}
