package com.ObjectsToSend;

import java.io.Serializable;

public class Pet implements Serializable {
    private String type;
    private String name;
    private int age;

    public Pet(String type, String name, int age) {
        setType(type);
        setName(name);
        setAge(age);
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
