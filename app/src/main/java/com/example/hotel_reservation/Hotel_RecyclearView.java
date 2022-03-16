package com.example.hotel_reservation;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Hotel_RecyclearView {
    private Context mContext;
    private Hotel_Adaptor mhotel_adaptor;
    public void setConfig(RecyclerView recyclerView, Context context, List<Hotel_Class> hotel_class, List<String> keys){
        mContext = context;
        mhotel_adaptor = new Hotel_Adaptor(hotel_class, keys);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(mhotel_adaptor);
    }
    class HotelItemView extends RecyclerView.ViewHolder{
        private TextView mName;
        private TextView mLocation;
        private TextView mContact;

        private  String key;

        public HotelItemView(ViewGroup parent){
            super(LayoutInflater.from(mContext)
                    .inflate(R.layout.item_hotel_list,parent,false));
            mName = (TextView) itemView.findViewById(R.id.HotelName);
            mLocation = (TextView) itemView.findViewById(R.id.Location);
            mContact = (TextView) itemView.findViewById(R.id.Contact);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(mContext, Hotel_Room_List_ForManager.class);
                    intent.putExtra("key",key);
                    intent.putExtra("Name",mName.getText().toString());
                    intent.putExtra("Location",mLocation.getText().toString());
                    intent.putExtra("Contact",mContact.getText().toString());
                    mContext.startActivity(intent);
                }
            });

        }

        public void bind(Hotel_Class hotel, String key){
            mName.setText(hotel.getHotelname());
            mLocation.setText(hotel.getHotellocation());
            mContact.setText(hotel.getHotelContact());
            this.key = key;
        }
    }
    class Hotel_Adaptor extends RecyclerView.Adapter<HotelItemView>{
        private List<Hotel_Class> mHotel_List;
        private  List<String> mkeys;

        public Hotel_Adaptor(List<Hotel_Class> mHotel_List, List<String> mkeys) {
            this.mHotel_List = mHotel_List;
            this.mkeys = mkeys;
        }

        public Hotel_Adaptor() {
            super();
        }



        @NonNull
        @Override
        public HotelItemView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new HotelItemView(parent);
        }


        @Override
        public void onBindViewHolder(@NonNull HotelItemView holder, int position) {
            holder.bind(mHotel_List.get(position),mkeys.get(position));
        }


        @Override
        public int getItemCount() {
            return mHotel_List.size();
        }
    }
}
