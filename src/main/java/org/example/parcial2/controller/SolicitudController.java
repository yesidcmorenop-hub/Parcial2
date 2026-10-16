package org.example.parcial2.controller;

import org.example.parcial2.model.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SolicitudController {

    private final Empresa empresa;

    public SolicitudController(Empresa empresa) {
        this.empresa = empresa;
    }

    public List<Cliente> obtenerClientes() {
        return empresa.getListCliente();
    }

    public void obtenerCatalogoServicios(List<ServicioTecnologico> destino) {
        empresa.obtenerCatalogoServicios(destino);
    }

    public List<Solicitud> obtenerSolicitudesRegistradas() {
        return empresa.getListSolicitud();
    }

    public String registrarSolicitud(String codigo, Cliente cliente, List<ServicioTecnologico> servicios,
                                     LocalDate fechaAtencion, NivelPrioridad prioridad, String observaciones) {
        if (codigo == null || codigo.trim().isEmpty()) {
            return "El código de la solicitud es obligatorio.";
        }
        if (cliente == null) {
            return "Debe seleccionar un cliente asociado.";
        }
        if (servicios == null || servicios.isEmpty()) {
            return "La solicitud debe incluir al menos un servicio o paquete tecnológico.";
        }

        double costoTotal = 0.0;
        for (ServicioTecnologico s : servicios) {
            costoTotal += s.calcularCosto();
        }

        Solicitud nuevaSolicitud = new Solicitud.Builder()
                .codigo(codigo)
                .fechaCreacion(LocalDate.now())
                .cliente(cliente)
                .listServicioTecnologico(new ArrayList<>(servicios))
                .fechaAtencion(fechaAtencion)
                .nivelPrioridad(prioridad)
                .observacion(observaciones)
                .costoTotal(costoTotal)
                .build();

        empresa.registrarSolicitud(nuevaSolicitud);
        return "Solicitud registrada exitosamente con un costo total de $" + costoTotal;
    }
}