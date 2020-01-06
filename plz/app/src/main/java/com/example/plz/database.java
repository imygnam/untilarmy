package com.example.plz;

import io.realm.RealmObject;

public class database extends RealmObject {
    private String name;
    private String num;
    private String date;

    public String getName(){
        return name;
    }
    public String getNum(){
        return num;
    }
    public  String getDate(){
        return date;
    }
    public void setName(String name){
        this.name = name;
    }
    public void setNum(String num){
        this.num = num;
    }
    public void setDate(String date){
        this.date = date;
    }
}