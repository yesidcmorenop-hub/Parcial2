package org.example.parcial2.model;

import java.util.ArrayList;

public class PaqueteTecnologico extends ServicioTecnologico{
    private ArrayList<ServicioTecnologico> listServicioTecnologico;

    public PaqueteTecnologico(int codigo, String nombre, String descripcion, double costoBase, Especialidad especialidad) {
        super(codigo, nombre, descripcion, costoBase, especialidad);
    }
}
