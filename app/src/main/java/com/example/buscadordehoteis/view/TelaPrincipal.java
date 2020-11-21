package com.example.buscadordehoteis.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.buscadordehoteis.R;

public class TelaPrincipal extends AppCompatActivity {
    Button btAvancar, btVoltar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_principal);

        btAvancar = findViewById(R.id.bt_avancar);
        btVoltar = findViewById(R.id.bt_voltar);

        btAvancar.setOnClickListener(v -> {
            Intent telaHotel = new Intent(TelaPrincipal.this, ViewPagerActivityHotel.class);
            startActivity(telaHotel);
        });

        btVoltar.setOnClickListener(v -> {
            finish();
        });

    }
}