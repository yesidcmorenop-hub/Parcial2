package org.example.parcial2.factory;

import org.example.parcial2.model.InstalacionRed;
import org.example.parcial2.model.ServicioTecnologico;

public class FactoryInstalacionRed extends FactoryServicioTecnologico{

    @Override
    public ServicioTecnologico crearServicio(int codigo) {
        return new InstalacionRed(codigo);
    }
}
