package com.karique.work.dev.day2day.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.karique.work.dev.day2day.R;
import com.karique.work.dev.day2day.adapters.PacientesAdapter;
import com.karique.work.dev.day2day.models.Paciente;
import com.karique.work.dev.day2day.network.Day2DayApi;
import com.karique.work.dev.day2day.session.SessionManager;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MisApoderadosFragment extends Fragment {

    private ConstraintLayout messageConstraintLayout;
    private TextView messageTextView;

    private RecyclerView pacientesRecyclerView;
    private PacientesAdapter pacientesAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private List<Paciente> pacientes;

    private CardView registrarCardView;
    private SessionManager sessionManager;

    public MisApoderadosFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_mis_apoderados, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        sessionManager = SessionManager.getInstance(getContext());
        pacientesRecyclerView = view.findViewById(R.id.pacientesRecyclerView);
        messageConstraintLayout = view.findViewById(R.id.messageConstraintLayout);
        messageTextView = view.findViewById(R.id.messageTextView);

        pacientes = new ArrayList<>();
        pacientesAdapter = new PacientesAdapter(pacientes);
        pacientesAdapter.setOnPacienteClicked(new PacientesAdapter.OnPacienteClickedListener() {
            @Override
            public void OnPacienteClicked(Paciente Paciente) {
                if (onPacienteClickedListener != null){
                    onPacienteClickedListener.OnPacienteClicked(Paciente);
                }
            }
        });

        layoutManager = new LinearLayoutManager(getActivity());
        pacientesRecyclerView.setAdapter(pacientesAdapter);
        pacientesRecyclerView.setLayoutManager(layoutManager);

        registrarCardView = view.findViewById(R.id.registrarCardView);
        registrarCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onAddPacienteClickedListener != null){
                    onAddPacienteClickedListener.OnAddPacienteClicked();
                }
            }
        });

        getPacientes();
    }

    public void getPacientes(){
        onProgressBarChanged.OnProgressBarVisible();

        AndroidNetworking.get(Day2DayApi.PACIENTS_BY_TUTOR(String.valueOf(sessionManager.getidtutor())))
                .addHeaders("Content-Type", "application/json")
                .setPriority(Priority.HIGH)
                .setTag(getString(R.string.app_name))
                .build()
                .getAsJSONArray(new JSONArrayRequestListener() {
                    @Override
                    public void onResponse(JSONArray response) {
                        onProgressBarChanged.OnProgressBarHide();
                        messageConstraintLayout.setVisibility(View.GONE);

                        pacientes.clear();
                        pacientes.addAll(Paciente.from(response));
                        Collections.reverse(pacientes);
                        pacientesAdapter.notifyDataSetChanged();

                        if (pacientes.isEmpty()){
                            showNoDataMessage("Sin datos");
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        onProgressBarChanged.OnProgressBarHide();
                        showNoDataMessage("Error de carga: " + anError.getErrorDetail());
                    }
                });
    }
    private void showNoDataMessage(String message){
        messageTextView.setText(message);
        messageConstraintLayout.setVisibility(View.VISIBLE);
    }


    public interface OnPacienteClickedListener {
        void OnPacienteClicked(Paciente Paciente);
    }
    private OnPacienteClickedListener onPacienteClickedListener;
    public void setOnPacienteClicked(OnPacienteClickedListener onPacienteClickedListener) {
        this.onPacienteClickedListener = onPacienteClickedListener;
    }

    public interface OnAddPacienteClickedListener {
        void OnAddPacienteClicked();
    }
    private OnAddPacienteClickedListener onAddPacienteClickedListener;
    public void setOnAddPacienteClicked(OnAddPacienteClickedListener onAddPacienteClickedListener) {
        this.onAddPacienteClickedListener = onAddPacienteClickedListener;
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