package org.example.parcial2.controller;

import org.example.parcial2.model.*;

import java.util.ArrayList;

public class ServicioController {

    private final Empresa empresa;

    public ServicioController(Empresa empresa) {
        this.empresa = empresa;
    }

    /**
     * Registra un nuevo servicio utilizando los constructores / fábricas del modelo.
     */
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

    /**
     * Aplica el patrón Decorator según las características seleccionadas.
     */
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

    /**
     * Obtiene la lista completa de servicios del catálogo.
     */
    public ArrayList<ServicioTecnologico> obtenerCatalogoServicios() {
        ArrayList<ServicioTecnologico> catalogo = new ArrayList<>();
        empresa.obtenerCatalogoServicios(catalogo);
        return catalogo;
    }

    /**
     * Registra un servicio personalizado (decorado) en el catálogo general de la empresa.
     */
    public void registrarServicioPersonalizado(ServicioTecnologico servicioPersonalizado) {
        if (servicioPersonalizado != null) {
            empresa.registrarServicio(servicioPersonalizado);
        }
    }
}


