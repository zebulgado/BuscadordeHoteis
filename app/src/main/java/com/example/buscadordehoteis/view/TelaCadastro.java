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
import com.example.buscadordehoteis.service.metodosUtil;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.buscadordehoteis.view.validacao.ValidandoCampos.checarRadioGroup;
import static com.example.buscadordehoteis.view.validacao.ValidandoCampos.checarVazio;
import static com.example.buscadordehoteis.view.validacao.ValidandoCampos.validarCpf;
import static com.example.buscadordehoteis.view.validacao.ValidandoCampos.validarNascimento;

public class TelaCadastro extends AppCompatActivity {

    EditText edNome, edEmail, edNascimento, edSenha, edTelefone, edCpf;
    RadioGroup rgFidelidade;
    RadioButton rbSim, rbNao;
    Button btCadastrar, btDevAlterarCadastro;

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
            Guest novoGuest = new Guest(edCpf.getText().toString(),
                    edNome.getText().toString(),
                    edEmail.getText().toString(),
                    edSenha.getText().toString(),
                    edTelefone.getText().toString(),
                    metodosUtil.conversorStringData(edNascimento.getText().toString()),
                    metodosUtil.verificarFidelidade(rgFidelidade)
                    );
//ENVIA GUEST PARA BACKEND
            if (checarVazio(edNome)){
            } else if (checarVazio(edTelefone)) {
            } else if (checarVazio(edEmail)) {
            } else if (checarVazio(edNascimento)) {
            } else if (validarNascimento(edNascimento)) {
            } else if (checarVazio(edCpf)) {
            } else if (validarCpf(edCpf)) {
            } else if (checarVazio(edSenha)) {
            } else if ((checarRadioGroup(rgFidelidade, TelaCadastro.this))) {
            } else {
                RetrofitConfig retrofitConfig = new RetrofitConfig();
                Call<Guest> createRequestCall = retrofitConfig.getGuestService().insertGuest(novoGuest);
                createRequestCall.enqueue(new Callback<Guest>() {
                    @Override
                    public void onResponse(Call<Guest> call, Response<Guest> response) {
                        finish();
                    }

                    @Override
                    public void onFailure(Call<Guest> call, Throwable t) {
                        Log.e("GuestService   ", "Erro ao cadastrar o guest:" + t.getMessage());
                        Toast.makeText(TelaCadastro.this, "Sua request falhou!", Toast.LENGTH_LONG).show();
                    }
                });
//            edCpf.addTextChangedListener(Mask.insert(Mask.CPF_MASK, edCpf));
            }
        });

        btDevAlterarCadastro.setOnClickListener(v -> {
            Intent telaCadastro = new Intent(TelaCadastro.this, TelaAlterarCadastro.class);
            startActivity(telaCadastro);
        });
    }
}