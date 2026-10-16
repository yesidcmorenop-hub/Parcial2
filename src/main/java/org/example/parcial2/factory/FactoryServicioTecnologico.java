package org.example.parcial2.factory;
import org.example.parcial2.model.*;

public abstract class FactoryServicioTecnologico {

    /**
     * metodo que permite crear un servicio tecnologico
     * @param codigo
     * @return
     */
    public abstract ServicioTecnologico crearServicio(int codigo);
}

