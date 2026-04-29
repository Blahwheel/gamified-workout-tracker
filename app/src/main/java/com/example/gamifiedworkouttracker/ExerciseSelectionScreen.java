package com.example.gamifiedworkouttracker;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ExerciseSelectionScreen extends AppCompatActivity {

    WorkoutTracker workoutTracker;
    TextView selectExercise;
    Button benchPress;
    Button shoulderPress;
    Button cableRow;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_exercise_selection_screen);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // our stuff
        workoutTracker = WorkoutTracker.getInstance();
        initWidgets();
    }

    private void initWidgets() {
        selectExercise = findViewById(R.id.selectExercise);
        benchPress = findViewById(R.id.benchPress);
        shoulderPress = findViewById(R.id.shoulderPress);
        cableRow = findViewById(R.id.cableRow);

        benchPress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                workoutTracker.addExercise("bench_press", 1.0);
                workoutTracker.setCurrentExercise("bench_press");
                Intent intent = new Intent(ExerciseSelectionScreen.this, LiftingScreen.class);
                startActivity(intent);
            }
            });
        shoulderPress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                workoutTracker.addExercise("shoulder_press", 3.0);
                workoutTracker.setCurrentExercise("shoulder_press");
                Intent intent = new Intent(ExerciseSelectionScreen.this, LiftingScreen.class);
                startActivity(intent);
            }
            });
        cableRow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                workoutTracker.addExercise("cable_row", 1.0);
                workoutTracker.setCurrentExercise("cable_row");
                Intent intent = new Intent(ExerciseSelectionScreen.this, LiftingScreen.class);
                startActivity(intent);
            }
            });
        }

}