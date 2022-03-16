package com.example.hotel_reservation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;

public class Add_Room_ForManager extends AppCompatActivity {
    private EditText RoomNumber,RoomName,Price,Space,Details;
    String key = "1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_room_for_manager);
        RoomName = findViewById(R.id.Room_Name);
        RoomNumber = findViewById(R.id.Room_Number);
        Price = findViewById(R.id.Room_Price);
        Space = findViewById(R.id.Room_Space);
        Details = findViewById(R.id.Room_Details);

    }
    public void Add_Room_ForManager (View v) {
        String id = getIntent().getStringExtra("key");
        Room_Class_Manager room = new Room_Class_Manager();
        room.setRoom_name(RoomName.getText().toString());
        room.setRoom_number(RoomNumber.getText().toString());
        room.setRoom_price(Price.getText().toString());
        room.setRoom_details(Details.getText().toString());
        room.setRoom_space(Space.getText().toString());
        new DAORoom_Manager().addRoom_Manager(id,room, new DAORoom_Manager.DataStatus_RoomManger() {
            @Override
            public void DataIsLoaded(List<Room_Class_Manager> rooms, List<String> keys) {

            }

            @Override
            public void DateIsInserted() {
                Toast.makeText(Add_Room_ForManager.this, "The Room has been inserted successfuly", Toast.LENGTH_LONG).show();
                finish();
            }

            @Override
            public void DataIsUpdated() {

            }

            @Override
            public void DataIsDeleted() {

            }
        });
    }
    public void Cancle(View v){ finish(); }
}