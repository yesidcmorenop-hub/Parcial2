package org.example.parcial2.viewController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class SolicitudesViewController implements Initializable {

    @FXML private Button btnVolver;
    @FXML private TextField txtCodigoSolicitud;
    @FXML private ComboBox<String> cmbClientes;
    @FXML private ListView<String> listElementosDisponibles;
    @FXML private Button btnAgregarElementoSolicitud;

    @FXML private DatePicker dateProgramada;
    @FXML private ComboBox<String> cmbPrioridad;
    @FXML private TextArea txtObservaciones;
    @FXML private Label lblCostoTotalSolicitud;
    @FXML private Button btnRegistrarSolicitud;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cmbClientes.getItems().addAll(
                "Empresa A - TechCorp S.A.S.",
                "Empresa B - InnovaSoft Ltda.",
                "Empresa C - Soluciones Globales"
        );

        cmbPrioridad.getItems().addAll("Baja", "Media", "Alta", "Urgente");

        listElementosDisponibles.getItems().addAll(
                "[Servicio] Mantenimiento Preventivo ($80.000)",
                "[Servicio] Instalación de Redes ($180.000)",
                "[Paquete] Paquete Empresarial Pro"
        );

        btnVolver.setOnAction(event -> cerrarVentana());
        btnAgregarElementoSolicitud.setOnAction(this::handleAgregarElemento);
        btnRegistrarSolicitud.setOnAction(this::handleRegistrarSolicitud);
    }

    @FXML
    private void handleAgregarElemento(ActionEvent event) {
        String seleccionado = listElementosDisponibles.getSelectionModel().getSelectedItem();
        if (seleccionado != null) {
            System.out.println("Elemento agregado a la solicitud: " + seleccionado);
        }
    }

    @FXML
    private void handleRegistrarSolicitud(ActionEvent event) {
        String codigo = txtCodigoSolicitud.getText();
        String clienteSeleccionado = cmbClientes.getValue();
        String prioridad = cmbPrioridad.getValue();

        System.out.println("Registrando Solicitud [Builder] -> Código: " + codigo +
                ", Cliente: " + clienteSeleccionado +
                ", Prioridad: " + prioridad);

    }

    private void cerrarVentano() {
    }

    private void cerrarVentana() {
        Stage stage = (Stage) btnVolver.getScene().getWindow();
        stage.close();
    }
}