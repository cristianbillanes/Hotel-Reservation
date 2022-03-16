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

public class Person_RecyclearView {
    private Context mContext;
    private PersonAdaptor mpersonadaptor;
    private String bookkey;

    public void setConfig(String bookkey,RecyclerView recyclerView, Context context, List<Person_Class> person, List<String> keys){
        mContext = context;
        mpersonadaptor = new PersonAdaptor(person, keys);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(mpersonadaptor);
        this.bookkey = bookkey;
    }

    class PersonItemView extends RecyclerView.ViewHolder{

        private TextView mName,mAddress,mContact;
        private String key;

        public PersonItemView(ViewGroup parent){
            super(LayoutInflater.from(mContext).
                    inflate(R.layout.item_person_list, parent,false));
            mName = (TextView) itemView.findViewById(R.id.Person_Name_Show);
            mAddress = (TextView) itemView.findViewById(R.id.Address_Show);
            mContact = (TextView) itemView.findViewById(R.id.Contact_Show);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(mContext, Person_Details.class);
                    intent.putExtra("key_person",key);
                    intent.putExtra("Bookkey", bookkey);
                    intent.putExtra("Name_Person",mName.getText().toString());
                    intent.putExtra("Address_Person",mAddress.getText().toString());
                    intent.putExtra("Contact_Person",mContact.getText().toString());
                    mContext.startActivity(intent);
                }
            });

        }
        public void bind(Person_Class person, String key){
            mName.setText(person.getName());
            mAddress.setText(person.getAddress());
            mContact.setText(person.getContact());
            this.key = key;
        }
    }
    class PersonAdaptor extends RecyclerView.Adapter<PersonItemView>{
        private List<Person_Class> mpersonlist;
        private  List<String> mkeys;

        public PersonAdaptor(List<Person_Class> mpersonlist, List<String> mkeys) {
            this.mpersonlist = mpersonlist;
            this.mkeys = mkeys;
        }

        public PersonAdaptor() {
            super();
        }


        @NonNull
        @Override
        public PersonItemView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new PersonItemView(parent);
        }


        @Override
        public void onBindViewHolder(@NonNull PersonItemView holder, int position) {
            holder.bind(mpersonlist.get(position),mkeys.get(position));
        }


        @Override
        public int getItemCount() {
            return mpersonlist.size();
        }
    }
}
