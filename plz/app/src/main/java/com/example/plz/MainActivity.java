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
<<<<<<< HEAD
=======
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
>>>>>>> ssm


public class MainActivity extends AppCompatActivity {

<<<<<<< HEAD
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(ContextCompat.checkSelfPermission(this,Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED){
            if(ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.SEND_SMS)){
=======
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
>>>>>>> ssm
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

<<<<<<< HEAD
=======

        //ID 버튼 클릭이벤트
        buttonID.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //입력한 값을 가져와 변수에 담는다
                Log.d("문제수 찾자", "찾자");
                GetBJ getBJ = new GetBJ();

                textviewBJ.setText(getBJ.getBJ(textID.getText().toString()));
            }
        });
>>>>>>> ssm
    }


}
