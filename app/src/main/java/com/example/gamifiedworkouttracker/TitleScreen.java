package com.example.gamifiedworkouttracker;

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
import androidx.room.Room;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


public class TitleScreen extends AppCompatActivity {

    // fields
    private WorkoutDao workoutDao;
    private WorkoutTracker workoutTracker;
    private final Executor dbExecutor = Executors.newSingleThreadExecutor();
    
    TextView textBox;
    Button pumpIron;
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

        AppDatabase db = Room.databaseBuilder(
                getApplicationContext(),
                AppDatabase.class,
                "workout-db"
        ).build();


        // our stuff
        workoutDao = db.workoutDao();
        initWidgets();
        loadTrackerData();
    }

    private void loadTrackerData() {
        dbExecutor.execute(() -> {
            WorkoutTracker saved = workoutDao.getTracker();
            if (saved != null) {
                workoutTracker = saved;
            } else {
                workoutTracker = new WorkoutTracker();
                workoutDao.saveTracker(workoutTracker);
            }
            
            runOnUiThread(this::updateUI);
        });
    }

    private void updateUI() {
        if (workoutTracker != null) {
            progressBar.setProgress(workoutTracker.getProgress());
            progressText.setText(String.valueOf(workoutTracker.getLevel()));
        }
    }
    private void initWidgets() {
        textBox = findViewById(R.id.textBox);
        pumpIron = findViewById(R.id.pumpIron);
        benchUp = findViewById(R.id.benchUp);
        gymImage = findViewById(R.id.gymImage);
        progressBar = findViewById(R.id.progressBar);
        progressBar.setMax(100);
        progressText = findViewById(R.id.progressText);


        pumpIron.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (workoutTracker == null) return;

                workoutTracker.doRep();
                updateUI();
                dbExecutor.execute(() -> {
                    workoutDao.saveTracker(workoutTracker);
                });

                benchUp.setVisibility(View.VISIBLE);
                gymImage.setVisibility(View.INVISIBLE);


                ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
                scheduler.schedule(() -> {
                    runOnUiThread(() -> {
                        benchUp.setVisibility(View.INVISIBLE);
                        gymImage.setVisibility(View.VISIBLE);
                    });
                }, 1, TimeUnit.SECONDS); // Reduced to 1s for better feel
                scheduler.shutdown();
            }
        });

    }

    public void updateProgress(int x) {
        progressBar.setProgress(x);
    }
}
