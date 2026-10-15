package org.example.parcial2.factory;
import org.example.parcial2.model.*;

public interface FactoryServicioTecnologico {
        ServicioTecnologico crearServicio(int codigo, String nombre, String descripcion, double costoBase, Especialidad especialidad);
    }

