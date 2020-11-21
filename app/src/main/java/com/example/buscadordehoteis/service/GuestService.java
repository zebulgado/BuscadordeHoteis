package com.example.buscadordehoteis.service;

import com.example.buscadordehoteis.model.Guest;


import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface GuestService {
    @GET("guest")
    Call<Guest> getAllGuest();

    @GET("guest/{cpf}")
    Call<Guest> getGuest(@Path("cpf") String cpf);

    @POST("guest")
    Call<Guest> insertGuest(@Body Guest guest);

    @PUT("guest")
    Call<Guest> updateGuest(@Body Guest guest);

    @DELETE("guest/{cpf}")
    Call<Void> delete(@Path("cpf") String cpf);

    @GET("guest/{cpf}")
    Call<Boolean> checkLogin(@Path("cpf") String cpf,@Query("password") String password);
}
