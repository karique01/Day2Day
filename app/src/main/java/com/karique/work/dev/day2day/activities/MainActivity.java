package com.karique.work.dev.day2day.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.karique.work.dev.day2day.R;
import com.karique.work.dev.day2day.session.SessionManager;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class MainActivity extends AppCompatActivity {

    CardView loginCardView;
    CardView registrarCardView;
    CardView resetPasswordCardView;

    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        hide();

        validarSessionPrevia();
        inicializarControles();
        inicializarEventos();
    }

    private void inicializarControles(){
        loginCardView = findViewById(R.id.loginCardView);
        registrarCardView = findViewById(R.id.registrarCardView);
        resetPasswordCardView = findViewById(R.id.resetPasswordCardView);
    }
    private void inicializarEventos() {
        loginCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mostrarChooseLoginActivity();
            }
        });
        registrarCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mostrarChooseRegisterActivity();
            }
        });
        resetPasswordCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mostrarResetPasswordActivity();
            }
        });
    }
    private void mostrarChooseLoginActivity(){
        Intent intent = new Intent(this, ChooseLoginActivity.class);
        startActivityForResult(intent, ChooseLoginActivity.REQUEST_CODE);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }
    private void mostrarChooseRegisterActivity(){
        Intent intent = new Intent(this, ChooseRegisterActivity.class);
        startActivityForResult(intent, ChooseRegisterActivity.REQUEST_CODE);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }
    private void mostrarResetPasswordActivity(){
        Intent intent = new Intent(this, ResetPasswordActivity.class);
        startActivityForResult(intent, ResetPasswordActivity.REQUEST_CODE);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }
    private void validarSessionPrevia(){
        sessionManager = SessionManager.getInstance(this);
        if (sessionManager.isPaciente()){
            mostrarMainPacienteActivity();
        }
        else if (sessionManager.isTutor()) {
            mostrarMainTutorActivity();
        }
    }
    private void mostrarMainPacienteActivity(){
        Intent intent = new Intent(this, MainPacienteActivity.class);
        startActivityForResult(intent, MainPacienteActivity.REQUEST_CODE);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        finishAffinity();
    }
    private void mostrarMainTutorActivity(){
        Intent intent = new Intent(this, MainTutorActivity.class);
        startActivityForResult(intent, MainTutorActivity.REQUEST_CODE);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        finishAffinity();
    }

    private void hide() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
    }

}