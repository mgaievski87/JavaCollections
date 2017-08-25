package com.javarush.task.task33.task3310.strategy;


import com.javarush.task.task33.task3310.ExceptionHandler;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by Maxim on 8/22/2017.
 */
public class FileBucket {
    private Path path;
    public FileBucket(){
        try {
            path = Files.createTempFile(null,null);
            Files.deleteIfExists(path);
            Files.createFile(path);

        } catch (IOException e) {
            ExceptionHandler.log(e);
        }
        path.toFile().deleteOnExit();
    }

    public long getFileSize(){
        try {
            return Files.size(path);
        } catch (IOException e) {
            return 0;
        }

    }

    public void putEntry(Entry entry){
        if(entry != null)
        try(OutputStream out = Files.newOutputStream(path);
            ObjectOutputStream objOut = new ObjectOutputStream(out)){
            objOut.writeObject(entry);
            /*for(Entry e = entry; e.next != null; e = e.next){
                objOut.writeLong(e.getKey());
            }*/

        }catch (IOException ex){
            ex.printStackTrace();
        }
    }

    public Entry getEntry(){
        if(getFileSize() == 0){
            return null;
        }
        try(InputStream in = Files.newInputStream(path);
            ObjectInputStream objIn = new ObjectInputStream(in)){

            Entry e = (Entry)objIn.readObject();

            return e;

        } catch (IOException ex){
            return null;
        }catch (ClassNotFoundException ex){
            return null;
        }
    }

    public void remove(){
        try {
                Files.delete(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
