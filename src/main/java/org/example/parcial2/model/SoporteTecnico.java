package org.example.parcial2.model;

public class SoporteTecnico extends DecoradorServicio{

    /**
     * obtiene el servicio tecnologico
     * @param servicio
     */
    public SoporteTecnico(ServicioTecnologico servicio){
        super(servicio);
    }

    /**
     * metodo para calcular el costo de la solicitud
     * @return
     */
    @Override
    public double calcularCosto() {
        return super.calcularCosto() + 50000.0;
    }
}
