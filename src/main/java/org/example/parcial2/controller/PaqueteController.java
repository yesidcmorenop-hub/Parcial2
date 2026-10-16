package org.example.parcial2.controller;

import org.example.parcial2.model.Empresa;
import org.example.parcial2.model.Especialidad;
import org.example.parcial2.model.PaqueteTecnologico;
import org.example.parcial2.model.ServicioTecnologico;

import java.util.List;

public class PaqueteController {

    private final Empresa empresa;

    public PaqueteController(Empresa empresa) {
        this.empresa = empresa;
    }

    public void obtenerCatalogo(List<ServicioTecnologico> destino) {
        empresa.obtenerCatalogoServicios(destino);
    }

    public String crearPaqueteTecnologico(int codigo, String nombre, List<ServicioTecnologico> serviciosSeleccionados) {
        if (nombre == null || nombre.trim().isEmpty()) {
            return "El nombre del paquete no puede estar vacío.";
        }
        if (serviciosSeleccionados == null || serviciosSeleccionados.isEmpty()) {
            return "Debe seleccionar al menos un elemento para configurar el paquete tecnológico.";
        }

        // Instanciamos el Paquete (Patrón Composite)
        PaqueteTecnologico nuevoPaquete = new PaqueteTecnologico(
                codigo,
                nombre,
                "Paquete tecnológico compuesto",
                0.0,
                Especialidad.MAN_PREVENTIVO
        );

        for (ServicioTecnologico servicio : serviciosSeleccionados) {
            boolean agregado = nuevoPaquete.agregarServicio(servicio);
            if (!agregado) {
                return "Error: No se pudo agregar el elemento debido a restricciones de dependencias circulares.";
            }
        }

        // Se registra en el catálogo general de la empresa como un servicio compuesto más
        empresa.registrarServicio(nuevoPaquete);
        return "Paquete tecnológico creado y registrado exitosamente.";
    }
}