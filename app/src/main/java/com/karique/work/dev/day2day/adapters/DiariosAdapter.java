package com.karique.work.dev.day2day.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.karique.work.dev.day2day.R;
import com.karique.work.dev.day2day.models.Diario;
import com.karique.work.dev.day2day.util.FuncionesFecha;

import java.util.Date;
import java.util.List;


public class DiariosAdapter extends RecyclerView.Adapter<DiariosAdapter.UserViewHolder> {
    private List<Diario> diarios;
    private boolean esPaciente;

    public DiariosAdapter(List<Diario> diarios) {
        this.diarios = diarios;
    }

    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new UserViewHolder(
                LayoutInflater
                        .from(parent.getContext())
                        .inflate(R.layout.card_diario, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        final Diario diario = diarios.get(position);

        holder.diarioTextView.setText(diario.getTexto());
        holder.dateTextView.setText((esPaciente ? "" : (diario.getResultadoDetallado() + "\n")) + diario.getFecha_horaFormated());

        holder.diarioConstraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onDiarioClickedListener != null){
                    onDiarioClickedListener.OnDiarioClicked(diario);
                }
            }
        });
        holder.diarioConstraintLayout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                if (onDiarioLongClickedListener != null){
                    onDiarioLongClickedListener.OnDiarioLongClicked(diario);
                }
                return false;
            }
        });
    }

    public boolean isEsPaciente() {
        return esPaciente;
    }

    public void setEsPaciente(boolean esPaciente) {
        this.esPaciente = esPaciente;
    }

    @Override
    public int getItemCount() {
        return diarios.size();
    }

    public class UserViewHolder extends RecyclerView.ViewHolder {
        ConstraintLayout diarioConstraintLayout;
        TextView diarioTextView;
        TextView dateTextView;

        public UserViewHolder(View itemView) {
            super(itemView);
            diarioConstraintLayout = itemView.findViewById(R.id.diarioConstraintLayout);
            diarioTextView = itemView.findViewById(R.id.diarioTextView);
            dateTextView = itemView.findViewById(R.id.dateTextView);
        }
    }

    public interface OnDiarioClickedListener {
        void OnDiarioClicked(Diario Diario);
    }
    private OnDiarioClickedListener onDiarioClickedListener;
    public void setOnDiarioClicked(OnDiarioClickedListener onDiarioClickedListener) {
        this.onDiarioClickedListener = onDiarioClickedListener;
    }

    public interface OnDiarioLongClickedListener {
        void OnDiarioLongClicked(Diario Diario);
    }
    private OnDiarioLongClickedListener onDiarioLongClickedListener;
    public void setOnDiarioLongClicked(OnDiarioLongClickedListener onDiarioLongClickedListener) {
        this.onDiarioLongClickedListener = onDiarioLongClickedListener;
    }
}
