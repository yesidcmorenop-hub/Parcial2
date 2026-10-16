package org.example.parcial2.factory;

import org.example.parcial2.model.MantenimientoCorrectivo;
import org.example.parcial2.model.ServicioTecnologico;

public class FactoryMantenimientoCorrectivo extends FactoryServicioTecnologico {

    /**
     * metodo que permite crear un servicio de mantenimiento de correccion
     * @param codigo
     * @return
     */
    @Override
    public ServicioTecnologico crearServicio(int codigo) {
        return new MantenimientoCorrectivo(codigo);
    }
}
