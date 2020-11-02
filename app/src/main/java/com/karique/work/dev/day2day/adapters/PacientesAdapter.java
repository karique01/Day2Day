package com.karique.work.dev.day2day.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.karique.work.dev.day2day.R;
import com.karique.work.dev.day2day.models.Paciente;
import com.karique.work.dev.day2day.util.FuncionesFecha;

import java.util.List;


public class PacientesAdapter extends RecyclerView.Adapter<PacientesAdapter.UserViewHolder> {
    private List<Paciente> pacientes;

    public PacientesAdapter(List<Paciente> pacientes) {
        this.pacientes = pacientes;
    }

    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new UserViewHolder(
                LayoutInflater
                        .from(parent.getContext())
                        .inflate(R.layout.card_paciente, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        final Paciente paciente = pacientes.get(position);

        holder.nombrePacienteTextView.setText(String.format("%s %s", paciente.getNombre(), paciente.getApellidos()));
        holder.edadTextView.setText(String.format("%d años", FuncionesFecha.getYearsSince(paciente.getFecha_nacimiento())));

        holder.pacienteConstraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onPacienteClickedListener != null){
                    onPacienteClickedListener.OnPacienteClicked(paciente);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return pacientes.size();
    }

    public class UserViewHolder extends RecyclerView.ViewHolder {
        ConstraintLayout pacienteConstraintLayout;
        TextView nombrePacienteTextView;
        TextView edadTextView;

        public UserViewHolder(View itemView) {
            super(itemView);
            pacienteConstraintLayout = itemView.findViewById(R.id.pacienteConstraintLayout);
            nombrePacienteTextView = itemView.findViewById(R.id.nombrePacienteTextView);
            edadTextView = itemView.findViewById(R.id.edadTextView);
        }
    }

    public interface OnPacienteClickedListener {
        void OnPacienteClicked(Paciente Paciente);
    }
    private OnPacienteClickedListener onPacienteClickedListener;
    public void setOnPacienteClicked(OnPacienteClickedListener onPacienteClickedListener) {
        this.onPacienteClickedListener = onPacienteClickedListener;
    }
}
