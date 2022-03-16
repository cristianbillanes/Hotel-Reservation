package com.example.hotel_reservation;

public class Room_Class_Manager {
    private String room_number;
    private String room_name;
    private String room_price;
    private String room_details;
    private String room_space;
    public Room_Class_Manager(){}

    public Room_Class_Manager(String room_number, String room_name, String room_price, String room_details, String room_space) {
        this.room_number = room_number;
        this.room_name = room_name;
        this.room_price = room_price;
        this.room_details = room_details;
        this.room_space = room_space;
    }

    public String getRoom_number() {
        return room_number;
    }

    public void setRoom_number(String room_number) {
        this.room_number = room_number;
    }

    public String getRoom_name() {
        return room_name;
    }

    public void setRoom_name(String room_name) {
        this.room_name = room_name;
    }

    public String getRoom_price() {
        return room_price;
    }

    public void setRoom_price(String room_price) {
        this.room_price = room_price;
    }

    public String getRoom_details() {
        return room_details;
    }

    public void setRoom_details(String room_details) {
        this.room_details = room_details;
    }

    public String getRoom_space() {
        return room_space;
    }

    public void setRoom_space(String room_space) {
        this.room_space = room_space;
    }
}
