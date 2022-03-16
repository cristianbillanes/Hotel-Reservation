package com.example.hotel_reservation;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class DAO_Person {
    private FirebaseDatabase mDataBase;
    private DatabaseReference mReferenceStudent;
    private List<Person_Class> persons = new ArrayList<>();
    public interface DataStatus_Person{
        void DataIsLoaded(List<Person_Class> persons, List<String> keys);
        void DateIsInserted();
        void DataIsDeleted();
        void DataIsUpdated();
    }
    public DAO_Person()
    {
        mDataBase = FirebaseDatabase.getInstance();
        mReferenceStudent = mDataBase.getReference("Book");
    }
    public void readPerson(String Bookid,final DataStatus_Person dataStatus){
        mReferenceStudent.child(Bookid).child("Person").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                persons.clear();
                List<String> keys = new ArrayList<>();
                for(DataSnapshot keyNods: snapshot.getChildren()){
                    keys.add(keyNods.getKey());
                    Person_Class person = keyNods.getValue(Person_Class.class);
                    persons.add(person);
                }
                dataStatus.DataIsLoaded(persons,keys);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    public void addPerson(String Bookid,Person_Class person, final DataStatus_Person dataStatus){
        final String key = mReferenceStudent.push().getKey();
        mReferenceStudent.child(Bookid).child("Person").child(key).setValue(person)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        dataStatus.DateIsInserted();
                    }
                });
    }
    public void updatePerson(String Bookid,String key, Person_Class person, final DataStatus_Person dataStatus){
        mReferenceStudent.child(Bookid).child("Person").child(key).setValue(person).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                dataStatus.DataIsUpdated();
            }
        });
    }
    public void deletePerson(String Bookid,String key,final DataStatus_Person dataStatus){
        mReferenceStudent.child(Bookid).child("Person").child(key).setValue(null)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        dataStatus.DataIsDeleted();
                    }
                });
    }
}
