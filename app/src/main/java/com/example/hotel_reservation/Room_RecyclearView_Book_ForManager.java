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

public class Room_RecyclearView_Book_ForManager {
    private Context mContext;
    private Room_RecyclearView_Book_ForManager.BookAdaptor_Manager mBook;

    public void setConfig(RecyclerView recyclerView, Context context, List<Room_Class_Customer> book_class, List<String> keys){
        mContext = context;
        mBook = new Room_RecyclearView_Book_ForManager.BookAdaptor_Manager(book_class, keys);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(mBook);
    }

    class BookItemView_Manager extends RecyclerView.ViewHolder{
        private TextView mHotel;
        private TextView mRoom;
        private TextView mPrice;
        private TextView mDate;
        private String key;
        private String  Date,Month,Year;

        public BookItemView_Manager(ViewGroup parent){
            super(LayoutInflater.from(mContext).
                    inflate(R.layout.item_room_list_customer, parent,false));
            mHotel = (TextView) itemView.findViewById(R.id.HotelName_Show);
            mRoom = (TextView) itemView.findViewById(R.id.RoomName_Show);
            mPrice = (TextView) itemView.findViewById(R.id.Price_Book_Show);
            mDate = (TextView) itemView.findViewById(R.id.Date_Show);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(mContext, Hotel_Book_ForManager.class);
                    intent.putExtra("key",key);
                    mContext.startActivity(intent);
                }
            });

        }
        public void bind(Room_Class_Customer book_class, String key){
            Date = book_class.getRoom_day();
            Month = book_class.getRoom_month();
            Year = book_class.getRoom_year();
            mHotel.setText(book_class.getRoom_hotel());
            mRoom.setText(book_class.getRoom_name());
            mPrice.setText(book_class.getRoom_price());
            mDate.setText(Month+" /"+Date+" /"+Year);
            this.key = key;
        }
    }
    class BookAdaptor_Manager extends RecyclerView.Adapter<Room_RecyclearView_Book_ForManager.BookItemView_Manager>{
        private List<Room_Class_Customer> mBookList;
        private  List<String> mkeys;

        public BookAdaptor_Manager(List<Room_Class_Customer> mBookList, List<String> mkeys) {
            this.mBookList = mBookList;
            this.mkeys = mkeys;
        }

        public BookAdaptor_Manager() {
            super();
        }


        @NonNull
        @Override
        public Room_RecyclearView_Book_ForManager.BookItemView_Manager onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new Room_RecyclearView_Book_ForManager.BookItemView_Manager(parent);
        }


        @Override
        public void onBindViewHolder(@NonNull Room_RecyclearView_Book_ForManager.BookItemView_Manager holder, int position) {
            holder.bind(mBookList.get(position),mkeys.get(position));
        }


        @Override
        public int getItemCount() {
            return mBookList.size();
        }
    }
}
