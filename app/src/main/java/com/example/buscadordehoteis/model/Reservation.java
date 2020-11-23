package com.example.buscadordehoteis.model;

import java.sql.Timestamp;

public class Reservation {
    private Long id;
    private Timestamp checkin;
    private Timestamp checkout;
    private String room;
    private Guest guest;
    private Hotel hotel;

    public Reservation() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Timestamp getCheckin() {
        return checkin;
    }

    public void setCheckin(Timestamp checkin) {
        this.checkin = checkin;
    }

    public Timestamp getCheckout() {
        return checkout;
    }

    public void setCheckout(Timestamp checkout) {
        this.checkout = checkout;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public Guest getGuest() {
        return guest;
    }

    public void setGuest(Guest guest) {
        this.guest = guest;
    }
}
