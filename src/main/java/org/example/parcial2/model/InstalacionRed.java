package org.example.parcial2.model;

public class InstalacionRed extends ServicioTecnologico{
    public InstalacionRed(int codigo) {
        super(codigo,"Instalación de Red", "Instalamos redes",
                180000.0, Especialidad.INSTALACION_RED);
    }

    @Override
    public double calcularCosto() {
        return this.costoBase;
    }
}
