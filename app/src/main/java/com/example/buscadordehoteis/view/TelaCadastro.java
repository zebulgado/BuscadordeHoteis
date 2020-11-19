package com.example.buscadordehoteis.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.buscadordehoteis.R;
import com.example.buscadordehoteis.model.Guest;
import com.example.buscadordehoteis.repository.RetrofitConfig;
import com.example.buscadordehoteis.service.Mask;
import com.example.buscadordehoteis.service.metodosUtil;

import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TelaCadastro extends AppCompatActivity {

    EditText edNome, edEmail, edNascimento, edSenha, edTelefone, edCpf;
    RadioGroup rgFidelidade;
    RadioButton rbSim, rbNao;
    Button btCadastrar, btDevAlterarCadastro;
    Date nascimento = new Date();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_cadastro);

        edEmail = findViewById(R.id.ed_email_cadastro);
        rgFidelidade = findViewById(R.id.op_fidelidade_cadastro);
        edNome = findViewById(R.id.ed_nome_cadastro);
        edNascimento = findViewById(R.id.ed_nascimento_cadastro);
        edSenha = findViewById(R.id.ed_senha_cadastro);
        edTelefone = findViewById(R.id.ed_telefone_cadastro);
        edCpf = findViewById(R.id.ed_cpf_cadastro);
        btCadastrar = findViewById(R.id.bt_cadastrar_cadastro);
        rbNao = findViewById(R.id.rb_nao_cadastro);
        rbSim = findViewById(R.id.rb_sim_cadastro);
        btDevAlterarCadastro = findViewById(R.id.dev_bt_chamar_alterar_cadastro);

        btCadastrar.setOnClickListener(v -> {

//MONTANDO O GUEST
            Guest novoGuest = new Guest();
            novoGuest.setName(edNome.getText().toString());
            novoGuest.setEmail(edEmail.getText().toString());
            novoGuest.setPhone(edTelefone.getText().toString());
            novoGuest.setCpf(edCpf.getText().toString());
            novoGuest.setPassword(edSenha.getText().toString());
            novoGuest.setBirthDate(metodosUtil.conversorStringData(edNascimento.getText().toString()));
            novoGuest.setIsLoyalty(metodosUtil.verificarFidelidade(rgFidelidade));
//ENVIA GUEST PARA BACKEND
            RetrofitConfig retrofitConfig = new RetrofitConfig();
            Call<Guest> createRequest = retrofitConfig.getGuestService().insertGuest(novoGuest);
            createRequest.enqueue(new Callback<Guest>() {
                @Override
                public void onResponse(Call<Guest> call, Response<Guest> response) {
                    finish();
                }

                @Override
                public void onFailure(Call<Guest> call, Throwable t) {
                    Log.e("GuestService   ", "Erro ao buscar o guest:" + t.getMessage());
                    Toast.makeText(TelaCadastro.this, "Sua request falhou!", Toast.LENGTH_LONG).show();
                }
            });
            edCpf.addTextChangedListener(Mask.insert(Mask.CPF_MASK, edCpf));
        });

        btDevAlterarCadastro.setOnClickListener(v -> {
            Intent telaCadastro = new Intent(TelaCadastro.this, TelaAlterarCadastro.class);
            startActivity(telaCadastro);
        });
    }
}