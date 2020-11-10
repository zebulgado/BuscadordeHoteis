package com.example.buscadordehoteis;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.buscadordehoteis.R;

public class TelaLogin extends AppCompatActivity {
    Button btEntrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_login);

        btEntrar = findViewById(R.id.bt_entrar);

        btEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent telaPrincipal = new Intent(TelaLogin.this, TelaPrincipal.class);
                startActivity(telaPrincipal);
            }
        });

    }
}