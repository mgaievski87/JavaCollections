package com.javarush.task.task33.task3310.strategy;

import java.io.Serializable;
import java.util.Map;
import java.util.Objects;

/**
 * Created by Maxim on 8/22/2017.
 */
public class Entry implements Serializable {

    private static final long serialVersionUID = 3624988207631342L;

    Long key;
    String value;
    Entry next;
    int hash;

    public Entry(int hash, Long key, String value, Entry next) {
        this.key = key;
        this.value = value;
        this.next = next;
        this.hash = hash;
    }

    public Long getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Entry))
        return false;
        Long k1 = ((Entry) o).getKey();
        String v1 = ((Entry) o).getValue();
        if((this.getKey() == k1) || (k1 !=  null && k1.equals(this.key))){
            if((this.getValue() == v1) || ((v1 != null) && v1.equals(this.value)))
                return true;
        }

        return false;

    }

    @Override
    public int hashCode() {
        return Objects.hashCode(key) ^ Objects.hashCode(value);
    }

    @Override
    public String toString() {
        return key + "=" + value;
    }
}
