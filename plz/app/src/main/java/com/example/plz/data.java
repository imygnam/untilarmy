package com.example.plz;


import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.RealmResults;

public class data extends AppCompatActivity{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
