package com.example.as2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity4 extends AppCompatActivity {
    private Button button;
    private Button button2;
    private Button button3;
    private Button button4;
    private Button button5;

    private RequestQueue requestQueue;
    private Spinner spinnerSeatingArea, spinnerfood, spinnernoguests;
    private EditText Customername, Customerphone, Bookingdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);


        requestQueue = Volley.newRequestQueue(MainActivity4.this);


        spinnerSeatingArea = findViewById(R.id.spinnerSeatingArea);
        spinnerfood = findViewById(R.id.spinnerfood);
        spinnernoguests = findViewById(R.id.spinnnernoguests);
        Customername = findViewById(R.id.CustomerName);
        Customerphone = findViewById(R.id.CustomerPhone);
        Bookingdate = findViewById(R.id.Bookingdate);

        ArrayAdapter<CharSequence> seatingAreaAdapter = ArrayAdapter.createFromResource(this,
                R.array.seating_area_options, android.R.layout.simple_spinner_item);
        seatingAreaAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSeatingArea.setAdapter(seatingAreaAdapter);

        ArrayAdapter<CharSequence> foodAdapter = ArrayAdapter.createFromResource(this,
                R.array.food, android.R.layout.simple_spinner_item);
        foodAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerfood.setAdapter(foodAdapter);

        ArrayAdapter<CharSequence> guestAdapter = ArrayAdapter.createFromResource(this,
                R.array.guests, android.R.layout.simple_spinner_item);
        guestAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnernoguests.setAdapter(guestAdapter);


        Button bookNowButton = findViewById(R.id.bookNowButton);
        bookNowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bookNowClick();
            }
        });


        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity3();
            }
        });

        button2 = findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity2();
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

    private void bookNowClick() {
        String selectedseat = spinnerSeatingArea.getSelectedItem().toString();
        String selectedfood = spinnerfood.getSelectedItem().toString();
        String selectedguests = spinnernoguests.getSelectedItem().toString();
        String selectedname = Customername.getText().toString();
        String selectedphone = Customerphone.getText().toString();
        String selecteddate = Bookingdate.getText().toString();


        SharedPreferences sharedPreferences = getSharedPreferences("UserCredentials", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("seatingArea", selectedseat);
        editor.putString("meal", selectedfood);
        editor.putString("tableSize", selectedguests);
        editor.putString("date", selecteddate);
        editor.apply();


        JSONObject postData = new JSONObject();
        try {
            postData.put("customerName", selectedname);
            postData.put("customerPhoneNumber", selectedphone);
            postData.put("meal", selectedfood);
            postData.put("seatingArea", selectedseat);
            postData.put("tableSize", Integer.parseInt(selectedguests));
            postData.put("date", selecteddate);
        } catch (JSONException e) {
            e.printStackTrace();
        }



        new Thread(new Runnable() {
            @Override
            public void run() {
                sendDataToAPI(postData);
            }
        }).start();

    }

    private void sendDataToAPI(JSONObject postData) {
        String url = "https://web.socem.plymouth.ac.uk/COMP2000/ReservationApi/api/Reservations";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.POST,
                url,
                postData,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        System.out.println("API Response: " + response.toString());
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Handle the error
                        error.printStackTrace();
                    }
                });
        showToast("Booking information sent to API");
        requestQueue.add(jsonObjectRequest);
    }

    private void showToast(String message) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                // Your toast code here
            }
        });
    }


    public void openActivity2() {
        Intent intent = new Intent(this, MainActivity2.class);
        startActivity(intent);
    }

    public void openActivity3() {
        Intent intent = new Intent(this, MainActivity3.class);
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
}
