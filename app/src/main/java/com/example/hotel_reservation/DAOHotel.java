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

public class DAOHotel {
    private FirebaseDatabase mDataBase;
    private DatabaseReference mReferenceHotel;
    private List<Hotel_Class> hotels = new ArrayList<>();
    private String UIDkey;
    public interface DataStatus_Hotel{
        void DataIsLoaded(List<Hotel_Class> hotels, List<String> keys);
        void DateIsInserted();
        void DataIsDeleted();
        void DataIsUpdated();
    }
    public DAOHotel()
    {
        mDataBase = FirebaseDatabase.getInstance();
        mReferenceHotel = mDataBase.getReference("Hotel");
        UIDkey = FirebaseAuth.getInstance().getCurrentUser().getUid();
    }
    public void readHotel(final DataStatus_Hotel dataStatus){
        mReferenceHotel.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                hotels.clear();
                List<String> keys = new ArrayList<>();
                for(DataSnapshot keyNods: snapshot.getChildren()){

                    Hotel_Class hotel = keyNods.getValue(Hotel_Class.class);
                    if(hotel.getManager_id().equals(UIDkey)){
                        keys.add(keyNods.getKey());
                        hotels.add(hotel);
                    }
                }
                dataStatus.DataIsLoaded(hotels,keys);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    public void readCustomer(final DataStatus_Hotel dataStatus){
        mReferenceHotel.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                hotels.clear();
                List<String> keys = new ArrayList<>();
                for(DataSnapshot keyNods: snapshot.getChildren()){
                    keys.add(keyNods.getKey());
                    Hotel_Class hotel = keyNods.getValue(Hotel_Class.class);
                    hotels.add(hotel);
                }
                dataStatus.DataIsLoaded(hotels,keys);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    public void addHotel(Hotel_Class hotel, final DataStatus_Hotel dataStatus){
        final String key = mReferenceHotel.push().getKey();
        mReferenceHotel.child(key).setValue(hotel)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        dataStatus.DateIsInserted();
                    }
                });
    }
    public void updateHotel(String key, Hotel_Class hotel, final DataStatus_Hotel dataStatus){
        mReferenceHotel.child(key).child("hotelContact").setValue(hotel.getHotelContact());
        mReferenceHotel.child(key).child("hotellocation").setValue(hotel.getHotellocation());
        mReferenceHotel.child(key).child("hotelname").setValue(hotel.getHotelname())
                .addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                dataStatus.DataIsUpdated();
            }
        });
    }
    public void deleteHotel(String key,final DataStatus_Hotel dataStatus){
        mReferenceHotel.child(key).setValue(null)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        dataStatus.DataIsDeleted();
                    }
                });
    }
}
