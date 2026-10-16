package org.example.parcial2.viewController;

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

    private TecnicoController tecnicoController;
    private TecnicoDisponible tecnicoActual;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.tecnicoController = new TecnicoController(Empresa.getInstance());

        // Cargamos las especialidades definidas en el modelo
        cmbEspecialidad.getItems().setAll(Especialidad.values());

        btnVolver.setOnAction(event -> cerrarVentana());
        btnConsultarTecnico.setOnAction(this::handleConsultarLegacy);
        btnVerificarYConfirmar.setOnAction(this::handleVerificarYConfirmar);
    }

    @FXML
    private void handleConsultarLegacy(ActionEvent event) {
        Especialidad especialidadSeleccionada = cmbEspecialidad.getValue();

        if (especialidadSeleccionada == null) {
            txtRespuestaLegacy.setText("Por favor, seleccione una especialidad primero.");
            return;
        }

        // Consultamos a través del controlador de negocio (Adapter integrado)
        tecnicoActual = tecnicoController.consultarDisponibilidadTecnico(especialidadSeleccionada);

        if (tecnicoActual != null) {
            txtRespuestaLegacy.setText("Respuesta traducida por el Adaptador:\n" +
                    "ID Técnico: " + tecnicoActual.getIdTecnico() + "\n" +
                    "Disponible: " + tecnicoActual.isDisponibilidad() + "\n" +
                    "Costo Desplazamiento: $" + tecnicoActual.getCostoDesplazamiento());

            lblIdTecnico.setText("ID Técnico: " + tecnicoActual.getIdTecnico());
            lblDisponibilidad.setText("Disponibilidad: " + (tecnicoActual.isDisponibilidad() ? "DISPONIBLE" : "NO_DISPONIBLE"));
            lblCostoDesplazamiento.setText("Costo Desplazamiento: $" + tecnicoActual.getCostoDesplazamiento());
        } else {
            txtRespuestaLegacy.setText("No se obtuvo respuesta del sistema externo.");
        }
    }

    @FXML
    private void handleVerificarYConfirmar(ActionEvent event) {
        if (tecnicoActual != null && tecnicoActual.isDisponibilidad()) {
            lblEstadoConfirmacion.setText("Estado: ¡Técnico verificado y asignado con éxito a la solicitud!");
        } else {
            lblEstadoConfirmacion.setText("Estado: Atención presencial no confirmada (Sin técnico disponible).");
        }
    }

    private void cerrarVentana() {
        Stage stage = (Stage) btnVolver.getScene().getWindow();
        stage.close();
    }
}