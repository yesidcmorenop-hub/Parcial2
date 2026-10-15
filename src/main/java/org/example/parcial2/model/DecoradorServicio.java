package org.example.parcial2.model;

public abstract class DecoradorServicio extends ServicioTecnologico{

    protected ServicioTecnologico servicio;

    public DecoradorServicio(ServicioTecnologico servicio){
        super(servicio.codigo, servicio.nombre,  servicio.descripcion, servicio.costoBase, servicio.especialidad);
        this.servicio = servicio;
    }
    @Override
    public double calcularCosto() {
        return this.servicio.calcularCosto();
    }
}
