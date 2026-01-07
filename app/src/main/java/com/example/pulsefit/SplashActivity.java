package com.example.pulsefit;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler(Looper.getMainLooper()).postDelayed(() -> {

            SessionManager session = new SessionManager(this);

            if (session.isLoggedIn()) {
                startActivity(new Intent(this, DashboardActivity.class));
            } else {
                startActivity(new Intent(this, LoginActivity.class));
            }

            finish();

        }, 2000); // 2 seconds
    }
}
