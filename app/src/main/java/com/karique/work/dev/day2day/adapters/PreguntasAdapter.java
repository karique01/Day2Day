package com.karique.work.dev.day2day.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatRadioButton;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.karique.work.dev.day2day.R;
import com.karique.work.dev.day2day.models.Pregunta;
import com.karique.work.dev.day2day.util.FuncionesFecha;

import java.util.Date;
import java.util.List;


public class PreguntasAdapter extends RecyclerView.Adapter<PreguntasAdapter.UserViewHolder> {
    private List<Pregunta> preguntas;

    public PreguntasAdapter(List<Pregunta> preguntas) {
        this.preguntas = preguntas;
    }

    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new UserViewHolder(
                LayoutInflater
                        .from(parent.getContext())
                        .inflate(R.layout.card_pregunta, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        final Pregunta pregunta = preguntas.get(position);

        holder.preguntaTextView.setText(String.format("%d. %s", pregunta.getPosicion(), pregunta.getDescripcionPregunta()));

        if (pregunta.getRespuesta() == 0){
            holder.ceroAppCompatRadioButton.setChecked(true);
        }
        else if (pregunta.getRespuesta() == 1){
            holder.unoAppCompatRadioButton.setChecked(true);
        }
        else if (pregunta.getRespuesta() == 2){
            holder.dosAppCompatRadioButton.setChecked(true);
        }
        else if (pregunta.getRespuesta() == 3){
            holder.tresAppCompatRadioButton.setChecked(true);
        }
    }

    public int getResultado(){
        int suma = 0;
        for (int i = 0; i < preguntas.size(); i++) {
            suma += preguntas.get(i).getRespuesta();
        }
        return suma;
    }

    @Override
    public int getItemCount() {
        return preguntas.size();
    }

    public class UserViewHolder extends RecyclerView.ViewHolder {
        TextView preguntaTextView;
        AppCompatRadioButton ceroAppCompatRadioButton;
        AppCompatRadioButton unoAppCompatRadioButton;
        AppCompatRadioButton dosAppCompatRadioButton;
        AppCompatRadioButton tresAppCompatRadioButton;

        public UserViewHolder(View itemView) {
            super(itemView);
            preguntaTextView = itemView.findViewById(R.id.preguntaTextView);
            ceroAppCompatRadioButton = itemView.findViewById(R.id.ceroAppCompatRadioButton);
            unoAppCompatRadioButton = itemView.findViewById(R.id.unoAppCompatRadioButton);
            dosAppCompatRadioButton = itemView.findViewById(R.id.dosAppCompatRadioButton);
            tresAppCompatRadioButton = itemView.findViewById(R.id.tresAppCompatRadioButton);


            ceroAppCompatRadioButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    Pregunta pregunta = preguntas.get(getAdapterPosition());
                    if (b) pregunta.setRespuesta(0);
                }
            });
            unoAppCompatRadioButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    Pregunta pregunta = preguntas.get(getAdapterPosition());
                    if (b) pregunta.setRespuesta(1);
                }
            });
            dosAppCompatRadioButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    Pregunta pregunta = preguntas.get(getAdapterPosition());
                    if (b) pregunta.setRespuesta(2);
                }
            });
            tresAppCompatRadioButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    Pregunta pregunta = preguntas.get(getAdapterPosition());
                    if (b) pregunta.setRespuesta(3);
                }
            });
        }
    }
}
