package com.example.pulsefit;

import android.content.Intent;
import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    EditText email, pass;
    SessionManager session;

    @Override
    protected void onCreate(Bundle b) {
        super.onCreate(b);

        session = new SessionManager(this);
        if (session.isLoggedIn()) {
            startActivity(new Intent(this, DashboardActivity.class));
            finish();
        }

        setContentView(R.layout.activity_login);

        email = findViewById(R.id.etEmail);
        pass = findViewById(R.id.etPassword);

        findViewById(R.id.btnLogin).setOnClickListener(v -> {
            if (email.getText().toString().isEmpty()) {
                email.setError("Required");
                return;
            }
            session.login(email.getText().toString());
            startActivity(new Intent(this, DashboardActivity.class));
            finish();
        });

        findViewById(R.id.txtRegister).setOnClickListener(v ->
                startActivity(new Intent(this, RegisterActivity.class)));
    }
}
