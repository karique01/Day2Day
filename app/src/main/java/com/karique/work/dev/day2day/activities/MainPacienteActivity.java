package com.karique.work.dev.day2day.activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.fragment.app.Fragment;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.karique.work.dev.day2day.R;
import com.karique.work.dev.day2day.fragments.MiDiarioFragment;
import com.karique.work.dev.day2day.fragments.MiPerfilFragment;
import com.karique.work.dev.day2day.fragments.TestsFragment;
import com.karique.work.dev.day2day.models.Diario;
import com.karique.work.dev.day2day.models.Paciente;
import com.karique.work.dev.day2day.models.Test;
import com.karique.work.dev.day2day.network.Day2DayApi;
import com.karique.work.dev.day2day.session.SessionManager;
import com.karique.work.dev.day2day.util.FuncionesUtiles;

import org.json.JSONObject;

public class MainPacienteActivity extends AppCompatActivity {
    public static int REQUEST_CODE = 144;

    TextView toolbarTitleTextView;
    ProgressBar progressBar;
    AppCompatImageButton backAppCompatImageButton;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            return navigateAccordingTo(item.getItemId());
        }
    };
    private BottomNavigationView navigation;

    MiDiarioFragment miDiarioFragment;
    MiPerfilFragment miPerfilFragment;
    TestsFragment testsFragment;
    SessionManager sessionManager;

    Paciente paciente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_paciente);
        hide();

        inicializarControles();
        inicializarEventos();
        inicializarDatos();
    }
    private void inicializarControles() {
        toolbarTitleTextView = findViewById(R.id.toolbarTitleTextView);
        progressBar = findViewById(R.id.progressBar);
        backAppCompatImageButton = findViewById(R.id.backAppCompatImageButton);
        navigation = findViewById(R.id.nav_view);

        sessionManager = SessionManager.getInstance(this);

        if (sessionManager.isPaciente()) {
            toolbarTitleTextView.setText(String.format("¡Bienvenido %s!", sessionManager.getnombre()));
            backAppCompatImageButton.setVisibility(View.GONE);
            paciente = sessionManager.toPaciente();
        }
        else {
            toolbarTitleTextView.setText(String.format("Detalles de %s", sessionManager.getnombre()));
            backAppCompatImageButton.setVisibility(View.VISIBLE);
            paciente = Paciente.from(getIntent().getExtras());
        }

        navigateAccordingTo(R.id.navigation_paciente_mi_diario);
    }
    private void inicializarEventos() {
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        backAppCompatImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    private void inicializarDatos() {}

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
        if (id == R.id.navigation_paciente_mi_diario) {
            return getMiDiarioFragment();
        }
        else if (id == R.id.navigation_paciente_tests) {
            return getTestsFragment();
        }
        else if (id == R.id.navigation_paciente_mi_perfil) {
            return getMiPerfilFragment();
        }
        return null;
    }
    private MiDiarioFragment getMiDiarioFragment(){
        miDiarioFragment = new MiDiarioFragment();
        miDiarioFragment.setPaciente(paciente);
        miDiarioFragment.setOnAddDiarioClicked(new MiDiarioFragment.OnAddDiarioClickedListener() {
            @Override
            public void OnAddDiarioClicked() {
                mostrarAgregarDiarioActivity();
            }
        });
        miDiarioFragment.setOnDiarioClicked(new MiDiarioFragment.OnDiarioClickedListener() {
            @Override
            public void OnDiarioClicked(Diario diario) {
                mostrarDetalleDiarioDialog(diario);
            }
        });
        miDiarioFragment.setOnDiarioLongClicked(new MiDiarioFragment.OnDiarioLongClickedListener() {
            @Override
            public void OnDiarioLongClicked(Diario diario) {
                showAlertDeleteDiario(diario);
            }
        });
        miDiarioFragment.setOnProgressBarChanged(new MiDiarioFragment.OnProgressBarChanged() {
            @Override
            public void OnProgressBarVisible() {
                progressBar.setVisibility(View.VISIBLE);
            }
            @Override
            public void OnProgressBarHide() {
                progressBar.setVisibility(View.GONE);
            }
        });
        return miDiarioFragment;
    }
    private MiPerfilFragment getMiPerfilFragment(){
        miPerfilFragment = new MiPerfilFragment();
        miPerfilFragment.setPaciente(paciente);
        miPerfilFragment.setOnProgressBarChanged(new MiPerfilFragment.OnProgressBarChanged() {
            @Override
            public void OnProgressBarVisible() {
                progressBar.setVisibility(View.VISIBLE);
            }

            @Override
            public void OnProgressBarHide() {
                progressBar.setVisibility(View.GONE);
            }
        });
        miPerfilFragment.setOnEditPasswordClicked(new MiPerfilFragment.OnEditPasswordClickedListener() {
            @Override
            public void OnEditPasswordClicked() {
                mostrarResetPasswordInAppActivity();
            }
        });
        return miPerfilFragment;
    }
    private TestsFragment getTestsFragment(){
        testsFragment = new TestsFragment();
        testsFragment.setPaciente(paciente);
        testsFragment.setOnAddTestClicked(new TestsFragment.OnAddTestClickedListener() {
            @Override
            public void OnAddTestClicked() {
                mostrarRealizarTestActivity(RealizarTestActivity.TIPO_TEST_PHQ9);
            }
        });
        testsFragment.setOnTestClicked(new TestsFragment.OnTestClickedListener() {
            @Override
            public void OnTestClicked(Test Test) {

            }
        });
        testsFragment.setOnTestLongClicked(new TestsFragment.OnTestLongClickedListener() {
            @Override
            public void OnTestLongClicked(Test test) {
                showAlertDeleteTest(test);
            }
        });
        testsFragment.setOnProgressBarChanged(new TestsFragment.OnProgressBarChanged() {
            @Override
            public void OnProgressBarVisible() {
                progressBar.setVisibility(View.VISIBLE);
            }
            @Override
            public void OnProgressBarHide() {
                progressBar.setVisibility(View.GONE);
            }
        });
        return testsFragment;
    }
    private void mostrarRealizarTestActivity(int tipoTest){
        Intent intent = new Intent(this, RealizarTestActivity.class);
        intent.putExtra("tipoTest", tipoTest);
        startActivityForResult(intent, RealizarTestActivity.REQUEST_CODE);
        overridePendingTransition( R.anim.slide_in_up, R.anim.slide_out_up);
    }
    private void mostrarAgregarDiarioActivity() {
        Intent intent = new Intent(this, AgregarDiarioActivity.class);
        startActivityForResult(intent, AgregarDiarioActivity.REQUEST_CODE);
        overridePendingTransition( R.anim.slide_in_up, R.anim.slide_out_up);
    }
    private void mostrarDetalleDiarioDialog(Diario diario){
        FuncionesUtiles.mostrarMensaje(diario.getFecha_horaFormated(), diario.getTexto(), this);
    }
    private void mostrarResetPasswordInAppActivity() {
        Intent intent = new Intent(this, ResetPasswordInAppActivity.class);
        startActivityForResult(intent, ResetPasswordInAppActivity.REQUEST_CODE);
        overridePendingTransition( R.anim.slide_in_up, R.anim.slide_out_up);
    }
    private void showAlertDeleteDiario(final Diario diario){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("¿Seguro que desea eliminar esta entrada del diario?")
                .setCancelable(true)
                .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        deleteDiario(diario);
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }
    private void deleteDiario(Diario diario){
        progressBar.setVisibility(View.VISIBLE);

        AndroidNetworking.delete(Day2DayApi.DIARIOS_URL + "/" + diario.getIddiario())
                .addHeaders("Content-Type", "application/json")
                .setPriority(Priority.HIGH)
                .setTag(getString(R.string.app_name))
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(
                                MainPacienteActivity.this,
                                "Diario eliminado correctamente",
                                Toast.LENGTH_SHORT
                        ).show();
                        miDiarioFragment.getDiarios();
                    }

                    @Override
                    public void onError(ANError anError) {
                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(
                                MainPacienteActivity.this,
                                "Eliminar diario" + " - " + "Error al eliminar el diario: " + anError.getErrorCode(),
                                Toast.LENGTH_SHORT
                        ).show();
                    }
                });
    }
    private void showAlertDeleteTest(final Test test){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("¿Seguro que desea eliminar este test?")
                .setCancelable(true)
                .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        deleteTest(test);
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }
    private void deleteTest(Test test){
        progressBar.setVisibility(View.VISIBLE);

        AndroidNetworking.delete(Day2DayApi.TESTS_URL + "/" + test.getIdtest())
                .addHeaders("Content-Type", "application/json")
                .setPriority(Priority.HIGH)
                .setTag(getString(R.string.app_name))
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(
                                MainPacienteActivity.this,
                                "Test eliminado correctamente",
                                Toast.LENGTH_SHORT
                        ).show();
                        testsFragment.getTests();
                    }

                    @Override
                    public void onError(ANError anError) {
                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(
                                MainPacienteActivity.this,
                                "Eliminar test" + " - " + "Error al eliminar el test: " + anError.getErrorCode(),
                                Toast.LENGTH_SHORT
                        ).show();
                    }
                });
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
        //test agregado correctamente
        if (requestCode == RealizarTestActivity.REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                if (testsFragment != null)
                    testsFragment.getTests();
            }
        }
        //diario agregado correctamente
        if (requestCode == AgregarDiarioActivity.REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                if (miDiarioFragment != null)
                    miDiarioFragment.getDiarios();
            }
        }
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
}