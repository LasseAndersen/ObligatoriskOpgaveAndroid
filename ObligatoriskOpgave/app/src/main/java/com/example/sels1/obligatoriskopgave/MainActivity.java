package com.example.sels1.obligatoriskopgave;

import android.content.Intent;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {


   String user;
    private GestureDetectorCompat gestureDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gestureDetector = new GestureDetectorCompat(this,new MyGestureListener());
    }

    public void GoToLogin(View view){
        Intent intent = new Intent(getBaseContext(), LoginActivity.class);
        intent.putExtra("USERNAME", user);
        startActivity(intent);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event){
        this.gestureDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    private class MyGestureListener extends GestureDetector.SimpleOnGestureListener{
        private static final String DEBUG = "Gestures";

        @Override
        public boolean onFling(MotionEvent event, MotionEvent event2, float velocityX, float velocityY){
            Log.d(DEBUG,"onFling: " + event.toString() + event2.toString());

            boolean RightFling = event.getX() < event2.getX();
            Log.d("#FLING","Fling: " + RightFling);
            if (RightFling){
                Intent intent = new Intent(getBaseContext(), LoginActivity.class);
                intent.putExtra("USERNAME", user);
                startActivity(intent);
            }
            return true;
        }
    }
}
