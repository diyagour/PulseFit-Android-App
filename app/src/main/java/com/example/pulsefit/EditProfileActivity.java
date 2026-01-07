package com.example.pulsefit;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class EditProfileActivity extends AppCompatActivity {

    EditText etAge, etHeight, etWeight, etStepsTarget;

    @Override
    protected void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.activity_edit_profile);

        etAge = findViewById(R.id.etAge);
        etHeight = findViewById(R.id.etHeight);
        etWeight = findViewById(R.id.etWeight);
        etStepsTarget = findViewById(R.id.etStepsTarget);
        Button btnSave = findViewById(R.id.btnSave);

        SharedPreferences sp = getSharedPreferences("profile", MODE_PRIVATE);

        // Load existing values
        etAge.setText(sp.getString("age", ""));
        etHeight.setText(sp.getString("height", ""));
        etWeight.setText(sp.getString("weight", ""));
        etStepsTarget.setText(sp.getString("steps_target", "8000"));

        btnSave.setOnClickListener(v -> {
            sp.edit()
                    .putString("age", etAge.getText().toString())
                    .putString("height", etHeight.getText().toString())
                    .putString("weight", etWeight.getText().toString())
                    .putString("steps_target", etStepsTarget.getText().toString())
                    .apply();
            finish();
        });
    }
}
