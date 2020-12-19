package com.example.buscadordehoteis.view.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.buscadordehoteis.R;
import com.example.buscadordehoteis.model.Hotel;
import com.example.buscadordehoteis.view.TelaPrincipal;
import com.example.buscadordehoteis.view.TelaReserva;
import com.example.buscadordehoteis.view.TelaSlidePageFragmentHotel;
import com.example.buscadordehoteis.view.ViewPagerActivityHotel;
import com.example.buscadordehoteis.view.adapter.PageViewAdapter;

import static com.example.buscadordehoteis.service.metodosUtil.reservarHotel;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentHotel#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentHotel extends Fragment {

    private static final String HOTEL_NOME = "param1";
    private static final String HOTEL_PRECO = "param2";
    private static final String HOTEL_CNPJ = "param3";
    public static final String RESERVAR_HOTEL_FILE = "RESERVAR_HOTEL";
    public static final String H_CNPJ = "HOTEL_CNPJ";
    private TextView tvNome, tvPreco;
    private Button btReservar;

    private String mHotelNome;
    private String mHotelPreco;
    private String mHotelCnpj;

    public FragmentHotel() {
        // Required empty public constructor
    }

    public static FragmentHotel newInstance(Hotel hotel) {
        FragmentHotel fragment = new FragmentHotel();
        Bundle args = new Bundle();
        args.putString(HOTEL_NOME, hotel.getName());
        args.putString(HOTEL_PRECO, hotel.getRegularWeekday().toString());
        args.putString(HOTEL_CNPJ, hotel.getCnpj());
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mHotelNome = getArguments().getString(HOTEL_NOME);
            mHotelPreco = getArguments().getString(HOTEL_PRECO);
            mHotelCnpj = getArguments().getString(HOTEL_CNPJ);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_hotel, container, false);
        tvNome = view.findViewById(R.id.tv_titulo_hotel_fragment_hotel_screen);
        tvPreco =  view.findViewById(R.id.tv_preco_hotel_fragment_hotel_screen);
        btReservar = view.findViewById(R.id.bt_reservar_fragment_hotel_screen);
        tvNome.setText(mHotelNome);
        tvPreco.setText(mHotelPreco);

        btReservar.setOnClickListener(v -> {
            Intent telaReserva = new Intent(getActivity(), TelaReserva.class);
            reservarHotel(mHotelCnpj = getArguments().getString(HOTEL_CNPJ), getActivity());
            startActivity(telaReserva);
        });

        return view;
    }
}