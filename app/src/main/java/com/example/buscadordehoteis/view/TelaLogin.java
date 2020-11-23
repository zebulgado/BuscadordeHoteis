package com.example.buscadordehoteis.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.buscadordehoteis.R;
import com.example.buscadordehoteis.repository.RetrofitConfig;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.buscadordehoteis.view.validacao.ValidandoCampos.checarVazio;

public class TelaLogin extends AppCompatActivity {
    Button btEntrar, btDepois, btNovoUsuario;
    EditText edCpf, edSenha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_login);

        btEntrar = findViewById(R.id.bt_entrar_login);
        btDepois = findViewById(R.id.bt_depois_login);
        btNovoUsuario = findViewById(R.id.bt_novo_usuario_login);
        edCpf = findViewById(R.id.ed_cpf_login);
        edSenha = findViewById(R.id.ed_senha_login);

        btDepois.setOnClickListener(v -> {
            Intent telaPrincipal = new Intent(TelaLogin.this, TelaPrincipal.class);
            startActivity(telaPrincipal);
        });

        btEntrar.setOnClickListener(v -> {
            if (checarVazio(edCpf)) {
            } else if (checarVazio(edSenha)){
            } else {
                RetrofitConfig retrofitConfig = new RetrofitConfig();
                Call<Boolean> getRequest = retrofitConfig.getGuestService().checkLogin(edCpf.getText().toString(), edSenha.getText().toString());
                getRequest.enqueue(new Callback<Boolean>() {
                    @Override
                    public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                        Boolean validou = response.body();
                        if (validou) {
                            Intent telaPrincipal = new Intent(TelaLogin.this, TelaPrincipal.class);
                            startActivity(telaPrincipal);
                        } else {
                            Toast.makeText(TelaLogin.this, "CPF ou Senha incorretos.", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Boolean> call, Throwable t) {
                        Toast.makeText(TelaLogin.this, "Deu ruim", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        btNovoUsuario.setOnClickListener(v -> {
            Intent telaCadastro = new Intent(TelaLogin.this, TelaCadastro.class);
            startActivity(telaCadastro);
        });
    }
}
