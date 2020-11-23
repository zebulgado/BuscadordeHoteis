package com.example.buscadordehoteis.service.validacao;

import android.content.Context;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import br.com.sapereaude.maskedEditText.MaskedEditText;

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

    public static Boolean checarVazio(MaskedEditText campo) {
        if (campo.getRawText().length() == 0) {
            campo.requestFocus();
            campo.setError("Este campo não pode estar vazio");
            return true;
        } else {
            return false;
        }
    }

    public static Boolean validarCpf(MaskedEditText campo) {
        if (campo.getRawText().length() != 11) {
            campo.requestFocus();
            campo.setError("Formato de CPF inválido");
            return true;
        } else {
            return false;
        }
    }

    public static Boolean validarNascimento(EditText campo) {
        if (campo.getText().toString().length() != 10) {
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