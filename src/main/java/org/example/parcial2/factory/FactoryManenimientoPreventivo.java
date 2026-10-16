package org.example.parcial2.factory;

import org.example.parcial2.model.MantenimientoPreventivo;
import org.example.parcial2.model.ServicioTecnologico;

public class FactoryManenimientoPreventivo  extends FactoryServicioTecnologico{

    /**
     * metodo que permite crear un servicio de mantenimiento preventivo
     * @param codigo
     * @return
     */
    @Override
    public ServicioTecnologico crearServicio(int codigo){
        return new MantenimientoPreventivo(codigo);
    }

}
