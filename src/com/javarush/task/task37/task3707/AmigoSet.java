package com.javarush.task.task37.task3707;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.*;

/**
 * Created by Maxim on 8/10/2017.
 */
public class AmigoSet<E> extends AbstractSet<E> implements Set<E>, Serializable, Cloneable {
    HashSet set;
    private static final Object PRESENT = new Object();
    private transient HashMap<E,Object> map;

    public AmigoSet() {
        this.map = new HashMap<>();
    }

    public AmigoSet(Collection<? extends E> collection){
        this.map = new HashMap<>((int)Math.max(16,Math.ceil(collection.size()/.75f)));

            this.addAll(collection);

    }

    @Override
    public boolean add(E e) {


            return null == this.map.put(e,PRESENT);


    }

    @Override
    public Iterator<E> iterator() {
        Iterator<E> iterator = map.keySet().iterator();
        return iterator;
    }

    @Override
    public boolean contains(Object o) {
        return this.map.containsKey(o);
    }

    @Override
    public boolean remove(Object o) {
        return this.map.remove(o) == null;
    }

    @Override
    public void clear() {
        this.map.clear();
    }

    @Override
    public boolean isEmpty() {
        return this.map.isEmpty();
    }

    @Override
    public Object clone() {
// this is a new Comment
        try {
            AmigoSet<E> amigoSetCopy = (AmigoSet)super.clone();
            amigoSetCopy.map = (HashMap)this.map.clone();
            return amigoSetCopy;

        } catch (Exception e) {
            throw new InternalError();
        }

    }

    @Override
    public int size() {

        return this.map.size();
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException{

            objectOutputStream.defaultWriteObject();

            objectOutputStream.writeFloat(HashMapReflectionHelper.callHiddenMethod(this.map,"loadFactor"));
            objectOutputStream.writeInt(HashMapReflectionHelper.callHiddenMethod(this.map, "capacity"));

            objectOutputStream.writeInt(this.map.size());
            for(E element : this.map.keySet()){
                 objectOutputStream.writeObject(element);
            }


    }

    private void readObject(ObjectInputStream objectInputStream)throws IOException, ClassNotFoundException{

        objectInputStream.defaultReadObject();

        float loadFactor = objectInputStream.readFloat();
        int capacity = objectInputStream.readInt();
        this.map = new HashMap<>(capacity,loadFactor);
        int size = objectInputStream.readInt();
        for (int i = 0; i < size; i++) {
            this.map.put((E)objectInputStream.readObject(), PRESENT);
        }


    }
}
