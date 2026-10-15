package org.example.parcial2.model;

import java.util.ArrayList;

public class PaqueteTecnologico extends ServicioTecnologico{
    private ArrayList<ServicioTecnologico> listServicioTecnologico;

    public PaqueteTecnologico(int codigo, String nombre, String descripcion, double costoBase, Especialidad especialidad) {
        super(codigo, nombre, descripcion, costoBase, especialidad);
        this.listServicioTecnologico = new ArrayList<>();
    }
    public void agregarServicio(ServicioTecnologico servicio) {
        this.listServicioTecnologico.add(servicio);
    }

    public void eliminarServicio(ServicioTecnologico servicio) {
        this.listServicioTecnologico.remove(servicio);
    }

    public ArrayList<ServicioTecnologico> getListServicios() {
        return listServicioTecnologico;
    }

    @Override
    public double calcularCosto() {
        double total = 0.0;
        for (ServicioTecnologico servicio : listServicioTecnologico) {
            total += servicio.calcularCosto();
        }
        return total;
    }
}
