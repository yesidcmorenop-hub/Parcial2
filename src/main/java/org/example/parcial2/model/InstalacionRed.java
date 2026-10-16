package org.example.parcial2.model;

public class InstalacionRed extends ServicioTecnologico{

    /**
     * creacion del servicio de intalacion de red
     * @param codigo
     */
    public InstalacionRed(int codigo) {
        super(codigo,"Instalación de Red", "Instalamos redes",
                180000.0, Especialidad.INSTALACION_RED);
    }

    /**
     *  metodo para calcular el costo del servicio
     * @return
     */
    @Override
    public double calcularCosto() {
        return this.costoBase;
    }

}
