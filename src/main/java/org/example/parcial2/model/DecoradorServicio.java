package org.example.parcial2.model;

public abstract class DecoradorServicio extends ServicioTecnologico{

    protected ServicioTecnologico servicio;

    /**
     * permite obtener el servicio y agregale un nuevo servicio
     * @param servicio
     */
    public DecoradorServicio(ServicioTecnologico servicio){
        super(servicio.codigo, servicio.nombre,  servicio.descripcion, servicio.costoBase, servicio.especialidad);
        this.servicio = servicio;
    }

    /**
     * metodo que permite calcular el costo del servicio
     * @return
     */
    @Override
    public double calcularCosto() {
        return this.servicio.calcularCosto();
    }
}
