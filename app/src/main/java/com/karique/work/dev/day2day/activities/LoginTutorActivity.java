package com.karique.work.dev.day2day.activities;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.google.firebase.iid.FirebaseInstanceId;
import com.karique.work.dev.day2day.R;
import com.karique.work.dev.day2day.models.Paciente;
import com.karique.work.dev.day2day.models.Tutor;
import com.karique.work.dev.day2day.network.Day2DayApi;
import com.karique.work.dev.day2day.session.SessionManager;
import com.libizo.CustomEditText;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginTutorActivity extends AppCompatActivity {
    public static int REQUEST_CODE = 326;

    AppCompatImageButton backAppCompatImageButton;
    CustomEditText emailCustomEditText;
    CustomEditText passwordCustomEditText;
    CardView loginCardView;
    ProgressBar loginProgressBar;
    TextView loginTextView;

    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_tutor);
        hide();

        inicializarControles();
        inicializarEventos();
    }

    private void inicializarControles() {
        sessionManager = SessionManager.getInstance(this);
        backAppCompatImageButton = findViewById(R.id.backAppCompatImageButton);
        emailCustomEditText = findViewById(R.id.emailCustomEditText);
        passwordCustomEditText = findViewById(R.id.passwordCustomEditText);
        loginCardView = findViewById(R.id.loginCardView);
        loginProgressBar = findViewById(R.id.loginProgressBar);
        loginTextView = findViewById(R.id.loginTextView);
    }
    private void inicializarEventos() {
        backAppCompatImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        loginCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });
    }
    private void login(){
        //validaciones
        if (emailCustomEditText.getText().length() == 0){
            Toast.makeText(LoginTutorActivity.this, "El campo correo está vacío", Toast.LENGTH_LONG).show();
            return;
        }
        if (passwordCustomEditText.getText().length() == 0){
            Toast.makeText(LoginTutorActivity.this, "El campo contraseña está vacío", Toast.LENGTH_LONG).show();
            return;
        }


        disableLoginButton();
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
                        updateToken();
                        mostrarMainTutorActivity();
                    }

                    @Override
                    public void onError(ANError anError) {
                        Toast.makeText(LoginTutorActivity.this, "Error de ingreso", Toast.LENGTH_LONG).show();
                        enableLoginButton();
                    }
                });
    }
    private void mostrarMainTutorActivity(){
        Intent intent = new Intent(this, MainTutorActivity.class);
        startActivityForResult(intent, MainTutorActivity.REQUEST_CODE);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        finishAffinity();
    }
    private void updateToken(){
        SessionManager sessionManager = SessionManager.getInstance(this);
        if (sessionManager.getidtutor() == 0){
            return;
        }

        JSONObject newCourseJsonObject = new JSONObject();
        try {
            newCourseJsonObject.put("userId", sessionManager.getidtutor());
            newCourseJsonObject.put("token", FirebaseInstanceId.getInstance().getToken());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        AndroidNetworking.post(Day2DayApi.TOKEN_FCM_URL)
                .addHeaders("Content-Type", "application/json")
                .setPriority(Priority.HIGH)
                .addJSONObjectBody(newCourseJsonObject)
                .setTag(getString(R.string.app_name))
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {

                    }

                    @Override
                    public void onError(ANError anError) {

                    }
                });
    }

    private void enableLoginButton() {
        loginCardView.setEnabled(true);
        loginTextView.setVisibility(View.VISIBLE);
        loginProgressBar.setVisibility(View.INVISIBLE);
    }
    private void disableLoginButton() {
        loginCardView.setEnabled(false);
        loginTextView.setVisibility(View.INVISIBLE);
        loginProgressBar.setVisibility(View.VISIBLE);
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