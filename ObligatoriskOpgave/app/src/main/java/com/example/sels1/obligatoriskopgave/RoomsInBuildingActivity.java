package com.example.sels1.obligatoriskopgave;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseUser;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class RoomsInBuildingActivity extends AppCompatActivity {


    String user;
    public static Buildings building;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rooms_in_building);
        user = (String) getIntent().getExtras().get("USERNAME");
        building = (Buildings) getIntent().getExtras().get("BUILDING");
        GetRoomTask task = new GetRoomTask();
        task.execute("https://anbo-roomreservation.azurewebsites.net/api/rooms");
    }
    private class GetRoomTask extends AsyncTask<String, Void, String>{
        int roomDay;
        int roomMonth;
        int roomYear;
        Room room;

        private void ActivityGo(){
            Intent intent = new Intent(getBaseContext(), ReservationInRoomsActivity.class);
            intent.putExtra("ROOM", room);
            intent.putExtra("DAY", roomDay);
            intent.putExtra("MONTH", roomMonth + 1);
            intent.putExtra("YEAR", roomYear);
            intent.putExtra("USERNAME",user);
            startActivity(intent);

        }
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
            final Room[] rooms = gson.fromJson(JsonString, Room[].class);

            ListView listView = findViewById(R.id.RoomIndBuildingListViewShowRooms);

            List<Room> SpecificRooms = new ArrayList<>();
            for (Room R:rooms) {
                if (R.getBuildingId() == building.getId())
                {
                    SpecificRooms.add(R);
                }

            }
            final ArrayAdapter<Room> roomAdapter = new ArrayAdapter<>(getBaseContext(), android.R.layout.simple_list_item_1, SpecificRooms);


            final Calendar roomCalendar = Calendar.getInstance();
            final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear,
                                      int dayOfMonth) {
                    // TODO Auto-generated method stub
                    roomCalendar.set(Calendar.YEAR, year);
                    roomCalendar.set(Calendar.MONTH, monthOfYear);
                    roomCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                    roomDay = dayOfMonth;
                    roomMonth = monthOfYear;
                    roomYear = year;

                    ActivityGo();


                }

            };
            listView.setAdapter(roomAdapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    new DatePickerDialog(RoomsInBuildingActivity.this, date, roomCalendar
                            .get(Calendar.YEAR), roomCalendar.get(Calendar.MONTH),
                            roomCalendar.get(Calendar.DAY_OF_MONTH)).show();
                    room = (Room) parent.getItemAtPosition(position);




                }
            });
        }
    }
}
