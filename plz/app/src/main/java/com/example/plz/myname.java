package com.example.plz;

import io.realm.RealmObject;

public class myname extends RealmObject {
    private String name;

    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }
}