package com.example.pulsefit.db;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "workouts")
public class WorkoutEntity {

    @PrimaryKey(autoGenerate = true)
    public int id;

    public String name;

    public WorkoutEntity(String name) {
        this.name = name;
    }
}

