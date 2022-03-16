package com.example.hotel_reservation;

public class Room_Class_Customer {
    private String room_hotel;
    private String room_name;
    private String room_price;
    private String room_id;
    private String room_manageruid;
    private String room_uid;
    private String room_hotelid;
    private String room_day;
    private String room_month;
    private String room_year;

    public Room_Class_Customer(){}

    public Room_Class_Customer(String room_hotel, String room_name, String room_price, String room_id, String room_manageruid, String room_uid, String room_hotelid, String room_day, String room_month, String room_year) {
        this.room_hotel = room_hotel;
        this.room_name = room_name;
        this.room_price = room_price;
        this.room_id = room_id;
        this.room_manageruid = room_manageruid;
        this.room_uid = room_uid;
        this.room_hotelid = room_hotelid;
        this.room_day = room_day;
        this.room_month = room_month;
        this.room_year = room_year;
    }

    public String getRoom_hotel() {
        return room_hotel;
    }

    public void setRoom_hotel(String room_hotel) {
        this.room_hotel = room_hotel;
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

    public String getRoom_id() {
        return room_id;
    }

    public void setRoom_id(String room_id) {
        this.room_id = room_id;
    }

    public String getRoom_manageruid() {
        return room_manageruid;
    }

    public void setRoom_manageruid(String room_manageruid) {
        this.room_manageruid = room_manageruid;
    }

    public String getRoom_uid() {
        return room_uid;
    }

    public void setRoom_uid(String room_uid) {
        this.room_uid = room_uid;
    }

    public String getRoom_hotelid() {
        return room_hotelid;
    }

    public void setRoom_hotelid(String room_hotelid) {
        this.room_hotelid = room_hotelid;
    }

    public String getRoom_day() {
        return room_day;
    }

    public void setRoom_day(String room_day) {
        this.room_day = room_day;
    }

    public String getRoom_month() {
        return room_month;
    }

    public void setRoom_month(String room_month) {
        this.room_month = room_month;
    }

    public String getRoom_year() {
        return room_year;
    }

    public void setRoom_year(String room_year) {
        this.room_year = room_year;
    }
}
