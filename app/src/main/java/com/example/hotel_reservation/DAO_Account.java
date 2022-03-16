package com.example.hotel_reservation;

import android.view.View;
import android.widget.Adapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

public class DAO_Account {
    private FirebaseDatabase mDataBase;
    private DatabaseReference mReferenceAccount;
    public interface DataStatus_Account{
        void DataIsLoaded(boolean exist);
        void DateIsInserted();
        void DataIsDeleted();
        void DataIsUpdated();
    }
    public DAO_Account()
    {
        mDataBase = FirebaseDatabase.getInstance();
        mReferenceAccount = mDataBase.getReference("Account");
    }
    public void readAccount(String key, String  type,final DataStatus_Account dataStatus){
        mReferenceAccount.child(type).child(key).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                dataStatus.DataIsLoaded(snapshot.exists());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    public void addAccount(String type,String key, final DataStatus_Account dataStatus){
        mReferenceAccount.child(type).child(key).setValue("Not yet")
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        dataStatus.DateIsInserted();
                    }
                });
    }
}
