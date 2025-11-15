package com.example.firebase_kev.models;

public class Tarea {
    private String id;
    private String nombre;
    private String descripcion;
    private String asignatura;

    public Tarea() {}

    public Tarea(String id, String nombre, String descripcion, String asignatura) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.asignatura = asignatura;
    }

    public String getId() { return id; }
    public String getNombre() { return nombre; }
    public String getDescripcion() { return descripcion; }
    public String getAsignatura() { return asignatura; }

    public void setId(String id) { this.id = id; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public void setAsignatura(String asignatura) { this.asignatura = asignatura; }
}
