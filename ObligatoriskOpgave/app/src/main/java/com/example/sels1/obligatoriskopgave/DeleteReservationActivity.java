package com.example.sels1.obligatoriskopgave;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseUser;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.text.MessageFormat;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class DeleteReservationActivity extends AppCompatActivity {

    String user;
    String Uri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_reservation);
        user =(String) getIntent().getExtras().get("USERNAME");


        String uri = MessageFormat.format("https://anbo-roomreservation.azurewebsites.net/api/reservations/user/{0}",user);
        Uri = uri;

    }

    @Override
    protected void onStart() {
        super.onStart();
        GetMyReservationsTask task = new GetMyReservationsTask();
        task.execute(Uri);
    }

    private class GetMyReservationsTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... strings) {
            Request.Builder build = new Request.Builder();
            String uri = strings[0];
            build.url(uri);
            Request request = build.build();
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
            final Reservation[] reservation = gson.fromJson(JsonString, Reservation[].class);
            ListView listView = findViewById(R.id.DeleteReservationListView);
            final ArrayAdapter<Reservation> adapter = new ArrayAdapter<>(getBaseContext(), android.R.layout.simple_list_item_1, reservation);
            listView.setAdapter(adapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent(getBaseContext(), DeleteSpecificReservation.class);
                    Reservation reservation = (Reservation) parent.getItemAtPosition(position);
                    intent.putExtra("RESERVATION",reservation);
                    intent.putExtra("USERNAME", user);
                    startActivity(intent);
                }
            });


        }
    }
}
