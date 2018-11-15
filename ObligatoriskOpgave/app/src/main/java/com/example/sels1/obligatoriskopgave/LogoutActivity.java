package com.example.sels1.obligatoriskopgave;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseUser;

public class LogoutActivity extends AppCompatActivity {

    String user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        user = (String) getIntent().getExtras().get("USERNAME");
        setContentView(R.layout.activity_logout);

        SharedPreferences sharedPreference = getSharedPreferences("loginPref" ,Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPreference.edit();
        //editor.clear();
        editor.putString("EMAIL", null);
        editor.putString("PASSWORD", null);
        editor.apply();

        user = null;
        Intent intent = new Intent(getBaseContext(), LoginActivity.class);
        intent.putExtra("USERNAME", user);
        Toast.makeText(this, "You have been signed out", Toast.LENGTH_SHORT).show();
        startActivity(intent);
    }
}
