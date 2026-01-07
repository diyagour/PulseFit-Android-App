package com.example.pulsefit.db;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "profile")
public class ProfileEntity {

    @PrimaryKey
    public int id = 1;

    public int age;
    public int height;
    public int weight;
}
