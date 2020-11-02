package com.karique.work.dev.day2day.activities;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.jaredrummler.materialspinner.MaterialSpinner;
import com.karique.work.dev.day2day.R;
import com.karique.work.dev.day2day.adapters.EspecialistasAdapter;
import com.karique.work.dev.day2day.adapters.PreguntasAdapter;
import com.karique.work.dev.day2day.models.Diario;
import com.karique.work.dev.day2day.models.Especialista;
import com.karique.work.dev.day2day.models.Paciente;
import com.karique.work.dev.day2day.network.Day2DayApi;
import com.karique.work.dev.day2day.repositories.PreguntasRepositories;
import com.karique.work.dev.day2day.session.SessionManager;
import com.karique.work.dev.day2day.util.FuncionesFecha;
import com.karique.work.dev.day2day.util.RegionesPeru;
import com.libizo.CustomEditText;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

public class RegisterPacienteActivity extends AppCompatActivity {
    public static int REQUEST_CODE = 196;

    TextView toolbarTitleTextView;
    ProgressBar progressBar;
    AppCompatImageButton backAppCompatImageButton;

    private ConstraintLayout messageConstraintLayout;
    private TextView messageTextView;

    private RecyclerView especialistasRecyclerView;
    private EspecialistasAdapter especialistasAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private List<Especialista> especialistas;

