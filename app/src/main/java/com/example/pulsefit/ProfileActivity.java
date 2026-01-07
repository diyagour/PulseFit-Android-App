package com.example.pulsefit;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.pulsefit.db.*;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.activity_profile);

        AppDatabase db = AppDatabase.get(this);
        ProfileEntity p = db.dao().getProfile();

        if (p != null) {
            ((TextView)findViewById(R.id.txtAge)).setText("Age: " + p.age);
            ((TextView)findViewById(R.id.txtHeight)).setText("Height: " + p.height);
            ((TextView)findViewById(R.id.txtWeight)).setText("Weight: " + p.weight);
        }

        findViewById(R.id.btnEdit).setOnClickListener(v ->
                startActivity(new android.content.Intent(this, EditProfileActivity.class)));
    }
}
