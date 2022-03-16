package com.example.hotel_reservation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;

import java.util.List;

public class Customer_Home extends AppCompatActivity {
    private RecyclerView mRecycleView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_home);
        mRecycleView = (RecyclerView) findViewById(R.id.HotelRecord);
        Show_list();
    }
    public void Show_List_Home(View v){
        Show_list();
    }
    public void Show_List_Book(View v){
        show_List_Book();
    }
    private void Show_list(){
        new DAOHotel().readCustomer(new DAOHotel.DataStatus_Hotel() {
            @Override
            public void DataIsLoaded(List<Hotel_Class> hotels, List<String> keys) {
                new Hotel_RecylearView_ForCusomer().setConfig(mRecycleView,Customer_Home.this,hotels, keys);
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
    private void show_List_Book()
    {
        new DAORoom_Customer().readRoom_Customers(new DAORoom_Customer.DataStatus_RoomCustomer() {
            @Override
            public void DataIsLoaded(List<Room_Class_Customer> rooms, List<String> keys) {
                new Room_RecyclearView_Book_ForCustomer().setConfig(mRecycleView,Customer_Home.this,rooms, keys);
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
    public void back(View v){
        Intent intent = new Intent(Customer_Home.this,MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();return;

    }
}