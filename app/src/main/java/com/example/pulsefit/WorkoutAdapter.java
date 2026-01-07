package com.example.pulsefit;

import android.view.*;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.pulsefit.db.WorkoutEntity;
import com.example.pulsefit.db.AppDatabase;
import java.util.List;

public class WorkoutAdapter extends RecyclerView.Adapter<WorkoutAdapter.VH> {

    List<WorkoutEntity> list;
    AppDatabase db;

    public WorkoutAdapter(List<WorkoutEntity> list, AppDatabase db) {
        this.list = list;
        this.db = db;
    }

    static class VH extends RecyclerView.ViewHolder {
        TextView t;
        VH(View v) {
            super(v);
            t = v.findViewById(android.R.id.text1);
        }
    }

    @Override
    public VH onCreateViewHolder(ViewGroup p, int v) {
        return new VH(LayoutInflater.from(p.getContext())
                .inflate(android.R.layout.simple_list_item_1, p, false));
    }

    @Override
    public void onBindViewHolder(VH h, int i) {
        h.t.setText(list.get(i).name);

        h.itemView.setOnLongClickListener(v -> {
            db.dao().deleteWorkout(list.get(i));
            list.remove(i);
            notifyDataSetChanged();
            return true;
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
