package com.example.buscadordehoteis.view.adapter;

import android.util.Log;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.buscadordehoteis.model.Hotel;
import com.example.buscadordehoteis.repository.RetrofitConfig;
import com.example.buscadordehoteis.view.TelaSlidePageFragmentHotel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PageViewAdapter extends FragmentStatePagerAdapter {

    int contador;

    public PageViewAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
//
//        switch (position) {
//            case 0:
                return new TelaSlidePageFragmentHotel(position+1);
//            case 1:
//                return new BlankFragment2();
//            default:
//                return new BlankFragment2();
//        }
    }



    @Override
    public int getCount() {
        RetrofitConfig retrofitConfig = new RetrofitConfig();
        Call<List<Hotel>> getAllRequestCall = retrofitConfig.getHotelService().getAllHotel();
        getAllRequestCall.enqueue(new Callback<List<Hotel>>() {
            @Override
            public void onResponse(Call<List<Hotel>> call, Response<List<Hotel>> response) {
                List<Hotel> hotels = response.body();
                contador = hotels.size();
            }

            @Override
            public void onFailure(Call<List<Hotel>> call, Throwable t) {
                Log.e("GuestService   ", "Erro ao buscar o guest:" + t.getMessage());
            }
        });
        return 3;
    }
}
