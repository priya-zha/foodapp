package com.example.food;

import androidx.appcompat.app.AppCompatActivity;

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
                boolean loginSuccessful = dbHelper.checkLogin(email, password);

                if (loginSuccessful) {
                    showToast("Login successful");
                    // Navigate to the main activity or perform other actions on successful login
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

