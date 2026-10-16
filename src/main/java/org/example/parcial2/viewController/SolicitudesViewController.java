package org.example.parcial2.viewController;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.example.parcial2.controller.SolicitudController;
import org.example.parcial2.model.*;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class SolicitudesViewController implements Initializable {

    @FXML private Button btnVolver;
    @FXML private TextField txtCodigoSolicitud;
    @FXML private ComboBox<Cliente> cmbClientes;
    @FXML private ListView<ServicioTecnologico> listElementosDisponibles;
    @FXML private ListView<ServicioTecnologico> listElementosSeleccionados;
    @FXML private Button btnAgregarElementoSolicitud;
    @FXML private Button btnQuitarElementoSolicitud;

    @FXML private DatePicker dateProgramada;
    @FXML private ComboBox<NivelPrioridad> cmbPrioridad;
    @FXML private TextArea txtObservaciones;
    @FXML private Label lblCostoTotalSolicitud;
    @FXML private Button btnRegistrarSolicitud;

    private SolicitudController solicitudController;
    private final ObservableList<ServicioTecnologico> disponiblesObs = FXCollections.observableArrayList();
    private final ObservableList<ServicioTecnologico> seleccionadosObs = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.solicitudController = new SolicitudController(Empresa.getInstance());
        if (solicitudController.obtenerClientes().isEmpty()) {

            Empresa.getInstance().getListCliente().add(new Cliente("Mariana", 123456, "mariana@techcorp.com"));
            Empresa.getInstance().getListCliente().add(new Cliente("Camilo", 112233, "camilo@techcorp.com"));
            Empresa.getInstance().getListCliente().add(new Cliente("Esteban", 122334, "esteban@techcorp.com"));
        }

        cmbClientes.setItems(FXCollections.observableArrayList(solicitudController.obtenerClientes()));
        cmbPrioridad.setItems(FXCollections.observableArrayList(NivelPrioridad.values()));

        cargarCatalogo();

        configurarCellFactory(listElementosDisponibles);
        configurarCellFactory(listElementosSeleccionados);

        btnVolver.setOnAction(event -> cerrarVentana());
        btnAgregarElementoSolicitud.setOnAction(this::handleAgregarElemento);
        if (btnQuitarElementoSolicitud != null) {
            btnQuitarElementoSolicitud.setOnAction(this::handleQuitarElemento);
        }
        btnRegistrarSolicitud.setOnAction(this::handleRegistrarSolicitud);
    }

    private void cargarCatalogo() {
        List<ServicioTecnologico> catalogo = new ArrayList<>();
        solicitudController.obtenerCatalogoServicios(catalogo);
        disponiblesObs.setAll(catalogo);
        listElementosDisponibles.setItems(disponiblesObs);
    }

    private void configurarCellFactory(ListView<ServicioTecnologico> listView) {
        listView.setCellFactory(param -> new ListCell<ServicioTecnologico>() {
            @Override
            protected void updateItem(ServicioTecnologico item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(item.getNombre() + " - Costo: $" + item.calcularCosto());
                }
            }
        });
    }

    @FXML
    private void handleAgregarElemento(ActionEvent event) {
        ServicioTecnologico seleccionado = listElementosDisponibles.getSelectionModel().getSelectedItem();
        if (seleccionado != null) {
            disponiblesObs.remove(seleccionado);
            seleccionadosObs.add(seleccionado);
            listElementosSeleccionados.setItems(seleccionadosObs);
            recalcularCostoTotal();
        }
    }

    @FXML
    private void handleQuitarElemento(ActionEvent event) {
        ServicioTecnologico seleccionado = listElementosSeleccionados.getSelectionModel().getSelectedItem();
        if (seleccionado != null) {
            seleccionadosObs.remove(seleccionado);
            disponiblesObs.add(seleccionado);
            listElementosDisponibles.setItems(disponiblesObs);
            recalcularCostoTotal();
        }
    }

    private void recalcularCostoTotal() {
        double total = 0.0;
        for (ServicioTecnologico s : seleccionadosObs) {
            total += s.calcularCosto();
        }
        lblCostoTotalSolicitud.setText("$" + total);
    }

    @FXML
    private void handleRegistrarSolicitud(ActionEvent event) {
        try {
            String codigo = txtCodigoSolicitud.getText();
            Cliente cliente = cmbClientes.getValue();
            NivelPrioridad prioridad = cmbPrioridad.getValue();
            LocalDate fechaAtencion = dateProgramada.getValue();
            String obs = txtObservaciones.getText();

            String resultado = solicitudController.registrarSolicitud(
                    codigo, cliente, new ArrayList<>(seleccionadosObs), fechaAtencion, prioridad, obs
            );

            mostrarInformacion(resultado);
            cerrarVentana();
        } catch (Exception e) {
            mostrarError("Error al registrar la solicitud. Verifique los campos obligatorios.");
        }
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
