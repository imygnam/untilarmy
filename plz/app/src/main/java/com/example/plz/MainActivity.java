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

import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;


public class MainActivity extends AppCompatActivity {

    String id;
    String  bj;
    EditText textID;
    TextView textviewBJ;
    GetBJ gbj = new GetBJ();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button buttonSend;
        Button buttonID;
        EditText textPhoneNo;
        EditText textSMS;
        ActionBar ab;



        textID = (EditText) findViewById(R.id.editTextID);
        buttonID = (Button) findViewById(R.id.sendID);
        textviewBJ = (TextView) findViewById(R.id.textviewBJ);

        //추가했엉
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

        //ID 버튼 클릭이벤트
        buttonID.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                //여기 아이디 넣으면 됨
                id = textID.getText().toString();
                new Description().execute();
                //
            }
        });

    }

// 무조건 크롤링 끝나면 뜸
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.menu1:
                Toast.makeText(this, "첫번째", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.menu2:
                Toast.makeText(this, "두번째", Toast.LENGTH_SHORT).show();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

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
                        Log.d("푼문제수",bj);
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
