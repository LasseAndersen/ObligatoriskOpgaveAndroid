package com.example.sels1.obligatoriskopgave;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseUser;

import okhttp3.OkHttpClient;

public class AddReservationActivity extends AppCompatActivity {

    FirebaseUser user;
    String day;
    String month;
    String year;
    EditText fromTime;
    EditText toTime;
    EditText purpose;
    Room room;
    String Json;
    String uri;
    OkHttpClient okHttpClient = new OkHttpClient();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_reservation);
        uri = "https://anbo-roomreservation.azurewebsites.net/api/reservations";
        fromTime = findViewById(R.id.AddReservationEditTextFromTime);
        toTime = findViewById(R.id.AddReservationEditViewToTime);
        purpose = findViewById(R.id.AddReservationEditTextPurpose);
    }
}
