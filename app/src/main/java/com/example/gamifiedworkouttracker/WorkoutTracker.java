package com.example.gamifiedworkouttracker;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Tracker")
public class WorkoutTracker {
    @PrimaryKey
    private int id = 1; // Always use the same ID for the single tracker row
    private int repsDone;
    private int xp;
    private int level;
    private int threshold;

    public WorkoutTracker() {
        repsDone = 0;
        xp = 0;
        level = 0;
        threshold = 1000;
    }

    public void doRep() {
        repsDone++;
        grantXP();
    }

    private void grantXP() {
        xp += 70;

        if (xp >= threshold) {
            xp = xp - threshold;
            level++;
            threshold = (int) (1.3 * threshold);
        }
    }

    public int getProgress() {
        if (threshold == 0) return 0;
        return 100 * xp / threshold;
    }
    public int getLevel() {
        return level;
    }
}
