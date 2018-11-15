package com.example.sels1.obligatoriskopgave;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseUser;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import okhttp3.OkHttpClient;

public class AddReservationActivity extends AppCompatActivity {

    String user;
    String day;
    String month;
    String year;
    String purpose;
    EditText fromTime;
    EditText toTime;
    EditText purposeEditText;
    Room room;
    String uri;
    String userId;
    String fromDateTime;
    String toDateTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_reservation);
        uri = "https://anbo-roomreservation.azurewebsites.net/api/reservations";
        user = (String) getIntent().getExtras().get("USERNAME");
        day = String.valueOf(getIntent().getExtras().get("DAY"));
        month = String.valueOf(getIntent().getExtras().get("MONTH"));
        year = String.valueOf(getIntent().getExtras().get("YEAR"));
        room = (Room) getIntent().getExtras().get("ROOM");
        fromTime = findViewById(R.id.AddReservationEditTextFromTime);
        toTime = findViewById(R.id.AddReservationEditViewToTime);
        purposeEditText = findViewById(R.id.AddReservationEditTextPurpose);


    }
    public void PostRes(View view) {
        try {
            JSONObject jsonObject = new JSONObject();
            String fromDateTime = month + "-" + day + "-" + year + " " + fromTime.getText().toString();
            String toDateTime = month + "-" + day + "-" + year + " " + toTime.getText().toString();
            String purpose = purposeEditText.getText().toString();
            jsonObject.put("fromTimeString", fromDateTime);
            jsonObject.put("toTimeString", toDateTime);
            //jsonObject.put("USERNAME", userId);
            jsonObject.put("userId", user);
            jsonObject.put("purpose", purpose);
            jsonObject.put("roomId", room.getId());

            String jsonDocument = jsonObject.toString();

            PostBookingTask task = new PostBookingTask();
            task.execute("https://anbo-roomreservation.azurewebsites.net/api/reservations", jsonDocument);

        } catch (JSONException ex) {
            Toast.makeText(this,ex.getMessage(),Toast.LENGTH_SHORT).show();
        }


        }
    private class PostBookingTask extends AsyncTask<String, Void, CharSequence> {


        @Override
        protected CharSequence doInBackground(String... params) {
            String urlString = params[0];
            String jsonDocument = params[1];
            try {
                URL url = new URL(urlString);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.setDoOutput(true);
                connection.setRequestProperty("Content-Type", "application/json");
                connection.setRequestProperty("Accept", "application/json");
                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(connection.getOutputStream());
                outputStreamWriter.write(jsonDocument);
                outputStreamWriter.flush();
                outputStreamWriter.close();
                int responseCode = connection.getResponseCode();
                if (responseCode / 100 != 2) {
                    String responseMessage = connection.getResponseMessage();
                    throw new IOException("HTTP response code: " + responseCode + " " + responseMessage);
                }
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(connection.getInputStream()));
                String line = reader.readLine();
                return line;
            } catch (MalformedURLException ex) {
                cancel(true);
                String message = ex.getMessage() + " " + urlString;
                Log.e("RES", message);
                return message;
            } catch (IOException ex) {
                cancel(true);
                Log.e("RES", ex.getMessage());
                return ex.getMessage();
            }
        }

        @Override
        protected void onPostExecute(CharSequence charSequence) {
            super.onPostExecute(charSequence);
            Toast.makeText(AddReservationActivity.this, "Post Success", Toast.LENGTH_SHORT).show();
            Log.d("POST WORKED", "YAY");
            Intent intent = new Intent( getBaseContext(), SeeReservationsActivity.class);
            intent.putExtra("USERNAME", userId);
            intent.putExtra("DAY", day);
            intent.putExtra("MONTH", month);
            intent.putExtra("YEAR", year);
            intent.putExtra("PURPOSE", purpose);
            intent.putExtra("ROOM", room);
            intent.putExtra("FROMTIME", fromDateTime);
            intent.putExtra("FROMTIME", toDateTime);
            startActivity(intent);
        }
    }
}
