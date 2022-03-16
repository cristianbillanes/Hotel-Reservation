package com.example.hotel_reservation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import java.util.List;

public class Add_Hotel_ForManager extends AppCompatActivity {
    private EditText Name,Location,Contact;
    String key = "1";
    private String manager_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_hotel_for_manager);
        Name = findViewById(R.id.Name);
        Location = findViewById(R.id.Location);
        Contact = findViewById(R.id.Contact);
        manager_id = FirebaseAuth.getInstance().getCurrentUser().getUid();
    }
    public void Add (View v) {
        Hotel_Class hotel = new Hotel_Class();
        hotel.setHotelname(Name.getText().toString());
        hotel.setHotellocation(Location.getText().toString());
        hotel.setHotelContact(Contact.getText().toString());
        hotel.setManager_id(manager_id);
        new DAOHotel().addHotel(hotel, new DAOHotel.DataStatus_Hotel() {
            @Override
            public void DataIsLoaded(List<Hotel_Class> hotels, List<String> keys) {

            }

            @Override
            public void DateIsInserted() {
                Toast.makeText(Add_Hotel_ForManager.this, "The Hotel has been inserted successfuly", Toast.LENGTH_LONG).show();
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
    public void Cancle(View v){
        finish();
    }
}