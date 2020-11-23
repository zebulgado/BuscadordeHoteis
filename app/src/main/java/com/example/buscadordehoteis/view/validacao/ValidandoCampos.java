package com.example.buscadordehoteis.view.validacao;

import android.content.Context;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

public class ValidandoCampos {

    public static Boolean checarVazio(EditText campo) {
        if (campo.getText().toString().length() == 0) {
            campo.requestFocus();
            campo.setError("Este campo não pode estar vazio");
            return true;
        } else {
            return false;
        }
    }

    public static Boolean validarCpf(EditText campo) {
        if (campo.getText().toString().length() != 11) {
            campo.requestFocus();
            campo.setError("Formato de CPF inválido");
            return true;
        } else {
            return false;
        }
    }

    public static Boolean validarNascimento(EditText campo) {
        if (campo.getText().toString().length() != 8) {
            campo.requestFocus();
            campo.setError("Formato de data inválido");
            return true;
        } else {
            return false;
        }
    }

    public static Boolean checarRadioGroup(RadioGroup radioGroup, Context context) {
        if ((radioGroup.getCheckedRadioButtonId() == -1)) {
            Toast.makeText(context, "Escolha uma opção de fidelidade", Toast.LENGTH_LONG).show();
            return true;
        } else {
            return false;
        }
    }
}