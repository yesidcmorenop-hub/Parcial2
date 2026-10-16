package org.example.parcial2;

import org.example.parcial2.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class EmpresaTest {

    private Empresa empresa;

    @BeforeEach
    void setUp() {
        empresa = Empresa.getInstance();
        empresa.getListSolicitud().clear();
        empresa.getListServicioTecnologico().clear();
        empresa.getListCliente().clear();
    }

    /**
     * Test del patron decorator
     * Verifica el precio base de un servicio (mantenimientoPreventivo = 80k )
     */

    @Test
    @DisplayName("Debe calcular costo base de un servicio simple sin decoradores")
    void testCostoServicioSimple() {
        ServicioTecnologico mantPreventivo = new MantenimientoPreventivo(1);
        assertEquals(80000.0, mantPreventivo.calcularCosto());
    }

    /**
     * Test que suma la atención prioritaria con el valor del mantenimiento preventivo
     * 35k + 80k = 115k
     */

    @Test
    @DisplayName("Debe aplicar un decorador de Atención Prioritaria (+35.000)")
    void testDecoradorAtencionPrioritaria() {
        ServicioTecnologico servicio = new MantenimientoPreventivo(1);
        servicio = new AtencionPrioritaria(servicio);

        assertEquals(115000.0, servicio.calcularCosto());
    }
    /**
     * Test que suma varios decoradores como el soporte y domicilio
     * 120k + 50k + 25k = 195k
     */

    @Test
    @DisplayName("Debe encadenar múltiples decoradores (Soporte + Domicilio)")
    void testDecoradoresAnidados() {
        ServicioTecnologico servicio = new MantenimientoCorrectivo(2);
        servicio = new SoporteTecnico(servicio);
        servicio = new AtencionDomicilio(servicio);
        assertEquals(195000.0, servicio.calcularCosto());
    }

    /**
     * Test patron composite
     * Test que calcula el costo de un paquete con serivicios individuales
     * 80k + 180k = 260k
     */

    @Test
    @DisplayName("Debe calcular el costo de un paquete con servicios individuales")
    void testPaqueteTecnologicoSimple() {
        PaqueteTecnologico paquete = new PaqueteTecnologico(10, "Paquete Básico", "Mantenimiento y Red", 0.0, null);
        paquete.agregarServicio(new MantenimientoPreventivo(1));
        paquete.agregarServicio(new InstalacionRed(2));

        assertEquals(260000.0, paquete.calcularCosto());
    }
    /**
     * Test para calcular el costo de un paquete que contiene otro paquete
     * paquete 1(mantenimiento): 80k + 120k = 200k
     * paquete 2(empresarial): paquete 1 + 180k = 380k
     */

    @Test
    @DisplayName("Debe calcular el costo de un paquete que contiene otro paquete")
    void testPaqueteTecnologicoAnidado() {

        PaqueteTecnologico paqueteMantenimiento = new PaqueteTecnologico(10, "Paquete Mantenimiento", "Mantenimientos", 0.0, null);
        paqueteMantenimiento.agregarServicio(new MantenimientoPreventivo(1));
        paqueteMantenimiento.agregarServicio(new MantenimientoCorrectivo(2));

        PaqueteTecnologico paqueteEmpresarial = new PaqueteTecnologico(20, "Paquete Empresarial", "Solución Completa", 0.0, null);
        paqueteEmpresarial.agregarServicio(paqueteMantenimiento);
        paqueteEmpresarial.agregarServicio(new InstalacionRed(3));

        assertEquals(380000.0, paqueteEmpresarial.calcularCosto());
    }
    /**
     * Test para evitar agregar un paquete dentro de si mismo
     */

    @Test
    @DisplayName("Debe evitar autorreferencia directa al agregar un paquete a sí mismo")
    void testEvitarReferenciaCircularMismoPaquete() {
        PaqueteTecnologico paquete = new PaqueteTecnologico(10, "Paquete A", "Prueba", 0.0, null);

        boolean agregado = paquete.agregarServicio(paquete);

        assertFalse(agregado);
        assertEquals(0, paquete.getListServicios().size());
    }
    /**
     * Test patron adapter
     * Debe adaptar la consulta a un TecnicoDisponible
     */

    @Test
    @DisplayName("Debe adaptar la consulta legacy a un objeto TecnicoDisponible estructurado")
    void testAdapterConsultaTecnicoDisponible() {
        SoporteLegacy legacy = new SoporteLegacy();
        IsistemaTecnico adapter = new SoporteLegacyAdapter(legacy);

        TecnicoDisponible tecnico = adapter.consultarDisponibilidad(Especialidad.MAN_PREVENTIVO);

        assertNotNull(tecnico);
        assertEquals("TEC-105", tecnico.getIdTecnico());
        assertTrue(tecnico.isDisponibilidad());
        assertEquals(30000.0, tecnico.getCostoDesplazamiento());
    }
    /**
     * Test para validar la disponibilidad de un tecnico
     */

    @Test
    @DisplayName("Debe validar la disponibilidad de un técnico desde la clase Empresa")
    void testEmpresaValidarDisponibilidad() {
        TecnicoDisponible tecnico = empresa.validarDisponibilidad(Especialidad.INSTALACION_RED);

        assertNotNull(tecnico);
        assertEquals("TEC-234", tecnico.getIdTecnico());
        assertTrue(tecnico.isDisponibilidad());
        assertEquals(35000.0, tecnico.getCostoDesplazamiento());
    }

    /**
     * Test Patron Builder
     */

    @Test
    @DisplayName("Debe crear una Solicitud usando el Builder")
    void testBuilderSolicitud() {
        ArrayList<ServicioTecnologico> servicios = new ArrayList<>();
        servicios.add(new MantenimientoPreventivo(1));

        Solicitud solicitud = new Solicitud.Builder()
                .codigo("SOL-001")
                .fechaCreacion(LocalDate.now())
                .observacion("Urgente")
                .listServicioTecnologico(servicios)
                .build();

        assertEquals("SOL-001", solicitud.getCodigo());
        assertEquals("Urgente", solicitud.getObservacion());
        assertEquals(1, solicitud.getListServicioTecnologico().size());
    }
    /**
     * Test Empresa, debe calcular el total de una solicitud usando los paquetes y decorador
     */

    @Test
    @DisplayName("Debe calcular el costo total de una solicitud en Empresa mezclando Paquetes y Decoradores")
    void testCalcularCostoSolicitudEnEmpresa() {
        ServicioTecnologico servicioConExtra = new AtencionPrioritaria(new MantenimientoPreventivo(1));

        PaqueteTecnologico paquete = new PaqueteTecnologico(10, "Paquete Redes", "Redes y Recup", 0.0, null);
        paquete.agregarServicio(new InstalacionRed(2));
        paquete.agregarServicio(new RecuperacionInformacion(3));


        ArrayList<ServicioTecnologico> listaServicios = new ArrayList<>();
        listaServicios.add(servicioConExtra);
        listaServicios.add(paquete);

        Solicitud solicitud = new Solicitud.Builder()
                .codigo("SOL-100")
                .listServicioTecnologico(listaServicios)
                .build();

        double costoTotal = empresa.calcularCostoSolicitud(solicitud);
        assertEquals(445000.0, costoTotal);
    }
}