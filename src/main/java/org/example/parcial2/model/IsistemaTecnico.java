package org.example.parcial2.model;

public interface IsistemaTecnico {

    /**
     * metodo para consultar si un tecnico esta o no disponile
     * @param especialidad
     * @return
     */
    TecnicoDisponible consultarDisponibilidad(Especialidad especialidad);
}
