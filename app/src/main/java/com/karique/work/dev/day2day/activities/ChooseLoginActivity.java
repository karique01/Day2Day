package com.karique.work.dev.day2day.activities;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.karique.work.dev.day2day.R;

public class ChooseLoginActivity extends AppCompatActivity {
    public static int REQUEST_CODE = 14;

    AppCompatImageButton backAppCompatImageButton;
    CardView accionPacienteCardView;
    CardView accionTutorCardView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_login);
        hide();

        inicializarControles();
        inicializarEventos();
    }

    private void inicializarControles() {
        backAppCompatImageButton = findViewById(R.id.backAppCompatImageButton);
        accionPacienteCardView = findViewById(R.id.accionPacienteCardView);
        accionTutorCardView = findViewById(R.id.accionTutorCardView);
    }
    private void inicializarEventos() {
        backAppCompatImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        accionPacienteCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mostrarLoginPacienteActivity();
            }
        });
        accionTutorCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mostrarLoginTutorActivity();
            }
        });
    }
    private void mostrarLoginPacienteActivity(){
        Intent intent = new Intent(this, LoginPacienteActivity.class);
        startActivityForResult(intent, LoginPacienteActivity.REQUEST_CODE);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }
    private void mostrarLoginTutorActivity(){
        Intent intent = new Intent(this, LoginTutorActivity.class);
        startActivityForResult(intent, LoginTutorActivity.REQUEST_CODE);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
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
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
}