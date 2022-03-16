package com.example.hotel_reservation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class Hotel_details extends AppCompatActivity {
    private EditText Name,Location,Contact;
    String key = "1";
    private String mname,mlocation,mcontact,manager_id=" ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel_details);
        key = getIntent().getStringExtra("key");
        mname = getIntent().getStringExtra("Name");
        mlocation = getIntent().getStringExtra("Location");
        mcontact = getIntent().getStringExtra("Contact");
        Name = findViewById(R.id.Name);
        Location = findViewById(R.id.Location);
        Contact = findViewById(R.id.Contact);
        Name.setText(mname);
        Location.setText(mlocation);
        Contact.setText(mcontact);
    }
    public void Update(View v){
        Hotel_Class hotel = new Hotel_Class();
        hotel.setHotelname(Name.getText().toString());
        hotel.setHotellocation(Location.getText().toString());
        hotel.setHotelContact(Contact.getText().toString());
        hotel.setManager_id(manager_id);
        new DAOHotel().updateHotel(key,hotel, new DAOHotel.DataStatus_Hotel() {
            @Override
            public void DataIsLoaded(List<Hotel_Class> hotels, List<String> keys) {

            }

            @Override
            public void DateIsInserted() {
                }

            @Override
            public void DataIsUpdated() {
                Toast.makeText(Hotel_details.this,
                        "Hotel record has ben updated successfully",
                        Toast.LENGTH_LONG).show();
                finish();

            }

            @Override
            public void DataIsDeleted() {

            }
        });

    }
    public void Delete(View v){
        new DAOHotel().deleteHotel(key, new DAOHotel.DataStatus_Hotel() {
            @Override
            public void DataIsLoaded(List<Hotel_Class> hotels, List<String> keys) {

            }

            @Override
            public void DateIsInserted() {
            }

            @Override
            public void DataIsUpdated() {

            }

            @Override
            public void DataIsDeleted() {
                Toast.makeText(Hotel_details.this,
                        "Hotel record has ben deleted successfully",
                        Toast.LENGTH_LONG).show();
                finish();
            }
        });

    }
    public void Cancle(View v){
        finish();
    }
}