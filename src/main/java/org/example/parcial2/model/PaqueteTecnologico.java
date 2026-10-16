package org.example.parcial2.model;

import java.util.ArrayList;

public class PaqueteTecnologico extends ServicioTecnologico{
    private ArrayList<ServicioTecnologico> listServicioTecnologico;

    public PaqueteTecnologico(int codigo, String nombre, String descripcion, double costoBase, Especialidad especialidad) {
        super(codigo, nombre, descripcion, costoBase, especialidad);
        this.listServicioTecnologico = new ArrayList<>();
    }
    public boolean agregarServicio(ServicioTecnologico servicio) {
        if (this.equals(servicio)) {
            return false;
        }
        if (servicio instanceof PaqueteTecnologico) {
            if (((PaqueteTecnologico) servicio).contienePaquete(this)) {
                return false;
            }
        }

        this.listServicioTecnologico.add(servicio);
        return true;
    }

    public boolean contienePaquete(PaqueteTecnologico paqueteBuscado) {
        for (ServicioTecnologico servicio : this.listServicioTecnologico) {
            if (servicio.equals(paqueteBuscado)) {
                return true;
            }
            if (servicio instanceof PaqueteTecnologico) {
                if (((PaqueteTecnologico) servicio).contienePaquete(paqueteBuscado)) {
                    return true;
                }
            }
        }
        return false;
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
