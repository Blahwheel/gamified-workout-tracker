package com.example.gamifiedworkouttracker;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ProgressBar;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;


public class TitleScreen extends AppCompatActivity {

    // fields
    WorkoutTracker workoutTracker;
    TextView textBox;
    Button selectExercise;
    ImageView benchUp;
    ImageView gymImage;
    ProgressBar progressBar;
    TextView progressText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // system stuff
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_title_screen);
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
        textBox = findViewById(R.id.textBox);
        selectExercise = findViewById(R.id.selectExerciseButton);
        benchUp = findViewById(R.id.benchUp);
        gymImage = findViewById(R.id.gymImage);
        progressBar = findViewById(R.id.progressBar);
        progressBar.setMax(100);
        progressText = findViewById(R.id.progressText);


        selectExercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TitleScreen.this, ExerciseSelectionScreen.class);
                startActivity(intent);

//                workoutTracker.doRep();
//                int progress = workoutTracker.getProgress();
//                progressBar.setProgress(progress);
//                String level = Integer.toString(workoutTracker.getLevel());
//                progressText.setText(level);
//
//                benchUp.setVisibility(View.VISIBLE);
//                gymImage.setVisibility(View.INVISIBLE);


//                ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
//                scheduler.schedule(() -> {
//                    benchUp.setVisibility(View.INVISIBLE);
//                    gymImage.setVisibility(View.VISIBLE);
//                }, 3, TimeUnit.SECONDS);
//
//                scheduler.shutdown(); // Close when no longer needed
            }
        });

    }

//    // REQUIRES: x <= max
//    public void updateProgress(int x) {
//        progressBar.setProgress(x);
//    }
}