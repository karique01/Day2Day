package com.karique.work.dev.day2day.activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.karique.work.dev.day2day.R;
import com.karique.work.dev.day2day.fragments.MiPerfilTutorFragment;
import com.karique.work.dev.day2day.fragments.MisApoderadosFragment;
import com.karique.work.dev.day2day.models.Paciente;
import com.karique.work.dev.day2day.session.SessionManager;

public class MainTutorActivity extends AppCompatActivity {
    public static int REQUEST_CODE = 145;

    TextView toolbarTitleTextView;
    ProgressBar progressBar;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            return navigateAccordingTo(item.getItemId());
        }
    };
    private BottomNavigationView navigation;

    MisApoderadosFragment misApoderadosFragment;
    MiPerfilTutorFragment miPerfilTutorFragment;
    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_tutor);
        hide();

        inicializarControles();
        inicializarEventos();
        inicializarDatos();
    }
    private void inicializarControles() {
        toolbarTitleTextView = findViewById(R.id.toolbarTitleTextView);
        progressBar = findViewById(R.id.progressBar);
        navigation = findViewById(R.id.nav_view);
    }
    private void inicializarEventos() {
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }
    private void inicializarDatos() {
        sessionManager = SessionManager.getInstance(this);
        toolbarTitleTextView.setText(String.format("¡Bienvenido %s!", sessionManager.getnombre()));
        navigateAccordingTo(R.id.navigation_tutor_mis_apoderados);
    }

    private boolean navigateAccordingTo(int id){
        try
        {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.content,getFragmentFor(id))
                    .commit();
            return true;
        }
        catch (NullPointerException e) {
            e.printStackTrace();
        }
        return false;
    }
    private Fragment getFragmentFor(int id) {
        if (id == R.id.navigation_tutor_mis_apoderados) {
            return getMisApoderadosFragment();
        }
        else if (id == R.id.navigation_tutor_mi_perfil) {
            return getMiPerfilTutorFragment();
        }
        return null;
    }
    private MisApoderadosFragment getMisApoderadosFragment(){
        misApoderadosFragment = new MisApoderadosFragment();
        misApoderadosFragment.setOnAddPacienteClicked(new MisApoderadosFragment.OnAddPacienteClickedListener() {
            @Override
            public void OnAddPacienteClicked() {
                mostrarRegisterPacienteActivity();
            }
        });
        misApoderadosFragment.setOnPacienteClicked(new MisApoderadosFragment.OnPacienteClickedListener() {
            @Override
            public void OnPacienteClicked(Paciente paciente) {
                mostrarPacienteDetalle(paciente);
            }
        });
        misApoderadosFragment.setOnProgressBarChanged(new MisApoderadosFragment.OnProgressBarChanged() {
            @Override
            public void OnProgressBarVisible() {
                progressBar.setVisibility(View.VISIBLE);
            }

            @Override
            public void OnProgressBarHide() {
                progressBar.setVisibility(View.GONE);
            }
        });
        return misApoderadosFragment;
    }
    private MiPerfilTutorFragment getMiPerfilTutorFragment(){
        miPerfilTutorFragment = new MiPerfilTutorFragment();
        miPerfilTutorFragment.setOnProgressBarChanged(new MiPerfilTutorFragment.OnProgressBarChanged() {
            @Override
            public void OnProgressBarVisible() {
                progressBar.setVisibility(View.VISIBLE);
            }

            @Override
            public void OnProgressBarHide() {
                progressBar.setVisibility(View.GONE);
            }
        });
        miPerfilTutorFragment.setOnEditPasswordClicked(new MiPerfilTutorFragment.OnEditPasswordClickedListener() {
            @Override
            public void OnEditPasswordClicked() {
                mostrarResetPasswordInAppActivity();
            }
        });
        return miPerfilTutorFragment;
    }
    private void mostrarRegisterPacienteActivity() {
        Intent intent = new Intent(this, RegisterPacienteActivity.class);
        startActivityForResult(intent, RegisterPacienteActivity.REQUEST_CODE);
        overridePendingTransition( R.anim.slide_in_up, R.anim.slide_out_up);
    }
    private void mostrarResetPasswordInAppActivity() {
        Intent intent = new Intent(this, ResetPasswordInAppActivity.class);
        startActivityForResult(intent, ResetPasswordInAppActivity.REQUEST_CODE);
        overridePendingTransition( R.anim.slide_in_up, R.anim.slide_out_up);
    }
    private void mostrarPacienteDetalle(Paciente paciente){
        Intent intent = new Intent(this, MainPacienteActivity.class);
        intent.putExtras(paciente.toBundle());
        startActivityForResult(intent, MainPacienteActivity.REQUEST_CODE);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }


    private void hide() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //paciente agregado correctamente
        if (requestCode == RegisterPacienteActivity.REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                if (misApoderadosFragment != null)
                    misApoderadosFragment.getPacientes();
            }
        }
    }
}