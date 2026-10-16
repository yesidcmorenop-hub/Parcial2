package org.example.parcial2.factory;

import org.example.parcial2.model.RecuperacionInformacion;
import org.example.parcial2.model.ServicioTecnologico;

public class FactoryRecuperacionInformacion extends FactoryServicioTecnologico {

    /**
     * metodo que permite crear un servicio de recuperacion de informacion
     * @param codigo
     * @return
     */
    @Override
    public ServicioTecnologico crearServicio(int codigo) {
        return new RecuperacionInformacion(codigo);
    }
}
