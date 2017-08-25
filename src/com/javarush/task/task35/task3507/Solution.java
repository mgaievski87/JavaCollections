package com.javarush.task.task35.task3507;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/* 
ClassLoader - что это такое?
*/
public class Solution {
    public static void main(String[] args) {
        Set<? extends Animal> allAnimals = getAllAnimals(Solution.class.getProtectionDomain().getCodeSource().getLocation().getPath() + Solution.class.getPackage().getName().replaceAll("[.]", "/") + "/data");
        System.out.println(allAnimals);
    }
    public static class ClassFromPath extends ClassLoader{
        public Class<?> load(Path path, String packageName){
            try {
                String className = packageName + "." + path.getFileName().toString().replace(".class", "");
                byte[] b = Files.readAllBytes(path);
                return defineClass(className, b, 0, b.length); //here main magic
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

    }
    public static Set<? extends Animal> getAllAnimals(String pathToAnimals) {
        Set<Animal> resultSet = new HashSet<>();


            File[] list = new File(pathToAnimals).listFiles();

                for(File file : list){
                    if(file.isFile() && file.getName().endsWith(".class")){

                        String packageName = Solution.class.getPackage().getName() + ".data";

                        Class clazz = new ClassFromPath().load(file.toPath(), packageName);

                        boolean hasZeroConstructor = false;
                        boolean isInstanceOfAnimal = false;

                        Constructor[] constructors = clazz.getConstructors();

                        for(Constructor constructor : constructors){
                            if(constructor.getParameterCount()==0)
                                hasZeroConstructor = true;
                        }

                        Class[] interfaces = clazz.getInterfaces();
                        for (Class interfaceClass : interfaces){
                            if(interfaceClass.getSimpleName().toString().equals("Animal"))
                                isInstanceOfAnimal = true;
                        }

                        if (hasZeroConstructor && isInstanceOfAnimal)
                            try {
                                resultSet.add((Animal)clazz.newInstance());
                            } catch (InstantiationException e) {
                                e.printStackTrace();
                            } catch (IllegalAccessException e) {
                                e.printStackTrace();
                            }

                    }

                }


        return resultSet;
    }
}
