package com.example.hotel_reservation;

public class Hotel_Class {
    private String hotelname;
    private String hotellocation;
    private String hotelContact;
    private String manager_id;
    public Hotel_Class(){}
    public Hotel_Class(String hotelname, String hotellocation, String hotelContact, String manager_id) {
        this.hotelname = hotelname;
        this.hotellocation = hotellocation;
        this.hotelContact = hotelContact;
        this.manager_id = manager_id;
    }

    public String getHotelname() {
        return hotelname;
    }

    public void setHotelname(String hotelname) {
        this.hotelname = hotelname;
    }

    public String getHotellocation() {
        return hotellocation;
    }

    public void setHotellocation(String hotellocation) {
        this.hotellocation = hotellocation;
    }

    public String getHotelContact() {
        return hotelContact;
    }

    public void setHotelContact(String hotelContact) {
        this.hotelContact = hotelContact;
    }

    public String getManager_id() {
        return manager_id;
    }

    public void setManager_id(String manager_id) {
        this.manager_id = manager_id;
    }
}
