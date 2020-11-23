package com.example.buscadordehoteis.service;

import com.example.buscadordehoteis.model.Reservation;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ReservationService {

    @GET("reservation")
    Call<List<Reservation>> getAllReservation();

    @GET("reservation")
    Call<Reservation> getReservation(Long id);

    @POST("reservation")
    void insert();

    @DELETE("reservation")
    void delete();
}
