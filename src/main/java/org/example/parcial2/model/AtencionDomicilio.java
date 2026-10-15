package org.example.parcial2.model;

public class AtencionDomicilio extends DecoradorServicio{

    public  AtencionDomicilio(ServicioTecnologico servicio) {
        super(servicio);
    }

    @Override
    public double calcularCosto() {
        return super.calcularCosto() + 25000.0;
    }
}
