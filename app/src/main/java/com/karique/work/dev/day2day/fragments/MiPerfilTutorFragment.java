package com.karique.work.dev.day2day.fragments;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.karique.work.dev.day2day.R;
import com.karique.work.dev.day2day.activities.MainActivity;
import com.karique.work.dev.day2day.session.SessionManager;
import com.karique.work.dev.day2day.util.FuncionesFecha;
import com.libizo.CustomEditText;

import java.util.Date;

public class MiPerfilTutorFragment extends Fragment {
    CustomEditText nameCustomEditText;
    CustomEditText lastNameCustomEditText;
    CustomEditText tipoDocumentacionCustomEditText;
    CustomEditText numDocIdentidadCustomEditText;
    CustomEditText fechaDeNacimientoCustomEditText;
    CustomEditText emailCustomEditText;
    CustomEditText telefonoCustomEditText;
    CustomEditText passwordCustomEditText;
    CustomEditText relacionCustomEditText;

    AppCompatImageButton editPasswordAppCompatImageButton;
    CardView logOutCardView;

    SessionManager sessionManager;

    public MiPerfilTutorFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_mi_perfil_tutor, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        inicializarControles(view);
        inicializarEventos();
        inicializarDatos();
    }

    private void inicializarControles(View view) {
        nameCustomEditText = view.findViewById(R.id.nameCustomEditText);
        lastNameCustomEditText = view.findViewById(R.id.lastNameCustomEditText);
        tipoDocumentacionCustomEditText = view.findViewById(R.id.tipoDocumentacionCustomEditText);
        numDocIdentidadCustomEditText = view.findViewById(R.id.numDocIdentidadCustomEditText);
        fechaDeNacimientoCustomEditText = view.findViewById(R.id.fechaDeNacimientoCustomEditText);
        emailCustomEditText = view.findViewById(R.id.emailCustomEditText);
        telefonoCustomEditText = view.findViewById(R.id.telefonoCustomEditText);
        passwordCustomEditText = view.findViewById(R.id.passwordCustomEditText);
        relacionCustomEditText = view.findViewById(R.id.relacionCustomEditText);

        editPasswordAppCompatImageButton = view.findViewById(R.id.editPasswordAppCompatImageButton);
        logOutCardView = view.findViewById(R.id.logOutCardView);
    }
    private void inicializarEventos() {
        logOutCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showLogOutDialog();
            }
        });
        editPasswordAppCompatImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onEditPasswordClickedListener != null){
                    onEditPasswordClickedListener.OnEditPasswordClicked();
                }
            }
        });
    }
    private void inicializarDatos() {
        sessionManager = SessionManager.getInstance(getContext());
        Date fecha = FuncionesFecha.getDateWithHourFromString(sessionManager.getfecha_nacimiento());
        String fechaNac = FuncionesFecha.formatDateYYYYMMDD(fecha);


        nameCustomEditText.setText(sessionManager.getnombre());
        lastNameCustomEditText.setText(sessionManager.getapellidos());
        tipoDocumentacionCustomEditText.setText(sessionManager.gettipo_documentacion());
        numDocIdentidadCustomEditText.setText(sessionManager.getnum_doc_identidad());
        fechaDeNacimientoCustomEditText.setText(fechaNac);
        emailCustomEditText.setText(sessionManager.getcorreo());
        telefonoCustomEditText.setText(String.valueOf(sessionManager.gettelefono()));
        relacionCustomEditText.setText(sessionManager.getrelacion());
    }
    private void showLogOutDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("¿Seguro que desea cerrar sesión?")
                .setCancelable(true)
                .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        logOut();
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
    private void logOut(){
        sessionManager.deleteTutor();
        Intent intent = new Intent(getActivity(), MainActivity.class);
        startActivity(intent);
        getActivity().finish();
    }

    public interface OnEditPasswordClickedListener {
        void OnEditPasswordClicked();
    }
    private OnEditPasswordClickedListener onEditPasswordClickedListener;
    public void setOnEditPasswordClicked(OnEditPasswordClickedListener onEditPasswordClickedListener) {
        this.onEditPasswordClickedListener = onEditPasswordClickedListener;
    }

    public interface OnProgressBarChanged {
        void OnProgressBarVisible();
        void OnProgressBarHide();
    }
    private OnProgressBarChanged onProgressBarChanged;
    public void setOnProgressBarChanged(OnProgressBarChanged onProgressBarChanged) {
        this.onProgressBarChanged = onProgressBarChanged;
    }
}