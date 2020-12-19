package com.example.buscadordehoteis.model;

import java.util.List;

public class Hotel {



    private String cnpj;
    private String name;
    private String email;
    private String phone;
    private double rating;
    private Double regularWeekend;
    private Double loyaltyWeekend;
    private Double regularWeekday;
    private Double loyaltyWeekday;
    private String urlPhoto;
    private List<Reservation> reservation;

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public Double getRegularWeekend() {
        return regularWeekend;
    }

    public void setRegularWeekend(Double regularWeekend) {
        this.regularWeekend = regularWeekend;
    }

    public Double getLoyaltyWeekend() {
        return loyaltyWeekend;
    }

    public void setLoyaltyWeekend(Double loyaltyWeekend) {
        this.loyaltyWeekend = loyaltyWeekend;
    }

    public Double getRegularWeekday() {
        return regularWeekday;
    }

    public void setRegularWeekday(Double regularWeekday) {
        this.regularWeekday = regularWeekday;
    }

    public Double getLoyaltyWeekday() {
        return loyaltyWeekday;
    }

    public void setLoyaltyWeekday(Double loyaltyWeekday) {
        this.loyaltyWeekday = loyaltyWeekday;
    }

    public String getUrlPhoto() {
        return urlPhoto;
    }

    public void setUrlPhoto(String urlPhoto) {
        this.urlPhoto = urlPhoto;
    }

    public List<Reservation> getReservation() {
        return reservation;
    }

    public void setReservation(List<Reservation> reservation) {
        this.reservation = reservation;
    }

    public Hotel() {
    }
}