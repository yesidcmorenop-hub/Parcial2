package org.example.parcial2.factory;

import org.example.parcial2.model.InstalacionRed;
import org.example.parcial2.model.ServicioTecnologico;

public class FactoryInstalacionRed extends FactoryServicioTecnologico{

    /**
     * metodo que permite crear un servicio de instalacion dw red
     * @param codigo
     * @return
     */
    @Override
    public ServicioTecnologico crearServicio(int codigo) {
        return new InstalacionRed(codigo);
    }
}
