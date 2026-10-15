package org.example.parcial2.model;

public class Empresa {
    private String nombre;
    private static Empresa instance;

    public Empresa(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public static Empresa getInstance() {
        if (instance == null) {
            instance = new Empresa("TechSolutions");
        }
        return instance;
    }
}
