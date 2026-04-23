package com.example.gamifiedworkouttracker;

public class WorkoutTracker {

    private int repsDone;

    public WorkoutTracker() {
        repsDone = 0;
    }
    public int getReps() {
        return repsDone;
    }

    public void doRep() {
        repsDone++;
    }

}
