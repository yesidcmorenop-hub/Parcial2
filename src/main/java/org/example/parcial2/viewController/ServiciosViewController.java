package org.example.parcial2.viewController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.net.URL;
import java.util.ResourceBundle;

public class ServiciosViewController implements Initializable {

    @FXML private TableView<?> tableServicios;
    @FXML private TableColumn<?, ?> colCodigo;
    @FXML private TableColumn<?, ?> colNombre;
    @FXML private TableColumn<?, ?> colCostoBase;
    @FXML private TableColumn<?, ?> colTipo;

    @FXML private ComboBox<String> cmbTipoServicio;
    @FXML private TextField txtCodigo;
    @FXML private Button btnCrearServicio;

    @FXML private CheckBox chkAtencionPrioritaria;
    @FXML private CheckBox chkSoporte30Dias;
    @FXML private CheckBox chkAtencionDomicilio;

    @FXML private Label lblCostoBase;
    @FXML private Label lblIncrementos;
    @FXML private Label lblCostoFinal;

    @FXML private Button btnGuardarPersonalizacion;
    @FXML private Button btnVolver;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cmbTipoServicio.getItems().addAll(
                "Mantenimiento Preventivo",
                "Mantenimiento Correctivo",
                "Instalación de Redes",
                "Recuperación de Información"
        );

        btnVolver.setOnAction(event -> cerrarVentana());

        btnCrearServicio.setOnAction(this::handleCrearServicio);
        btnGuardarPersonalizacion.setOnAction(this::handleAplicarComplementos);
    }

    @FXML
    private void handleCrearServicio(ActionEvent event) {
        String tipoSeleccionado = cmbTipoServicio.getValue();
        String codigo = txtCodigo.getText();

        System.out.println("Creando servicio tipo: " + tipoSeleccionado + " con código: " + codigo);
    }

    @FXML
    private void handleAplicarComplementos(ActionEvent event) {
        boolean prioritaria = chkAtencionPrioritaria.isSelected();
        boolean soporte = chkSoporte30Dias.isSelected();
        boolean domicilio = chkAtencionDomicilio.isSelected();

        System.out.println("Aplicando complementos -> Prioritaria: " + prioritaria + ", Soporte: " + soporte + ", Domicilio: " + domicilio);
    }

    private void cerrarVentana() {
        Stage stage = (Stage) btnVolver.getScene().getWindow();
        stage.close();
    }
}