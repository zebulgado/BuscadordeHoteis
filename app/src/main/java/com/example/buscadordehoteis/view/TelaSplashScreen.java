package com.example.buscadordehoteis.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.buscadordehoteis.R;

public class TelaSplashScreen extends AppCompatActivity {

    Button btButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_splash_screen);

        btButton = findViewById(R.id.bt_button);
        btButton.setOnClickListener(v -> {
            Intent telaPrincipal = new Intent(TelaSplashScreen.this, TelaPrincipal.class);
            startActivity(telaPrincipal);
        });


    }
}