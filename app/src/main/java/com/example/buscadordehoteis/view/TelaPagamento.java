package com.example.buscadordehoteis.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.buscadordehoteis.R;

import static com.example.buscadordehoteis.service.metodosUtil.verificarLoginStatus;
import static com.example.buscadordehoteis.view.TelaLogin.loginStatusFile;

public class TelaPagamento extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_pagamento);

    }

}

