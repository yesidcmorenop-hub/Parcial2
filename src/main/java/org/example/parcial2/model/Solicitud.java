package org.example.parcial2.model;

import java.time.LocalDate;
import java.util.ArrayList;

public class Solicitud {
    private int codigo;
    private LocalDate fechaCreacion;
    private double costoTotal;
    private LocalDate fechaAtencion;
    private String observacion;
    private NivelPrioridad nivelPrioridad;
    private ArrayList<ServicioTecnologico> listServicioTecnologico;
    private Cliente cliente;

    public Solicitud(int codigo, LocalDate fechaCreacion, double costoTotal, LocalDate fechaAtencion, NivelPrioridad nivelPrioridad, String observacion, ArrayList<ServicioTecnologico> listServicioTecnologico, Cliente cliente) {
        this.codigo = codigo;
        this.fechaCreacion = fechaCreacion;
        this.costoTotal = costoTotal;
        this.fechaAtencion = fechaAtencion;
        this.nivelPrioridad = nivelPrioridad;
        this.observacion = observacion;
        this.listServicioTecnologico = listServicioTecnologico;
        this.cliente = cliente;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDate fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public double getCostoTotal() {
        return costoTotal;
    }

    public void setCostoTotal(double costoTotal) {
        this.costoTotal = costoTotal;
    }

    public LocalDate getFechaAtencion() {
        return fechaAtencion;
    }

    public void setFechaAtencion(LocalDate fechaAtencion) {
        this.fechaAtencion = fechaAtencion;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public NivelPrioridad getNivelPrioridad() {
        return nivelPrioridad;
    }

    public void setNivelPrioridad(NivelPrioridad nivelPrioridad) {
        this.nivelPrioridad = nivelPrioridad;
    }

    public ArrayList<ServicioTecnologico> getListServicioTecnologico() {
        return listServicioTecnologico;
    }

    public void setListServicioTecnologico(ArrayList<ServicioTecnologico> listServicioTecnologico) {
        this.listServicioTecnologico = listServicioTecnologico;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}
