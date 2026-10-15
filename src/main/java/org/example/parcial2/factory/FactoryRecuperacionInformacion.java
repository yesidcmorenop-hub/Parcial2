package org.example.parcial2.factory;

import org.example.parcial2.model.RecuperacionInformacion;
import org.example.parcial2.model.ServicioTecnologico;

public class FactoryRecuperacionInformacion extends FactoryServicioTecnologico {

    @Override
    public ServicioTecnologico crearServicio(int codigo) {
        return new RecuperacionInformacion(codigo);
    }
}
