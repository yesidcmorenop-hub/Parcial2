package org.example.parcial2.model;

public class MantenimientoCorrectivo extends ServicioTecnologico{

    /**
     * creacion del servicio de mantenimiento correctivo
     * @param codigo
     */
    public MantenimientoCorrectivo(int codigo) {
        super(codigo,"Mantenimiento Correctivo", "Realizamos un mantenimiento para Corregir Errores",
                120000.0, Especialidad.MAN_CORRECTIVO);

    }

    /**
     * metodo para calcular el costo del servicio
     * @return
     */
    @Override
    public double calcularCosto() {
        return this.costoBase;
    }

}
