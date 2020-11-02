package com.karique.work.dev.day2day.activities;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.karique.work.dev.day2day.R;
import com.karique.work.dev.day2day.adapters.PreguntasAdapter;
import com.karique.work.dev.day2day.models.Diario;
import com.karique.work.dev.day2day.models.Test;
import com.karique.work.dev.day2day.network.Day2DayApi;
import com.karique.work.dev.day2day.repositories.PreguntasRepositories;
import com.karique.work.dev.day2day.session.SessionManager;
import com.karique.work.dev.day2day.util.FuncionesFecha;
import com.libizo.CustomEditText;

import org.json.JSONException;
import org.json.JSONObject;

public class AgregarDiarioActivity extends AppCompatActivity {
    public static int REQUEST_CODE = 149;

    TextView toolbarTitleTextView;
    ProgressBar progressBar;
    AppCompatImageButton backAppCompatImageButton;

    CustomEditText textoCustomEditText;
    SessionManager sessionManager;
    TextView fechaTestTextView;
    CardView guardarCardView;
    ProgressBar guardarProgressBar;
    TextView guardarTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_diario);
        hide();

        inicializarControles();
        inicializarEventos();
        inicializarDatos();
    }
    private void inicializarControles() {
        toolbarTitleTextView = findViewById(R.id.toolbarTitleTextView);
        progressBar = findViewById(R.id.progressBar);
        backAppCompatImageButton = findViewById(R.id.backAppCompatImageButton);

        textoCustomEditText = findViewById(R.id.textoCustomEditText);
        fechaTestTextView = findViewById(R.id.fechaTestTextView);
        guardarCardView = findViewById(R.id.guardarCardView);
        guardarProgressBar = findViewById(R.id.guardarProgressBar);
        guardarTextView = findViewById(R.id.guardarTextView);
    }
    private void inicializarEventos() {
        backAppCompatImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        guardarCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                analizarTexto();
            }
        });
    }
    private void inicializarDatos() {
        sessionManager = SessionManager.getInstance(this);
        toolbarTitleTextView.setText("Nueva entrada");
        fechaTestTextView.setText(FuncionesFecha.formatDateToTextForComment(FuncionesFecha.getCurrentDate()));
    }
    private void analizarTexto(){
        if (textoCustomEditText.getText().length() == 0){
            Toast.makeText(this, "No deje el campo de texto vacío", Toast.LENGTH_LONG).show();
            return;
        }

        disableRegisterButton();
        AndroidNetworking.post("https://api.meaningcloud.com/sentiment-2.1")
                .addHeaders("Content-Type", "application/json")
                .addQueryParameter("key", "22e4a35abfc7ff41a5804353e9939c9b")
                .addQueryParameter("of", "json")
                .addQueryParameter("lang", "es")
                .addQueryParameter("ilang", "es")
                .addQueryParameter("txt", textoCustomEditText.getText().toString())
                .setPriority(Priority.HIGH)
                .setTag(getString(R.string.app_name))
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        String resultado = "";
                        try {
                            resultado = response.getString("score_tag");
                            agregarDiario(resultado);
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(AgregarDiarioActivity.this, "Error al analizar el texto parse", Toast.LENGTH_LONG).show();
                            enableRegisterButton();
                        }
                    }
                    @Override
                    public void onError(ANError anError) {
                        Toast.makeText(AgregarDiarioActivity.this, "Error al analizar el texto", Toast.LENGTH_LONG).show();
                        enableRegisterButton();
                    }
                });
    }
    private void agregarDiario(String resultado) {
        Diario diario = new Diario();
        diario.setTexto(textoCustomEditText.getText().toString());
        diario.setResultado(resultado);
        diario.setRealizado(1);
        diario.setFecha_hora(FuncionesFecha.formatDateForAPI(FuncionesFecha.getCurrentDate()));
        diario.setPaginas(1);
        diario.setPaciente_idpaciente(sessionManager.getidpaciente());

        disableRegisterButton();
        AndroidNetworking.post(Day2DayApi.DIARIOS_URL)
                .addHeaders("Content-Type", "application/json")
                .addJSONObjectBody(diario.toJsonObject())
                .setPriority(Priority.HIGH)
                .setTag(getString(R.string.app_name))
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Toast.makeText(AgregarDiarioActivity.this, "Diario registrado", Toast.LENGTH_LONG).show();
                        setResult(Activity.RESULT_OK);
                        finish();
                    }

                    @Override
                    public void onError(ANError anError) {
                        Toast.makeText(AgregarDiarioActivity.this, "Error al registrar el Diario", Toast.LENGTH_LONG).show();
                        enableRegisterButton();
                    }
                });
    }
    private void disableRegisterButton(){
        guardarCardView.setEnabled(false);
        guardarTextView.setVisibility(View.INVISIBLE);
        guardarProgressBar.setVisibility(View.VISIBLE);
    }
    private void enableRegisterButton(){
        guardarCardView.setEnabled(true);
        guardarTextView.setVisibility(View.VISIBLE);
        guardarProgressBar.setVisibility(View.GONE);
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