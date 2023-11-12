package com.example.food;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

public class landingpage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landingpage);

        TextView textViewGreeting = findViewById(R.id.textViewGreeting);
        TextView textViewUsername = findViewById(R.id.textViewUsername);
        TextView textViewEmail = findViewById(R.id.textViewEmail);
        TextView textViewUntid = findViewById(R.id.textViewUntid);
        Button event = findViewById(R.id.event);
        // Retrieve user details from the intent
        String username = getIntent().getStringExtra("username");
        String email = getIntent().getStringExtra("email");
        String untid = getIntent().getStringExtra("untid");


        // Display a greeting message along with user details in the UI
        String greetingMessage = "Hello " + username + "!";
        textViewGreeting.setText(greetingMessage);
        textViewUsername.setText("Username: " + username);
        textViewEmail.setText("Email: " + email);
        textViewUntid.setText("UNTid: " + untid);
        initializeDatabase();
        event.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopupForm();
            }
        });
            }
            private void initializeDatabase() {
                initialize_database dbHelper = new initialize_database(this);
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                // The tables will be created in the onCreate method of the initialize_database class
                // If the database already exists, this method does nothing.
                db.close();
            }
    private void showPopupForm() {
        // Inflate the popup_form_layout.xml
        View popupView = getLayoutInflater().inflate(R.layout.popup_form_layout, null);

        // Create a PopupWindow
        PopupWindow popupWindow = new PopupWindow(
                popupView,
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                true
        );

        // Set up UI components and handle button click inside the popup
        EditText editTextDescription = popupView.findViewById(R.id.editTextDescription);




        Button buttonSave = popupView.findViewById(R.id.buttonSave);

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle save button click
                String description = editTextDescription.getText().toString().trim();
                // Get other data from EditText fields

                // Save the data to the database
              //  saveEventDataToDatabase(description);

                // Dismiss the popup
                popupWindow.dismiss();
            }
        });

        // Show the popup at the center of the screen
        popupWindow.showAtLocation(popupView, Gravity.CENTER, 0, 0);
    }

    private void saveEventDataToDatabase(String description) {
        // Use your SQLite database helper class (initialize_database) to save the data
        initialize_database dbHelper = new initialize_database(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        // Implement the logic to insert data into the database tables
        // You need to add the necessary code to handle event_date, location, etc.

        // Example: Insert data into leftover_food_event table
        ContentValues eventValues = new ContentValues();
        eventValues.put("description", description);
        // Add more values for other columns
        long eventId = db.insert("leftover_food_event", null, eventValues);

        // If eventId is -1, the insertion was not successful
        if (eventId != -1) {
            showToast("Event saved successfully with ID: " + eventId);
        } else {
            showToast("Error saving event.");
        }

        // Close the database
        db.close();
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

}

