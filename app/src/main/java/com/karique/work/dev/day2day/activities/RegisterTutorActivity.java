package com.karique.work.dev.day2day.activities;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.cardview.widget.CardView;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.jaredrummler.materialspinner.MaterialSpinner;
import com.karique.work.dev.day2day.R;
import com.karique.work.dev.day2day.models.Tutor;
import com.karique.work.dev.day2day.network.Day2DayApi;
import com.karique.work.dev.day2day.session.SessionManager;
import com.libizo.CustomEditText;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;

public class RegisterTutorActivity extends AppCompatActivity {
    public static int REQUEST_CODE = 669;

    AppCompatImageButton backAppCompatImageButton;

    CustomEditText nombreCustomEditText;
    CustomEditText apellidosCustomEditText;
    MaterialSpinner tipoDocumentoMaterialSpinner;
    CustomEditText numeroDocumentoEditText;
    CustomEditText fechaDeNacimientoCustomEditText;
    CustomEditText telefonoCustomEditText;
    CustomEditText emailCustomEditText;
    CustomEditText passwordCustomEditText;
    MaterialSpinner relacionMaterialSpinner;
    CardView registrarCardView;
    TextView registrarTextView;
    ProgressBar registrarProgressBar;

    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_tutor);
        hide();

        inicializarControles();
        inicializarEventos();
        inicializarDatos();
    }

    private void inicializarControles() {
        backAppCompatImageButton = findViewById(R.id.backAppCompatImageButton);
        nombreCustomEditText = findViewById(R.id.nombreCustomEditText);
        apellidosCustomEditText = findViewById(R.id.apellidosCustomEditText);
        tipoDocumentoMaterialSpinner = findViewById(R.id.tipoDocumentoMaterialSpinner);
        numeroDocumentoEditText = findViewById(R.id.numeroDocumentoEditText);
        fechaDeNacimientoCustomEditText = findViewById(R.id.fechaDeNacimientoCustomEditText);
        telefonoCustomEditText = findViewById(R.id.telefonoCustomEditText);
        emailCustomEditText = findViewById(R.id.emailCustomEditText);
        passwordCustomEditText = findViewById(R.id.passwordCustomEditText);
        relacionMaterialSpinner = findViewById(R.id.relacionMaterialSpinner);
        registrarCardView = findViewById(R.id.registrarCardView);
        registrarTextView = findViewById(R.id.registrarTextView);
        registrarProgressBar = findViewById(R.id.registrarProgressBar);
    }
    private void inicializarEventos() {
        backAppCompatImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        registrarCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registrarTutor();
            }
        });
        fechaDeNacimientoCustomEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                seleccionarFechaDeNacimiento();
            }
        });
    }
    private void inicializarDatos() {
        sessionManager = SessionManager.getInstance(this);
        tipoDocumentoMaterialSpinner.setItems(
                "DNI",
                "DOCUMENTO DE EXTRANGERÍA",
                "PASAPORTE"
        );
        relacionMaterialSpinner.setItems(
                "Padre",
                "Tio",
                "Apoderado"
        );
    }
    private void registrarTutor() {
        if (!validacionCampos()) return;

        Tutor tutor = new Tutor();
        tutor.setNombre(nombreCustomEditText.getText().toString());
        tutor.setApellidos(apellidosCustomEditText.getText().toString());
        tutor.setTipo_documentacion(tipoDocumentoMaterialSpinner.getItems().get(tipoDocumentoMaterialSpinner.getSelectedIndex()).toString());
        tutor.setNum_doc_identidad(numeroDocumentoEditText.getText().toString());
        tutor.setFecha_nacimiento(fechaDeNacimientoCustomEditText.getText().toString());
        tutor.setTelefono(Integer.parseInt(telefonoCustomEditText.getText().toString()));
        tutor.setCorreo(emailCustomEditText.getText().toString());
        tutor.setContrasenia(passwordCustomEditText.getText().toString());
        tutor.setRelacion(relacionMaterialSpinner.getItems().get(relacionMaterialSpinner.getSelectedIndex()).toString());
        tutor.setEstado(1);
        tutor.setDes_estado("Verificado");

        disableButton();
        AndroidNetworking.post(Day2DayApi.TUTORS_URL)
                .addHeaders("Content-Type", "application/json")
                .addJSONObjectBody(tutor.toJsonObject())
                .setPriority(Priority.HIGH)
                .setTag(getString(R.string.app_name))
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Toast.makeText(RegisterTutorActivity.this, "Registro correcto", Toast.LENGTH_LONG).show();
                        login();
                    }

                    @Override
                    public void onError(ANError anError) {
                        if (anError.getErrorCode() == 409){
                            Toast.makeText(RegisterTutorActivity.this, "Error: el correo ya ha sido usado antes", Toast.LENGTH_LONG).show();
                        }
                        else {
                            Toast.makeText(RegisterTutorActivity.this, "Error al registrar el tutor", Toast.LENGTH_LONG).show();
                        }

                        enableButton();
                    }
                });
    }
    private void seleccionarFechaDeNacimiento(){
        DatePickerDialog dpdFecha;
        final Calendar _calendario = Calendar.getInstance();
        _calendario.setTime(_calendario.getTime());

        dpdFecha = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                fechaDeNacimientoCustomEditText.setText(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);
            }
        },
                _calendario.get(Calendar.YEAR),
                _calendario.get(Calendar.MONTH),
                _calendario.get(Calendar.DAY_OF_MONTH)
        );
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP && Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            dpdFecha.getDatePicker().getTouchables().get(1).performClick();
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            dpdFecha.getDatePicker().getTouchables().get(0).performClick();
        }
        Calendar calendarioMin = Calendar.getInstance();
        calendarioMin.add(Calendar.YEAR, -100);
        dpdFecha.getDatePicker().setMinDate(calendarioMin.getTimeInMillis());
        Calendar calendarioMax = Calendar.getInstance();
        calendarioMax.add(Calendar.YEAR, -17);
        dpdFecha.getDatePicker().setMaxDate(calendarioMax.getTimeInMillis());
        dpdFecha.setButton(DatePickerDialog.BUTTON_POSITIVE, "Listo", dpdFecha);
        dpdFecha.setTitle("Ingrese su fecha de nacimiento");
        dpdFecha.show();
    }
    private boolean validacionCampos(){
        if (nombreCustomEditText.getText().length() == 0){
            Toast.makeText(this, "El campo nombre está vacío", Toast.LENGTH_LONG).show();
            return false;
        }
        if (apellidosCustomEditText.getText().length() == 0){
            Toast.makeText(this, "El campo apellido está vacío", Toast.LENGTH_LONG).show();
            return false;
        }
        if (numeroDocumentoEditText.getText().length() == 0){
            Toast.makeText(this, "El campo DNI está vacío", Toast.LENGTH_LONG).show();
            return false;
        }
        if (fechaDeNacimientoCustomEditText.getText().length() == 0){
            Toast.makeText(this, "El campo fecha de nacimiento está vacío", Toast.LENGTH_LONG).show();
            return false;
        }
        if (telefonoCustomEditText.getText().length() == 0){
            Toast.makeText(this, "El campo telefono está vacío", Toast.LENGTH_LONG).show();
            return false;
        }
        if (emailCustomEditText.getText().length() == 0){
            Toast.makeText(this, "El campo correo está vacío", Toast.LENGTH_LONG).show();
            return false;
        }
        if (passwordCustomEditText.getText().length() == 0){
            Toast.makeText(this, "El campo contraseña está vacío", Toast.LENGTH_LONG).show();
            return false;
        }

        return true;
    }
    private void hide() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
    }
    private void login(){
        disableButton();
        JSONObject loginJsonObject = new JSONObject();

        try {
            loginJsonObject.put("correo", emailCustomEditText.getText().toString());
            loginJsonObject.put("contrasenia", passwordCustomEditText.getText().toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        AndroidNetworking.post(Day2DayApi.LOGIN_TUTOR_URL)
                .addHeaders("Content-Type", "application/json")
                .addJSONObjectBody(loginJsonObject)
                .setPriority(Priority.HIGH)
                .setTag(getString(R.string.app_name))
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Tutor userLogin = Tutor.from(response);
                        sessionManager.saveTutor(userLogin);
                        //updateToken();
                        mostrarMainTutorActivity();
                    }

                    @Override
                    public void onError(ANError anError) {
                        Toast.makeText(RegisterTutorActivity.this, "Error de ingreso", Toast.LENGTH_LONG).show();
                        enableButton();
                    }
                });
    }
    private void mostrarMainTutorActivity(){
        Intent intent = new Intent(this, MainTutorActivity.class);
        startActivityForResult(intent, MainTutorActivity.REQUEST_CODE);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        finishAffinity();
    }
    private void enableButton() {
        registrarCardView.setEnabled(true);
        registrarTextView.setVisibility(View.VISIBLE);
        registrarProgressBar.setVisibility(View.INVISIBLE);
    }
    private void disableButton() {
        registrarCardView.setEnabled(false);
        registrarTextView.setVisibility(View.INVISIBLE);
        registrarProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
}