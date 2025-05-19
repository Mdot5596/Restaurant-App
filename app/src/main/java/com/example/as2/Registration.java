package com.example.as2;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Registration extends AppCompatActivity {

    private static final String PREFS_NAME = "MyPrefs";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_PASSWORD = "password";

    private EditText registrationUsernameEditText;
    private EditText registrationPasswordEditText;
    private Button registrationButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        registrationUsernameEditText = findViewById(R.id.registrationUsernameEditText);
        registrationPasswordEditText = findViewById(R.id.registrationPasswordEditText);
        registrationButton = findViewById(R.id.registrationButton);

        registrationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String enteredUsername = registrationUsernameEditText.getText().toString();
                String enteredPassword = registrationPasswordEditText.getText().toString();

                if (!isUsernameExists(enteredUsername)) {

                    registerUser(enteredUsername, enteredPassword);
                    Toast.makeText(Registration.this, "Registration successful", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(Registration.this, "Username already exists", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private boolean isUsernameExists(String username) {
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        String storedUsername = prefs.getString(KEY_USERNAME, "");
        return storedUsername.equalsIgnoreCase(username);
    }

    private void registerUser(String enteredUsername, String enteredPassword) {
        SharedPreferences.Editor editor = getSharedPreferences(PREFS_NAME, MODE_PRIVATE).edit();
        editor.putString(KEY_USERNAME, enteredUsername);
        editor.putString(KEY_PASSWORD, enteredPassword);
        editor.apply();
    }
}

