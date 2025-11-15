package com.example.firebase_kev;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.FirebaseDatabase;

public class EditarActivity extends AppCompatActivity {

    TextInputEditText txtNombre, txtDesc;
    Spinner spinner;
    Button btnGuardar, btnVer;
    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar);

        txtNombre = findViewById(R.id.txtNombreEditar);
        txtDesc = findViewById(R.id.txtDescripcionEditar);
        spinner = findViewById(R.id.spinnerEditar);
        btnGuardar = findViewById(R.id.btnGuardarCambios);
        btnVer = findViewById(R.id.btnVerListado);

        String[] asignaturas = {
                "Programación Android",
                "Computación en la nube",
                "IOT",
                "Arquitectura de sistemas",
                "Integración de competencias II"
        };

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_dropdown_item,
                asignaturas);
        spinner.setAdapter(adapter);

        Intent i = getIntent();
        id = i.getStringExtra("id");
        txtNombre.setText(i.getStringExtra("nombre"));
        txtDesc.setText(i.getStringExtra("descripcion"));

        String asignatura = i.getStringExtra("asignatura");
        spinner.setSelection(adapter.getPosition(asignatura));

        btnGuardar.setOnClickListener(v -> guardarCambios());
        btnVer.setOnClickListener(v ->
                startActivity(new Intent(EditarActivity.this, ListadoActivity.class))
        );
    }

    private void guardarCambios() {
        FirebaseDatabase.getInstance().getReference("tareas")
                .child(id)
                .child("nombre").setValue(txtNombre.getText().toString());

        FirebaseDatabase.getInstance().getReference("tareas")
                .child(id)
                .child("descripcion").setValue(txtDesc.getText().toString());

        FirebaseDatabase.getInstance().getReference("tareas")
                .child(id)
                .child("asignatura").setValue(spinner.getSelectedItem().toString())
                .addOnSuccessListener(unused ->
                        Toast.makeText(this, "Modificado correctamente", Toast.LENGTH_SHORT).show()
                );
    }
}
