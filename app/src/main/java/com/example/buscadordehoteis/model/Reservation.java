package com.example.buscadordehoteis.model;

import java.util.Date;

public class Reservation {
    private Long id;
    private Date checkin;
    private Date checkout;
    private Guest guest;
    private Hotel hotel;

    public Reservation() {
        super();
    }

    public Reservation(Date checkin, Date checkout, Guest guest, Hotel hotel, Long id) {
        this.checkin = checkin;
        this.checkout = checkout;
        this.guest = guest;
        this.hotel = hotel;
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCheckin() {
        return checkin;
    }

    public void setCheckin(Date checkin) {
        this.checkin = checkin;
    }

    public Date getCheckout() {
        return checkout;
    }

    public void setCheckout(Date checkout) {
        this.checkout = checkout;
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
