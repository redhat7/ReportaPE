package com.example.capsula.reportape.presentation.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;

import com.example.capsula.reportape.R;
import com.example.capsula.reportape.data.repositories.local.SessionManager;

/**
 * Created by Desarrollo3 on 13/02/2017.
 */

public class LandingActivity extends AppCompatActivity {
    SessionManager sessionManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);

        new CountDownTimer(4000, 1000) { //4000 milli seconds is total time, 1000 milli seconds is time interval

            public void onTick(long millisUntilFinished) {
            }
            public void onFinish() {
                sessionManager = new SessionManager(getApplicationContext());
                if (sessionManager.getEmail() == null ){
                    Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                    startActivity(intent);
                    finish();
                }else {
                    Intent intent = new Intent(getApplicationContext(),ReportarActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        }.start();



    }
}
