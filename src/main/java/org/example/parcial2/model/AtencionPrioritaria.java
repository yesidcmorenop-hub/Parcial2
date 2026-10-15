package org.example.parcial2.model;

public class AtencionPrioritaria extends DecoradorServicio{

    public AtencionPrioritaria(ServicioTecnologico servicio) {
        super(servicio);
    }

    @Override
    public double calcularCosto() {
        return super.calcularCosto() + 3500.0;
    }
}
