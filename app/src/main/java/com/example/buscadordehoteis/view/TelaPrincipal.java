package com.example.buscadordehoteis.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.buscadordehoteis.R;

public class TelaPrincipal extends AppCompatActivity {
    Button btAvancar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_principal);

        btAvancar = findViewById(R.id.bt_avancar);
        btAvancar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent selecaoHotel = new Intent(TelaPrincipal.this, SelecaoHotel.class);
                startActivity(selecaoHotel);
            }
        });

    }
}