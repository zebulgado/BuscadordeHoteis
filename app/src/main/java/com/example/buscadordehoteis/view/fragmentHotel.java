package com.example.buscadordehoteis.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.buscadordehoteis.R;
import com.example.buscadordehoteis.model.Hotel;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link fragmentHotel#newInstance} factory method to
 * create an instance of this fragment.
 */
public class fragmentHotel extends Fragment {

    private static final String HOTEL_NOME = "param1";
    private static final String HOTEL_PRECO = "param2";
    private TextView tvNome, tvPreco;

    private String mHotelNome;
    private String mHotelPreco;

    public fragmentHotel() {
        // Required empty public constructor
    }

    public static fragmentHotel newInstance(Hotel hotel) {
        fragmentHotel fragment = new fragmentHotel();
        Bundle args = new Bundle();
        args.putString(HOTEL_NOME, hotel.getName());
        args.putString(HOTEL_PRECO, hotel.getRegularWeekday().toString());
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mHotelNome = getArguments().getString(HOTEL_NOME);
            mHotelPreco = getArguments().getString(HOTEL_PRECO);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_hotel, container, false);
        tvNome = view.findViewById(R.id.tv_titulo_hotel_fragment_hotel_screen);
        tvPreco =  view.findViewById(R.id.tv_preco_hotel_fragment_hotel_screen);
        tvNome.setText(mHotelNome);
        tvPreco.setText(mHotelPreco);
        return view;
    }
}