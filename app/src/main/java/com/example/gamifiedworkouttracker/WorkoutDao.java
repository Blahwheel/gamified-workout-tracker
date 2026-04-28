package com.example.gamifiedworkouttracker;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface WorkoutDao {
    @Query("SELECT * FROM Tracker LIMIT 1")
    WorkoutTracker getTracker();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void saveTracker(WorkoutTracker tracker);

    @Update
    void updateTracker(WorkoutTracker tracker);
}
