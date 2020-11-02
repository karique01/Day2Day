package com.karique.work.dev.day2day.activities;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.cardview.widget.CardView;

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
import com.jaredrummler.materialspinner.MaterialSpinner;
import com.karique.work.dev.day2day.R;
import com.karique.work.dev.day2day.models.NewPasswordEmail;
import com.karique.work.dev.day2day.models.NewPasword;
import com.karique.work.dev.day2day.network.Day2DayApi;
import com.libizo.CustomEditText;

import org.json.JSONObject;

public class ResetPasswordActivity extends AppCompatActivity {
    public static int REQUEST_CODE = 42;

    AppCompatImageButton backAppCompatImageButton;

    CustomEditText emailCustomEditText;
    CustomEditText dniCustomEditText;
    MaterialSpinner tiposMaterialSpinner;

    CardView enviarCorreoCardView;
    ProgressBar enviarCorreoProgressBar;
    TextView enviarCorreoTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);
        hide();

        inicializarControles();
        inicializarEventos();
        inicializarDatos();
    }

    private void inicializarControles() {
        backAppCompatImageButton = findViewById(R.id.backAppCompatImageButton);

        emailCustomEditText = findViewById(R.id.emailCustomEditText);
        dniCustomEditText = findViewById(R.id.dniCustomEditText);
        tiposMaterialSpinner = findViewById(R.id.tiposMaterialSpinner);

        enviarCorreoCardView = findViewById(R.id.enviarCorreoCardView);
        enviarCorreoProgressBar = findViewById(R.id.enviarCorreoProgressBar);
        enviarCorreoTextView = findViewById(R.id.enviarCorreoTextView);
    }
    private void inicializarEventos() {
        backAppCompatImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        enviarCorreoCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cambiarPassword();
            }
        });
    }
    private void inicializarDatos() {
        tiposMaterialSpinner.setItems("Paciente", "Tutor");
    }
    private void cambiarPassword() {
        if (!validacionCampos()) return;

        NewPasswordEmail newPasswordEmail = new NewPasswordEmail();
        newPasswordEmail.setDni(dniCustomEditText.getText().toString());
        newPasswordEmail.setEmail(emailCustomEditText.getText().toString());
        newPasswordEmail.setTipo(tiposMaterialSpinner.getSelectedIndex() + 1);

        disableRegisterButton();
        AndroidNetworking.post(Day2DayApi.SEND_RESET_PASSWORD_EMAIL_URL)
                .addHeaders("Content-Type", "application/json")
                .addJSONObjectBody(newPasswordEmail.toJsonObject())
                .setPriority(Priority.HIGH)
                .setTag(getString(R.string.app_name))
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Toast.makeText(ResetPasswordActivity.this, "Se le envió el correo correctamente", Toast.LENGTH_LONG).show();
                        setResult(Activity.RESULT_OK);
                        finish();
                    }

                    @Override
                    public void onError(ANError anError) {
                        Toast.makeText(ResetPasswordActivity.this, "Error al enviar el correo: puede que su correo, documento de identidad o tipo ed usuario sean los incorrectos.", Toast.LENGTH_LONG).show();
                        enableRegisterButton();
                    }
                });
    }
    private void disableRegisterButton(){
        enviarCorreoCardView.setEnabled(false);
        enviarCorreoTextView.setVisibility(View.INVISIBLE);
        enviarCorreoProgressBar.setVisibility(View.VISIBLE);
    }
    private void enableRegisterButton(){
        enviarCorreoCardView.setEnabled(true);
        enviarCorreoTextView.setVisibility(View.VISIBLE);
        enviarCorreoProgressBar.setVisibility(View.GONE);
    }
    private boolean validacionCampos(){
        if (emailCustomEditText.getText().length() == 0){
            Toast.makeText(this, "El campo email está vacío", Toast.LENGTH_LONG).show();
            return false;
        }
        if (dniCustomEditText.getText().length() == 0){
            Toast.makeText(this, "El campo dni está vacío", Toast.LENGTH_LONG).show();
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
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
}