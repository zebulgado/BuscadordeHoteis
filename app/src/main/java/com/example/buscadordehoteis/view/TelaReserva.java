package com.example.buscadordehoteis.view;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.buscadordehoteis.R;
import com.example.buscadordehoteis.model.Guest;
import com.example.buscadordehoteis.model.Hotel;
import com.example.buscadordehoteis.model.Reservation;
import com.example.buscadordehoteis.repository.RetrofitConfig;
import com.example.buscadordehoteis.service.GuestService;
import com.example.buscadordehoteis.service.metodosUtil;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.buscadordehoteis.service.metodosUtil.atualizaClickPerfil;
import static com.example.buscadordehoteis.service.metodosUtil.salvarLoginStatus;
import static com.example.buscadordehoteis.service.metodosUtil.verificarCnpjHotel;
import static com.example.buscadordehoteis.service.metodosUtil.verificarCpfLogado;
import static java.sql.Types.TIMESTAMP;

public class TelaReserva extends AppCompatActivity {
    Button btAvancar, btVoltar;
    EditText edDataCheckin, edDataCheckout, edHoraCheckin, edHoraCheckout;
    ImageView ivPerfil;
    Calendar calendarioCheckin = Calendar.getInstance();
    Calendar calendarioCheckout = Calendar.getInstance();
    Guest hospede = new Guest();
    Hotel hotel = new Hotel();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_reserva);

        btAvancar = findViewById(R.id.bt_avancar_tela_reserva);
        btVoltar = findViewById(R.id.bt_voltar_tela_reserva);
        ivPerfil = findViewById(R.id.iv_perfil_tela_reserva);
        edDataCheckin = findViewById(R.id.ed_checkin_data_tela_reserva);
        edDataCheckout = findViewById(R.id.ed_checkout_data_tela_reserva);

        atualizaClickPerfil(ivPerfil, TelaReserva.this);

        DatePickerDialog.OnDateSetListener checkinData = (view, year, monthOfYear, dayOfMonth) -> {
            calendarioCheckin.set(Calendar.YEAR, year);
            calendarioCheckin.set(Calendar.MONTH, monthOfYear);
            calendarioCheckin.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabel(edDataCheckin, calendarioCheckin);
        };

        edDataCheckin.setOnClickListener(v -> {
            new DatePickerDialog(TelaReserva.this, checkinData, calendarioCheckin
                    .get(Calendar.YEAR), calendarioCheckin.get(Calendar.MONTH),
                    calendarioCheckin.get(Calendar.DAY_OF_MONTH)).show();
        });

        DatePickerDialog.OnDateSetListener checkoutData = (view, year, monthOfYear, dayOfMonth) -> {
            calendarioCheckout.set(Calendar.YEAR, year);
            calendarioCheckout.set(Calendar.MONTH, monthOfYear);
            calendarioCheckout.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabel(edDataCheckout, calendarioCheckout);
        };

        edDataCheckout.setOnClickListener(v -> {
            new DatePickerDialog(TelaReserva.this, checkoutData, calendarioCheckout
                    .get(Calendar.YEAR), calendarioCheckout.get(Calendar.MONTH),
                    calendarioCheckout.get(Calendar.DAY_OF_MONTH)).show();
        });

        btAvancar.setOnClickListener(v -> {
            RetrofitConfig retrofitConfig = new RetrofitConfig();
            Call<Guest> getGuestRequest = retrofitConfig.getGuestService().getGuest(verificarCpfLogado(TelaReserva.this));
            getGuestRequest.enqueue(new Callback<Guest>() {
                @Override
                public void onResponse(Call<Guest> call, Response<Guest> response) {
                    hospede = response.body();
                    RetrofitConfig retrofitConfig = new RetrofitConfig();
                    Call<Hotel> getHotelRequest = retrofitConfig.getHotelService().getHotel(verificarCnpjHotel(TelaReserva.this));
                    getHotelRequest.enqueue(new Callback<Hotel>() {
                        @Override
                        public void onResponse(Call<Hotel> call, Response<Hotel> response) {
                            hotel = response.body();
                            Date entrada = calendarioCheckin.getTime();
                            Date saida = calendarioCheckout.getTime();
                            Long id = 9223372036854775806l;

                            Reservation reserva = new Reservation(entrada, saida, hospede, hotel, id);
                            RetrofitConfig retrofitConfig = new RetrofitConfig();
                            Call<Reservation> insertReservationCall = retrofitConfig.getReservationService().insertReservation(reserva);
                            insertReservationCall.enqueue(new Callback<Reservation>() {
                                @Override
                                public void onResponse(Call<Reservation> call, Response<Reservation> response) {
                                    Toast.makeText(TelaReserva.this, "Reserva criada com sucesso", Toast.LENGTH_SHORT).show();
                                }

                                @Override
                                public void onFailure(Call<Reservation> call, Throwable t) {
                                    Toast.makeText(TelaReserva.this, "Deu ruim", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }

                        @Override
                        public void onFailure(Call<Hotel> call, Throwable t) {
                            Toast.makeText(TelaReserva.this, "Deu ruim", Toast.LENGTH_SHORT).show();
                        }
                    });
                }

                @Override
                public void onFailure(Call<Guest> call, Throwable t) {
                    Toast.makeText(TelaReserva.this, "Deu ruim", Toast.LENGTH_SHORT).show();
                }
            });
        });
    }

    private void updateLabel(EditText editData, Calendar calendario) {
        String myFormat = "dd/MM/yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        editData.setText(sdf.format(calendario.getTime()));
    }

    private void updateTimeLabel(EditText editData, Calendar calendario) {
        String myFormat = "HH:mm";
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
