package com.example.food;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.core.view.WindowCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.food.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;
    private MyDatabaseHelper dbHelper;
    private EditText editTextUsername, editTextEmail, editTextPassword, editUintId;
    TextView logintext;
    private Button buttonRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

                dbHelper = new MyDatabaseHelper(this);

                editTextUsername = findViewById(R.id.editTextUsername);
                editTextEmail = findViewById(R.id.editTextEmail);
                editTextPassword = findViewById(R.id.editTextPassword);
                editUintId = findViewById(R.id.editUintId);
                logintext = findViewById(R.id.logintext);

                buttonRegister = findViewById(R.id.buttonRegister);
                buttonRegister.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        registerUser();
                    }
                });

                logintext.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(MainActivity.this, login.class);
                        startActivity(intent);
                    }
                });
            }

    private void registerUser() {
        String username = editTextUsername.getText().toString().trim();
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
        String uint_id = editTextPassword.getText().toString().trim();

        if (!username.isEmpty() && !email.isEmpty() && !password.isEmpty()) {
            User newUser = new User(username, email, password,uint_id);
            long newRowId = dbHelper.insertUser(newUser);

            if (newRowId != -1) {
                showToast("Thank you for registering an account");
                // Clear input fields after successful registration
                editTextUsername.getText().clear();
                editTextEmail.getText().clear();
                editTextPassword.getText().clear();
                editUintId.getText().clear();

                Intent intent = new Intent(MainActivity.this, login.class);
                startActivity(intent);

            } else {
                showToast("Error registering user.");
            }
        } else {
            showToast("Please fill in all fields.");
        }
    }

    private void showToast(String message) {
                Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
            }
        }


