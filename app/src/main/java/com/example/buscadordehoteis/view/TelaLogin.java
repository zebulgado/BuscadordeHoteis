package com.example.buscadordehoteis.view;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.buscadordehoteis.R;

public class TelaLogin extends AppCompatActivity {
    Button btEntrar, btDepois, btNovoUsuario;
    EditText edCpf, edSenha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_login);

        btEntrar = findViewById(R.id.bt_entrar);
        btDepois = findViewById(R.id.bt_depois);
        btNovoUsuario = findViewById(R.id.bt_novo_usuario);
        edCpf = findViewById(R.id.ed_cpf_cadastro);
        edSenha = findViewById(R.id.ed_senha_cadastro);

        btDepois.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent telaPrincipal = new Intent(TelaLogin.this, TelaPrincipal.class);
                startActivity(telaPrincipal);
            }
        });

        btEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // verificarCampoCpf();
               // verificarCampoSenha();

            }
        });

        btNovoUsuario.setOnClickListener(v -> {
            Intent telaCadastro = new Intent(TelaLogin.this, TelaCadastro.class);
            startActivity(telaCadastro);
        });
    }

    public void verificarCampoCpf() {
        if (edCpf.getText().toString().equals("")) {
            Toast.makeText(TelaLogin.this, "Prencha os campos!", Toast.LENGTH_SHORT).show();
            edCpf.setBackgroundColor(Color.RED);
        }
    }
    public void verificarCampoSenha() {
        if (edSenha.getText().toString().equals("")) {
            Toast.makeText(TelaLogin.this, "Prencha os campos!", Toast.LENGTH_SHORT).show();
            edSenha.setBackgroundColor(Color.RED);
        }
    }
}
