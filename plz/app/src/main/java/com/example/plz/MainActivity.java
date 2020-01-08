package com.example.plz;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;

import android.content.DialogInterface;
import android.content.pm.PackageManager;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import io.realm.Realm;


import android.util.Log;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;


public class MainActivity extends AppCompatActivity {

    String id;
    String bj;
    TextView textviewBJ;
    GetBJ gbj = new GetBJ();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Realm.init(this);


        if (ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.SEND_SMS)) {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("문자");
                builder.setMessage("님이 허용해줘야 앱이 돌아감");

                builder.setNeutralButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.SEND_SMS}, 1001);
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();

            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS}, 1001);
            }
        }

        final Realm mRealm = Realm.getDefaultInstance();
        myname mn = mRealm.where(myname.class).findFirst();

        if (mn == null) {

            AlertDialog.Builder ad = new AlertDialog.Builder(MainActivity.this);

            ad.setTitle("당신의 아이디");
            ad.setMessage("잘 확인하고 입력하시오. 실수하면 지웠다 깔아야함.");

            final EditText et = new EditText(MainActivity.this);
            ad.setView(et);

            ad.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    final String value = et.getText().toString();
                    dialog.dismiss();

                    mRealm.executeTransaction(new Realm.Transaction() {
                        @Override
                        public void execute(Realm realm) {
                            myname mkmn = realm.createObject(myname.class);
                            mkmn.setName(value);
                            database mkdb = realm.createObject(database.class);
                            mkdb.setDate(sms_day.today());
                            mkdb.setName(value);
                            GetBJ getBJ = new GetBJ();
                            String x = getBJ.getBJ(value);
                            mkdb.setNum(x);
                        }
                    });

                }
            });
            ad.show();
        }

        database db = mRealm.where(database.class).findFirst();

        if (db == null) ;
        else {
            if (db.getDate().equals(sms_day.today())) {
                Toast myToast = Toast.makeText(this.getApplicationContext(), ""+sms_day.days(sms_day.today(),"2020|01|18"), Toast.LENGTH_LONG);
                myToast.show();
            } else {
                Toast myToast = Toast.makeText(this.getApplicationContext(), "출석체크 되었음", Toast.LENGTH_LONG);
                myToast.show();
            }
            mn = mRealm.where(myname.class).findFirst();
            final String name = mn.getName();
            GetBJ getBJ = new GetBJ();
            final String x = getBJ.getBJ(name);

            mRealm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    database mkdb = realm.where(database.class).equalTo("name", name).findFirst();
                    mkdb.setDate(sms_day.today());
                    mkdb.setName(name);
                    mkdb.setNum(x);
                }
            });
        }
    }
    // 무조건 크롤링 끝나면 뜸
    private class Description extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... params) {
            try {

                Document doc = Jsoup.connect("https://www.acmicpc.net/user/" + id).get();

                Elements titles = doc.select("td a"); //필요한 녀석만 꼬집어서 지정

                if (titles.toString() != null) {
                    String str = titles.toString();
                    bj = "";
                    int i = 0;
                    for (; str.charAt(i) != '>'; i++) {

                    }

                    for (i++; str.charAt(i) != '<'; i++) {
                        bj += str.charAt(i);
                        Log.d("푼문제수", bj);
                    }

                } else bj = null;

            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            textviewBJ.setText(bj);
        }
    }

    //

}
