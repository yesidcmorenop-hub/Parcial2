package org.example.parcial2.model;

import java.util.ArrayList;

public class Empresa {
    private String nombre;
    private static Empresa instance;
    private ArrayList<Solicitud> listSolicitud;
    private ArrayList<ServicioTecnologico> listServicioTecnologico;
    private ArrayList<Cliente> listCliente;

    private Empresa() {
        this.nombre = "TechSolutions";
        this.listSolicitud = new ArrayList<>();
        this.listServicioTecnologico = new ArrayList<>();
        this.listCliente = new ArrayList<>();
    }

    public static Empresa getInstance() {
        if (instance == null) {
            instance = new Empresa();
        }
        return instance;
          }
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}
