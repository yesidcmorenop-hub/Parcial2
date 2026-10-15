package org.example.parcial2.model;

public class SoporteTecnico extends DecoradorServicio{

    public SoporteTecnico(ServicioTecnologico servicio){
        super(servicio);
    }

    @Override
    public double calcularCosto() {
        return super.calcularCosto() + 50000.0;
    }
}
