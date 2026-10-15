package org.example.parcial2.model;

public class RecuperacionInformacion extends ServicioTecnologico{

    public RecuperacionInformacion(int codigo) {
        super(codigo,"Recuperacion de Informacion", "Recuperamos toda Información Perdida",
               150000.0, Especialidad.RECUPERAR_INFORMACION);
    }

    @Override
    public double calcularCosto() {
        return this.costoBase;
    }

    @Override
    public double calcularCosto() {
        return getCostoBase();
    }
}
