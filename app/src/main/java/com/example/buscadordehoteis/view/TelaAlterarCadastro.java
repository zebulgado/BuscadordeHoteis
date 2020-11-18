package com.example.buscadordehoteis.view;

import androidx.appcompat.app.AppCompatActivity;

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

        edCpf = findViewById(R.id.ed_cpf);
        edRetornoEmail = findViewById(R.id.ed_retono_email);
        rgRetornoFidelidade = findViewById(R.id.op_fidelidade);
        edRetornoNome = findViewById(R.id.ed_retono_nome);
        edRetornoNascimento = findViewById(R.id.ed_retono_nascimento);
        edRetornoSenha = findViewById(R.id.ed_retono_senha);
        edRetornoTelefone = findViewById(R.id.ed_retono_telefone);
        edRetornoCpf = findViewById(R.id.ed_retono_cpf);
        btConsultar = findViewById(R.id.bt_consultar);
        btAtualizar = findViewById(R.id.bt_atualizar);
        btSalvar = findViewById(R.id.bt_salvar);
        rbNao = findViewById(R.id.rb_nao);
        rbSim = findViewById(R.id.rb_sim);
        cbExcluir = findViewById(R.id.cb_excluir);
        btVoltar = findViewById(R.id.bt_voltar);

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
                        rgRetornoFidelidade.check(R.id.rb_sim);
                        respostaFidelidade = true;
                    } else {
                        rgRetornoFidelidade.check(R.id.rb_nao);
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
                RetrofitConfig retrofitConfig = new RetrofitConfig();
                Call<Void> deleteRequest = retrofitConfig.getGuestService().delete(edRetornoCpf.getText().toString());
                deleteRequest.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                       /* AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                        builder.setMessage(R.string.dialog_message)
                                .setTitle(R.string.dialog_title);
                        AlertDialog dialog = builder.create();*/
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Log.e("GuestService   ", "Erro ao apagar o guest:" + t.getMessage());
                        Toast.makeText(TelaAlterarCadastro.this, "Sua request falhou!", Toast.LENGTH_LONG).show();
                    }
                });
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
}