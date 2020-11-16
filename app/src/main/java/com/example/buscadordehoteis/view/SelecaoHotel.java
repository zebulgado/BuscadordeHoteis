package com.example.buscadordehoteis.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;

import com.example.buscadordehoteis.R;

import org.json.JSONException;
import org.json.JSONObject;

public class SelecaoHotel extends AppCompatActivity {
    TextView tvNomeHotel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selecao_hotel);

        //requestData(BASE_URL);
    }
}