package com.example.pulsefit;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DashboardActivity extends AppCompatActivity {

    TextView txtEnergyStatus, btnLogout;
    EditText etReflection;
    Button btnLow, btnMedium, btnHigh;
    Button btnSaveReflection, btnLogMovement, btnBodyInsights;

    String todayEnergy = "";

    @Override
    protected void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.activity_dashboard);

        // Views
        txtEnergyStatus = findViewById(R.id.txtEnergyStatus);
        etReflection = findViewById(R.id.etReflection);
        btnLogout = findViewById(R.id.btnLogout);

        btnLow = findViewById(R.id.btnLow);
        btnMedium = findViewById(R.id.btnMedium);
        btnHigh = findViewById(R.id.btnHigh);

        btnSaveReflection = findViewById(R.id.btnSaveReflection);
        btnLogMovement = findViewById(R.id.btnLogMovement);
        btnBodyInsights = findViewById(R.id.btnBodyInsights);

        // 🔓 LOGOUT (TOP-RIGHT)
        btnLogout.setOnClickListener(v -> {
            // Clear session
            getSharedPreferences("session", MODE_PRIVATE)
                    .edit()
                    .clear()
                    .apply();

            // Clear back stack & go to Login
            Intent intent = new Intent(DashboardActivity.this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        });

        // LOAD ENERGY
        SharedPreferences spEnergy = getSharedPreferences("energy", MODE_PRIVATE);
        todayEnergy = spEnergy.getString("today_energy", "");

        if (!todayEnergy.isEmpty()) {
            txtEnergyStatus.setText("Energy today: " + todayEnergy);
        }

        btnLow.setOnClickListener(v -> saveEnergy("Low"));
        btnMedium.setOnClickListener(v -> saveEnergy("Medium"));
        btnHigh.setOnClickListener(v -> saveEnergy("High"));

        // LOAD REFLECTION
        SharedPreferences spJournal = getSharedPreferences("journal", MODE_PRIVATE);
        etReflection.setText(spJournal.getString("daily_note", ""));

        btnSaveReflection.setOnClickListener(v -> {
            v.startAnimation(
                    AnimationUtils.loadAnimation(this, android.R.anim.fade_in)
            );
            spJournal.edit()
                    .putString("daily_note", etReflection.getText().toString())
                    .apply();
        });

        // Log Gentle Movement
        btnLogMovement.setOnClickListener(v -> {
            startActivity(new Intent(
                    DashboardActivity.this,
                    StepTrackerActivity.class
            ));
        });

        // Body Insights
        btnBodyInsights.setOnClickListener(v -> {
            Intent i = new Intent(this, StatsActivity.class);
            i.putExtra("energy", todayEnergy);
            startActivity(i);
        });
    }

    private void saveEnergy(String level) {
        SharedPreferences.Editor editor =
                getSharedPreferences("energy", MODE_PRIVATE).edit();
        editor.putString("today_energy", level);
        editor.apply();

        todayEnergy = level;
        txtEnergyStatus.setText("Energy today: " + level);
    }
}
