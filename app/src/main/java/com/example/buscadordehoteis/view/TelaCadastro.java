package com.example.buscadordehoteis.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;

import com.example.buscadordehoteis.R;
import com.example.buscadordehoteis.service.MaskEditUtil;

public class TelaCadastro extends AppCompatActivity {

    EditText edNascimento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_cadastro);

        edNascimento = findViewById(R.id.ed_nascimento);

        edNascimento.addTextChangedListener(MaskEditUtil.mask(edNascimento, "##/##/####"));
    }
}