package com.example.hotel_reservation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Calendar;

import com.google.firebase.auth.FirebaseAuth;

import java.util.List;

public class Add_Room_ForCustomer extends AppCompatActivity {
    private TextView hotel,room,price;
    private DatePicker datePicker;
    private String mHotelName,mRoomName,mPrice,mHotelID,mkey;
    private String UIDkey,ManagerUID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_room_for_customer);
        hotel = findViewById(R.id.textviewHotel);
        room = findViewById(R.id.textviewRoom);
        price = findViewById(R.id.textviewPrice);
        datePicker = findViewById(R.id.datePicker_room);
        mHotelName = getIntent().getStringExtra("HotelName");
        mRoomName = getIntent().getStringExtra("Name");
        mPrice = getIntent().getStringExtra("Price");
        mHotelID = getIntent().getStringExtra("Hotelid");
        mkey = getIntent().getStringExtra("key");
        ManagerUID = getIntent().getStringExtra("ManagerUID");
        UIDkey = FirebaseAuth.getInstance().getCurrentUser().getUid();
        hotel.setText("Hotel: "+mHotelName);
        room.setText("Room: "+mRoomName);
        price.setText("Price: "+mPrice);
        final Calendar c = Calendar.getInstance();
        int myear=c.get(Calendar.YEAR);
        int mmonth=c.get(Calendar.MONTH);
        int mday=c.get(Calendar.DAY_OF_MONTH);
        datePicker.updateDate(myear,mmonth,mday);
        datePicker.setMinDate(System.currentTimeMillis());
    }
    public void Add (View v) {
        Room_Class_Customer room = new Room_Class_Customer();
        room.setRoom_hotelid(mHotelID);
        room.setRoom_hotel(mHotelName);
        room.setRoom_name(mRoomName);
        room.setRoom_price(mPrice);
        room.setRoom_id(mkey);
        room.setRoom_uid(UIDkey);
        room.setRoom_manageruid(ManagerUID);
        room.setRoom_day(datePicker.getDayOfMonth()+"");
        room.setRoom_month(datePicker.getMonth()+"");
        room.setRoom_year(datePicker.getYear()+"");
        new DAORoom_Customer().addRoom_Customers(room, new DAORoom_Customer.DataStatus_RoomCustomer() {
            @Override
            public void DataIsLoaded(List<Room_Class_Customer> rooms, List<String> keys) {

            }

            @Override
            public void DateIsInserted() {
                Toast.makeText(Add_Room_ForCustomer.this, "The Room has been inserted successfuly", Toast.LENGTH_LONG).show();
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