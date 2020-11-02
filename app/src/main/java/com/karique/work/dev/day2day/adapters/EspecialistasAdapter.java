package com.karique.work.dev.day2day.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.karique.work.dev.day2day.R;
import com.karique.work.dev.day2day.models.Especialista;

import java.util.List;


public class EspecialistasAdapter extends RecyclerView.Adapter<EspecialistasAdapter.UserViewHolder> {
    private List<Especialista> especialistas;

    public EspecialistasAdapter(List<Especialista> especialistas) {
        this.especialistas = especialistas;
    }

    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new UserViewHolder(
                LayoutInflater
                        .from(parent.getContext())
                        .inflate(R.layout.card_especialista, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final UserViewHolder holder, int position) {
        final Especialista especialista = especialistas.get(holder.getAdapterPosition());

        holder.especialistaTitleTextView.setText(String.format("%s %s", especialista.getNombre(), especialista.getApellidos()));
        holder.especialistaTextView.setText(especialista.getEmpresa());

        holder.especialistaConstraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                desEscoger();
                especialista.setEscogido(true);
                notifyDataSetChanged();
            }
        });

        if (especialista.isEscogido())
            holder.borderConstraintLayout.setBackgroundResource(R.drawable.button_bordered_selected);
        else
            holder.borderConstraintLayout.setBackgroundResource(R.drawable.button_bordered);
    }

    public Especialista getEspecialistaSeleccionado(){
        for (int i = 0; i < especialistas.size(); i++) {
            if (especialistas.get(i).isEscogido()){
                return especialistas.get(i);
            }
        }
        return null;
    }
    private void desEscoger(){
        for (int i = 0; i < especialistas.size(); i++) {
            especialistas.get(i).setEscogido(false);
        }
    }

    @Override
    public int getItemCount() {
        return especialistas.size();
    }

    public class UserViewHolder extends RecyclerView.ViewHolder {
        ConstraintLayout especialistaConstraintLayout;
        TextView especialistaTitleTextView;
        TextView especialistaTextView;
        ConstraintLayout borderConstraintLayout;

        public UserViewHolder(View itemView) {
            super(itemView);
            especialistaConstraintLayout = itemView.findViewById(R.id.especialistaConstraintLayout);
            especialistaTitleTextView = itemView.findViewById(R.id.especialistaTitleTextView);
            especialistaTextView = itemView.findViewById(R.id.especialistaTextView);
            borderConstraintLayout = itemView.findViewById(R.id.borderConstraintLayout);
        }
    }
}
