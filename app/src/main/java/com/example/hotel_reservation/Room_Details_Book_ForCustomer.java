package com.example.hotel_reservation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import java.util.Calendar;
import java.util.List;

public class Room_Details_Book_ForCustomer extends AppCompatActivity {
    private TextView hotel,room,price;
    private DatePicker datePicker;
    private String mHotelName,mRoomName,mPrice,mkey;
    private int mDay,mMonth,mYear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_details_book_for_customer);
        hotel = findViewById(R.id.textviewHotel);
        room = findViewById(R.id.textviewRoom);
        price = findViewById(R.id.textviewPrice);
        datePicker = findViewById(R.id.datePicker_room);
        mHotelName = getIntent().getStringExtra("HotelName_Book");
        mRoomName = getIntent().getStringExtra("RoomName_Book");
        mPrice = getIntent().getStringExtra("Price_Book");
        mkey = getIntent().getStringExtra("key");
        mDay = Integer.parseInt(getIntent().getStringExtra("Date"));
        mMonth = Integer.parseInt(getIntent().getStringExtra("Month"));
        mYear = Integer.parseInt(getIntent().getStringExtra("Year"));
        hotel.setText("Hotel: "+mHotelName);
        room.setText("Room: "+mRoomName);
        price.setText("Price: "+mPrice);
        datePicker.updateDate(mYear,mMonth,mDay);
        datePicker.setMinDate(System.currentTimeMillis());
    }
    public void Update_Book(View v){
        Room_Class_Customer rooms_class = new Room_Class_Customer();
        rooms_class.setRoom_day(datePicker.getDayOfMonth()+"");
        rooms_class.setRoom_month(datePicker.getMonth()+"");
        rooms_class.setRoom_year(datePicker.getYear()+"");
        new DAORoom_Customer().updateRoom_Customers(mkey,rooms_class, new DAORoom_Customer.DataStatus_RoomCustomer() {
            @Override
            public void DataIsLoaded(List<Room_Class_Customer> rooms, List<String> keys) {

            }

            @Override
            public void DateIsInserted() {
            }

            @Override
            public void DataIsUpdated() {
                Toast.makeText(Room_Details_Book_ForCustomer.this,
                        "Updated successfully",
                        Toast.LENGTH_LONG).show();

            }

            @Override
            public void DataIsDeleted() {

            }
        });
    }
    public void Delete_Book(View v){
        new DAORoom_Customer().deleteRoom_Customers(mkey, new DAORoom_Customer.DataStatus_RoomCustomer() {
            @Override
            public void DataIsLoaded(List<Room_Class_Customer> rooms, List<String> keys) {

            }

            @Override
            public void DateIsInserted() {
            }

            @Override
            public void DataIsUpdated() {

            }

            @Override
            public void DataIsDeleted() {
                Toast.makeText(Room_Details_Book_ForCustomer.this,
                        "Deleted successfully",
                        Toast.LENGTH_LONG).show();
                finish();
            }
        });
    }
    public void Cancle(View v){
        finish();return;
    }
}