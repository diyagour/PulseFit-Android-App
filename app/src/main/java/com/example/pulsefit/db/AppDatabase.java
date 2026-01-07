package com.example.pulsefit.db;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {WorkoutEntity.class, ProfileEntity.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase db;

    public abstract AppDao dao();

    public static synchronized AppDatabase get(Context c) {
        if (db == null) {
            db = Room.databaseBuilder(c,
                            AppDatabase.class,
                            "pulsefit_db")
                    .allowMainThreadQueries()
                    .build();
        }
        return db;
    }
}
