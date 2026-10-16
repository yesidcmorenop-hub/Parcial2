package org.example.parcial2.controller;

import org.example.parcial2.model.Empresa;
import org.example.parcial2.model.Especialidad;
import org.example.parcial2.model.TecnicoDisponible;

public class TecnicoController {

    private final Empresa empresa;

    public TecnicoController(Empresa empresa) {
        this.empresa = empresa;
    }

    public TecnicoDisponible consultarDisponibilidadTecnico(Especialidad especialidad) {
        if (especialidad == null) {
            return null;
        }
        TecnicoDisponible tecnico = empresa.validarDisponibilidad(especialidad);

        return tecnico;
    }
}