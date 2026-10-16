package org.example.parcial2.viewController;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
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

    // Tabla de Historial
    @FXML private TableView<Solicitud> tableSolicitudes;
    @FXML private TableColumn<Solicitud, String> colCodigo;
    @FXML private TableColumn<Solicitud, String> colCliente;
    @FXML private TableColumn<Solicitud, LocalDate> colFecha;
    @FXML private TableColumn<Solicitud, NivelPrioridad> colPrioridad;
    @FXML private TableColumn<Solicitud, Double> colCostoTotal;

    private SolicitudController solicitudController;
    private final ObservableList<ServicioTecnologico> disponiblesObs = FXCollections.observableArrayList();
    private final ObservableList<ServicioTecnologico> seleccionadosObs = FXCollections.observableArrayList();
    private final ObservableList<Solicitud> listaSolicitudesObs = FXCollections.observableArrayList();


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
        this.solicitudController = new SolicitudController(Empresa.getInstance());
        if (solicitudController.obtenerClientes().isEmpty()) {
            Empresa.getInstance().getListCliente().add(new Cliente("Mariana", 123456, "mariana@techcorp.com"));
            Empresa.getInstance().getListCliente().add(new Cliente("Camilo", 112233, "camilo@techcorp.com"));
            Empresa.getInstance().getListCliente().add(new Cliente("Esteban", 122334, "esteban@techcorp.com"));
        }

        cmbClientes.setItems(FXCollections.observableArrayList(solicitudController.obtenerClientes()));
        cmbPrioridad.setItems(FXCollections.observableArrayList(NivelPrioridad.values()));

        cargarCatalogo();
        initTableBinding();
        actualizarTablaSolicitudes();

        configurarCellFactory(listElementosDisponibles);
        configurarCellFactory(listElementosSeleccionados);

        btnVolver.setOnAction(event -> cerrarVentana());
        btnAgregarElementoSolicitud.setOnAction(this::handleAgregarElemento);
        if (btnQuitarElementoSolicitud != null) {
            btnQuitarElementoSolicitud.setOnAction(this::handleQuitarElemento);
        }
        btnRegistrarSolicitud.setOnAction(this::handleRegistrarSolicitud);
    }

    /**
     * carga los servicios disponibles
     */
    private void cargarCatalogo() {
        List<ServicioTecnologico> catalogo = new ArrayList<>();
        solicitudController.obtenerCatalogoServicios(catalogo);
        disponiblesObs.setAll(catalogo);
        listElementosDisponibles.setItems(disponiblesObs);
    }

    private void initTableBinding() {
        colCodigo.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getCodigo()));
        colCliente.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getCliente().getNombre()));
        colFecha.setCellValueFactory(data -> new SimpleObjectProperty<>(data.getValue().getFechaAtencion()));
        colPrioridad.setCellValueFactory(data -> new SimpleObjectProperty<>(data.getValue().getNivelPrioridad()));
        colCostoTotal.setCellValueFactory(data -> new SimpleObjectProperty<>(data.getValue().getCostoTotal()));
    }

    private void actualizarTablaSolicitudes() {
        listaSolicitudesObs.setAll(solicitudController.obtenerSolicitudesRegistradas());
        tableSolicitudes.setItems(listaSolicitudesObs);
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

    /**
     * permite agregar los elementos a las solicitudes
     * @param event
     */
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

    /**
     * permite quitar los elementos de las solicitudes
     * @param event
     */
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

    /**
     * permite calcular el costo total
     */
    private void recalcularCostoTotal() {
        double total = 0.0;
        for (ServicioTecnologico s : seleccionadosObs) {
            total += s.calcularCosto();
        }
        lblCostoTotalSolicitud.setText("Costo Total: $" + total);
    }

    /**
     * permite registrar las solicitudes
     * @param event
     */
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
            actualizarTablaSolicitudes();
            limpiarFormulario();

        } catch (Exception e) {
            mostrarError("Error al registrar la solicitud. Verifique los campos obligatorios.");
        }
    }

    /**
     * elimina los espacios seleccionados
     */
    private void limpiarFormulario() {
        txtCodigoSolicitud.clear();
        cmbClientes.getSelectionModel().clearSelection();
        cmbPrioridad.getSelectionModel().clearSelection();
        dateProgramada.setValue(null);
        txtObservaciones.clear();
        seleccionadosObs.clear();
        cargarCatalogo(); // Devuelve los elementos seleccionados al catálogo disponible
        lblCostoTotalSolicitud.setText("Costo Total: $0");
    }

    /**
     * permite cerrar la ventana y volver al menu
     */
    private void cerrarVentana() {
        Stage stage = (Stage) btnVolver.getScene().getWindow();
        stage.close();
    }

    /**
     * muestra los mensajes despues de hacer algo
     * @param mensaje
     */
    private void mostrarInformacion(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("TechSolutions");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    /**
     * mustra los errores
     * @param mensaje
     */
    private void mostrarError(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("TechSolutions");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}