package com.example.sels1.obligatoriskopgave;

import android.content.Intent;
import android.icu.text.MessageFormat;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseUser;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ReservationInRoomsActivity extends AppCompatActivity {

    public static Room room;
    int day;
    int month;
    int year;
    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation_in_rooms);
        room = (Room) getIntent().getExtras().get("ROOM");
        day = (int) getIntent().getExtras().get("DAY");
        month = (int) getIntent().getExtras().get("MONTH");
        year = (int) getIntent().getExtras().get("YEAR");
        user = (FirebaseUser) getIntent().getExtras().get("USER");

        String stringDay = String.valueOf(day);
        String stringMonth = String.valueOf(month);
        String stringYears = String.valueOf(year);


        String dateSelected = stringYears+"-"+stringMonth+"-"+stringDay;

        TextView TextName = findViewById(R.id.ReservationInRoomTextViewShowsRoomName);
        TextName.setText(room.getName());
        String roomSelected = String.valueOf(room.getId());

        GetReservationTask task = new GetReservationTask();
        String uri = MessageFormat.format("https://anbo-roomreservation.azurewebsites.net/api/reservations/room/{0}/date/{1}",roomSelected,dateSelected);
        task.execute(uri);

    }
    private class GetReservationTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... strings) {
            Request.Builder builder = new Request.Builder();
            String uri = strings[0];
            builder.url(uri);
            Request request = builder.build();
            try {
                OkHttpClient client = new OkHttpClient.Builder().build();
                Response response = client.newCall(request).execute();
                String JsonString = response.body().string();
                Log.d("BUILDINGS", JsonString);
                return JsonString;
            } catch (IOException ex) {
                Log.e("BUILDINGS", ex.getMessage());
            }
            return null;

        }
        @Override
        protected void onPostExecute(String JsonString) {

            Gson gson = new GsonBuilder().create();
            final Reservation[] reservations = gson.fromJson(JsonString, Reservation[].class);
            ListView listView = findViewById(R.id.ReservationInRoomListViewShowsReservations);

            final ArrayAdapter<Reservation> adapter = new ArrayAdapter<>(getBaseContext(), android.R.layout.simple_list_item_1, reservations);

            listView.setAdapter(adapter);

            }
        }
    }


