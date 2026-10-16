package org.example.parcial2.model;

public class MantenimientoPreventivo extends ServicioTecnologico {

    /**
     * metodo para crear el servicio de mantenimiento preventivo
     * @param codigo
     */
    public MantenimientoPreventivo(int codigo) {
        super(codigo,"Mantenimiento Preventivo", "Realizamos un Mantenimiento para Prevenir Fallas",
                80000.0, Especialidad.MAN_PREVENTIVO);
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
