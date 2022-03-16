package com.example.hotel_reservation;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class DAORoom_Customer {
    private FirebaseDatabase mDataBase;
    private DatabaseReference mReferenceroom_customer;
    private List<Room_Class_Customer> rooms = new ArrayList<>();
    private String UIDkey;
    public interface DataStatus_RoomCustomer{
        void DataIsLoaded(List<Room_Class_Customer> rooms, List<String> keys);
        void DateIsInserted();
        void DataIsDeleted();
        void DataIsUpdated();
    }
    public DAORoom_Customer()
    {
        mDataBase = FirebaseDatabase.getInstance();
        mReferenceroom_customer = mDataBase.getReference("Book");
        UIDkey = FirebaseAuth.getInstance().getCurrentUser().getUid();
    }
    public void readRoom_Customers(final DataStatus_RoomCustomer dataStatus){
        mReferenceroom_customer.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                rooms.clear();
                List<String> keys = new ArrayList<>();
                for(DataSnapshot keyNods: snapshot.getChildren()){

                    Room_Class_Customer room = keyNods.getValue(Room_Class_Customer.class);
                    if(UIDkey.equals(room.getRoom_uid())){
                        keys.add(keyNods.getKey());
                        rooms.add(room);
                    }

                }
                dataStatus.DataIsLoaded(rooms,keys);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    public void readRoom_Manager(final DataStatus_RoomCustomer dataStatus){
        mReferenceroom_customer.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                rooms.clear();
                List<String> keys = new ArrayList<>();
                for(DataSnapshot keyNods: snapshot.getChildren()){

                    Room_Class_Customer room = keyNods.getValue(Room_Class_Customer.class);
                    if(UIDkey.equals(room.getRoom_manageruid())){
                        keys.add(keyNods.getKey());
                        rooms.add(room);
                    }

                }
                dataStatus.DataIsLoaded(rooms,keys);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    public void addRoom_Customers(Room_Class_Customer room, final DataStatus_RoomCustomer dataStatus){
        final String key = mReferenceroom_customer.push().getKey();
        mReferenceroom_customer.child(key).setValue(room)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        dataStatus.DateIsInserted();
                    }
                });
    }
    public void updateRoom_Customers(String key, Room_Class_Customer room, final DataStatus_RoomCustomer dataStatus){
        mReferenceroom_customer.child(key).child("room_day").setValue(room.getRoom_day());
        mReferenceroom_customer.child(key).child("room_month").setValue(room.getRoom_month());
        mReferenceroom_customer.child(key).child("room_year").setValue(room.getRoom_year()).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                dataStatus.DataIsUpdated();
            }
        });
    }
    public void deleteRoom_Customers(String key,final DataStatus_RoomCustomer dataStatus){
        mReferenceroom_customer.child(key).setValue(null)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        dataStatus.DataIsDeleted();
                    }
                });
    }
}
