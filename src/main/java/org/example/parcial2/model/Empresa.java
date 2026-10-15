package org.example.parcial2.model;

import java.util.ArrayList;

public class Empresa {
    private String nombre;
    private static Empresa instance;
    private ArrayList<Solicitud> listSolicitud;
    private ArrayList<ServicioTecnologico> listservicioTecnologico;
    private ArrayList<Cliente> listCliente;

    public Empresa(String nombre, ArrayList<Solicitud> listSolicitud, ArrayList<ServicioTecnologico> listservicioTecnologico, ArrayList<Cliente> listCliente) {
        this.nombre = nombre;
        this.listSolicitud = listSolicitud;
        this.listservicioTecnologico = listservicioTecnologico;
        this.listCliente = listCliente;
    }

  //  public static Empresa getInstance() {
  //      if (instance == null) {
  //          instance = new Empresa("TechSolutions",);
  //      }
  //      return instance;
  //  }
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}
