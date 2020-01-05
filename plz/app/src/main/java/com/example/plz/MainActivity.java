package com.example.plz;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
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

    Button buttonSend;
    Button buttonID;
    EditText textPhoneNo;
    EditText textSMS;
    EditText textID;
    TextView textviewBJ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        final GetBJ gbj = new GetBJ();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonSend = (Button) findViewById(R.id.buttonSend);
        textPhoneNo = (EditText) findViewById(R.id.editTextPhoneNo);
        textSMS = (EditText) findViewById(R.id.editTextSMS);
        textID = (EditText) findViewById(R.id.editTextID);
        buttonID = (Button) findViewById(R.id.sendID);
        textviewBJ = (TextView) findViewById(R.id.textviewBJ);

        //추가했엉
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.SEND_SMS)) {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("정보");
                builder.setMessage("This app won't work properly unless you grant SMS");

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


        //
        new Description().execute();
        //



        //버튼 클릭이벤트
        buttonSend.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //입력한 값을 가져와 변수에 담는다
                String phoneNo = textPhoneNo.getText().toString();
                String sms = textSMS.getText().toString();

                try {
                    //전송
                    SmsManager smsManager = SmsManager.getDefault();
                    smsManager.sendTextMessage(phoneNo, null, sms, null, null);
                    Toast.makeText(getApplicationContext(), "전송 완료!", Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "SMS faild, please try again later!", Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }

            }
        });
    }
/*
        buttonID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //입력한 값을 가져와 변수에 담는다
                String ID = textID.getText().toString();

                Log.d("독", "트라이전");
                try{
                    Document doc = Jsoup.connect("https://movie.naver.com/movie/running/current.nhn").get();

                    Log.d("독", doc.toString());

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });*/

    private class Description extends AsyncTask<Void, Void, Void> {

        /*
        //진행바표시
        private ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            //진행다일로그 시작
            progressDialog = new ProgressDialog(MainActivity.this);
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDialog.setMessage("잠시 기다려 주세요.");
            progressDialog.show();

        }
*/

        @Override
        protected Void doInBackground(Void... params) {
            try {
                Document doc = Jsoup.connect("https://www.acmicpc.net/user/godgsds").get();
                Elements mElementDataSize = doc.select("td a"); //필요한 녀석만 꼬집어서 지정

                Log.d("엘레멘트", mElementDataSize+"");
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

}
