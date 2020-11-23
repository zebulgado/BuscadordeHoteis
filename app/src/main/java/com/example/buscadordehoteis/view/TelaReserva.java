package com.example.buscadordehoteis.view;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TimePicker;

import androidx.appcompat.app.AppCompatActivity;

import com.example.buscadordehoteis.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import static com.example.buscadordehoteis.service.metodosUtil.atualizaClickPerfil;

public class TelaReserva extends AppCompatActivity {
    Button btAvancar, btVoltar;
    EditText edDataCheckin, edDataCheckout, edHoraCkeckin, edHoraCheckout;
    ImageView ivPerfil;
    final Calendar calendario = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_reserva);

        btAvancar = findViewById(R.id.bt_avancar_tela_reserva);
        btVoltar = findViewById(R.id.bt_voltar_tela_reserva);
        ivPerfil = findViewById(R.id.iv_perfil_tela_reserva);
        edDataCheckin = findViewById(R.id.ed_checkin_data_tela_reserva);
        edDataCheckout = findViewById(R.id.ed_checkout_data_tela_reserva);
        edHoraCkeckin = findViewById(R.id.ed_checkin_hora_tela_reserva);
        edHoraCheckout = findViewById(R.id.ed_checkout_hora_tela_reserva);

        atualizaClickPerfil(ivPerfil, TelaReserva.this);

        DatePickerDialog.OnDateSetListener checkinData = (view, year, monthOfYear, dayOfMonth) -> {
            calendario.set(Calendar.YEAR, year);
            calendario.set(Calendar.MONTH, monthOfYear);
            calendario.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabel(edDataCheckin);
        };

        edDataCheckin.setOnClickListener(v -> {
            new DatePickerDialog(TelaReserva.this, checkinData, calendario
                    .get(Calendar.YEAR), calendario.get(Calendar.MONTH),
                    calendario.get(Calendar.DAY_OF_MONTH)).show();
        });

        DatePickerDialog.OnDateSetListener checkoutData = (view, year, monthOfYear, dayOfMonth) -> {
            calendario.set(Calendar.YEAR, year);
            calendario.set(Calendar.MONTH, monthOfYear);
            calendario.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabel(edDataCheckout);
        };

        edDataCheckout.setOnClickListener(v -> {
            new DatePickerDialog(TelaReserva.this, checkoutData, calendario
                    .get(Calendar.YEAR), calendario.get(Calendar.MONTH),
                    calendario.get(Calendar.DAY_OF_MONTH)).show();
        });
    }

    private void updateLabel(EditText editData) {
        String myFormat = "dd/MM/yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        editData.setText(sdf.format(calendario.getTime()));
    }
    private void showTimePicker(View view) {
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);
        boolean is24HourFormat = android.text.format.DateFormat.is24HourFormat(this);

//        TimePickerDialog dialog = new TimePickerDialog(this, (
//                timePicker, hourOfDay, minutes) -> {
//            calendario.set(Calendar.HOUR_OF_DAY, hourOfDay);
//            calendario.set(Calendar.MINUTE, minutes);
//            Date selectedDate = calendario.getTime();
//            String result = dateFormat.format(selectedDate);
//            editText.setText(result);
//            listener.onDateSelected(selectedDate);
//            nextFocus(view);
//            date = null;
//        },
//                hour,
//                minute,
//                is24HourFormat);
//        dialog.setTitle(binding.getLabel());
//        dialog.show();
    }
}
