package com.example.androoidprojectclass.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.androoidprojectclass.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class CalisthenicsDetailsActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseDatabase database;
    private DatabaseReference usersRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calisthenics_details);

        // Initialize Firebase Auth and Realtime Database
        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        usersRef = database.getReference("users");

        // Retrieve the workout data from the intent
        String workoutName = getIntent().getStringExtra("workoutName");
        int workoutImage = getIntent().getIntExtra("workoutImage", 0);
        String workoutInstructions = getIntent().getStringExtra("workoutInstructions");
        int workoutSets = getIntent().getIntExtra("workoutSets", 0);
        int workoutReps = getIntent().getIntExtra("workoutReps", 0);
        String workoutDuration = getIntent().getStringExtra("workoutDuration");

        // Populate the views with the workout details
        ImageView imageView = findViewById(R.id.workout_image);
        imageView.setImageResource(workoutImage);

        TextView nameTextView = findViewById(R.id.workout_name);
        nameTextView.setText(workoutName);

        TextView durationTextView = findViewById(R.id.workout_duration);
        durationTextView.setText("Duration: " + workoutDuration);

        TextView instructionsTextView = findViewById(R.id.workout_instructions);
        instructionsTextView.setText(workoutInstructions);

        TextView setsTextView = findViewById(R.id.workout_sets);
        setsTextView.setText("Sets: " + workoutSets);

        TextView repsTextView = findViewById(R.id.workout_reps);
        repsTextView.setText("Reps: " + workoutReps);

        // Add the "Add to My Workouts" button
        Button addWorkoutButton = findViewById(R.id.add_workout_button);
        addWorkoutButton.setOnClickListener(v -> {
            saveWorkoutToDatabase(workoutName, workoutDuration, workoutImage, workoutInstructions, workoutSets, workoutReps);
        });

        // Add the "Go to My Profile" button
        Button goToProfileButton = findViewById(R.id.go_to_profile_button);
        goToProfileButton.setOnClickListener(v -> {
            navigateToUserProfile();
        });
    }

    private void saveWorkoutToDatabase(String name, String duration, int imageResource, String instructions, int sets, int reps) {
        // Get the current user's UID
        String userId = mAuth.getCurrentUser().getUid();

        // Get a reference to the user's "workouts" node
        DatabaseReference workoutsRef = usersRef.child(userId).child("workouts");

        // Create a new workout object
        Map<String, Object> newWorkout = new HashMap<>();
        newWorkout.put("name", name);
        newWorkout.put("duration", duration);
        newWorkout.put("imageResource", imageResource);
        newWorkout.put("instructions", instructions);
        newWorkout.put("sets", sets);
        newWorkout.put("reps", reps);

        // Get a new child reference for the workout
        DatabaseReference newWorkoutRef = workoutsRef.push();

        // Save the workout data to the new child node
        newWorkoutRef.setValue(newWorkout);

        Toast.makeText(this, "Workout added to your workouts!", Toast.LENGTH_SHORT).show();
    }

    private void navigateToUserProfile() {
        // Implement the logic to navigate to the user's profile screen
        // For example, you can start a new activity:
        Intent intent = new Intent(CalisthenicsDetailsActivity.this, UserProfileActivity.class);
        startActivity(intent);
    }
}