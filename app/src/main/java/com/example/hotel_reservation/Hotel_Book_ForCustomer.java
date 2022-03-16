package com.example.hotel_reservation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.List;

public class Hotel_Book_ForCustomer extends AppCompatActivity {
    private RecyclerView mRecycleView;
    private String key;
    private String mHotelName_Book,mRoomName_Book,mPrice_Book;
    private String mDate,mMonth,mYear,mDMY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel_book_for_customer);
        mRecycleView = (RecyclerView) findViewById(R.id.RoomRecord_Manager);
        key = getIntent().getStringExtra("key");
        mHotelName_Book = getIntent().getStringExtra("HotelName_Book");
        mRoomName_Book = getIntent().getStringExtra("RoomName_Book");
        mPrice_Book = getIntent().getStringExtra("Price_Book");
        mDate = getIntent().getStringExtra("Date");
        mMonth = getIntent().getStringExtra("Month");
        mYear = getIntent().getStringExtra("Year");
        mDMY = getIntent().getStringExtra("DMY");
        new DAO_Person().readPerson(key, new DAO_Person.DataStatus_Person() {
            @Override
            public void DataIsLoaded(List<Person_Class> persons, List<String> keys) {
                new Person_RecyclearView().setConfig(key, mRecycleView, Hotel_Book_ForCustomer.this, persons, keys);
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
    public void Detail_Person(View v){
        Intent intent = new Intent(this, Room_Details_Book_ForCustomer.class);
        intent.putExtra("key",key);
        intent.putExtra("HotelName_Book",mHotelName_Book);
        intent.putExtra("RoomName_Book",mRoomName_Book);
        intent.putExtra("Price_Book",mPrice_Book);
        intent.putExtra("Date",mDate);
        intent.putExtra("Month",mMonth);
        intent.putExtra("Year",mYear);
        intent.putExtra("DMY",mDMY);
        startActivity(intent);
    }
    public void ADD_Person(View v){
        Intent intent = new Intent(this, Add_Person.class);
        intent.putExtra("Bookkey",key);
        startActivity(intent);
    }
}