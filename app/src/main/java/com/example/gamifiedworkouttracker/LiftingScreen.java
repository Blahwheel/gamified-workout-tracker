package com.example.gamifiedworkouttracker;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class LiftingScreen extends AppCompatActivity {

    // fields

    ImageView exerciseImage;
    WorkoutTracker workoutTracker;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_lifting_screen);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // our stuff
        workoutTracker = WorkoutTracker.getInstance();
        initPicture();
        gameLoop();
    }

    private void initPicture() {
        exerciseImage = findViewById(R.id.imageView);
        String exerciseName = workoutTracker.getCurrentExercise();

        switch (exerciseName) {
            case "bench_press":
                exerciseImage.setImageResource(R.drawable.bench);
                break;
            case "shoulder_press":
                exerciseImage.setImageResource(R.drawable.shoulder_press);
                break;
            case "cable_row":
                exerciseImage.setImageResource(R.drawable.cable_row);
                break;
            default:
                exerciseImage.setImageResource(R.drawable.gym);
                break;
        }
    }
    private void gameLoop(){
        
    }


}