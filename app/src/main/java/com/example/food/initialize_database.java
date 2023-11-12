package com.example.food;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class initialize_database extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "mydatabases";
    private static final int DATABASE_VERSION = 1;

    public initialize_database(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Define your database schema here
        String createTableQuery = "CREATE TABLE users (\n" +
                "    user_id INT PRIMARY KEY, -- this is the student id, or the employee id. Uniquely identifies users in our system\n" +
                "    FirstName VARCHAR(50),\n" +
                "    LastName VARCHAR(50),\n" +
                "    password VARCHAR(100),\n" +
                "    Department VARCHAR(100), \n" +
                "    designation VARCHAR(50), -- this field describes whether they're a teacher, staff, student etc. \n" +
                "    allergic_to VARCHAR(50), -- this field describes in csv what the users are allergic to.\n" +
                "    dietary_preferences VARCHAR(150), -- this fields has things like kosher, halal etc. \n" +
                "    conditional_prefernces VARCHAR(150) -- this has conditional preferences like if someone is sometimes vegetarian, and sometimes not. \n" +
                ");";
        db.execSQL(createTableQuery);

        createTableQuery = "CREATE TABLE leftover_food_event (\n" +
                "    event_id INT PRIMARY KEY, -- this is the id of the actual food distribution event \n" +
                "    initiator_user_id INT, -- this is the id of the person who creates the food distribution event\n" +
                "    description VARCHAR(255), -- self explanatory \n" +
                "    create_timestamp TIMESTAMP, -- when the entry was first created\n" +
                "    update_timestamp TIMESTAMP, -- this is triggered everytime the entry is updated\n" +
                "    event_date DATE, -- this is the date when the event takes place. \n" +
                "    location VARCHAR(100), -- location \n" +
                "    is_approved BOOLEAN, \n" +
                "    avaiable_until TIMESTAMP, -- the timestamp until when the event is avaiable \n" +
                "    event_food_detail_id INT -- this links to the food detail table \n" +
                ");";
        db.execSQL(createTableQuery);
        createTableQuery = "CREATE TABLE event_food_item (\n" +
                "    food_item_id INT PRIMARY KEY, -- this is the id of each event food item \n" +
                "    event_id INT , -- this is the id of the event which advertised that this food was available\n" +
                "    description VARCHAR(255), -- self explanatory \n" +
                "    quantity INT, -- this describes how many quantities of this food remains \n" +
                "    allergen VARCHAR(255), -- this describes in csv the major allergens that this might have\n" +
                "    create_timestamp TIMESTAMP, -- when the entry was first created\n" +
                "    update_timestamp TIMESTAMP -- this is triggered everytime the entry is updated\n" +
                ");";
        db.execSQL(createTableQuery);
        createTableQuery = "CREATE TABLE food_items (\n" +
                "    event_id INT PRIMARY KEY, -- this is the id of each event food item \n" +
                "    food_item_id INT , -- this is the id of the event which advertised that this food was available\n" +
                "    description VARCHAR(255), -- self explanatory \n" +
                "    date VARCHAR(255), -- this describes how many quantities of this food remains \n" +
                "    location VARCHAR(255), -- this describes in csv the major allergens that this might have\n" +
                "    availableUntil VARCHAR(255), -- when the entry was first created\n" +
                "    foodname VARCHAR(255), -- this is triggered everytime the entry is updated\n" +
                "    fdescription VARCHAR(255), -- this is triggered everytime the entry is updated\n" +
                "    quantity VARCHAR(255), -- this is triggered everytime the entry is updated\n" +
                "    allergen VARCHAR(255) -- when the entry was first created\n" +
                ");";
        db.execSQL(createTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Implement if you need to handle upgrades
    }
}