package org.example.parcial2.viewController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.example.parcial2.controller.MenuPrincipalController;
import org.example.parcial2.model.Empresa;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MenuPrincipalViewController implements Initializable {

    @FXML private Label lblTituloEmpresa;
    private MenuPrincipalController menuPrincipalController;

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
        this.menuPrincipalController = new MenuPrincipalController(Empresa.getInstance());

        if (lblTituloEmpresa != null) {
            lblTituloEmpresa.setText("Bienvenido a " + menuPrincipalController.getNombreEmpresa());
        }
    }

    /**
     * metodo para abrir la gestion de servicios
     * @param event
     */
    @FXML
    private void handleGestionServicios(ActionEvent event) {
        abrirVentana("servicios-view.fxml", "Gestión y Personalización de Servicios");
    }

    /**
     * metodo para abrir la gestion de paquetes
     * @param event
     */
    @FXML
    private void handlePaquetesTecnologicos(ActionEvent event) {
        abrirVentana("paquetes-view.fxml", "Configuración de Paquetes Tecnológicos");
    }

    /**
     * metodo para abrir la gestion de solicitudes
     * @param event
     */
    @FXML
    private void handleRegistroSolicitudes(ActionEvent event) {
        abrirVentana("solicitudes-view.fxml", "Registro de Solicitudes de Servicios");
    }

    /**
     * metodo para abrir la gestion de tecnicos
     * @param event
     */

    @FXML
    private void handleConsultaTecnicos(ActionEvent event) {
        abrirVentana("tecnicos-view.fxml", "Consulta de Técnicos y Costos");
    }

    /**
     * Método para abrir ventanas
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