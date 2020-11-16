package com.example.buscadordehoteis;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TelaSplashScreen extends AppCompatActivity {

    Button btButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_splash_screen);

        btButton = findViewById(R.id.bt_button);
        btButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent telaPrincipal = new Intent(TelaSplashScreen.this, TelaPrincipal.class);
                startActivity(telaPrincipal);
            }
        });


    }
}