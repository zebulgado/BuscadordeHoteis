package com.example.buscadordehoteis.model;

import java.util.Date;
import java.util.List;

public class Guest {
    private String cpf;
    private String name;
    private String email;
    private String password;
    private String phone;
    private Date birthDate;
    private Boolean isLoyalty;
    private List<Reservation> reservation;

    public Guest() {
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Boolean getIsLoyalty() {
        return isLoyalty;
    }

    public void setIsLoyalty(Boolean isLoyalty) {
        this.isLoyalty = isLoyalty;
    }

    public List<Reservation> getReservation() {
        return reservation;
    }

    public void setReservation(List<Reservation> reservation) {
        this.reservation = reservation;
    }
}
