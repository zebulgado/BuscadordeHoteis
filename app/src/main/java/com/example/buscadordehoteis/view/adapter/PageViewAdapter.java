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

        for (Hotel hotel: hotelList) {
            return new FragmentHotel();
        }
        return new FragmentHotel().newInstance(hotelList.get(0));
//
//        switch (position) {
//            case 0:
//            case 1:
//                return new BlankFragment2();
//            default:
//                return new BlankFragment2();
//        }
    }




    @Override
    public int getCount() {
        return contador;
    }
}
