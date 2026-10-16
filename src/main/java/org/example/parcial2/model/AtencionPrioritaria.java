package org.example.parcial2.model;

public class AtencionPrioritaria extends DecoradorServicio{

    public AtencionPrioritaria(ServicioTecnologico servicio) {
        super(servicio);
    }

    /**
     * metodo que permite calcular el costo del servicio
     * @return
     */
    @Override
    public double calcularCosto() {
        return super.calcularCosto() + 35000.0;
    }
}
