package com.javarush.task.task31.task3101;

import java.io.*;
import java.util.*;

/*
Проход по дереву файлов
*/
public class Solution {
    public static void main(String[] args) {
        if(args != null && args.length == 2){
        File path = new File(args[0]);

        File resultFileAbsolutePath = new File(args[1]);

            File allFilesContent = new File(resultFileAbsolutePath.getParent() + "\\" + "allFilesContent.txt");
            FileUtils.renameFile(resultFileAbsolutePath, allFilesContent);

            try (FileOutputStream fileOutputStream = new FileOutputStream(allFilesContent)) {

            }
            catch (IOException ex){

            }

            List<File> fileList = new ArrayList<>(listOfFiles(path));
            Collections.sort(fileList, new Comparator<File>() {
                @Override
                public int compare(File o1, File o2) {

                    return o1.getName().compareTo(o2.getName());
                }
            });


            Iterator<File> iterator = fileList.iterator();
            while (iterator.hasNext()){
                File fileItem = iterator.next();
                if(fileItem.length() > 50){
                    FileUtils.deleteFile(fileItem);
                    iterator.remove();

                }
            }




            try {
                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(allFilesContent));
                for(File fileItem : fileList){
                    BufferedReader reader = new BufferedReader(new FileReader(fileItem));
                    String line = "";
                    while((line = reader.readLine()) != null) {
                        bufferedWriter.write(line);
                        bufferedWriter.newLine();
                    }

                    reader.close();
                }
                bufferedWriter.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }catch (IOException e) {
                e.printStackTrace();
            }





        }
    }
    private static List<File> listOfFiles(File directoryName){
        List<File> resultList = new ArrayList<>();
        List<File> currentList;

        currentList = Arrays.asList(directoryName.listFiles());
        for(File fileItem : currentList){
            if(fileItem.isFile()){
                resultList.add(fileItem);
            }
            else{
                resultList.addAll(listOfFiles(fileItem));
            }
        }
        return resultList;
    }

    public static void deleteFile(File file) {
        if (!file.delete()) System.out.println("Can not delete file with name " + file.getName());
    }
}