    private ScrollView datosPacienteScrollView;
    private CustomEditText nombreCustomEditText;
    private CustomEditText apellidosCustomEditText;
    private MaterialSpinner tipoDocumentoMaterialSpinner;
    private CustomEditText numeroDocumentoEditText;
    private CustomEditText fechaDeNacimientoCustomEditText;
    private MaterialSpinner nivelEstudiosMaterialSpinner;
    private CustomEditText ocupacionCustomEditText;
    private CustomEditText telefonoCustomEditText;
    private CustomEditText emailCustomEditText;
    private CustomEditText passwordCustomEditText;
    private CustomEditText direccionCustomEditText;
    private MaterialSpinner departamentoMaterialSpinner;
    private MaterialSpinner provinciaMaterialSpinner;
    private MaterialSpinner distritoMaterialSpinner;
    private CardView registrarCardView;
    private TextView registrarTextView;
    private ProgressBar registrarProgressBar;

    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_paciente);
        hide();

        inicializarControles();
        inicializarEventos();
        inicializarDatos();
    }
    private void inicializarControles() {
        sessionManager = SessionManager.getInstance(this);
        toolbarTitleTextView = findViewById(R.id.toolbarTitleTextView);
        progressBar = findViewById(R.id.progressBar);
        backAppCompatImageButton = findViewById(R.id.backAppCompatImageButton);

        datosPacienteScrollView = findViewById(R.id.datosPacienteScrollView);
        nombreCustomEditText = findViewById(R.id.nombreCustomEditText);
        apellidosCustomEditText = findViewById(R.id.apellidosCustomEditText);
        tipoDocumentoMaterialSpinner = findViewById(R.id.tipoDocumentoMaterialSpinner);
        numeroDocumentoEditText = findViewById(R.id.numeroDocumentoEditText);
        fechaDeNacimientoCustomEditText = findViewById(R.id.fechaDeNacimientoCustomEditText);
        nivelEstudiosMaterialSpinner = findViewById(R.id.nivelEstudiosMaterialSpinner);
        ocupacionCustomEditText = findViewById(R.id.ocupacionCustomEditText);
        telefonoCustomEditText = findViewById(R.id.telefonoCustomEditText);
        emailCustomEditText = findViewById(R.id.emailCustomEditText);
        passwordCustomEditText = findViewById(R.id.passwordCustomEditText);
        direccionCustomEditText = findViewById(R.id.direccionCustomEditText);
        departamentoMaterialSpinner = findViewById(R.id.departamentoMaterialSpinner);
        provinciaMaterialSpinner = findViewById(R.id.provinciaMaterialSpinner);
        distritoMaterialSpinner = findViewById(R.id.distritoMaterialSpinner);
        registrarCardView = findViewById(R.id.registrarCardView);
        registrarTextView = findViewById(R.id.registrarTextView);
        registrarProgressBar = findViewById(R.id.registrarProgressBar);

        especialistasRecyclerView = findViewById(R.id.especialistasRecyclerView);
        messageConstraintLayout = findViewById(R.id.messageConstraintLayout);
        messageTextView = findViewById(R.id.messageTextView);
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
                agregarPaciente();
            }
        });
        fechaDeNacimientoCustomEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                seleccionarFechaDeNacimiento();
            }
        });
        departamentoMaterialSpinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {
                provinciaMaterialSpinner.setItems(RegionesPeru.getProvincias(departamentoMaterialSpinner.getItems().get(position).toString()));
            }
        });
        provinciaMaterialSpinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {
                distritoMaterialSpinner.setItems(RegionesPeru.getDistritos(provinciaMaterialSpinner.getItems().get(position).toString()));
            }
        });
    }
    private void inicializarDatos() {
        tipoDocumentoMaterialSpinner.setItems("DNI", "Documento de extrangería", "Pasaporte");
        nivelEstudiosMaterialSpinner.setItems("PRIMARIA", "SECUNDARIA", "SUPERIOR");

        departamentoMaterialSpinner.setItems(RegionesPeru.getDepartamentos());
        provinciaMaterialSpinner.setItems(RegionesPeru.getProvincias(RegionesPeru.getDepartamentos().get(0)));
        distritoMaterialSpinner.setItems(RegionesPeru.getDistritos(RegionesPeru.getProvincias(RegionesPeru.getDepartamentos().get(0)).get(0)));

        especialistas = new ArrayList<>();
        especialistasAdapter = new EspecialistasAdapter(especialistas);
        layoutManager = new GridLayoutManager(this, 2, RecyclerView.VERTICAL, false);
        especialistasRecyclerView.setAdapter(especialistasAdapter);
        especialistasRecyclerView.setLayoutManager(layoutManager);

        getEspecialistas();
    }

    public void getEspecialistas(){
        progressBar.setVisibility(View.VISIBLE);

        AndroidNetworking.get(Day2DayApi.ESPECIALISTAS_URL)
                .addHeaders("Content-Type", "application/json")
                .setPriority(Priority.HIGH)
                .setTag(getString(R.string.app_name))
                .build()
                .getAsJSONArray(new JSONArrayRequestListener() {
                    @Override
                    public void onResponse(JSONArray response) {
                        progressBar.setVisibility(View.GONE);
                        messageConstraintLayout.setVisibility(View.GONE);

                        especialistas.clear();
                        especialistas.addAll(Especialista.from(response));
                        Collections.reverse(especialistas);
                        especialistasAdapter.notifyDataSetChanged();
                        datosPacienteScrollView.setVisibility(View.VISIBLE);

                        if (especialistas.isEmpty()){
                            datosPacienteScrollView.setVisibility(View.GONE);
                            showNoDataMessage("Sin datos");
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        progressBar.setVisibility(View.GONE);
                        showNoDataMessage("Error de carga: " + anError.getErrorDetail());
                    }
                });
    }
    private void agregarPaciente() {
        if (!validacionCampos()) return;

        Paciente paciente = new Paciente();
        paciente.setNombre(nombreCustomEditText.getText().toString());
        paciente.setApellidos(apellidosCustomEditText.getText().toString());
        paciente.setTipo_documentacion(tipoDocumentoMaterialSpinner.getItems().get(tipoDocumentoMaterialSpinner.getSelectedIndex()).toString());
        paciente.setNum_doc_identidad(numeroDocumentoEditText.getText().toString());
        paciente.setFecha_nacimiento(fechaDeNacimientoCustomEditText.getText().toString());
        paciente.setNivel_estudios(nivelEstudiosMaterialSpinner.getSelectedIndex() + 1);
        paciente.setDes_estudios(nivelEstudiosMaterialSpinner.getItems().get(nivelEstudiosMaterialSpinner.getSelectedIndex()).toString());
        paciente.setOcupacion(ocupacionCustomEditText.getText().toString());
        paciente.setTelefono(Integer.parseInt(telefonoCustomEditText.getText().toString()));
        paciente.setCorreo(emailCustomEditText.getText().toString());
        paciente.setContrasenia(passwordCustomEditText.getText().toString());
        paciente.setDireccion(direccionCustomEditText.getText().toString());
        paciente.setDistrito(distritoMaterialSpinner.getItems().get(distritoMaterialSpinner.getSelectedIndex()).toString());
        paciente.setProvincia(provinciaMaterialSpinner.getItems().get(provinciaMaterialSpinner.getSelectedIndex()).toString());
        paciente.setDepartamento(departamentoMaterialSpinner.getItems().get(departamentoMaterialSpinner.getSelectedIndex()).toString());
        paciente.setEstado(1);
        paciente.setDes_estado("ACTIVO");
        paciente.setEspecialista_idespe(especialistasAdapter.getEspecialistaSeleccionado().getIdespe());
        paciente.setTutor_idtutor(sessionManager.getidtutor());

        disableRegisterButton();
        AndroidNetworking.post(Day2DayApi.PACIENTES_URL)
                .addHeaders("Content-Type", "application/json")
                .addJSONObjectBody(paciente.toJsonObject())
                .setPriority(Priority.HIGH)
                .setTag(getString(R.string.app_name))
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Toast.makeText(RegisterPacienteActivity.this, "Paciente registrado", Toast.LENGTH_LONG).show();
                        setResult(Activity.RESULT_OK);
                        finish();
                    }
                    @Override
                    public void onError(ANError anError) {
                        if (anError.getErrorCode() == 409){
                            Toast.makeText(RegisterPacienteActivity.this, "Error: el correo ya ha sido usado antes", Toast.LENGTH_LONG).show();
                        }
                        else {
                            Toast.makeText(RegisterPacienteActivity.this, "Error al registrar el paciente", Toast.LENGTH_LONG).show();
                        }

                        enableRegisterButton();
                    }
                });
    }
    private void showNoDataMessage(String message){
        messageTextView.setText(message);
        messageConstraintLayout.setVisibility(View.VISIBLE);
    }

    private void disableRegisterButton(){
        registrarCardView.setEnabled(false);
        registrarTextView.setVisibility(View.INVISIBLE);
        registrarProgressBar.setVisibility(View.VISIBLE);
    }
    private void enableRegisterButton(){
        registrarCardView.setEnabled(true);
        registrarTextView.setVisibility(View.VISIBLE);
        registrarProgressBar.setVisibility(View.GONE);
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
        if (ocupacionCustomEditText.getText().length() == 0){
            Toast.makeText(this, "El campo ocupación está vacío", Toast.LENGTH_LONG).show();
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
        if (direccionCustomEditText.getText().length() == 0){
            Toast.makeText(this, "El campo dirección está vacío", Toast.LENGTH_LONG).show();
            return false;
        }
        if (especialistasAdapter.getEspecialistaSeleccionado() == null){
            Toast.makeText(this, "Seleccione un especialista primero", Toast.LENGTH_LONG).show();
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

    @Override
    public void finish() {
        super.finish();
        PreguntasRepositories.getInstance().reinciarRespuestas();
        overridePendingTransition(R.anim.slide_from_top,R.anim.slide_in_top);
    }
}