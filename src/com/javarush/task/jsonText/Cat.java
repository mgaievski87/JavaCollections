package com.javarush.task.jsonText;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Maxim on 7/31/2017.
 */

@JsonAutoDetect

class Cat {
    @JsonProperty("alies") public String name;
    public int age;
    @JsonIgnore public int weight;
    Cat() { }
}
