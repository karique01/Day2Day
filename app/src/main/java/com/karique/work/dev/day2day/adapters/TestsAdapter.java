package com.karique.work.dev.day2day.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.karique.work.dev.day2day.R;
import com.karique.work.dev.day2day.models.Test;
import com.karique.work.dev.day2day.util.FuncionesFecha;

import java.util.Date;
import java.util.List;


public class TestsAdapter extends RecyclerView.Adapter<TestsAdapter.UserViewHolder> {
    private List<Test> tests;
    private boolean esPaciente;

    public TestsAdapter(List<Test> tests) {
        this.tests = tests;
    }

    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new UserViewHolder(
                LayoutInflater
                        .from(parent.getContext())
                        .inflate(R.layout.card_test, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        final Test test = tests.get(position);

        holder.nombreTestTextView.setText(String.format("%s - %s", test.getNombre_test(), test.getDescripcionResultado()));

        Date fecha = FuncionesFecha.getDateWithHourFromString(test.getFecha_hora());
        String fechaNac = FuncionesFecha.formatDateToTextForComment(fecha);
        holder.dateTextView.setText(String.format("%s\n%s", esPaciente ? "" : test.getDescripcionLargaResultado(), fechaNac));

        holder.resultadoTextView.setText(String.format("Resultado\n%d", test.getResultado()));

        holder.testConstraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onTestClickedListener != null){
                    onTestClickedListener.OnTestClicked(test);
                }
            }
        });

        holder.testConstraintLayout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                if (onTestLongClickedListener != null){
                    onTestLongClickedListener.OnTestLongClicked(test);
                }
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return tests.size();
    }

    public class UserViewHolder extends RecyclerView.ViewHolder {
        ConstraintLayout testConstraintLayout;
        TextView nombreTestTextView;
        TextView dateTextView;
        TextView resultadoTextView;

        public UserViewHolder(View itemView) {
            super(itemView);
            testConstraintLayout = itemView.findViewById(R.id.testConstraintLayout);
            nombreTestTextView = itemView.findViewById(R.id.nombreTestTextView);
            dateTextView = itemView.findViewById(R.id.dateTextView);
            resultadoTextView = itemView.findViewById(R.id.resultadoTextView);
        }
    }

    public boolean isEsPaciente() {
        return esPaciente;
    }

    public void setEsPaciente(boolean esPaciente) {
        this.esPaciente = esPaciente;
    }

    public interface OnTestClickedListener {
        void OnTestClicked(Test Test);
    }
    private OnTestClickedListener onTestClickedListener;
    public void setOnTestClicked(OnTestClickedListener onTestClickedListener) {
        this.onTestClickedListener = onTestClickedListener;
    }

    public interface OnTestLongClickedListener {
        void OnTestLongClicked(Test Test);
    }
    private OnTestLongClickedListener onTestLongClickedListener;
    public void setOnTestLongClicked(OnTestLongClickedListener onTestLongClickedListener) {
        this.onTestLongClickedListener = onTestLongClickedListener;
    }
}
