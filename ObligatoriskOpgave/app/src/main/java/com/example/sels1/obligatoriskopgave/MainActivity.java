package com.example.sels1.obligatoriskopgave;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {


   FirebaseUser user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void GoToLogin(View view){
        Intent intent = new Intent(getBaseContext(), UserLogInActivity.class);
        intent.putExtra("USER", user);
        startActivity(intent);
    }
}
