package com.example.gamifiedworkouttracker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WorkoutTracker {

    private static WorkoutTracker instance = null;
    private Map<String, ExerciseTracker> exercises;
    private int xp;
    private int level;
    private int threshold;
    private String currentExercise;

    public static WorkoutTracker getInstance() {
        if (instance == null) {
            instance = new WorkoutTracker();
        }
        return instance;
    }

    private WorkoutTracker() {
        exercises = new HashMap<>();
        xp = 0;
        level = 0;
        threshold = 1000;
    }

    public void doRep(String exerciseName, int weight) {
        if (!exercises.containsKey(exerciseName)) {
            exercises.put(exerciseName, new ExerciseTracker(exerciseName, 1.0));
        }
        ExerciseTracker exercise = exercises.get(exerciseName);
        int exp = exercise.addRep(weight);
        grantXP(exp);

    }
    public void addExercise(String exerciseName, double weightMultiplier) {
        exercises.put(exerciseName, new ExerciseTracker(exerciseName, weightMultiplier));
    }

    private void grantXP(int xpToGrant) {
        xp += xpToGrant;

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
    public String getCurrentExercise() {
        return currentExercise;
    }
    public void setCurrentExercise(String exercise) {
        currentExercise = exercise;
    }

}
