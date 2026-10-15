package org.example.parcial2.model;

public class MantenimientoPreventivo extends ServicioTecnologico {


    public MantenimientoPreventivo(int codigo) {
        super(codigo,"Mantenimiento Preventivo", "Realizamos un Mantenimiento para Prevenir Fallas",
                80000.0, Especialidad.MAN_PREVENTIVO);
    }

    @Override
    public double calcularCosto() {
        return this.costoBase;
    }
}
