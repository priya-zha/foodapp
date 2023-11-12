package com.example.food;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class login extends AppCompatActivity {
    private MyDatabaseHelper dbHelper;
    private EditText editTextLoginEmail, editTextLoginPassword;
    private Button buttonLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);;

        dbHelper = new MyDatabaseHelper(this);

        editTextLoginEmail = findViewById(R.id.editTextLoginEmail);
        editTextLoginPassword = findViewById(R.id.editTextLoginPassword);

        buttonLogin = findViewById(R.id.buttonLogin);
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUser();
            }
        });
    }

    private void loginUser() {
        String email = editTextLoginEmail.getText().toString().trim();
        String password = editTextLoginPassword.getText().toString().trim();

        if (!email.isEmpty() && !password.isEmpty()) {
            User loginUser = new User("", email, password,"");
            boolean loginSuccessful = dbHelper.checkLogin(loginUser);

            if (loginSuccessful) {
                showToast("Login successful");
                // Retrieve user details from the database
                User loggedInUser = dbHelper.getUserByEmail(email);
                Intent intent = new Intent(login.this, landingpage.class);
                intent.putExtra("username", loggedInUser.getUsername());
                intent.putExtra("email", loggedInUser.getEmail());
                intent.putExtra("untid", loggedInUser.getUntid());
                startActivity(intent);
            } else {
                showToast("Invalid email or password");
            }
        } else {
            showToast("Please fill in all fields");
        }
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}