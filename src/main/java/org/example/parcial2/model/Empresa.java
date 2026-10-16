package org.example.parcial2.model;

import java.util.ArrayList;
import java.util.List;

public class Empresa {
    private String nombre;
    private static Empresa instance;
    private ArrayList<Solicitud> listSolicitud;
    private ArrayList<ServicioTecnologico> listServicioTecnologico;
    private ArrayList<Cliente> listCliente;

    private IsistemaTecnico sistemaTecnico;

    private Empresa() {
        this.nombre = "TechSolutions";
        this.listSolicitud = new ArrayList<>();
        this.listServicioTecnologico = new ArrayList<>();
        this.listCliente = new ArrayList<>();
    }

    public static Empresa getInstance() {
        if (instance == null) {
            instance = new Empresa();
        }
        return instance;
    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ArrayList<Solicitud> getListSolicitud() {
        return listSolicitud;
    }

    public ArrayList<ServicioTecnologico> getListServicioTecnologico() {
        return listServicioTecnologico;
    }

    public ArrayList<Cliente> getListCliente() {
        return listCliente;
    }

    public void registrarServicio(ServicioTecnologico servicioTecnologico) {
        this.listServicioTecnologico.add(servicioTecnologico);
    }

    public void obtenerCatalogoServicios(List<ServicioTecnologico> destino) {
        destino.clear();
        destino.addAll(listServicioTecnologico);
    }

    public void registrarSolicitud(Solicitud solicitud) {
        this.listSolicitud.add(solicitud);
    }

    public void registrarCliente(Cliente cliente) {
        this.listCliente.add(cliente);
    }

    public TecnicoDisponible validarDisponibilidad(Especialidad especialidad) {
        if (this.sistemaTecnico == null) {
            this.sistemaTecnico = new SoporteLegacyAdapter(new SoporteLegacy());
        }
        return this.sistemaTecnico.consultarDisponibilidad(especialidad);
    }

    public double calcularCostoSolicitud(Solicitud solicitud) {
        double total = 0.0;
        for (ServicioTecnologico servicio : solicitud.getListServicioTecnologico()) {
            total += servicio.calcularCosto();
        }
        return total;
    }


}
