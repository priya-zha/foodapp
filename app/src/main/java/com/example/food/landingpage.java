package com.example.food;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class landingpage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landingpage);

        TextView textViewGreeting = findViewById(R.id.textViewGreeting);
        TextView textViewUsername = findViewById(R.id.textViewUsername);
        TextView textViewEmail = findViewById(R.id.textViewEmail);
        // Retrieve user details from the intent
        String username = getIntent().getStringExtra("username");
        String email = getIntent().getStringExtra("email");

        // Display a greeting message along with user details in the UI
        String greetingMessage = "Hello " + username + "!";
        textViewGreeting.setText(greetingMessage);
        textViewUsername.setText("Username: " + username);
        textViewEmail.setText("Email: " + email);

    }
}