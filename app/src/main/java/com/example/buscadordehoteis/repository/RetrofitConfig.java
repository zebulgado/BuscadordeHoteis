package com.example.buscadordehoteis.repository;

import com.example.buscadordehoteis.service.HotelService;

import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class RetrofitConfig {

    private Retrofit retrofit;

    public RetrofitConfig() {

        this.retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2/")
                .addConverterFactory(JacksonConverterFactory.create())
                .build();
    }

    public HotelService getHotelService(){
        return retrofit.create(HotelService.class);
    }
}