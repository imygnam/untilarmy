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

    String getBJ(String id){
        Log.d("getBJ start", id);
        int i =0;
        this.id = id;
        new Description().execute();
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

            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }


}
