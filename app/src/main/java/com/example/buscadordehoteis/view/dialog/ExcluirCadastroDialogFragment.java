package com.example.buscadordehoteis.view.dialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.fragment.app.DialogFragment;

import com.example.buscadordehoteis.repository.RetrofitConfig;
import com.example.buscadordehoteis.view.TelaAlterarCadastro;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.buscadordehoteis.service.metodosUtil.deslogar;

public class ExcluirCadastroDialogFragment extends DialogFragment {

    private String Cpf;
    private Context context;

    public ExcluirCadastroDialogFragment(String Cpf, Context context) {
        this.Cpf = Cpf;
        this.context = context;
    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("Ao clicar em OK sua conta será completamente apagada.")
                .setTitle("Confirma Exclusão?")
                .setPositiveButton("Sim", (dialog, id) -> {
                    RetrofitConfig retrofitConfig = new RetrofitConfig();
                    Call<Void> deleteRequest = retrofitConfig.getGuestService().delete(Cpf);
                    deleteRequest.enqueue(new Callback<Void>() {
                        @Override
                        public void onResponse(Call<Void> call, Response<Void> response) {
                            deslogar(context);
                            ((Activity)(context)).finish();
                        }

                        @Override
                        public void onFailure(Call<Void> call, Throwable t) {
                            Log.e("GuestService   ", "Erro ao apagar o guest:" + t.getMessage());
                            Toast.makeText(context, "Sua request falhou!", Toast.LENGTH_LONG).show();
                        }
                    });
                })
                .setNegativeButton("Não", (dialog, id) -> {

                });

        return builder.create();
    }
}

