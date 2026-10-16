package org.example.parcial2.model;

public class RecuperacionInformacion extends ServicioTecnologico{

    /**
     * metodo que crea el servicio de recuperacion de informacion
     * @param codigo
     */
    public RecuperacionInformacion(int codigo) {
        super(codigo,"Recuperacion de Informacion", "Recuperamos toda Información Perdida",
               150000.0, Especialidad.RECUPERAR_INFORMACION);
    }

    /**
     * metodo que permite calcular el costo del servicio
     * @return
     */
    @Override
    public double calcularCosto() {
        return this.costoBase;
    }

}
