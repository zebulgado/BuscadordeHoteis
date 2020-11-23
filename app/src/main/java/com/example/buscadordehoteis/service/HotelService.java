package com.example.buscadordehoteis.service;

import com.example.buscadordehoteis.model.Hotel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface HotelService {

    @GET("hotel")
    Call<List<Hotel>> getAllHotel();

    @GET("hotel")
    Call<Hotel> getHotel(String cnpj);

    @POST("hotel")
    void insert();

    @DELETE("hotel")
    void delete();
}