package com.example.pulsefit;

import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

public class BmiActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.activity_bmi);

        EditText h = findViewById(R.id.etHeight);
        EditText w = findViewById(R.id.etWeight);
        TextView r = findViewById(R.id.txtResult);

        findViewById(R.id.btnCalc).setOnClickListener(v -> {
            float height = Float.parseFloat(h.getText().toString()) / 100;
            float weight = Float.parseFloat(w.getText().toString());
            float bmi = weight / (height * height);
            r.setText("BMI: " + bmi);
        });
    }
}
