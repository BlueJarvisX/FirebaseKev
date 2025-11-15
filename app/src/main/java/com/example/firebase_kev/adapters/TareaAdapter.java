package com.example.firebase_kev.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.firebase_kev.EditarActivity;
import com.example.firebase_kev.R;
import com.example.firebase_kev.models.Tarea;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class TareaAdapter extends RecyclerView.Adapter<TareaAdapter.ViewHolder> {

    Context context;
    List<Tarea> lista;

    public TareaAdapter(Context context, List<Tarea> lista) {
        this.context = context;
        this.lista = lista;
    }

    @NonNull
    @Override
    public TareaAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_tarea, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull TareaAdapter.ViewHolder holder, int position) {
        Tarea t = lista.get(position);

        holder.txtNombre.setText(t.getNombre());
        holder.txtDesc.setText(t.getDescripcion());
        holder.txtAsignatura.setText(t.getAsignatura());

        holder.btnEditar.setOnClickListener(v -> {
            Intent i = new Intent(context, EditarActivity.class);
            i.putExtra("id", t.getId());
            i.putExtra("nombre", t.getNombre());
            i.putExtra("descripcion", t.getDescripcion());
            i.putExtra("asignatura", t.getAsignatura());
            context.startActivity(i);
        });

        holder.btnEliminar.setOnClickListener(v ->
                FirebaseDatabase.getInstance().getReference("tareas")
                        .child(t.getId()).removeValue()
        );
    }

    @Override
    public int getItemCount() { return lista.size(); }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtNombre, txtDesc, txtAsignatura;
        Button btnEditar, btnEliminar;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtNombre = itemView.findViewById(R.id.txtNombre);
            txtDesc = itemView.findViewById(R.id.txtDescripcion);
            txtAsignatura = itemView.findViewById(R.id.txtAsignatura);
            btnEditar = itemView.findViewById(R.id.btnEditar);
            btnEliminar = itemView.findViewById(R.id.btnEliminar);
        }
    }
}
