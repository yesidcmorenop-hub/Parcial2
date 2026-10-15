package org.example.parcial2.model;

public class SoporteLegacy {
    /**
     * Metodo que recibe el codigoEspecialidad y retorna un String
     */
    public String verificarDisponibilidad(int codigoEspecialidad) {
        if (codigoEspecialidad == 101) {
            return "TEC-105|DISPONIBLE|30000";
        } else {
            if (codigoEspecialidad == 102) {
                return "TEC-208|DISPONIBLE|45000";
            } else {
                if (codigoEspecialidad == 103) {
                    return "TEC-234|DISPONIBLE|35000";
                } else {
                    return "SIN_TECNICO|NO_DISPONIBLE|0";
                }
            }
        }
    }
}
