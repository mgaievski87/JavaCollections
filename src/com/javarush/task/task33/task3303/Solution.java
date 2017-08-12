package com.javarush.task.task33.task3303;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;

import java.nio.file.Files;
import java.nio.file.Paths;

/* 
Десериализация JSON объекта
*/
public class Solution {
    public static <T> T convertFromJsonToNormal(String fileName, Class<T> clazz) throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        T object = objectMapper.readValue(new File(fileName),clazz);
        return object;
    }

    public static void main(String[] args) {
        try {
            Cat cat = Solution.convertFromJsonToNormal("D:\\JavaProjects\\JavaRushHomeWork\\JavaRushTasks\\4.JavaCollections\\src\\com\\javarush\\task\\task33\\task3303\\JsonFile",Cat.class);
            System.out.println("Cat name: " + cat.name);
            System.out.println("Cat age: " + cat.age);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    @JsonAutoDetect
    public static class Cat{
        public String name;
        public int age;
        public Cat(){}
    }
}
