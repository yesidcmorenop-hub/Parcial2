package org.example.parcial2.viewController;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.example.parcial2.controller.TecnicoController;
import org.example.parcial2.model.Empresa;
import org.example.parcial2.model.Especialidad;
import org.example.parcial2.model.TecnicoDisponible;

import java.net.URL;
import java.util.ResourceBundle;

public class TecnicosViewController implements Initializable {

    @FXML private Button btnVolver;
    @FXML private ComboBox<Especialidad> cmbEspecialidad;
    @FXML private Button btnConsultarTecnico;
    @FXML private TextArea txtRespuestaLegacy;

    @FXML private Label lblIdTecnico;
    @FXML private Label lblDisponibilidad;
    @FXML private Label lblCostoDesplazamiento;
    @FXML private Label lblEstadoConfirmacion;
    @FXML private Button btnVerificarYConfirmar;

    // Tabla de Historial de Técnicos
    @FXML private TableView<ConsultaTecnicoRecord> tableTecnicos;
    @FXML private TableColumn<ConsultaTecnicoRecord, String> colEspecialidad;
    @FXML private TableColumn<ConsultaTecnicoRecord, String> colIdTecnico;
    @FXML private TableColumn<ConsultaTecnicoRecord, String> colDisponibilidad;
    @FXML private TableColumn<ConsultaTecnicoRecord, Double> colCostoDesplazamiento;

    private TecnicoController tecnicoController;
    private TecnicoDisponible tecnicoActual;
    private final ObservableList<ConsultaTecnicoRecord> listaHistorialObs = FXCollections.observableArrayList();


    /**
     * metodo que inicia los procesos
     * @param location
     * The location used to resolve relative paths for the root object, or
     * {@code null} if the location is not known.
     *
     * @param resources
     * The resources used to localize the root object, or {@code null} if
     * the root object was not localized.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.tecnicoController = new TecnicoController(Empresa.getInstance());

        cmbEspecialidad.getItems().setAll(Especialidad.values());

        initTableBinding();

        btnVolver.setOnAction(event -> cerrarVentana());
        btnConsultarTecnico.setOnAction(this::handleConsultarLegacy);
        btnVerificarYConfirmar.setOnAction(this::handleVerificarYConfirmar);
    }

    private void initTableBinding() {
        colEspecialidad.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getEspecialidad()));
        colIdTecnico.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getIdTecnico()));
        colDisponibilidad.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getDisponibilidad()));
        colCostoDesplazamiento.setCellValueFactory(data -> new SimpleObjectProperty<>(data.getValue().getCostoDesplazamiento()));

        tableTecnicos.setItems(listaHistorialObs);
    }

    /**
     * permite consultar la informacion del t4ecnico
     * @param event
     */
    @FXML
    private void handleConsultarLegacy(ActionEvent event) {
        Especialidad especialidadSeleccionada = cmbEspecialidad.getValue();

        if (especialidadSeleccionada == null) {
            txtRespuestaLegacy.setText("Por favor, seleccione una especialidad primero.");
            return;
        }
        tecnicoActual = tecnicoController.consultarDisponibilidadTecnico(especialidadSeleccionada);

        if (tecnicoActual != null) {
            String estadoDisp = tecnicoActual.isDisponibilidad() ? "DISPONIBLE" : "NO_DISPONIBLE";

            txtRespuestaLegacy.setText("Respuesta traducida por el Adaptador:\n" +
                    "ID Técnico: " + tecnicoActual.getIdTecnico() + "\n" +
                    "Disponible: " + tecnicoActual.isDisponibilidad() + "\n" +
                    "Costo Desplazamiento: $" + tecnicoActual.getCostoDesplazamiento());

            lblIdTecnico.setText("ID Técnico: " + tecnicoActual.getIdTecnico());
            lblDisponibilidad.setText("Disponibilidad: " + estadoDisp);
            lblCostoDesplazamiento.setText("Costo Desplazamiento: $" + tecnicoActual.getCostoDesplazamiento());

            ConsultaTecnicoRecord record = new ConsultaTecnicoRecord(
                    especialidadSeleccionada.toString(),
                    tecnicoActual.getIdTecnico(),
                    estadoDisp,
                    tecnicoActual.getCostoDesplazamiento()
            );
            listaHistorialObs.add(record);

        } else {
            txtRespuestaLegacy.setText("No se obtuvo respuesta del sistema externo.");
        }
    }

    /**
     * permite verificar si el tecnico esta disponible
     * @param event
     */
    @FXML
    private void handleVerificarYConfirmar(ActionEvent event) {
        if (tecnicoActual != null && tecnicoActual.isDisponibilidad()) {
            lblEstadoConfirmacion.setText("Estado: ¡Técnico verificado y asignado con éxito a la solicitud!");
        } else {
            lblEstadoConfirmacion.setText("Estado: Atención presencial no confirmada (Sin técnico disponible).");
        }
    }

    /**
     * permite cerrar la ventana y volver al menu
     */
    private void cerrarVentana() {
        Stage stage = (Stage) btnVolver.getScene().getWindow();
        stage.close();
    }

    /**
     * Clase auxiliar interna para modelar las filas de la tabla de historial de técnicos.
     */
    public static class ConsultaTecnicoRecord {
        private final String especialidad;
        private final String idTecnico;
        private final String disponibilidad;
        private final double costoDesplazamiento;

        public ConsultaTecnicoRecord(String especialidad, String idTecnico, String disponibilidad, double costoDesplazamiento) {
            this.especialidad = especialidad;
            this.idTecnico = idTecnico;
            this.disponibilidad = disponibilidad;
            this.costoDesplazamiento = costoDesplazamiento;
        }

        public String getEspecialidad() { return especialidad; }
        public String getIdTecnico() { return idTecnico; }
        public String getDisponibilidad() { return disponibilidad; }
        public double getCostoDesplazamiento() { return costoDesplazamiento; }
    }
}