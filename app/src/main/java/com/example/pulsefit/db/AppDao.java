package com.example.pulsefit.db;

import androidx.room.*;
import java.util.List;

@Dao
public interface AppDao {

    @Insert
    void insertWorkout(WorkoutEntity w);

    @Query("SELECT * FROM workouts")
    List<WorkoutEntity> getWorkouts();

    @Delete
    void deleteWorkout(WorkoutEntity w);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void saveProfile(ProfileEntity p);

    @Query("SELECT * FROM profile WHERE id=1")
    ProfileEntity getProfile();
}
