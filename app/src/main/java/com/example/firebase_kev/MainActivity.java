package com.example.firebase_kev;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.firebase_kev.models.Tarea;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    TextInputEditText txtNombre, txtDesc;
    Spinner spinnerAsignatura;
    Button btnGuardar, btnVer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtNombre = findViewById(R.id.txtNombre);
        txtDesc = findViewById(R.id.txtDescripcion);
        spinnerAsignatura = findViewById(R.id.spinnerAsignatura);
        btnGuardar = findViewById(R.id.btnGuardar);
        btnVer = findViewById(R.id.btnVerTareas);

        // Spinner
        String[] asignaturas = {
                "Programación Android",
                "Computación en la nube",
                "IOT",
                "Arquitectura de sistemas",
                "Integración de competencias II"
        };

        spinnerAsignatura.setAdapter(new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                asignaturas
        ));

        btnGuardar.setOnClickListener(v -> guardarTarea());
        btnVer.setOnClickListener(v ->
                startActivity(new Intent(MainActivity.this, ListadoActivity.class))
        );
    }

    private void guardarTarea() {
        String nombre = txtNombre.getText().toString();
        String desc = txtDesc.getText().toString();
        String asig = spinnerAsignatura.getSelectedItem().toString();

        if (nombre.isEmpty() || desc.isEmpty()) {
            Toast.makeText(this, "Completa todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        String id = FirebaseDatabase.getInstance().getReference().child("tareas").push().getKey();
        Tarea t = new Tarea(id, nombre, desc, asig);

        FirebaseDatabase.getInstance().getReference("tareas")
                .child(id)
                .setValue(t)
                .addOnSuccessListener(unused -> Toast.makeText(this, "Tarea guardada", Toast.LENGTH_SHORT).show());
    }
}
