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
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.karique.work.dev.day2day.R;
import com.karique.work.dev.day2day.activities.MainActivity;
import com.karique.work.dev.day2day.models.Especialista;
import com.karique.work.dev.day2day.models.Paciente;
import com.karique.work.dev.day2day.models.Test;
import com.karique.work.dev.day2day.network.Day2DayApi;
import com.karique.work.dev.day2day.session.SessionManager;
import com.karique.work.dev.day2day.util.FuncionesFecha;
import com.libizo.CustomEditText;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Date;

public class MiPerfilFragment extends Fragment {
    TextView especialistaTextView;
    CustomEditText nameCustomEditText;
    CustomEditText lastNameCustomEditText;
    CustomEditText tipoDocumentacionCustomEditText;
    CustomEditText numDocIdentidadCustomEditText;
    CustomEditText fechaDeNacimientoCustomEditText;
    CustomEditText nivelEstudiosCustomEditText;
    CustomEditText ocupacionCustomEditText;
    CustomEditText emailCustomEditText;
    CustomEditText direccionCustomEditText;
    CustomEditText distritoCustomEditText;
    CustomEditText provinciaCustomEditText;
    CustomEditText departamentoCustomEditText;
    CustomEditText telefonoCustomEditText;
    CustomEditText passwordCustomEditText;

    AppCompatImageButton editPasswordAppCompatImageButton;
    CardView logOutCardView;

    SessionManager sessionManager;
    Especialista especialista;
    Paciente paciente;

    public MiPerfilFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_mi_perfil, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        inicializarControles(view);
        inicializarEventos();
        inicializarDatos();
    }

    private void inicializarControles(View view) {
        sessionManager = SessionManager.getInstance(getContext());
        especialistaTextView = view.findViewById(R.id.especialistaTextView);
        nameCustomEditText = view.findViewById(R.id.nameCustomEditText);
        lastNameCustomEditText = view.findViewById(R.id.lastNameCustomEditText);
        tipoDocumentacionCustomEditText = view.findViewById(R.id.tipoDocumentacionCustomEditText);
        numDocIdentidadCustomEditText = view.findViewById(R.id.numDocIdentidadCustomEditText);
        fechaDeNacimientoCustomEditText = view.findViewById(R.id.fechaDeNacimientoCustomEditText);
        nivelEstudiosCustomEditText = view.findViewById(R.id.nivelEstudiosCustomEditText);
        ocupacionCustomEditText = view.findViewById(R.id.ocupacionCustomEditText);
        emailCustomEditText = view.findViewById(R.id.emailCustomEditText);
        direccionCustomEditText = view.findViewById(R.id.direccionCustomEditText);
        distritoCustomEditText = view.findViewById(R.id.distritoCustomEditText);
        provinciaCustomEditText = view.findViewById(R.id.provinciaCustomEditText);
        departamentoCustomEditText = view.findViewById(R.id.departamentoCustomEditText);
        telefonoCustomEditText = view.findViewById(R.id.telefonoCustomEditText);
        passwordCustomEditText = view.findViewById(R.id.passwordCustomEditText);

        editPasswordAppCompatImageButton = view.findViewById(R.id.editPasswordAppCompatImageButton);
        logOutCardView = view.findViewById(R.id.logOutCardView);

        if (sessionManager.isTutor()){
            logOutCardView.setVisibility(View.GONE);
            editPasswordAppCompatImageButton.setVisibility(View.GONE);
        }
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
        Date fecha = FuncionesFecha.getDateWithHourFromString(paciente.getFecha_nacimiento());
        String fechaNac = FuncionesFecha.formatDateYYYYMMDD(fecha);

        nameCustomEditText.setText(paciente.getNombre());
        lastNameCustomEditText.setText(paciente.getApellidos());
        tipoDocumentacionCustomEditText.setText(paciente.getTipo_documentacion());
        numDocIdentidadCustomEditText.setText(paciente.getNum_doc_identidad());
        fechaDeNacimientoCustomEditText.setText(fechaNac);
        nivelEstudiosCustomEditText.setText(paciente.getnivel_estudiosStr());
        ocupacionCustomEditText.setText(paciente.getOcupacion());
        emailCustomEditText.setText(paciente.getCorreo());
        direccionCustomEditText.setText(paciente.getDireccion());
        distritoCustomEditText.setText(paciente.getDistrito());
        provinciaCustomEditText.setText(paciente.getProvincia());
        departamentoCustomEditText.setText(paciente.getDepartamento());
        telefonoCustomEditText.setText(String.valueOf(paciente.getTelefono()));
        getEspecialista();
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public void getEspecialista(){
        onProgressBarChanged.OnProgressBarVisible();

        String especialistaURL = Day2DayApi.ESPECIALISTAS_URL + "/" + paciente.getEspecialista_idespe();

        AndroidNetworking.get(especialistaURL)
                .addHeaders("Content-Type", "application/json")
                .setPriority(Priority.HIGH)
                .setTag(getString(R.string.app_name))
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        onProgressBarChanged.OnProgressBarHide();
                        especialista = Especialista.from(response);
                        setDatosEspecialista(especialista);
                    }
                    @Override
                    public void onError(ANError anError) {
                        onProgressBarChanged.OnProgressBarHide();
                        Toast.makeText(getContext(), "Error al cargar datos del especialista", Toast.LENGTH_LONG).show();
                    }
                });
    }
    private void setDatosEspecialista(Especialista especialista) {
        especialistaTextView.setText(
                String.format(
                        "%s %s\n%s\n%s",
                        especialista.getNombre(), especialista.getApellidos(),
                        especialista.getCorreo(),
                        especialista.getEmpresa())
        );
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
        sessionManager.deletePaciente();
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