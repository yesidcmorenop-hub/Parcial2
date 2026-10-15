package org.example.parcial2.viewController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MenuPrincipalViewController {

    @FXML
    private void handleGestionServicios(ActionEvent event) {
        abrirVentana("servicios-view.fxml", "Gestión y Personalización de Servicios");
    }

    @FXML
    private void handlePaquetesTecnologicos(ActionEvent event) {
        abrirVentana("paquetes-view.fxml", "Configuración de Paquetes Tecnológicos");
    }

    @FXML
    private void handleRegistroSolicitudes(ActionEvent event) {
        abrirVentana("solicitudes-view.fxml", "Registro de Solicitudes de Servicios");
    }

    @FXML
    private void handleConsultaTecnicos(ActionEvent event) {
        abrirVentana("tecnicos-view.fxml", "Consulta de Técnicos y Costos");
    }

    /**
     *  metodo para abrir las ventanas FXMl
     * @param fxmlFile
     * @param titulo
     */
    private void abrirVentana(String fxmlFile, String titulo) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/parcial2/" + fxmlFile));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle(titulo);
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("No se pudo cargar la vista: " + fxmlFile);
        }
    }
}