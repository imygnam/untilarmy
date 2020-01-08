package com.example.plz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.telephony.SmsManager;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class sms_day extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public static String today(){
        Date currentTime = Calendar.getInstance().getTime();
        SimpleDateFormat dayFormat = new SimpleDateFormat("dd", Locale.getDefault());
        SimpleDateFormat monthFormat = new SimpleDateFormat("MM", Locale.getDefault());
        SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy", Locale.getDefault());

        String year = yearFormat.format(currentTime);
        String month = monthFormat.format(currentTime);
        String day = dayFormat.format(currentTime);

        return year + "|" + month + "|" + day;
    }

    public static void send(String num){
        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(num, null, "공부하시고 복받으시오", null, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static int days(String first, String last){
        int x[] = {0, 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        String s[] = first.split("|");
        String l[] = last.split("|");
        int a = Integer.parseInt(s[1]);
        int b = Integer.parseInt(l[1]);
        int i = Integer.parseInt(s[2]);
        int j = Integer.parseInt(l[2]);
        while(true){
            if(a == b) break;
            j = x[b - 1] + j;
            b--;
        }
        return j - i;
    }
}
