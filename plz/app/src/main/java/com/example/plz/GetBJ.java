package com.example.plz;

import android.os.AsyncTask;
import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;



public class GetBJ {

    private static String htmlPageURL = "https://www.acmicpc.net/user/";

    public String id;
    public static String bj;
    int t;

    String getBJ(String id){
        Log.d("getBJ start", id);
        int i =0;
        this.id = id;
        t = 0;

        new Description().execute();

        Log.d("문제수 찾는중", "시작" + t);
        while(true){
            if(t == 1) break;
            for(int q = 0; q < 100; q++){

            }
            Log.d("반복", ""+t);
        }
        Log.d("문제수 찾는중", "끝");
        return bj;
    }


    private class Description extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {
            try {

                Document doc = Jsoup.connect("https://www.acmicpc.net/user/"+id).get();

                Elements titles = doc.select("td a"); //필요한 녀석만 꼬집어서 지정

                Log.d("엘레멘트", titles+"");

                if(titles.toString() != null) {
                    String str = titles.toString();
                    bj = "";
                    int i =0;
                    for(; str.charAt(i) != '>';i++){

                    }
                    Log.d("문제수 찾는중", ""+str.charAt(i));
                    for(i++;str.charAt(i) != '<';i++){
                        Log.d("문제수 찾는중", ""+bj);
                        bj += str.charAt(i);
                    }

                }
                else bj = null;
                Log.d("문제수 찾는중", "끌");
                t = 1;

            } catch (IOException e) {
                e.printStackTrace();
                Log.d("문제수 찾는중", "아웃");
                t = 1;
            }
            Log.d("문제수 찾는중", "끝자락" + t);
            t = 1;
            return null;
        }
    }


}
