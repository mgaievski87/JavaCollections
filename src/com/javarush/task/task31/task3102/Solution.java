package com.javarush.task.task31.task3102;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.SynchronousQueue;

/* 
Находим все файлы
*/
public class Solution {
    public static List<String> getFileTree(String root) throws IOException {

        List<String> resultList = new ArrayList<>();

        Queue<File> queue = new PriorityQueue<>();

        queue.add(new File(root));
        while (!queue.isEmpty()){
            File fileItem = queue.poll();
            if(fileItem != null)
            if(fileItem.isFile())
                resultList.add(fileItem.getAbsolutePath());
            else {
                for(File file : fileItem.listFiles()){
                    queue.offer(file);
                }
            }
        }
        return resultList;

    }

    public static void main(String[] args) {

    }
}
