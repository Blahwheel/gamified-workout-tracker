package com.example.gamifiedworkouttracker;
public class WorkoutTracker {

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
    public int getReps() {
        return repsDone;
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
        return 100 * xp/threshold;
    }
    public int getLevel() {
        return level;
    }

}
