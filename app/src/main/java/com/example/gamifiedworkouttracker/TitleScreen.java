package com.example.gamifiedworkouttracker;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


public class TitleScreen extends AppCompatActivity {

    // fields
    WorkoutTracker workoutTracker;
    TextView textBox;
    Button pumpIron;
    ImageView benchUp;
    ImageView gymImage;

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
        workoutTracker = new WorkoutTracker();
        initWidgets();

    }
    private void initWidgets() {
        textBox = findViewById(R.id.textBox);
        pumpIron = findViewById(R.id.pumpIron);
        benchUp = findViewById(R.id.benchUp);
        gymImage = findViewById(R.id.gymImage);

        pumpIron.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                workoutTracker.doRep();

                benchUp.setVisibility(View.VISIBLE);
                gymImage.setVisibility(View.INVISIBLE);


                ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
                scheduler.schedule(() -> {
                    benchUp.setVisibility(View.INVISIBLE);
                    gymImage.setVisibility(View.VISIBLE);
                }, 3, TimeUnit.SECONDS);

                scheduler.shutdown(); // Close when no longer needed
            }
        });

    }
}