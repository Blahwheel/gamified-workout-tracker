package com.example.gamifiedworkouttracker;

import java.util.HashMap;
import java.util.Map;


public class ExerciseTracker {

    // eg: bench_press, dip, cable_row, shoulder_press
    private String exerciseName;
    private double weightMultiplier;
    private Map<Integer, Integer> weightRepsMap;

    ExerciseTracker(String exerciseName, double weightMultiplier) {
        this.exerciseName = exerciseName;
        this.weightMultiplier = weightMultiplier;
        this.weightRepsMap = new HashMap<>();
    }
    public String getName() {
        return exerciseName;
    }
    public double getWeightMultiplier() {
        return weightMultiplier;
    }
    public Map<Integer, Integer> getWeightRepsMap() {
        return weightRepsMap;
    }
    public int addRep(int weight) {
        if (!weightRepsMap.containsKey(weight)) {
            weightRepsMap.put(weight, 1);
        } else {
            weightRepsMap.put(weight, weightRepsMap.get(weight) + 1);
        }
        return (int) (weight * weightMultiplier);
    }
}
