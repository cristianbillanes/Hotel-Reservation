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

public class DAORoom_Manager {
    private FirebaseDatabase mDataBase;
    private DatabaseReference mReferenceRoomManager;
    private List<Room_Class_Manager> rooms = new ArrayList<>();
    public interface DataStatus_RoomManger{
        void DataIsLoaded(List<Room_Class_Manager> rooms, List<String> keys);
        void DateIsInserted();
        void DataIsDeleted();
        void DataIsUpdated();
    }
    public DAORoom_Manager()
    {
        mDataBase = FirebaseDatabase.getInstance();
        mReferenceRoomManager = mDataBase.getReference("Hotel");
    }
    public void readRoom_Manager(String id,final DataStatus_RoomManger dataStatus){
        mReferenceRoomManager.child(id).child("Room").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                rooms.clear();
                List<String> keys = new ArrayList<>();
                for(DataSnapshot keyNods: snapshot.getChildren()){
                    keys.add(keyNods.getKey());
                    Room_Class_Manager room = keyNods.getValue(Room_Class_Manager.class);
                    rooms.add(room);
                }
                dataStatus.DataIsLoaded(rooms,keys);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    public void addRoom_Manager(String id,Room_Class_Manager room, final DataStatus_RoomManger dataStatus){
        final String key = mReferenceRoomManager.push().getKey();
        mReferenceRoomManager.child(id).child("Room").child(key).setValue(room)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        dataStatus.DateIsInserted();
                    }
                });
    }
    public void updateRoom_Manager(String id,String key, Room_Class_Manager room, final DataStatus_RoomManger dataStatus){
        mReferenceRoomManager.child(id).child("Room").child(key).child("room_number").setValue(room.getRoom_number());
        mReferenceRoomManager.child(id).child("Room").child(key).child("room_name").setValue(room.getRoom_name());
        mReferenceRoomManager.child(id).child("Room").child(key).child("room_price").setValue(room.getRoom_price());
        mReferenceRoomManager.child(id).child("Room").child(key).child("room_space").setValue(room.getRoom_space());
        mReferenceRoomManager.child(id).child("Room").child(key).child("room_details").setValue(room.getRoom_details())
                .addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                dataStatus.DataIsUpdated();
            }
        });
    }
    public void deleteRoom_Manager(String id,String key,final DataStatus_RoomManger dataStatus){
        mReferenceRoomManager.child(id).child("Room").child(key).setValue(null)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        dataStatus.DataIsDeleted();
                    }
                });
    }
}
