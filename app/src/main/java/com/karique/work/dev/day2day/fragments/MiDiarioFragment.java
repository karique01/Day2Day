package com.karique.work.dev.day2day.fragments;

import android.app.Activity;
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
import com.karique.work.dev.day2day.adapters.DiariosAdapter;
import com.karique.work.dev.day2day.models.Diario;
import com.karique.work.dev.day2day.models.Paciente;
import com.karique.work.dev.day2day.network.Day2DayApi;
import com.karique.work.dev.day2day.session.SessionManager;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MiDiarioFragment extends Fragment {

    private ConstraintLayout messageConstraintLayout;
    private TextView messageTextView;

    private RecyclerView diariosRecyclerView;
    private DiariosAdapter diariosAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private List<Diario> diarios;

    private CardView nuevoTestCardView;
    private SessionManager sessionManager;

    Paciente paciente;

    public MiDiarioFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_mi_diario, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        sessionManager = SessionManager.getInstance(getContext());
        diariosRecyclerView = view.findViewById(R.id.diariosRecyclerView);
        messageConstraintLayout = view.findViewById(R.id.messageConstraintLayout);
        messageTextView = view.findViewById(R.id.messageTextView);

        diarios = new ArrayList<>();
        diariosAdapter = new DiariosAdapter(diarios);
        diariosAdapter.setEsPaciente(sessionManager.isPaciente());
        diariosAdapter.setOnDiarioClicked(new DiariosAdapter.OnDiarioClickedListener() {
            @Override
            public void OnDiarioClicked(Diario Diario) {
                if (onDiarioClickedListener != null){
                    onDiarioClickedListener.OnDiarioClicked(Diario);
                }
            }
        });
        diariosAdapter.setOnDiarioLongClicked(new DiariosAdapter.OnDiarioLongClickedListener() {
            @Override
            public void OnDiarioLongClicked(Diario diario) {
                if (onDiarioLongClickedListener != null){
                    onDiarioLongClickedListener.OnDiarioLongClicked(diario);
                }
            }
        });
        layoutManager = new LinearLayoutManager(getActivity());
        diariosRecyclerView.setAdapter(diariosAdapter);
        diariosRecyclerView.setLayoutManager(layoutManager);

        nuevoTestCardView = view.findViewById(R.id.nuevoTestCardView);
        nuevoTestCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onAddDiarioClickedListener != null){
                    onAddDiarioClickedListener.OnAddDiarioClicked();
                }
            }
        });

        if (sessionManager.isTutor()){
            nuevoTestCardView.setVisibility(View.GONE);
        }

        getDiarios();
    }

    public void getDiarios(){
        onProgressBarChanged.OnProgressBarVisible();

        AndroidNetworking.get(Day2DayApi.DIARIOS_BY_PACIENT(String.valueOf(paciente.getIdpaciente())))
                .addHeaders("Content-Type", "application/json")
                .setPriority(Priority.HIGH)
                .setTag(getString(R.string.app_name))
                .build()
                .getAsJSONArray(new JSONArrayRequestListener() {
                    @Override
                    public void onResponse(JSONArray response) {
                        onProgressBarChanged.OnProgressBarHide();
                        messageConstraintLayout.setVisibility(View.GONE);

                        diarios.clear();
                        diarios.addAll(Diario.from(response));
                        Collections.reverse(diarios);
                        diariosAdapter.notifyDataSetChanged();

                        if (diarios.isEmpty()){
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

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public interface OnDiarioClickedListener {
        void OnDiarioClicked(Diario Diario);
    }
    private OnDiarioClickedListener onDiarioClickedListener;
    public void setOnDiarioClicked(OnDiarioClickedListener onDiarioClickedListener) {
        this.onDiarioClickedListener = onDiarioClickedListener;
    }

    public interface OnAddDiarioClickedListener {
        void OnAddDiarioClicked();
    }
    private OnAddDiarioClickedListener onAddDiarioClickedListener;
    public void setOnAddDiarioClicked(OnAddDiarioClickedListener onAddDiarioClickedListener) {
        this.onAddDiarioClickedListener = onAddDiarioClickedListener;
    }

    public interface OnDiarioLongClickedListener {
        void OnDiarioLongClicked(Diario Diario);
    }
    private OnDiarioLongClickedListener onDiarioLongClickedListener;
    public void setOnDiarioLongClicked(OnDiarioLongClickedListener onDiarioLongClickedListener) {
        this.onDiarioLongClickedListener = onDiarioLongClickedListener;
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