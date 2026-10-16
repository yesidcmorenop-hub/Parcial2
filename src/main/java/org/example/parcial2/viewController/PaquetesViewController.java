package org.example.parcial2.viewController;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.example.parcial2.controller.PaqueteController;
import org.example.parcial2.model.Empresa;
import org.example.parcial2.model.ServicioTecnologico;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class PaquetesViewController implements Initializable {

    @FXML private TextField txtNombrePaquete;
    @FXML private ListView<ServicioTecnologico> listDisponibles;
    @FXML private ListView<ServicioTecnologico> listSeleccionados;
    @FXML private Button btnAgregar;
    @FXML private Button btnQuitar;
    @FXML private Button btnCrearPaquete;
    @FXML private Label lblCostoTotalPaquete;
    @FXML private Button btnVolver;

    private PaqueteController paqueteController;
    private final ObservableList<ServicioTecnologico> listaDisponiblesObs = FXCollections.observableArrayList();
    private final ObservableList<ServicioTecnologico> listaSeleccionadosObs = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.paqueteController = new PaqueteController(Empresa.getInstance());

        cargarCatalogoDisponibles();

        btnAgregar.setOnAction(this::handleAgregarElemento);
        btnQuitar.setOnAction(this::handleQuitarElemento);
        btnCrearPaquete.setOnAction(this::handleCrearPaquete);
        btnVolver.setOnAction(event -> cerrarVentana());
    }

    private void cargarCatalogoDisponibles() {
        List<ServicioTecnologico> catalogo = new ArrayList<>();
        paqueteController.obtenerCatalogo(catalogo);
        listaDisponiblesObs.setAll(catalogo);
        listDisponibles.setItems(listaDisponiblesObs);

        // Personalizamos cómo se ven los objetos en las listas gráficas
        configurarCellFactory(listDisponibles);
        configurarCellFactory(listSeleccionados);
    }

    private void configurarCellFactory(ListView<ServicioTecnologico> listView) {
        listView.setCellFactory(param -> new ListCell<ServicioTecnologico>() {
            @Override
            protected void updateItem(ServicioTecnologico item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(item.getNombre() + " (Costo: $" + item.calcularCosto() + ")");
                }
            }
        });
    }

    @FXML
    private void handleAgregarElemento(ActionEvent event) {
        ServicioTecnologico seleccionado = listDisponibles.getSelectionModel().getSelectedItem();
        if (seleccionado != null) {
            listaDisponiblesObs.remove(seleccionado);
            listaSeleccionadosObs.add(seleccionado);
            listSeleccionados.setItems(listaSeleccionadosObs);
            recalcularCostoVisual();
        }
    }

    @FXML
    private void handleQuitarElemento(ActionEvent event) {
        ServicioTecnologico seleccionado = listSeleccionados.getSelectionModel().getSelectedItem();
        if (seleccionado != null) {
            listaSeleccionadosObs.remove(seleccionado);
            listaDisponiblesObs.add(seleccionado);
            listDisponibles.setItems(listaDisponiblesObs);
            recalcularCostoVisual();
        }
    }

    private void recalcularCostoVisual() {
        double total = 0.0;
        for (ServicioTecnologico s : listaSeleccionadosObs) {
            total += s.calcularCosto();
        }
        lblCostoTotalPaquete.setText("$" + total);
    }

    @FXML
    private void handleCrearPaquete(ActionEvent event) {
        try {
            String nombre = txtNombrePaquete.getText();
            int codigoGenerado = (int) (Math.random() * 90000) + 10000; // Código único aleatorio

            String resultado = paqueteController.crearPaqueteTecnologico(codigoGenerado, nombre, new ArrayList<>(listaSeleccionadosObs));
            mostrarInformacion(resultado);

            // Si la creación fue exitosa, actualizamos la lista disponible y limpiamos la interfaz sin cerrar la ventana
            if (!resultado.contains("Error")) {
                cargarCatalogoDisponibles();
                limpiarFormulario();
            }
        } catch (Exception e) {
            mostrarError("Verifique los datos ingresados para el paquete.");
        }
    }

    private void limpiarFormulario() {
        txtNombrePaquete.clear();
        listaSeleccionadosObs.clear();
        listSeleccionados.setItems(listaSeleccionadosObs);
        lblCostoTotalPaquete.setText("$0.0");
    }

    private void cerrarVentana() {
        Stage stage = (Stage) btnVolver.getScene().getWindow();
        stage.close();
    }

    private void mostrarInformacion(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("TechSolutions");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    private void mostrarError(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("TechSolutions");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}