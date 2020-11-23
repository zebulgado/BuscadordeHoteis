package com.example.buscadordehoteis.service;

import com.example.buscadordehoteis.model.Guest;
import com.example.buscadordehoteis.model.Reservation;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ReservationService {

    @GET("reservation")
    Call<List<Reservation>> getAllReservation();

    @GET("reservation/{id}")
    Call<Reservation> getReservation(@Path("id") Long id);

    @POST("reservation")
    Call<Reservation> insertReservation(@Body Reservation reservation);

    @PUT("reservation")
    Call<Guest> updateReservation(@Body Reservation reservation);

    @DELETE("guest/{id}")
    Call<Void> deleteReservation(@Path("id") Long id);
}
