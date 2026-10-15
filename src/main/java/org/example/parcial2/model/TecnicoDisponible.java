package org.example.parcial2.model;

public class TecnicoDisponible {
    private int idTecnico;
    private boolean disponibilidad;
    private double costoDesplazamiento;

    public TecnicoDisponible(int idTecnico, boolean disponibilidad, double costoDesplazamiento) {
        this.idTecnico = idTecnico;
        this.disponibilidad = disponibilidad;
        this.costoDesplazamiento = costoDesplazamiento;
    }

    public int getIdTecnico() {
        return idTecnico;
    }

    public void setIdTecnico(int idTecnico) {
        this.idTecnico = idTecnico;
    }

    public boolean isDisponibilidad() {
        return disponibilidad;
    }

    public void setDisponibilidad(boolean disponibilidad) {
        this.disponibilidad = disponibilidad;
    }

    public double getCostoDesplazamiento() {
        return costoDesplazamiento;
    }

    public void setCostoDesplazamiento(double costoDesplazamiento) {
        this.costoDesplazamiento = costoDesplazamiento;
    }
}
