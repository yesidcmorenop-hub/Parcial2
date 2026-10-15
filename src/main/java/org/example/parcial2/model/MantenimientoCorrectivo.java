package org.example.parcial2.model;

public class MantenimientoCorrectivo extends ServicioTecnologico{

    public MantenimientoCorrectivo(int codigo) {
        super(codigo,"Mantenimiento Correctivo", "Realizamos un mantenimiento para Corregir Errores",
                120000.0, Especialidad.MAN_CORRECTIVO);

    }

    @Override
    public double calcularCosto() {
        return this.costoBase;
    }
}
