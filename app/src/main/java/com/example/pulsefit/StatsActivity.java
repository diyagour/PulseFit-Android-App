package com.example.pulsefit;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.*;

import java.util.ArrayList;

public class StatsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.activity_stats);

        BarChart chart = findViewById(R.id.barChart);

        ArrayList<BarEntry> entries = new ArrayList<>();
        entries.add(new BarEntry(1, 200));
        entries.add(new BarEntry(2, 450));
        entries.add(new BarEntry(3, 300));
        entries.add(new BarEntry(4, 600));
        entries.add(new BarEntry(5, 800));

        BarDataSet set = new BarDataSet(entries, "Weekly Steps");
        BarData data = new BarData(set);
        chart.setData(data);
        chart.invalidate();
    }
}
