package org.example.parcial2.model;

import java.time.LocalDate;
import java.util.ArrayList;

import static java.lang.System.out;

public class PruebaAdapter {
        public static void main(String[] args) {
            System.out.println("=== INICIANDO PRUEBA INTEGRAL DEL SISTEMA TECHSOLUTIONS ===\n");

            // 1. Singleton: Obtenemos la instancia única de Empresa
            Empresa empresa = Empresa.getInstance();
            System.out.println("1. Empresa inicializada: " + empresa.getNombre());

            // 2. Factory & Servicios: Creamos servicios base
            // (Asumiendo que tus fábricas o constructores reciben el código como vimos antes)
            ServicioTecnologico preventivo = new MantenimientoPreventivo(1234);
            ServicioTecnologico correctivo = new MantenimientoCorrectivo(3241);

            empresa.registrarServicio(preventivo);
            empresa.registrarServicio(correctivo);
            System.out.println("2. Servicios creados y registrados en el catálogo.");

            // 3. Decorator: Añadimos características complementarias
            // Supongamos que al correctivo le ponemos Atención Prioritaria ($35.000)
            // (Asegúrate de tener tu clase decoradora concreta para Prioridad)
            // ServicioTecnologico servicioDecorado = new AtencionPrioritaria(correctivo);
            // System.out.println("3. Costo con decorador (Prioridad): $" + servicioDecorado.calcularCosto());

            // 4. Composite: Creamos un Paquete Tecnológico
            PaqueteTecnologico paqueteEmpresarial = new PaqueteTecnologico(
                    9998, "Paquete Empresarial", "Mantenimiento integral y preventivo", 0.0, Especialidad.MAN_PREVENTIVO
            );
            paqueteEmpresarial.agregarServicio(preventivo);
            paqueteEmpresarial.agregarServicio(correctivo);

            System.out.println("4. Paquete tecnológico creado. Costo total del paquete: $" + paqueteEmpresarial.calcularCosto());

            // 5. Adapter: Consultamos disponibilidad de un técnico mediante LegacySupport
            TecnicoDisponible tecnico = empresa.validarDisponibilidad(Especialidad.MAN_CORRECTIVO);
            System.out.println("5. Adaptador probado - Técnico asignado: " + tecnico.getIdTecnico() +
                    " | Disponible: " + tecnico.isDisponibilidad() +
                    " | Desplazamiento: $" + tecnico.getCostoDesplazamiento());

            // 6. Builder: Registramos una Solicitud completa
            Cliente clientePrueba = new Cliente("Juan Pérez", 12345678, "juan@mail.com");
            empresa.getListCliente().add(clientePrueba);

            ArrayList<ServicioTecnologico> serviciosSolicitados = new ArrayList<>();
            serviciosSolicitados.add(paqueteEmpresarial);

            Solicitud solicitud = new Solicitud.Builder()
                    .codigo("SOL-001")
                    .fechaCreacion(LocalDate.now())
                    .cliente(clientePrueba)
                    .listServicioTecnologico(serviciosSolicitados)
                    .nivelPrioridad(NivelPrioridad.ALTA)
                    .observacion("Revisión urgente de equipos de red.")
                    .build();

            empresa.registrarSolicitud(solicitud);

            double costoTotalSolicitud = empresa.calcularCostoSolicitud(solicitud) + (tecnico.isDisponibilidad() ? tecnico.getCostoDesplazamiento() : 0);
            solicitud.setCostoTotal(costoTotalSolicitud);

            System.out.println("6. Solicitud creada exitosamente con Builder.");
            System.out.println("   -> Código Solicitud: " + solicitud.getCodigo());
            System.out.println("   -> Cliente: " + solicitud.getCliente().getNombre());
            System.out.println("   -> Costo Total (Servicios + Desplazamiento): $" + solicitud.getCostoTotal());

            System.out.println("\n=== ¡PRUEBA FINALIZADA CON ÉXITO! ===");
        }
}
