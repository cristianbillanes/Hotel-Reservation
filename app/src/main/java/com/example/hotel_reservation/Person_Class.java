package com.example.hotel_reservation;

public class Person_Class {
    private String name;
    private String address;
    private String contact;
    private String vaccinated;
    public Person_Class(){}

    public Person_Class(String name, String address, String contact, String vaccinated) {
        this.name = name;
        this.address = address;
        this.contact = contact;
        this.vaccinated = vaccinated;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getVaccinated() {
        return vaccinated;
    }

    public void setVaccinated(String vaccinated) {
        this.vaccinated = vaccinated;
    }
}
