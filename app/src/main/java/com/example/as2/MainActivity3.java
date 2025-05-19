package com.example.as2;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import com.google.android.material.button.MaterialButton;

public class MainActivity3 extends AppCompatActivity {

    private Button button;
    private Button button2;
    private Button button3;
    private Button button4;
    private Button button5;

    private Button logoutbtn;
    private Spinner spinnerFavoriteMeal;
    private Spinner spinnerFavouriteSeat;
    private Spinner spinnerNoti;
    private MaterialButton saveButton;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        spinnerFavoriteMeal = findViewById(R.id.spinnerFavoriteMeal);
        spinnerFavouriteSeat = findViewById(R.id.spinnerFavouriteSeat);
        spinnerNoti = findViewById(R.id.spinnernoti);
        saveButton = findViewById(R.id.savebtn);
        logoutbtn = findViewById(R.id.logoutbtn);

        sharedPreferences = getSharedPreferences("YourPrefs", Context.MODE_PRIVATE);

        setUpSpinners();
        loadSavedData();

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveData();

            }
        });

        logoutbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity();
            }
        });

        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity2();
            }
        });

        button2 = findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity4();
            }
        });

        button3 = findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity5();
            }
        });

        button4 = findViewById(R.id.button4);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity6();
            }
        });

        button5 = findViewById(R.id.button5);
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity7();
            }
        });
    }

    // Function to save data when Save Changes button is pressed
    private void saveData() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        String favoriteMeal = spinnerFavoriteMeal.getSelectedItem().toString();
        editor.putString("favoriteMeal", favoriteMeal);
        String favoriteSeat = spinnerFavouriteSeat.getSelectedItem().toString();
        editor.putString("favoriteSeat", favoriteSeat);
        editor.apply();
    }

    private void loadSavedData() {
        // Implementation for loading saved data
        String savedFavoriteMeal = sharedPreferences.getString("favoriteMeal", "");
        if (!savedFavoriteMeal.isEmpty()) {
            int spinnerPosition = getPositionInArray(savedFavoriteMeal, getResources().getStringArray(R.array.user_fav_food));
            spinnerFavoriteMeal.setSelection(spinnerPosition);
        }

        String savedFavoriteSeat = sharedPreferences.getString("favoriteSeat", "");
        if (!savedFavoriteSeat.isEmpty()) {
            int spinnerPosition = getPositionInArray(savedFavoriteSeat, getResources().getStringArray(R.array.seating_area_options));
            spinnerFavouriteSeat.setSelection(spinnerPosition);
        }
    }

    private int getPositionInArray(String value, String[] array) {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, array);
        return adapter.getPosition(value);
    }


    private void setUpSpinners() {
        // Implementation for setting up spinners
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.user_fav_food, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerFavoriteMeal.setAdapter(adapter);

        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,
                R.array.seating_area_options, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerFavouriteSeat.setAdapter(adapter1);

        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,
                R.array.notifications, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerNoti.setAdapter(adapter2);
    }

    public void openActivity2() {
        Intent intent = new Intent(this, MainActivity2.class);
        startActivity(intent);
    }

    public void openActivity4() {
        Intent intent = new Intent(this, MainActivity4.class);
        startActivity(intent);
    }

    public void openActivity5() {
        Intent intent = new Intent(this, MainActivity5.class);
        startActivity(intent);
    }

    public void openActivity6() {
        Intent intent = new Intent(this, MainActivity6.class);
        startActivity(intent);
    }

    public void openActivity7() {
        Intent intent = new Intent(this, MainActivity7.class);
        startActivity(intent);
    }

    public void openActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
