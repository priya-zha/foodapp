package com.example.food;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class DetailsActivity extends AppCompatActivity {
    TextView textViewDescription,textViewDate,textViewLocation,textViewAvailableUntil,textViewQuantity,textViewAllergen;
    String username;
    String email;
    String untid;
    Button claim;
    TextView warning1, warning2, warning3;
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.landing_page);
        textViewDescription = findViewById(R.id.textViewDescription);
        textViewDate = findViewById(R.id.textViewDate);
        textViewLocation = findViewById(R.id.textViewLocation);
        textViewAvailableUntil = findViewById(R.id.textViewAvailableUntil);
        textViewQuantity = findViewById(R.id.textViewQuantity);
        textViewAllergen = findViewById(R.id.warning1);
        claim= findViewById(R.id.claim);
        warning1 = findViewById(R.id.warning1);
        warning2 = findViewById(R.id.warning2);
        warning3 = findViewById(R.id.warning3);
        imageView = findViewById(R.id.foodItemImageView);


        FoodItem selectedFoodItem = (FoodItem) getIntent().getSerializableExtra("foodItem");
        username = getIntent().getStringExtra("username");
        email = getIntent().getStringExtra("email");
        untid = getIntent().getStringExtra("untid");



        // Use the selectedFoodItem to populate the UI or perform any other actions
        if (selectedFoodItem != null) {
            // Example: Display the description in a TextView
            if(selectedFoodItem.getDescription().equals("Burger")){
                imageView.setImageResource(R.drawable.burger);

            } else if (selectedFoodItem.getDescription().equals("Pizza")) {
                imageView.setImageResource(R.drawable.pizza);

            } else if (selectedFoodItem.getDescription().equals("Fries")) {
                imageView.setImageResource(R.drawable.fries);

            } else if (selectedFoodItem.getDescription().equals("Biryani")) {
                imageView.setImageResource(R.drawable.biryani);

            } else if (selectedFoodItem.getDescription().equals("chicken") || selectedFoodItem.getFoodDescription().equals("nuggets")) {
                imageView.setImageResource(R.drawable.chicken);
            }
            else{
                imageView.setImageResource(R.drawable.pizza);
            }

            textViewDescription.setText(selectedFoodItem.getDescription());
            textViewDate.setText( selectedFoodItem.getDate());
            textViewLocation.setText(selectedFoodItem.getLocation());
            textViewAvailableUntil.setText(selectedFoodItem.getAvailableUntil());
            textViewQuantity.setText("Quantity :"+ selectedFoodItem.getQuantity());
            textViewAllergen.setText(selectedFoodItem.getAllergen());



            // Add more TextViews or UI elements to display other details
        }
        else {
            // Handle the case where the FoodItem is null
            Toast.makeText(this, "No data available for selected item", Toast.LENGTH_SHORT).show();
        }
        if (username != null && username.equals(username)) {
            // The current user is the one who filled the form, hide the claim button

            claim.setVisibility(View.GONE);
        } else {
            // The current user is different, show the claim button
            claim.setVisibility(View.VISIBLE);
        }
    }
}