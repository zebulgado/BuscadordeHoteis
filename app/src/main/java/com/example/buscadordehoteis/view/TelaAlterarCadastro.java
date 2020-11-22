package com.example.buscadordehoteis.view;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
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

public class TelaAlterarCadastro extends AppCompatActivity {
    EditText edCpf;
    EditText edRetornoNome, edRetornoTelefone, edRetornoEmail, edRetornoCpf, edRetornoNascimento, edRetornoSenha;
    RadioGroup rgRetornoFidelidade;
    RadioButton rbSim, rbNao;
    Button btConsultar, btAtualizar, btSalvar, btVoltar;
    CheckBox cbExcluir;
    Boolean cadastroLocalizado = false, respostaFidelidade = false, atualizarSenha = false;
    String retornoSenha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alterar_cadastro);

        edCpf = findViewById(R.id.ed_cpf_consulta_alterar_cadastro);
        edRetornoEmail = findViewById(R.id.ed_email_alterar_cadastro);
        rgRetornoFidelidade = findViewById(R.id.op_fidelidade_alterar_cadastro);
        edRetornoNome = findViewById(R.id.ed_nome_alterar_cadastro);
        edRetornoNascimento = findViewById(R.id.ed_nascimento_alterar_cadastro);
        edRetornoSenha = findViewById(R.id.ed_senha_alterar_cadastro);
        edRetornoTelefone = findViewById(R.id.ed_telefone_alterar_cadastro);
        edRetornoCpf = findViewById(R.id.ed_cpf_alterar_cadastro);
        btConsultar = findViewById(R.id.bt_consultar_alterar_cadastro);
        btAtualizar = findViewById(R.id.bt_atualizar_alterar_cadastro);
        btSalvar = findViewById(R.id.bt_salvar_alterar_cadastro);
        rbNao = findViewById(R.id.rb_nao_alterar_cadastro);
        rbSim = findViewById(R.id.rb_sim_alterar_cadastro);
        cbExcluir = findViewById(R.id.cb_excluir_alterar_cadastro);
        btVoltar = findViewById(R.id.bt_voltar_alterar_cadastro);

        btConsultar.setOnClickListener(v -> {
            RetrofitConfig retrofitConfig = new RetrofitConfig();
            Call<Guest> getRequest = retrofitConfig.getGuestService().getGuest(edCpf.getText().toString());
            getRequest.enqueue(new Callback<Guest>() {
                @Override
                public void onResponse(Call<Guest> call, Response<Guest> response) {
                    cadastroLocalizado = true;
                    Guest guest = response.body();
                    edRetornoCpf.setText(guest.getCpf());
                    edRetornoEmail.setText(guest.getEmail());
                    edRetornoNascimento.setText(guest.getBirthDate().toString());
                    edRetornoNome.setText(guest.getName());
                    edRetornoTelefone.setText(guest.getPhone());
                    retornoSenha = guest.getPassword();
                    if (guest.getIsLoyalty()){
                        rgRetornoFidelidade.check(R.id.rb_sim_alterar_cadastro);
                        respostaFidelidade = true;
                    } else {
                        rgRetornoFidelidade.check(R.id.rb_nao_alterar_cadastro);
                        respostaFidelidade = false;
                    }
                }

                @Override
                public void onFailure(Call<Guest> call, Throwable t) {
                    Log.e("GuestService   ", "Erro ao buscar o guest:" + t.getMessage());
                    Toast.makeText(TelaAlterarCadastro.this, "Sua request falhou!", Toast.LENGTH_LONG).show();
                }
            });
            });

        btAtualizar.setOnClickListener(v -> {
            if (cadastroLocalizado) {
                edRetornoEmail.setEnabled(true);
                edRetornoTelefone.setEnabled(true);
                edRetornoSenha.setEnabled(true);
                rbNao.setEnabled(true);
                rbSim.setEnabled(true);
                btAtualizar.setVisibility(View.GONE);
                btSalvar.setVisibility(View.VISIBLE);
                cbExcluir.setEnabled(true);
            }
        });

        btSalvar.setOnClickListener(v -> {
// Verifica se deve apagar o cadastro
            if (cbExcluir.isChecked()){
                DialogFragment excluirCadastroDialog = new ExcluirCadastroDialogFragment(
                        edRetornoCpf.getText().toString(), TelaAlterarCadastro.this);
                excluirCadastroDialog.show(getSupportFragmentManager(), "Exlcuindo conta");

// Atualiza o cadastro
            } else {
                RetrofitConfig retrofitConfig = new RetrofitConfig();
                Call<Guest> getRequest = retrofitConfig.getGuestService().getGuest(edRetornoCpf.getText().toString());
                getRequest.enqueue(new Callback<Guest>() {
                    @Override
                    public void onResponse(Call<Guest> call, Response<Guest> response) {
                        Guest atualizadoGuest = response.body();
// Montando o novo Guest
                        atualizadoGuest.setEmail(edRetornoEmail.getText().toString());
                        atualizadoGuest.setIsLoyalty(metodosUtil.verificarFidelidade(rgRetornoFidelidade));
                        if (atualizarSenha) {
                            atualizadoGuest.setPassword(edRetornoSenha.getText().toString());
                        } else {
                            atualizadoGuest.setPassword(retornoSenha);
                        }
                        atualizadoGuest.setPhone(edRetornoTelefone.getText().toString());
// Envia Guest para update
                        RetrofitConfig retrofitConfig = new RetrofitConfig();
                        Call<Guest> updateRequest = retrofitConfig.getGuestService().updateGuest(atualizadoGuest);
                        updateRequest.enqueue(new Callback<Guest>() {
                            @Override
                            public void onResponse(Call<Guest> call, Response<Guest> response) {

                            }

                            @Override
                            public void onFailure(Call<Guest> call, Throwable t) {
                                Log.e("GuestService   ", "Erro ao buscar o guest:" + t.getMessage());
                                Toast.makeText(TelaAlterarCadastro.this, "Sua request falhou!", Toast.LENGTH_LONG).show();
                            }
                        });
                    }

                    @Override
                    public void onFailure(Call<Guest> call, Throwable t) {
                        Log.e("GuestService   ", "Erro ao buscar o guest:" + t.getMessage());
                        Toast.makeText(TelaAlterarCadastro.this, "Sua request falhou!", Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
// Verifica se a senha foi alterada
        edRetornoSenha.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                atualizarSenha = false;
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                atualizarSenha = true;
            }

            @Override
            public void afterTextChanged(Editable s) {
                atualizarSenha = true;
            }
        });
    }
//
//    public static void finalizaActitivy(){
//        TelaAlterarCadastro.finish();
//    }
}