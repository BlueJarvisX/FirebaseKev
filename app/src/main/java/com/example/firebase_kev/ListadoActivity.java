package com.example.firebase_kev;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.firebase_kev.adapters.TareaAdapter;
import com.example.firebase_kev.models.Tarea;
import com.google.firebase.database.*;

import java.util.ArrayList;

public class ListadoActivity extends AppCompatActivity {

    RecyclerView recycler;
    ArrayList<Tarea> lista;
    TareaAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado);

        recycler = findViewById(R.id.recyclerTareas);
        recycler.setLayoutManager(new LinearLayoutManager(this));

        lista = new ArrayList<>();
        adapter = new TareaAdapter(this, lista);
        recycler.setAdapter(adapter);

        cargarDatos();
    }

    private void cargarDatos() {
        FirebaseDatabase.getInstance().getReference("tareas")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot snapshot) {
                        lista.clear();
                        for (DataSnapshot ds : snapshot.getChildren()) {
                            Tarea t = ds.getValue(Tarea.class);
                            lista.add(t);
                        }
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(DatabaseError error) {}
                });
    }
}
