package com.example.buscadordehoteis.service;

import android.widget.RadioGroup;

import com.example.buscadordehoteis.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class metodosUtil {

    public static Boolean verificarFidelidade(RadioGroup radioGroup){
        boolean resposta = false;
        int checkedId = radioGroup.getCheckedRadioButtonId();
        switch (checkedId) {
            case R.id.rb_sim:
                resposta = true;
                break;
            case R.id.rb_nao:
                resposta = false;
                break;
        }
        return resposta;
    }

    public static Date conversorStringData(String dataString){
        Date data = null;
        SimpleDateFormat formatter = new SimpleDateFormat("ddMMyyyy", Locale.ENGLISH);
        try {
            return formatter.parse(dataString);
        } catch (
                ParseException e) {
            e.printStackTrace();
        }
        return data;
    }

}
