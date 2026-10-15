package org.example.parcial2.viewController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class TecnicosViewController implements Initializable {

    @FXML private Button btnVolver;
    @FXML private ComboBox<String> cmbEspecialidad;
    @FXML private Button btnConsultarTecnico;
    @FXML private TextArea txtRespuestaLegacy;

    @FXML private Label lblIdTecnico;
    @FXML private Label lblDisponibilidad;
    @FXML private Label lblCostoDesplazamiento;
    @FXML private Label lblEstadoConfirmacion;
    @FXML private Button btnVerificarYConfirmar;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cmbEspecialidad.getItems().addAll(
                "Mantenimiento General",
                "Redes y Conectividad",
                "Recuperación de Datos",
                "Soporte de Software"
        );

        btnVolver.setOnAction(event -> cerrarVentana());
        btnConsultarTecnico.setOnAction(this::handleConsultarLegacy);
        btnVerificarYConfirmar.setOnAction(this::handleVerificarYConfirmar);
    }

    @FXML
    private void handleConsultarLegacy(ActionEvent event) {
        String especialidadSeleccionada = cmbEspecialidad.getValue();

        if (especialidadSeleccionada == null || especialidadSeleccionada.isEmpty()) {
            txtRespuestaLegacy.setText("Por favor, seleccione una especialidad primero.");
            return;
        }

        System.out.println("Consultando sistema externo para: " + especialidadSeleccionada);

        String respuestaCrudaSimulada = "TEC-402|DISPONIBLE|35000";
        txtRespuestaLegacy.setText(respuestaCrudaSimulada);

        lblIdTecnico.setText("ID Técnico: TEC-402");
        lblDisponibilidad.setText("Disponibilidad: DISPONIBLE");
        lblCostoDesplazamiento.setText("Costo Desplazamiento: $35.000");
    }

    @FXML
    private void handleVerificarYConfirmar(ActionEvent event) {
        System.out.println("Verificando y confirmando solicitud con técnico adaptado...");
        lblEstadoConfirmacion.setText("Estado: ¡Técnico verificado y asignado con éxito a la solicitud!");
    }

    private void cerrarVentana() {
        Stage stage = (Stage) btnVolver.getScene().getWindow();
        stage.close();
    }
}