package org.example.parcial2.model;

import java.util.ArrayList;

public class PaqueteTecnologico extends ServicioTecnologico{
    private ArrayList<ServicioTecnologico> listServicioTecnologico;

    /**
     * metodo constructor del paquete
     * @param codigo de paquete
     * @param nombre de paquete
     * @param descripcion de paquete
     * @param costoBase de paquete
     * @param especialidad de paquete
     */
    public PaqueteTecnologico(int codigo, String nombre, String descripcion, double costoBase, Especialidad especialidad) {
        super(codigo, nombre, descripcion, costoBase, especialidad);
        this.listServicioTecnologico = new ArrayList<>();
    }

    /**
     * permite agregar servicios al paquete
     * @param servicio
     * @return
     */
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

    /**
     * metodo que verifica que no se cree un paquete dentro de si mismo
     * @param paqueteBuscado
     * @return
     */
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

    /**
     * metodo para calcular el costo del paquete
     * @return
     */
    @Override
    public double calcularCosto() {
        double total = 0.0;
        for (ServicioTecnologico servicio : listServicioTecnologico) {
            total += servicio.calcularCosto();
        }
        return total;
    }
}
