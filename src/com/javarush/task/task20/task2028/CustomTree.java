package com.javarush.task.task20.task2028;

import java.io.Serializable;
import java.util.*;

/* 
Построй дерево(1)
*/
public class CustomTree extends AbstractList<String> implements Cloneable, Serializable {
    Entry<String> root = new Entry<>("0");

    @Override
    public boolean addAll(int index, Collection<? extends String> c) {

        throw new UnsupportedOperationException();

    }

    @Override
    protected void removeRange(int fromIndex, int toIndex) {

        throw new UnsupportedOperationException();

    }

    @Override
    public List<String> subList(int fromIndex, int toIndex) {

        throw new UnsupportedOperationException();

    }

    @Override
    public String remove(int index) {

        throw new UnsupportedOperationException();

    }

    @Override
    public void add(int index, String element) {

        throw new UnsupportedOperationException();

    }

    @Override
    public String set(int index, String element) {

        throw new UnsupportedOperationException();

    }

    @Override
    public String get(int index) {
        return null;
    }

    protected CustomTree() {
        super();
    }

    @Override
    public int size() {
        int counter = 0;
        Queue<Entry> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            Entry current = queue.poll();
            counter++;
            current.checkChildren();

            if (!current.availableToAddLeftChildren)
                queue.add(current.leftChild);
            if (!current.availableToAddRightChildren) {
                queue.add(current.rightChild);
            }
        }
        counter--;
        return counter;
    }

    public boolean add(String name) {

        Queue<Entry> queue = new LinkedList<>();
        queue.add(root);
        Entry entryToAdd = new Entry(name);
        while (!queue.isEmpty()) {
            Entry current = queue.poll();
            current.checkChildren();

            if (current.availableToAddLeftChildren) {
                current.leftChild = entryToAdd;
                current.leftChild.parent = current;
                return true;
            } else if (current.availableToAddRightChildren) {
                current.rightChild = entryToAdd;
                current.rightChild.parent = current;

                return true;
            } else {
                queue.add(current.leftChild);
                queue.add(current.rightChild);
            }

        }
        return false;

    }




    public boolean remove(Object name) {
        Queue<Entry> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()){
            Entry current = queue.poll();
            if(current.leftChild != null && current.leftChild.elementName.equals(name)){
                current.leftChild = null;
               /* if(!current.availableToAddLeftChildren)
                remove(current.leftChild.elementName);
                if(!current.availableToAddRightChildren)
                remove(current.rightChild.elementName);
                System.out.println(current.elementName +" is Removed");*/

                return true;
            }
            else if(current.rightChild != null && current.rightChild.elementName.equals(name)){
                current.rightChild = null;
                return true;
            }
            else{
                if(!current.availableToAddLeftChildren)
                queue.add(current.leftChild);
                if(!current.availableToAddRightChildren)
                queue.add(current.rightChild);
            }
        }
        return false;
    }
    public Entry<String> getParent(String name){
        Queue<Entry> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()){

                Entry current;

                current = queue.poll();
                current.checkChildren();
                if(current.elementName.equals(name))
                    return current.parent;
                else{
                    if(current.leftChild != null)
                        queue.add(current.leftChild);
                    if(current.rightChild != null)
                        queue.add(current.rightChild);
                }


        }
        return null;
    }

    public static void main(String[] args) {
        List<String> list = new CustomTree();
        for (int i = 1; i < 16; i++) {
            list.add(String.valueOf(i));
        }
        System.out.println("Size: " + list.size());
        System.out.println("Expected 3, actual is " + ((CustomTree) list).getParent("8"));
        list.remove("5");

        System.out.println("Expected null, actual is " + ((CustomTree) list).getParent("11"));
    }

    static class Entry<T> implements Serializable{
        String elementName;
        int lineNumber;
        boolean availableToAddLeftChildren;
        boolean availableToAddRightChildren;
        Entry<T> parent, leftChild, rightChild;


        public Entry(String elementName) {
            this.elementName = elementName;
            this.availableToAddLeftChildren = true;
            this.availableToAddRightChildren = true;
        }
        void checkChildren(){
            if(leftChild != null) availableToAddLeftChildren = false;
            if(rightChild != null) availableToAddRightChildren = false;
        }
        boolean isAvailableToAddChildren(){
            return availableToAddLeftChildren || availableToAddRightChildren;
        }
    }

}
