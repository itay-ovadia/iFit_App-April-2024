package com.example.androoidprojectclass.Activities;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.androoidprojectclass.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class HomePageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        // Initialize Firebase Realtime Database
        FirebaseDatabase database = FirebaseDatabase.getInstance("https://ifit-mobile-app-default-rtdb.firebaseio.com/");
        DatabaseReference usersRef = database.getReference("users");

        // Read data from the "users" node
        usersRef.child("user1").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String email = snapshot.child("email").getValue(String.class);
                String name = snapshot.child("name").getValue(String.class);
                String password = snapshot.child("password").getValue(String.class);

                // Print the user data to logcat
                Log.d(TAG, "User1 data:");
                Log.d(TAG, "Email: " + email);
                Log.d(TAG, "Name: " + name);
                Log.d(TAG, "Password: " + password);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Handle any errors
                Log.w(TAG, "Failed to read user data.", error.toException());
            }
        });

        /*add new user to firebase
        Map<String, Object> newUser = new HashMap<>();
        newUser.put("email", "newuser@example.com");
        newUser.put("name", "New User");
        newUser.put("password", "newpassword");
        usersRef.child("user3").setValue(newUser, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                if (error == null) {
                    Log.d(TAG, "##@#$!@#$!@$#NEWUSER added$$$$$@#$@#$@#$");
                } else {
                    Log.w(TAG, "Failed to add new user.", error.toException());
                }
            }
        });
*/
        // Initialize RecyclerView
        //  RecyclerView recyclerView = findViewById(R.id.recycler_view_workouts);
        //  recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Generate workout data
     //   List<Yoga> yogas = generateYogas();

        // Set up the adapter
    //    YogaAdapter adapter = new YogaAdapter(yogas);
        //  recyclerView.setAdapter(adapter);

        // Button to browse workouts
        Button browseYogasButton = findViewById(R.id.choose_BTN_yoga);
        browseYogasButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start a new activity to display all workouts
                Intent intent = new Intent(HomePageActivity.this, AllYogaActivity.class);
                startActivity(intent);
            }
        });
        Button browseCalisthenicsButton = findViewById(R.id.choose_BTN_calisthenics);
        browseCalisthenicsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start a new activity to display all food items
                Intent intent = new Intent(HomePageActivity.this, AllCalisthenicsActivity.class);
                startActivity(intent);
            }
        });
    }

}