package com.example.sels1.obligatoriskopgave;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


import com.google.firebase.auth.FirebaseUser;

public class UserLogInActivity extends AppCompatActivity {

    FirebaseUser user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_log_in);
        user =(FirebaseUser) getIntent().getExtras().get("USER");
        //Toolbar toolbar = findViewById(R.id.LoginToolbar);
        //setSupportActionBar(toolbar);
}


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }


    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.logOut:
                Intent intent = new Intent(getBaseContext(), MainActivity.class);
                startActivity(intent);

        }
        return super.onOptionsItemSelected(item);
    }



    public void ViewReservations(View view){
        Intent intent = new Intent(this, SeeReservationsActivity.class);
        intent.putExtra("USER", user);
        startActivity(intent);
    }

    public void AddReservation(View v){
        Intent intent = new Intent(this, AvailableBuildings.class);
        intent.putExtra("USER", user);
        startActivity(intent);
    }
    public void DeleteReservation(View v){
        Intent intent = new Intent(this, DeleteReservationActivity.class);
        intent.putExtra("USER", user);
        startActivity(intent);
    }
}
