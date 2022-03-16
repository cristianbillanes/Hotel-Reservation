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

public class Room_RecyclearView_ForCustomer {
    private Context mContext;
    private Room_RecyclearView_ForCustomer.RoomAdaptor_Customer mRoom;
    private String Hotelid;
    private String HotelName;
    private String manageruid;

    public void setConfig(String manageruid,String HotelName,String Hotelid,RecyclerView recyclerView, Context context, List<Room_Class_Manager> room_class, List<String> keys){
        mContext = context;
        mRoom = new Room_RecyclearView_ForCustomer.RoomAdaptor_Customer(room_class, keys);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(mRoom);
        this.Hotelid = Hotelid;
        this.HotelName = HotelName;
        this.manageruid = manageruid;
    }

    class RoomItemView_Customer extends RecyclerView.ViewHolder{
        private TextView mDetail;
        private TextView mNumber;
        private TextView mPrice;
        private TextView mSpace;
        private TextView mName;

        private  String key;

        public RoomItemView_Customer(ViewGroup parent){
            super(LayoutInflater.from(mContext).
                    inflate(R.layout.item_room_list_manager, parent,false));
            mDetail = (TextView) itemView.findViewById(R.id.Detail_Show);
            mName = (TextView) itemView.findViewById(R.id.Name_Show);
            mNumber = (TextView) itemView.findViewById(R.id.ID_Show);
            mPrice = (TextView) itemView.findViewById(R.id.Price_Show);
            mSpace = (TextView) itemView.findViewById(R.id.Space_Show);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(mContext, Add_Room_ForCustomer.class);
                    intent.putExtra("key",key);
                    intent.putExtra("Hotelid",Hotelid);
                    intent.putExtra("HotelName",HotelName);
                    intent.putExtra("Name",mName.getText().toString());
                    intent.putExtra("Number",mNumber.getText().toString());
                    intent.putExtra("Price",mPrice.getText().toString());
                    intent.putExtra("Space",mSpace.getText().toString());
                    intent.putExtra("Detail",mDetail.getText().toString());
                    intent.putExtra("ManagerUID",manageruid);
                    mContext.startActivity(intent);
                }
            });

        }
        public void bind(Room_Class_Manager room_class, String key){
            mNumber.setText(room_class.getRoom_number());
            mName.setText(room_class.getRoom_name());
            mDetail.setText(room_class.getRoom_details());
            mPrice.setText(room_class.getRoom_price());
            mSpace.setText(room_class.getRoom_space());
            this.key = key;
        }
    }
    class RoomAdaptor_Customer extends RecyclerView.Adapter<Room_RecyclearView_ForCustomer.RoomItemView_Customer>{
        private List<Room_Class_Manager> mRoomList;
        private  List<String> mkeys;

        public RoomAdaptor_Customer(List<Room_Class_Manager> mRoomList, List<String> mkeys) {
            this.mRoomList = mRoomList;
            this.mkeys = mkeys;
        }

        public RoomAdaptor_Customer() {
            super();
        }


        @NonNull
        @Override
        public Room_RecyclearView_ForCustomer.RoomItemView_Customer onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new Room_RecyclearView_ForCustomer.RoomItemView_Customer(parent);
        }


        @Override
        public void onBindViewHolder(@NonNull Room_RecyclearView_ForCustomer.RoomItemView_Customer holder, int position) {
            holder.bind(mRoomList.get(position),mkeys.get(position));
        }


        @Override
        public int getItemCount() {
            return mRoomList.size();
        }
    }
}
