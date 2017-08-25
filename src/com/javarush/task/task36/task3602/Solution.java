package com.javarush.task.task36.task3602;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/* 
Найти класс по описанию
*/
public class Solution {
    public static void main(String[] args) {
        System.out.println(getExpectedClass());
    }

    public static Class getExpectedClass() {
        boolean implementsList = false;
        boolean isPrivate = false;
        boolean isStatic = false;
        Class[] classes = Collections.class.getDeclaredClasses();
        for(Class clazz : classes){

                if(List.class.isAssignableFrom(clazz))
                    implementsList = true;

            if(Modifier.isPrivate(clazz.getModifiers()))
                isPrivate = true;
            if(Modifier.isStatic(clazz.getModifiers()))
                isStatic = true;
            if(implementsList && isPrivate && isStatic){
                //System.out.println(clazz.getName());
                try {
                    Constructor constructor = clazz.getDeclaredConstructor();
                    constructor.setAccessible(true);
                    List list = (List)constructor.newInstance();
                    list.get(0);
                } catch (IndexOutOfBoundsException e) {
                    return clazz;
                }catch (NoSuchMethodException e) {
                    continue;
                }catch (InstantiationException e) {
                    continue;
                } catch (IllegalAccessException e) {
                    continue;
                } catch (InvocationTargetException e) {
                    continue;
                }catch (ClassCastException e) {
                    continue;
                }

            }
        }
        return null;
    }
}
