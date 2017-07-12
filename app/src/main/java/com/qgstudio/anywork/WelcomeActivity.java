package com.qgstudio.anywork;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;

import com.qgstudio.anywork.floginandsign.EnterActivity;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_welcome);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(WelcomeActivity.this, EnterActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.fade_in_center, R.anim.slide_out_left);
                finish();
                overridePendingTransition(R.anim.fade_in_center, R.anim.slide_out_left);
            }
        }, 0);
        //2000
    }
}
