package com.example.pulsefit;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;

public class StepTrackerActivity extends AppCompatActivity
        implements SensorEventListener {

    // ✅ Buttons
    Button btnAddSteps, btnLogout, btnAddWorkout, btnRemind;

    private SensorManager sensorManager;
    private Sensor stepSensor;

    private TextView txtSteps, txtGoal;
    private ProgressBar progressSteps;

    private int stepCount = 0;
    private final int DAILY_GOAL = 5000;

    // 🧩 FEATURE 2
    private int lastVibrationStep = 0;

    private static final int NOTIFICATION_PERMISSION_CODE = 201;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_tracker);

        txtSteps = findViewById(R.id.txtSteps);
        txtGoal = findViewById(R.id.txtGoal);
        progressSteps = findViewById(R.id.progressSteps);

        btnAddSteps = findViewById(R.id.btnAddSteps);
        btnLogout = findViewById(R.id.btnLogout);
        btnAddWorkout = findViewById(R.id.btnAddWorkout);
        btnRemind = findViewById(R.id.btnRemind);

        txtGoal.setText("Daily Goal: " + DAILY_GOAL + " steps");
        progressSteps.setMax(DAILY_GOAL);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        if (sensorManager != null) {
            stepSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
        }

        if (stepSensor == null) {
            Toast.makeText(this, "Step sensor not available", Toast.LENGTH_LONG).show();
        }

        // ✅ Manual Add Steps
        btnAddSteps.setOnClickListener(v -> {
            stepCount += 100;
            updateUI();
        });

        // 🧩 FEATURE 1: Reminder Notification (SAFE)
        btnRemind.setOnClickListener(v -> checkNotificationPermissionAndNotify());

        // ✅ Logout
        btnLogout.setOnClickListener(v -> {
            getSharedPreferences("session", MODE_PRIVATE)
                    .edit().clear().apply();
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        });

        // ✅ Add Workout
        btnAddWorkout.setOnClickListener(v ->
                startActivity(new Intent(this, WorkoutActivity.class)));
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (stepSensor != null) {
            sensorManager.registerListener(this, stepSensor, SensorManager.SENSOR_DELAY_UI);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (sensorManager != null) {
            sensorManager.unregisterListener(this);
        }
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        stepCount = (int) event.values[0];
        updateUI();
    }

    private void updateUI() {
        txtSteps.setText("Steps: " + stepCount);
        progressSteps.setProgress(stepCount);
        checkForVibration();
    }

    // 🧩 FEATURE 2: Vibrate every 1000 steps
    private void checkForVibration() {
        if (stepCount - lastVibrationStep >= 1000) {
            Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
            if (vibrator != null) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    vibrator.vibrate(
                            VibrationEffect.createOneShot(
                                    300,
                                    VibrationEffect.DEFAULT_AMPLITUDE
                            )
                    );
                } else {
                    vibrator.vibrate(300);
                }
            }
            lastVibrationStep = stepCount;
        }
    }

    // ✅ Android 13+ SAFE permission check
    private void checkNotificationPermissionAndNotify() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(
                    this,
                    android.Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED) {

                ActivityCompat.requestPermissions(
                        this,
                        new String[]{android.Manifest.permission.POST_NOTIFICATIONS},
                        NOTIFICATION_PERMISSION_CODE
                );
                return;
            }
        }
        sendReminderNotification();
    }

    // 🧩 FEATURE 1: FINAL Notification Method (YOUR VERSION, FIXED)
    private void sendReminderNotification() {

        String channelId = "pulsefit_reminder";

        NotificationManager manager =
                (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(
                    channelId,
                    "PulseFit Reminders",
                    NotificationManager.IMPORTANCE_DEFAULT
            );
            manager.createNotificationChannel(channel);
        }

        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(this, channelId)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle("PulseFit Reminder 🌸")
                        .setContentText("Time to move gently and care for your body.")
                        .setAutoCancel(true)
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        manager.notify(1001, builder.build());
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {}
}
