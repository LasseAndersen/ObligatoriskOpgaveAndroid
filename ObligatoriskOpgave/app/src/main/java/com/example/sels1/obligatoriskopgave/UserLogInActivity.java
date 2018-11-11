package com.example.sels1.obligatoriskopgave;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseUser;

public class UserLogInActivity extends AppCompatActivity {

    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_log_in);
        user =(FirebaseUser) getIntent().getExtras().get("USER");
    }

    public void ViewReservations(View view){
        Intent intent = new Intent(this, SeeReservationsActivity.class);
        intent.putExtra("USER", user);
        startActivity(intent);
    }

    public void AddReservation(View v){
        Intent intent = new Intent(this, AddReservationActivity.class);
        intent.putExtra("USER", user);
        startActivity(intent);
    }
    public void DeleteReservation(View v){
        Intent intent = new Intent(this, DeleteReservationActivity.class);
        intent.putExtra("USER", user);
        startActivity(intent);
    }
}
