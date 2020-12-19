package com.example.buscadordehoteis.view.adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.buscadordehoteis.model.Hotel;
import com.example.buscadordehoteis.view.fragment.FragmentHotel;

import java.util.List;

public class PageViewAdapter extends FragmentStatePagerAdapter {

    int contador;
    List<Hotel> hotelList;

    public PageViewAdapter(FragmentManager fm, List<Hotel> hotelList) {
        super(fm);
        this.hotelList = hotelList;
        this.contador = hotelList.size();
    }

    @Override
    public Fragment getItem(int position) {
        return new FragmentHotel().newInstance(hotelList.get(position));
    }

    @Override
    public int getCount() {
        return contador;
    }
}
