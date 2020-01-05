package com.example.plz;

import android.os.AsyncTask;
import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;


public class GetBJ extends AsyncTask<Void, Void, Void> {



    private static String htmlPageURL = "https://www.acmicpc.net/user/";

    public String id;
    public Elements mTitles;

    String getBJ(String id){
        Log.d("getBJ start", id);
        int i =0;
        this.id = id;
        doInBackground();
        for(Element e: mTitles){
            Log.d("getBJ for", i+"");
            i++;
            if(i==1) return e.text();
        }
        return "Error";
    }


    @Override
    protected Void doInBackground(Void... params) {
        try{
            Log.d("getBJ for", "try 시작");

            Document doc = Jsoup.connect("https://www.acmicpc.net/user/godgsds").get();

            Log.d("getBJ for", "doc 받음");


            //사이트 받기
            Elements titles = doc.select("div.col-md-3 a");
            Log.d("haha", htmlPageURL+id);
            mTitles = titles;

        } catch (IOException e) {
            Log.d("err123", htmlPageURL+id);
            e.printStackTrace();
        }
        return null;
    }


}
