package com.example.buscadordehoteis.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.buscadordehoteis.R;

import static com.example.buscadordehoteis.service.metodosUtil.verificarLoginStatus;
import static com.example.buscadordehoteis.view.TelaLogin.loginStatusFile;

public class TelaPrincipal extends AppCompatActivity {
    Button btAvancar, btVoltar;
    ImageView ivPerfil;
    SharedPreferences loginStatusPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_principal);

        btAvancar = findViewById(R.id.bt_avancar_tela_principal);
        btVoltar = findViewById(R.id.bt_voltar_tela_principal);
        ivPerfil = findViewById(R.id.iv_perfil_tela_principal);

        loginStatusPreferences = this.getSharedPreferences(loginStatusFile, Context.MODE_PRIVATE);
        verificarLoginStatus(loginStatusPreferences, ivPerfil, TelaPrincipal.this);

        btAvancar.setOnClickListener(v -> {
            Intent telaHotel = new Intent(TelaPrincipal.this, ViewPagerActivityHotel.class);
            startActivity(telaHotel);
        });

        btVoltar.setOnClickListener(v -> {
            finish();
        });

    }
}