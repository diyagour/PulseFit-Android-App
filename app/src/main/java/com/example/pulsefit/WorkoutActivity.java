package com.example.pulsefit;

import android.os.Bundle;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.pulsefit.db.*;

import java.util.List;

public class WorkoutActivity extends AppCompatActivity {

    AppDatabase db;
    List<WorkoutEntity> list;

    @Override
    protected void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.activity_workout);

        db = AppDatabase.get(this);
        list = db.dao().getWorkouts();

        EditText et = findViewById(R.id.etWorkout);
        RecyclerView rv = findViewById(R.id.recycler);
        rv.setLayoutManager(new LinearLayoutManager(this));

        WorkoutAdapter adapter = new WorkoutAdapter(list, db);
        rv.setAdapter(adapter);

        findViewById(R.id.btnAdd).setOnClickListener(v -> {
            if (!et.getText().toString().isEmpty()) {
                db.dao().insertWorkout(new WorkoutEntity(et.getText().toString()));
                list.clear();
                list.addAll(db.dao().getWorkouts());
                adapter.notifyDataSetChanged();
                et.setText("");
            }
        });
    }
}
