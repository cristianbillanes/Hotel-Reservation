package com.example.hotel_reservation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void Manager(View v){
        Intent intent = new Intent(this, Log_In_ForManager.class);
        startActivity(intent);
    }
    public void Customer(View v){
        Intent intent = new Intent(this, Log_In_forCustomer.class);
        startActivity(intent);
    }
}