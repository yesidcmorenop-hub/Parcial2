package org.example.parcial2.model;

import java.util.ArrayList;

public class Cliente {
    private String nombre;
    private int cedula;
    private String correoElectronico;
    private ArrayList<Solicitud> solicitud;

    public Cliente(String nombre, int cedula, String correoElectronico) {
        this.nombre = nombre;
        this.cedula = cedula;
        this.correoElectronico = correoElectronico;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public int getCedula() {
        return cedula;
    }

    public void setCedula(int cedula) {
        this.cedula = cedula;
    }
}
