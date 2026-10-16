package org.example.parcial2.model;

import java.time.LocalDate;
import java.util.ArrayList;

public class Solicitud {
    private String codigo;
    private LocalDate fechaCreacion;
    private double costoTotal;
    private LocalDate fechaAtencion;
    private String observacion;
    private NivelPrioridad nivelPrioridad;
    private Cliente cliente;
    private ArrayList<ServicioTecnologico> listServicioTecnologico;


    /**
     * Constructor que permite crear solicitudes flexibles
     */

    private Solicitud(Builder builder) {
        this.codigo = builder.codigo;
        this.fechaCreacion = builder.fechaCreacion;
        this.costoTotal = builder.costoTotal;
        this.fechaAtencion = builder.fechaAtencion;
        this.observacion = builder.observacion;
        this.nivelPrioridad = builder.nivelPrioridad;
        this.cliente = builder.cliente;
        this.listServicioTecnologico = builder.listServicioTecnologico;
    }


    /**
     * Getters y Setters
     */
    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }

    public LocalDate getFechaCreacion() { return fechaCreacion; }
    public void setFechaCreacion(LocalDate fechaCreacion) { this.fechaCreacion = fechaCreacion; }

    public double getCostoTotal() { return costoTotal; }
    public void setCostoTotal(double costoTotal) { this.costoTotal = costoTotal; }

    public LocalDate getFechaAtencion() { return fechaAtencion; }
    public void setFechaAtencion(LocalDate fechaAtencion) { this.fechaAtencion = fechaAtencion; }

    public String getObservacion() { return observacion; }
    public void setObservacion(String observacion) { this.observacion = observacion; }

    public NivelPrioridad getNivelPrioridad() { return nivelPrioridad; }
    public void setNivelPrioridad(NivelPrioridad nivelPrioridad) { this.nivelPrioridad = nivelPrioridad; }

    public ArrayList<ServicioTecnologico> getListServicioTecnologico() { return listServicioTecnologico; }
    public void setListServicioTecnologico(ArrayList<ServicioTecnologico> listServicioTecnologico) { this.listServicioTecnologico = listServicioTecnologico; }

    public Cliente getCliente() { return cliente; }
    public void setCliente(Cliente cliente) { this.cliente = cliente; }

    /**
     * Clase Builder interna
     */

    public static class Builder {
        private String codigo;
        private LocalDate fechaCreacion;
        private double costoTotal;
        private LocalDate fechaAtencion;
        private String observacion;
        private NivelPrioridad nivelPrioridad;
        private Cliente cliente;
        private ArrayList<ServicioTecnologico> listServicioTecnologico;

        public Builder codigo(String codigo) {
            this.codigo = codigo;
            return this;
        }

        public Builder fechaCreacion(LocalDate fechaCreacion) {
            this.fechaCreacion = fechaCreacion;
            return this;
        }

        public Builder costoTotal(double costoTotal) {
            this.costoTotal = costoTotal;
            return this;
        }

        public Builder fechaAtencion(LocalDate fechaAtencion) {
            this.fechaAtencion = fechaAtencion;
            return this;
        }

        public Builder observacion(String observacion) {
            this.observacion = observacion;
            return this;
        }

        public Builder nivelPrioridad(NivelPrioridad nivelPrioridad) {
            this.nivelPrioridad = nivelPrioridad;
            return this;
        }

        public Builder cliente(Cliente cliente) {
            this.cliente = cliente;
            return this;
        }

        public Builder listServicioTecnologico(ArrayList<ServicioTecnologico> listServicioTecnologico) {
            this.listServicioTecnologico = listServicioTecnologico;
            return this;
        }

        /**
         * Metodo que construye el objeto final
         */

        public Solicitud build() {
            return new Solicitud(this);
        }
    }
}
