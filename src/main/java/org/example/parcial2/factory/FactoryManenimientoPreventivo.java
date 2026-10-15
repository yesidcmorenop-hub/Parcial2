package org.example.parcial2.factory;

import org.example.parcial2.model.MantenimientoPreventivo;
import org.example.parcial2.model.ServicioTecnologico;

public class FactoryManenimientoPreventivo  extends FactoryServicioTecnologico{

    @Override
    public ServicioTecnologico crearServicio(int codigo){
        return new MantenimientoPreventivo(codigo);
    }

}
