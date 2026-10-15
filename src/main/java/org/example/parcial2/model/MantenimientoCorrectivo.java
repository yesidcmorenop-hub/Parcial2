package org.example.parcial2.model;

public class MantenimientoCorrectivo extends ServicioTecnologico{
    public MantenimientoCorrectivo(int codigo, String nombre, String descripcion, double costoBase, Especialidad especialidad) {
        super(codigo, nombre, descripcion, costoBase, especialidad);
    }

    @Override
    public double calcularCosto() {
        return getCostoBase();
    }
}
