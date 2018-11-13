package com.example.sels1.obligatoriskopgave;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseUser;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class DeleteSpecificReservation extends AppCompatActivity {

    FirebaseUser user;
    Reservation reservation;
    String uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_specific_reservation);
        user =(FirebaseUser) getIntent().getExtras().get("USER");
        reservation = (Reservation) getIntent().getExtras().get("RESERVATION");
        uri = "https://anbo-roomreservation.azurewebsites.net/api/reservations/";


    }
    private class DeleteReservationTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... strings) {

            String code = "";
            HttpUrl route = HttpUrl.parse(uri).newBuilder().addPathSegment(reservation.getId()).build();
            Request request = new Request.Builder().url(route).delete().build();

            try{
                OkHttpClient client = new OkHttpClient.Builder().build();
                Response response = client.newCall(request).execute();
                if (response.code() == 204 || response.code() == 200) code = "complete";
                else if (response.code() == 404) code = "not found";
                else code = String.valueOf(response.code());
                return code;
            }catch (IOException ex) {
                Log.e("Delete", ex.getMessage());
            }
            return null;

        }
        @Override
        protected void onPostExecute(String JsonString) {

            if (JsonString.equals("complete"))
            {
                Toast.makeText(DeleteSpecificReservation.this, "Reservation deleted", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getBaseContext(), DeleteReservationActivity.class);
                intent.putExtra("USER",user);
                startActivity(intent);
            }
            else if (JsonString.equals("not found"))
            {
                Toast.makeText(DeleteSpecificReservation.this, "Delete Failed, Nothing found", Toast.LENGTH_SHORT).show();
            }

            else{
                Toast.makeText(DeleteSpecificReservation.this,  JsonString, Toast.LENGTH_SHORT).show();
            }

        }
    }
}
