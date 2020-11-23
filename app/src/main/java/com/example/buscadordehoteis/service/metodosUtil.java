package com.example.buscadordehoteis.service;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.ImageView;
import android.widget.RadioGroup;

import com.example.buscadordehoteis.R;
import com.example.buscadordehoteis.view.TelaAlterarCadastro;
import com.example.buscadordehoteis.view.TelaCadastro;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static com.example.buscadordehoteis.view.TelaLogin.loginId;
import static com.example.buscadordehoteis.view.TelaLogin.loginStatus;
import static com.example.buscadordehoteis.view.TelaLogin.loginStatusFile;

public class metodosUtil {

    public static Boolean verificarFidelidade(RadioGroup radioGroup) {
        boolean resposta = false;
        int checkedId = radioGroup.getCheckedRadioButtonId();
        switch (checkedId) {
            case R.id.rb_sim_cadastro:
                resposta = true;
                break;
            case R.id.rb_nao_cadastro:
                resposta = false;
                break;
        }
        return resposta;
    }

    public static Date conversorStringData(String dataString) {
        Date data = null;
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
        try {
            return formatter.parse(dataString);
        } catch (
                ParseException e) {
            e.printStackTrace();
        }
        return data;
    }

    public static void salvarLoginStatus(Boolean bool, String id, Context context) {
        SharedPreferences loginStatusPreferences = context.getSharedPreferences(loginStatusFile, Context.MODE_PRIVATE);
        SharedPreferences.Editor editorPreferences = loginStatusPreferences.edit();
        editorPreferences.putBoolean(loginStatus, bool);
        editorPreferences.putString(loginId, id);
        editorPreferences.apply();
    }

    public static void atualizaClickPerfil(ImageView imageView, Context context) {
        SharedPreferences loginStatusPreferences = context.getSharedPreferences(loginStatusFile, Context.MODE_PRIVATE);
        if (loginStatusPreferences.getBoolean(loginStatus, false)) {
            imageView.setOnClickListener(v -> {
                Intent telaAlterarCadastro = new Intent(context, TelaAlterarCadastro.class);
                context.startActivity(telaAlterarCadastro);
            });
        } else {
            imageView.setOnClickListener(v -> {
                Intent telaCadastro = new Intent(context, TelaCadastro.class);
                context.startActivity(telaCadastro);
            });
        }
    }

    public static String verificarCpfLogado(Context context) {
        SharedPreferences loginStatusPreferences = context.getSharedPreferences(loginStatusFile, Context.MODE_PRIVATE);
        return loginStatusPreferences.getString(loginId, "convidado");
    }

    public static void deslogar(Context context) {
        salvarLoginStatus(false, "convidado", context);
    }
}

