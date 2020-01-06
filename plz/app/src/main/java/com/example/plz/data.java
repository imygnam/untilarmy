package com.example.plz;


import androidx.appcompat.app.AppCompatActivity;

import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.RealmResults;

public class data extends AppCompatActivity{
    public class database extends RealmObject{
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
    public void setData(final String date, final String name, final String num){
        Realm.init(this);
        Realm mRealm = Realm.getDefaultInstance();

        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                RealmResults<database> dbl = realm.where(database.class).equalTo("name", name).findAll();
                if(dbl.size() == 0) {
                    database db = realm.createObject(database.class);
                    db.setDate(date);
                    db.setName(name);
                    db.setNum(num);
                }
                else{
                    database db = dbl.first();
                    db.setDate(date);
                    db.setName(name);
                    db.setNum(num);
                }
            }
        });
    }
}
