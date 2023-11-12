package com.example.food;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class landingpage extends AppCompatActivity {
    ListView listView;
    Button event;
    TextView textViewGreeting;
    TextView textViewUsername;
    TextView textViewEmail;
    TextView textViewUntid;
    String username;
    String email;
    String untid;
    String admin = "0";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landingpage);

        textViewGreeting = findViewById(R.id.textViewGreeting);
        textViewUsername = findViewById(R.id.textViewUsername);
        textViewEmail = findViewById(R.id.textViewEmail);
        textViewUntid = findViewById(R.id.textViewUntid);
        event = findViewById(R.id.event);
        listView = findViewById(R.id.listViewFoodItems);

        // Retrieve user details from the intent
        username = getIntent().getStringExtra("username");
        email = getIntent().getStringExtra("email");
        untid = getIntent().getStringExtra("untid");

        // Display a greeting message along with user details in the UI
        String greetingMessage = "Hello " + username + "!";
        textViewGreeting.setText(greetingMessage);
        textViewUsername.setText("Username: " + username);
        textViewEmail.setText("Email: " + email);
        textViewUntid.setText("UNTid: " + untid);

        initializeDatabase();
        displayFoodItemsInListView();

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
        EditText editTextDate = popupView.findViewById(R.id.editTextDate);
        EditText editTextLocation = popupView.findViewById(R.id.editTextLocation);
        EditText editTextAvailableUntil = popupView.findViewById(R.id.editTextAvailableUntil);
        EditText foodname = popupView.findViewById(R.id.foodname);
        EditText description = popupView.findViewById(R.id.description);
        EditText quantity = popupView.findViewById(R.id.quantity);
        EditText allergy = popupView.findViewById(R.id.allergy);

        Button buttonSave = popupView.findViewById(R.id.buttonSave);

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle save button click
                String editTDescription = editTextDescription.getText().toString().trim();
                String editTDate = editTextDate.getText().toString().trim();
                String editTLocation = editTextLocation.getText().toString().trim();
                String editTAvailableUntil = editTextAvailableUntil.getText().toString().trim();
                String editTfoodname = foodname.getText().toString().trim();
                String editTdescription = description.getText().toString().trim();
                String editTquantity = quantity.getText().toString().trim();
                String editTallergy = allergy.getText().toString().trim();

                // Get other data from EditText fields

                // Save the data to the database
                saveEventDataToDatabase(editTDescription, editTDate, editTLocation, editTAvailableUntil,
                        editTfoodname, editTdescription, editTquantity, editTallergy);

                // Dismiss the popup
                popupWindow.dismiss();
                Toast.makeText(landingpage.this, editTDescription + editTDate + editTLocation, Toast.LENGTH_SHORT).show();
                admin = "1";
            }
        });

        // Show the popup at the center of the screen
        popupWindow.showAtLocation(popupView, Gravity.CENTER, 0, 0);
    }

    private void saveEventDataToDatabase(String description, String date, String location, String availableUntil,
                                         String foodName, String foodDescription, String foodQuantity, String foodAllergy) {
        // Use your SQLite database helper class (initialize_database) to save the data
        initialize_database dbHelper = new initialize_database(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        // Implement the logic to insert data into the database tables
        ContentValues foodItemsValues = new ContentValues();
        foodItemsValues.put("description", description);
        foodItemsValues.put("date", date);
        foodItemsValues.put("location", location);
        foodItemsValues.put("availableUntil", availableUntil);
        foodItemsValues.put("foodname", foodName);
        foodItemsValues.put("fdescription", foodDescription);
        foodItemsValues.put("quantity", foodQuantity);
        foodItemsValues.put("allergen", foodAllergy);

        long foodItemId = db.insert("food_items", null, foodItemsValues);

        // If foodItemId is -1, the insertion was not successful
        if (foodItemId != -1) {
            showToast("Food item saved successfully with ID: " + foodItemId);
        } else {
            showToast("Error saving food item.");
        }

        // Close the database
        db.close();
    }
    private void displayFoodItemsInListView() {
        // Retrieve food items from the database
        List<FoodItem> foodItemList = getAllFoodItemsFromDatabase();

        // Create an adapter
      //  ArrayAdapter<FoodItem> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, foodItemList);

        // Bind the adapter to the ListView
       // listView.setAdapter(adapter);
        // Retrieve food items from the database
       // List<FoodItem> foodItemList = getAllFoodItemsFromDatabase();

        // Create a custom adapter
        FoodItemAdapter adapter = new FoodItemAdapter(this, foodItemList);

        // Bind the adapter to the ListView
        listView.setAdapter(adapter);
        listView.setOnItemClickListener((parent, view, position, id) -> {
            FoodItem selectedFoodItem = (FoodItem) parent.getItemAtPosition(position);
            openDetailsActivity(selectedFoodItem);
        });
    }
    private void openDetailsActivity(FoodItem selectedFoodItem) {
        // Assuming you have a DetailsActivity class for showing more details
        Intent intent = new Intent(this, DetailsActivity.class);
        intent.putExtra("foodItem", selectedFoodItem);
        intent.putExtra("username",username );
        intent.putExtra("email",email);
        intent.putExtra("untid",untid );
        intent.putExtra("admin",admin );
        startActivity(intent);

    }
    @SuppressLint("Range")
    private List<FoodItem> getAllFoodItemsFromDatabase() {
        List<FoodItem> foodItemList = new ArrayList<>();

        initialize_database dbHelper = new initialize_database(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        // Define the columns you want to retrieve
        String[] projection = {
                "event_id",
                "food_item_id",
                "description",
                "date",
                "location",
                "availableUntil",
                "foodname",
                "fdescription",
                "quantity",
                "allergen"
        };

        // Query the database
        Cursor cursor = db.query("food_items", projection, null, null, null, null, null);

        // Process the cursor and populate the list
        if (cursor != null && cursor.moveToFirst()) {
            do {
                FoodItem foodItem = new FoodItem();
                foodItem.setEventId(cursor.getLong(cursor.getColumnIndex("event_id")));
                foodItem.setFoodItemId(cursor.getLong(cursor.getColumnIndex("food_item_id")));
                foodItem.setDescription(cursor.getString(cursor.getColumnIndex("description")));
                foodItem.setDate(cursor.getString(cursor.getColumnIndex("date")));
                foodItem.setLocation(cursor.getString(cursor.getColumnIndex("location")));
                foodItem.setAvailableUntil(cursor.getString(cursor.getColumnIndex("availableUntil")));
                foodItem.setFoodName(cursor.getString(cursor.getColumnIndex("foodname")));
                foodItem.setFoodDescription(cursor.getString(cursor.getColumnIndex("fdescription")));
                foodItem.setQuantity(cursor.getString(cursor.getColumnIndex("quantity")));
                foodItem.setAllergen(cursor.getString(cursor.getColumnIndex("allergen")));
                foodItemList.add(foodItem);
            } while (cursor.moveToNext());

            cursor.close();
        }

        // Close the database
        db.close();

        return foodItemList;
    }


    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
