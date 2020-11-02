package com.karique.work.dev.day2day.activities;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
import com.karique.work.dev.day2day.adapters.TestsAdapter;
import com.karique.work.dev.day2day.models.Pregunta;
import com.karique.work.dev.day2day.models.Test;
import com.karique.work.dev.day2day.network.Day2DayApi;
import com.karique.work.dev.day2day.repositories.PreguntasRepositories;
import com.karique.work.dev.day2day.session.SessionManager;
import com.karique.work.dev.day2day.util.FuncionesFecha;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class RealizarTestActivity extends AppCompatActivity {
    public static int REQUEST_CODE = 146;
    public static int TIPO_TEST_PHQ9 = 1;
    public static int TIPO_TEST_WHO5 = 2;

    TextView toolbarTitleTextView;
    ProgressBar progressBar;
    AppCompatImageButton backAppCompatImageButton;

    SessionManager sessionManager;
    int tipoTest;
    TextView especialistaTitleTextView;
    CardView guardarCardView;
    ProgressBar guardarProgressBar;
    TextView guardarTextView;

    private ConstraintLayout messageConstraintLayout;
    private TextView messageTextView;

    private RecyclerView preguntasRecyclerView;
    private PreguntasAdapter preguntasAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private List<Pregunta> preguntas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_realizar_test);
        hide();

        inicializarControles();
        inicializarEventos();
        inicializarDatos();
    }
    private void inicializarControles() {
        toolbarTitleTextView = findViewById(R.id.toolbarTitleTextView);
        progressBar = findViewById(R.id.progressBar);
        backAppCompatImageButton = findViewById(R.id.backAppCompatImageButton);

        especialistaTitleTextView = findViewById(R.id.especialistaTitleTextView);
        guardarCardView = findViewById(R.id.guardarCardView);
        guardarProgressBar = findViewById(R.id.guardarProgressBar);
        guardarTextView = findViewById(R.id.guardarTextView);

        preguntasRecyclerView = findViewById(R.id.preguntasRecyclerView);
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
        guardarCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                agregarTest();
            }
        });
    }
    private void inicializarDatos()     {
        sessionManager = SessionManager.getInstance(this);
        tipoTest = getIntent().getIntExtra("tipoTest", 0);
        toolbarTitleTextView.setText(tipoTest == 1 ? "Test PHQ-9" : "Test WHO-5");
        especialistaTitleTextView.setText(tipoTest == 1 ? "Durante las últimas 2 semanas, ¿qué tan seguido ha tenido molestias debido a los siguientes problemas?" : "Test WHO-5");

        preguntas = PreguntasRepositories.getInstance().getPreguntas();
        preguntasAdapter = new PreguntasAdapter(preguntas);
        layoutManager = new LinearLayoutManager(this);
        preguntasRecyclerView.setAdapter(preguntasAdapter);
        preguntasRecyclerView.setLayoutManager(layoutManager);
    }
    private void agregarTest() {
        Test test = new Test();
        test.setNombre_test(tipoTest == 1 ? "PHQ-9" : "WHO-5");
        test.setResultado(preguntasAdapter.getResultado());
        test.setRealizado(1);
        test.setFecha_hora(FuncionesFecha.formatDateForAPI(FuncionesFecha.getCurrentDate()));
        test.setPaciente_idpaciente(sessionManager.getidpaciente());

        disableRegisterButton();
        AndroidNetworking.post(Day2DayApi.TESTS_URL)
                .addHeaders("Content-Type", "application/json")
                .addJSONObjectBody(test.toJsonObject())
                .setPriority(Priority.HIGH)
                .setTag(getString(R.string.app_name))
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Toast.makeText(RealizarTestActivity.this, "Test registrado", Toast.LENGTH_LONG).show();
                        setResult(Activity.RESULT_OK);
                        finish();
                    }

                    @Override
                    public void onError(ANError anError) {
                        Toast.makeText(RealizarTestActivity.this, "Error al registrar el test", Toast.LENGTH_LONG).show();
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