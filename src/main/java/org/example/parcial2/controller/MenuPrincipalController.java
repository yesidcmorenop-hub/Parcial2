package org.example.parcial2.controller;

import org.example.parcial2.model.Empresa;

public class MenuPrincipalController {

    private final Empresa empresa;

    public MenuPrincipalController(Empresa empresa) {
        this.empresa = empresa;
    }

    public String getNombreEmpresa() {
        return empresa.getNombre();
    }
}