package com.example.hotel_reservation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;

public class Add_Person extends AppCompatActivity {
    private EditText Name,Address,Contact;
    private RadioButton Vaccinated;
    String key;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_person);
        Name = findViewById(R.id.Person_Name);
        Address = findViewById(R.id.Person_Address);
        Contact = findViewById(R.id.Person_Contact);
        Vaccinated = findViewById(R.id.Radio_Vaccinted);
        key = getIntent().getStringExtra("key_person");
    }
    public void Add (View v) {
        Person_Class person = new Person_Class();
        person.setName(Name.getText().toString());
        person.setAddress(Address.getText().toString());
        person.setContact(Contact.getText().toString());
        if(Vaccinated.isChecked()) person.setVaccinated("True");
        else person.setVaccinated("False");
        String bookid = getIntent().getStringExtra("Bookkey");
        new DAO_Person().addPerson(bookid,person, new DAO_Person.DataStatus_Person() {
            @Override
            public void DataIsLoaded(List<Person_Class> persons, List<String> keys) {

            }

            @Override
            public void DateIsInserted() {
                Toast.makeText(Add_Person.this, "The inserted successfuly", Toast.LENGTH_LONG).show();
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