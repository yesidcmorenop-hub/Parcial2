package org.example.parcial2.viewController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.net.URL;
import java.util.ResourceBundle;

public class PaquetesViewController implements Initializable {

    @FXML private TextField txtNombrePaquete;
    @FXML private ListView<String> listDisponibles;
    @FXML private ListView<String> listSeleccionados;
    @FXML private Button btnAgregar;
    @FXML private Button btnQuitar;
    @FXML private Button btnCrearPaquete;
    @FXML private Label lblCostoTotalPaquete;
    @FXML private Button btnVolver;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        listDisponibles.getItems().addAll(
                "Mantenimiento Preventivo ($80.000)",
                "Mantenimiento Correctivo ($120.000)",
                "Instalación de Redes ($180.000)",
                "Recuperación de Información ($150.000)"
        );


        btnAgregar.setOnAction(this::handleAgregarElemento);
        btnQuitar.setOnAction(this::handleQuitarElemento);
        btnCrearPaquete.setOnAction(this::handleCrearPaquete);
        btnVolver.setOnAction(event -> cerrarVentana());
    }

    @FXML
    private void handleAgregarElemento(ActionEvent event) {
        String seleccionado = listDisponibles.getSelectionModel().getSelectedItem();
        if (seleccionado != null) {
            listDisponibles.getItems().remove(seleccionado);
            listSeleccionados.getItems().add(seleccionado);
        }
    }

    @FXML
    private void handleQuitarElemento(ActionEvent event) {
        String seleccionado = listSeleccionados.getSelectionModel().getSelectedItem();
        if (seleccionado != null) {
            listSeleccionados.getItems().remove(seleccionado);
            listDisponibles.getItems().add(seleccionado);

        }
    }

    @FXML
    private void handleCrearPaquete(ActionEvent event) {
        String nombre = txtNombrePaquete.getText();
        System.out.println("Creando paquete compuesto: " + nombre);
    }

    private void cerrarVentana() {
        Stage stage = (Stage) btnVolver.getScene().getWindow();
        stage.close();
    }
}