package org.example.parcial2.model;

public class SoporteLegacyAdapter implements IsistemaTecnico{
    private SoporteLegacy legacySystem;

    public SoporteLegacyAdapter(SoporteLegacy legacySystem) {
        this.legacySystem = legacySystem;
    }

    @Override
    public TecnicoDisponible consultarDisponibilidad(Especialidad especialidad) {
        int codigo = 0;

        if (especialidad == Especialidad.MAN_PREVENTIVO) {
            codigo = 101;
        } else if (especialidad == Especialidad.MAN_CORRECTIVO) {
            codigo = 102;
        } else if (especialidad == Especialidad.INSTALACION_RED) {
            codigo = 103;
        } else if (especialidad == Especialidad.RECUPERAR_INFORMACION) {
            codigo = 104;
        }
        String respuesta = legacySystem.verificarDisponibilidad(codigo);

        String[] partes = respuesta.split("\\|");

        String idTecnico = partes[0];
        boolean disponible = partes[1].equals("DISPONIBLE");
        double costo = Double.parseDouble(partes[2]);

        return new TecnicoDisponible(idTecnico, disponible, costo);
    }

}
