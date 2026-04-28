package com.example.gamifiedworkouttracker;
import androidx.room.Database;
import androidx.room.RoomDatabase;
@Database(entities = {WorkoutTracker.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract WorkoutDao workoutDao();
}
