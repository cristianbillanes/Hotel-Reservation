package com.example.hotel_reservation;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Person_RecyclearView_Hotel {
    private Context mContext;
    private Person_RecyclearView_Hotel.PersonAdaptor_Hotel mpersonadaptor;
    private String bookkey;

    public void setConfig(String bookkey, RecyclerView recyclerView, Context context, List<Person_Class> person, List<String> keys){
        mContext = context;
        mpersonadaptor = new PersonAdaptor_Hotel(person, keys);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(mpersonadaptor);
        this.bookkey = bookkey;
    }

    class PersonItemView_Hotel extends RecyclerView.ViewHolder{

        private TextView mName,mAddress,mContact;
        private String key;

        public PersonItemView_Hotel(ViewGroup parent){
            super(LayoutInflater.from(mContext).
                    inflate(R.layout.item_person_list, parent,false));
            mName = (TextView) itemView.findViewById(R.id.Person_Name_Show);
            mAddress = (TextView) itemView.findViewById(R.id.Address_Show);
            mContact = (TextView) itemView.findViewById(R.id.Contact_Show);
        }
        public void bind(Person_Class person, String key){
            mName.setText(person.getName());
            mAddress.setText(person.getAddress());
            mContact.setText(person.getContact());
            this.key = key;
        }
    }
    class PersonAdaptor_Hotel extends RecyclerView.Adapter<Person_RecyclearView_Hotel.PersonItemView_Hotel>{
        private List<Person_Class> mpersonlist;
        private  List<String> mkeys;

        public PersonAdaptor_Hotel(List<Person_Class> mpersonlist, List<String> mkeys) {
            this.mpersonlist = mpersonlist;
            this.mkeys = mkeys;
        }

        public PersonAdaptor_Hotel() {
            super();
        }


        @NonNull
        @Override
        public PersonItemView_Hotel onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new PersonItemView_Hotel(parent);
        }


        @Override
        public void onBindViewHolder(@NonNull PersonItemView_Hotel holder, int position) {
            holder.bind(mpersonlist.get(position),mkeys.get(position));
        }


        @Override
        public int getItemCount() {
            return mpersonlist.size();
        }
    }
}
