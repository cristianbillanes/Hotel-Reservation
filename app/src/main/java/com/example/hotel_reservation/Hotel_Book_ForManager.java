package com.example.hotel_reservation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.List;

public class Hotel_Book_ForManager extends AppCompatActivity {
    private RecyclerView mRecycleView;
    private String key;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel_book_for_manager);
        mRecycleView = (RecyclerView) findViewById(R.id.RoomRecord_Manager);
        key = getIntent().getStringExtra("key");
        new DAO_Person().readPerson(key, new DAO_Person.DataStatus_Person() {
            @Override
            public void DataIsLoaded(List<Person_Class> persons, List<String> keys) {
                new Person_RecyclearView_Hotel().setConfig(key, mRecycleView, Hotel_Book_ForManager.this, persons, keys);
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