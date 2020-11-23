package com.example.buscadordehoteis.service;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.ImageView;
import android.widget.RadioGroup;

import com.example.buscadordehoteis.R;
import com.example.buscadordehoteis.view.TelaAlterarCadastro;
import com.example.buscadordehoteis.view.TelaCadastro;
import com.example.buscadordehoteis.view.TelaLogin;
import com.example.buscadordehoteis.view.TelaPrincipal;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static com.example.buscadordehoteis.view.TelaLogin.loginId;
import static com.example.buscadordehoteis.view.TelaLogin.loginStatus;

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

    public static void salvarLoginStatus(SharedPreferences loginStatusPreferences, Boolean bool, String id) {
            SharedPreferences.Editor editorPrefrences = loginStatusPreferences.edit();
            editorPrefrences.putBoolean(loginStatus, bool);
            editorPrefrences.putString(loginId, id);
            editorPrefrences.apply();
    }

    public static void verificarLoginStatus(SharedPreferences loginStatusPreferences, ImageView imageView, Context context) {
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
}

