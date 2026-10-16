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

    // Etiqueta opcional en tu FXML para mostrar el título de la empresa
    @FXML private Label lblTituloEmpresa;

    private MenuPrincipalController menuPrincipalController;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Inicializamos el controlador de negocio con el Singleton de la empresa
        this.menuPrincipalController = new MenuPrincipalController(Empresa.getInstance());

        if (lblTituloEmpresa != null) {
            lblTituloEmpresa.setText("Bienvenido a " + menuPrincipalController.getNombreEmpresa());
        }
    }

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
     * Método para abrir las ventanas FXML secundarias
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