package org.example.parcial2.controller;

import org.example.parcial2.model.*;

import java.util.ArrayList;

public class ServicioController {

    private final Empresa empresa;

    public ServicioController(Empresa empresa) {
        this.empresa = empresa;
    }

    public String crearServicio(String tipo, int codigo, String nombre, String descripcion) {
        ServicioTecnologico nuevoServicio = null;

        if (tipo.equals("Mantenimiento Preventivo")) {
            nuevoServicio = new MantenimientoPreventivo(codigo);
        } else if (tipo.equals("Mantenimiento Correctivo")) {
            nuevoServicio = new MantenimientoCorrectivo(codigo);
        } else if (tipo.equals("Instalación de Redes")) {
            nuevoServicio = new InstalacionRed(codigo);
        } else if (tipo.equals("Recuperación de Información")) {
            nuevoServicio = new RecuperacionInformacion(codigo);
        }

        if (nuevoServicio != null) {
            empresa.registrarServicio(nuevoServicio);
            return "Servicio creado exitosamente.";
        }
        return "No se pudo determinar el tipo de servicio.";
    }

    public ServicioTecnologico aplicarDecoradores(ServicioTecnologico servicioBase, boolean prioritaria, boolean soporte, boolean domicilio) {
        ServicioTecnologico servicioResultado = servicioBase;

        if (prioritaria) {
            servicioResultado = new AtencionPrioritaria(servicioResultado);
        }
        if (soporte) {
            servicioResultado = new SoporteTecnico(servicioResultado);
        }
        if (domicilio) {
            servicioResultado = new AtencionDomicilio(servicioResultado);
        }

        return servicioResultado;
    }

    public ArrayList<ServicioTecnologico> obtenerCatalogoServicios() {
        ArrayList<ServicioTecnologico> catalogo = new ArrayList<>();
        empresa.obtenerCatalogoServicios(catalogo);
        return catalogo;
    }

    public void registrarServicioPersonalizado(ServicioTecnologico servicioPersonalizado) {
        if (servicioPersonalizado != null) {
            empresa.registrarServicio(servicioPersonalizado);
        }
    }
}