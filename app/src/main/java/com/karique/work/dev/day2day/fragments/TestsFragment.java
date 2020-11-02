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
import com.karique.work.dev.day2day.adapters.TestsAdapter;
import com.karique.work.dev.day2day.models.Paciente;
import com.karique.work.dev.day2day.models.Test;
import com.karique.work.dev.day2day.network.Day2DayApi;
import com.karique.work.dev.day2day.session.SessionManager;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TestsFragment extends Fragment {

    private ConstraintLayout messageConstraintLayout;
    private TextView messageTextView;

    private RecyclerView testsRecyclerView;
    private TestsAdapter testsAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private List<Test> tests;

    private CardView empezarTestCardView;
    private SessionManager sessionManager;

    Paciente paciente;

    public TestsFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tests, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        sessionManager = SessionManager.getInstance(getContext());
        testsRecyclerView = view.findViewById(R.id.testsRecyclerView);
        messageConstraintLayout = view.findViewById(R.id.messageConstraintLayout);
        messageTextView = view.findViewById(R.id.messageTextView);

        tests = new ArrayList<>();
        testsAdapter = new TestsAdapter(tests);
        testsAdapter.setEsPaciente(sessionManager.isPaciente());
        testsAdapter.setOnTestClicked(new TestsAdapter.OnTestClickedListener() {
            @Override
            public void OnTestClicked(Test test) {
                if (onTestClickedListener != null){
                    onTestClickedListener.OnTestClicked(test);
                }
            }
        });
        testsAdapter.setOnTestLongClicked(new TestsAdapter.OnTestLongClickedListener() {
            @Override
            public void OnTestLongClicked(Test test) {
                if (onTestLongClickedListener != null){
                    onTestLongClickedListener.OnTestLongClicked(test);
                }
            }
        });
        layoutManager = new LinearLayoutManager(getActivity());
        testsRecyclerView.setAdapter(testsAdapter);
        testsRecyclerView.setLayoutManager(layoutManager);

        empezarTestCardView = view.findViewById(R.id.empezarTestCardView);
        empezarTestCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onAddTestClickedListener != null){
                    onAddTestClickedListener.OnAddTestClicked();
                }
            }
        });

        getTests();
        if (sessionManager.isTutor()){
            empezarTestCardView.setVisibility(View.GONE);
        }
    }

    public void getTests(){
        onProgressBarChanged.OnProgressBarVisible();

        AndroidNetworking.get(Day2DayApi.TESTS_BY_PACIENT(String.valueOf(paciente.getIdpaciente())))
                .addHeaders("Content-Type", "application/json")
                .setPriority(Priority.HIGH)
                .setTag(getString(R.string.app_name))
                .build()
                .getAsJSONArray(new JSONArrayRequestListener() {
                    @Override
                    public void onResponse(JSONArray response) {
                        onProgressBarChanged.OnProgressBarHide();
                        messageConstraintLayout.setVisibility(View.GONE);

                        tests.clear();
                        tests.addAll(Test.from(response));
                        Collections.reverse(tests);
                        testsAdapter.notifyDataSetChanged();

                        if (tests.isEmpty()){
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

    public interface OnTestClickedListener {
        void OnTestClicked(Test Test);
    }
    private OnTestClickedListener onTestClickedListener;
    public void setOnTestClicked(OnTestClickedListener onTestClickedListener) {
        this.onTestClickedListener = onTestClickedListener;
    }

    public interface OnAddTestClickedListener {
        void OnAddTestClicked();
    }
    private OnAddTestClickedListener onAddTestClickedListener;
    public void setOnAddTestClicked(OnAddTestClickedListener onAddTestClickedListener) {
        this.onAddTestClickedListener = onAddTestClickedListener;
    }

    public interface OnTestLongClickedListener {
        void OnTestLongClicked(Test Test);
    }
    private OnTestLongClickedListener onTestLongClickedListener;
    public void setOnTestLongClicked(OnTestLongClickedListener onTestLongClickedListener) {
        this.onTestLongClickedListener = onTestLongClickedListener;
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