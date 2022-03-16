package com.example.hotel_reservation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class Room_details_ForManager extends AppCompatActivity {
    private EditText RoomNumber,RoomName,Price,Space,Details;
    String key, id;
    private String mDetail,mNumber,mPrice,mSpace,mName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_details_for_manager);
        RoomName = findViewById(R.id.Room_Name);
        RoomNumber = findViewById(R.id.Room_Number);
        Price = findViewById(R.id.Room_Price);
        Space = findViewById(R.id.Room_Space);
        Details = findViewById(R.id.Room_Details);
        key = getIntent().getStringExtra("key");
        id = getIntent().getStringExtra("id");
        mDetail = getIntent().getStringExtra("Detail");
        mNumber = getIntent().getStringExtra("Number");
        mPrice = getIntent().getStringExtra("Price");
        mSpace = getIntent().getStringExtra("Space");
        mName = getIntent().getStringExtra("Name");
        RoomNumber.setText(mNumber);
        Price.setText(mPrice);
        Space.setText(mSpace);
        Details.setText(mDetail);
        RoomName.setText(mName);
    }
    public void Update_Room_ForManager(View v){
        Room_Class_Manager rooms = new Room_Class_Manager();
        rooms.setRoom_details(Details.getText().toString());
        rooms.setRoom_space(Space.getText().toString());
        rooms.setRoom_price(Price.getText().toString());
        rooms.setRoom_number(RoomNumber.getText().toString());
        rooms.setRoom_name(RoomName.getText().toString());
        new DAORoom_Manager().updateRoom_Manager(id,key,rooms, new DAORoom_Manager.DataStatus_RoomManger() {
            @Override
            public void DataIsLoaded(List<Room_Class_Manager> rooms, List<String> keys) {

            }

            @Override
            public void DateIsInserted() {
            }

            @Override
            public void DataIsUpdated() {
                Toast.makeText(Room_details_ForManager.this,
                        "Room record has ben updated successfully",
                        Toast.LENGTH_LONG).show();
                finish();

            }

            @Override
            public void DataIsDeleted() {

            }
        });
    }
    public void Delete(View v){
        new DAORoom_Manager().deleteRoom_Manager(id,key, new DAORoom_Manager.DataStatus_RoomManger() {
            @Override
            public void DataIsLoaded(List<Room_Class_Manager> rooms, List<String> keys) {

            }

            @Override
            public void DateIsInserted() {
            }

            @Override
            public void DataIsUpdated() {

            }

            @Override
            public void DataIsDeleted() {
                Toast.makeText(Room_details_ForManager.this,
                        "Room record has ben deleted successfully",
                        Toast.LENGTH_LONG).show();
                finish();
            }
        });
    }
    public void Cancle(View v){
        finish();return;
    }
}