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

public class Room_RecyclearView_ForManager {
    private Context mContext;
    private RoomAdaptor mRoom;
    private String id;

    public void setConfig(String id,RecyclerView recyclerView, Context context, List<Room_Class_Manager> room_class, List<String> keys){
        mContext = context;
        mRoom = new RoomAdaptor(room_class, keys);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(mRoom);
        this.id = id;
    }

    class RoomItemView extends RecyclerView.ViewHolder{
        private TextView mDetail;
        private TextView mNumber;
        private TextView mPrice;
        private TextView mSpace;
        private TextView mName;

        private  String key;

        public RoomItemView(ViewGroup parent){
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
                    Intent intent = new Intent(mContext, Room_details_ForManager.class);
                    intent.putExtra("key",key);
                    intent.putExtra("id",id);
                    intent.putExtra("Name",mName.getText().toString());
                    intent.putExtra("Number",mNumber.getText().toString());
                    intent.putExtra("Price",mPrice.getText().toString());
                    intent.putExtra("Space",mSpace.getText().toString());
                    intent.putExtra("Detail",mDetail.getText().toString());
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
    class RoomAdaptor extends RecyclerView.Adapter<RoomItemView>{
        private List<Room_Class_Manager> mRoomList;
        private  List<String> mkeys;

        public RoomAdaptor(List<Room_Class_Manager> mRoomList, List<String> mkeys) {
            this.mRoomList = mRoomList;
            this.mkeys = mkeys;
        }

        public RoomAdaptor() {
            super();
        }


        @NonNull
        @Override
        public RoomItemView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new RoomItemView(parent);
        }


        @Override
        public void onBindViewHolder(@NonNull RoomItemView holder, int position) {
            holder.bind(mRoomList.get(position),mkeys.get(position));
        }


        @Override
        public int getItemCount() {
            return mRoomList.size();
        }
    }
}
