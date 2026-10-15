package org.example.parcial2.factory;

import org.example.parcial2.model.MantenimientoCorrectivo;
import org.example.parcial2.model.ServicioTecnologico;

public class FactoryMantenimientoCorrectivo extends FactoryServicioTecnologico {

    @Override
    public ServicioTecnologico crearServicio(int codigo) {
        return new MantenimientoCorrectivo(codigo);
    }
}
