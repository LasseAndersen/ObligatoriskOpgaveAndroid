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

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class AvailableBuildings extends AppCompatActivity {

    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_available_buildings);
        user = (FirebaseUser) getIntent().getExtras().get("USER");
        GetBuildingsTask buildingTask = new GetBuildingsTask();
        buildingTask.execute("https://anbo-roomreservation.azurewebsites.net/api/buildings");
    }

    private class GetBuildingsTask extends AsyncTask<String, Void, String> {
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
            final Buildings[] Buildings = gson.fromJson(JsonString, Buildings[].class);

            ListView listView = findViewById(R.id.AvailableBuildingsListViewShowBuildings);

            final ArrayAdapter<Buildings> adapter = new ArrayAdapter<>(getBaseContext(), android.R.layout.simple_list_item_1, Buildings);

            listView.setAdapter(adapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent(getBaseContext(), AvailableRooms.class);
                    Buildings building = (Buildings) parent.getItemAtPosition(position);
                    intent.putExtra("BUILDING", building);
                    intent.putExtra("USER", user);
                    startActivity(intent);
                }
            });
        }
    }

}
