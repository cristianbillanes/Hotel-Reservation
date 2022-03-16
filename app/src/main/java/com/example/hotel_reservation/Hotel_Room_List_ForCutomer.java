package com.example.hotel_reservation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.List;

public class Hotel_Room_List_ForCutomer extends AppCompatActivity {
    private RecyclerView mRecycleView;
    private String key="1";
    private String mName,mLocation,mContact,managerid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel_room_list_for_cutomer);
        mRecycleView = (RecyclerView) findViewById(R.id.RoomRecord_Manager);
        key = getIntent().getStringExtra("key");
        mName = getIntent().getStringExtra("Name");
        mLocation = getIntent().getStringExtra("Location");
        mContact = getIntent().getStringExtra("Contact");
        managerid = getIntent().getStringExtra("managerid");
        new DAORoom_Manager().readRoom_Manager(key, new DAORoom_Manager.DataStatus_RoomManger() {
            @Override
            public void DataIsLoaded(List<Room_Class_Manager> rooms, List<String> keys) {
                new Room_RecyclearView_ForCustomer().setConfig(managerid,mName,key, mRecycleView, Hotel_Room_List_ForCutomer.this, rooms, keys);
            }

            @Override
            public void DateIsInserted() {

            }

            @Override
            public void DataIsUpdated() {

            }

            @Override
            public void DataIsDeleted() {

            }
        });
    }
    public void Back(View v){
    finish();return;
    }




}