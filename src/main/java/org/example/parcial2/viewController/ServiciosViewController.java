package org.example.parcial2.viewController;

import org.example.parcial2.controller.ServicioController;
import org.example.parcial2.model.Empresa;
import org.example.parcial2.model.Especialidad;
import org.example.parcial2.model.ServicioTecnologico;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class ServiciosViewController implements Initializable {

    private ServicioController servicioController;
    private ServicioTecnologico servicioSeleccionado;

    private final ObservableList<ServicioTecnologico> listaServicios = FXCollections.observableArrayList();

    @FXML private TableView<ServicioTecnologico> tableServicios;
    @FXML private TableColumn<ServicioTecnologico, Integer> colCodigo;
    @FXML private TableColumn<ServicioTecnologico, String> colNombre;
    @FXML private TableColumn<ServicioTecnologico, Double> colCostoBase;
    @FXML private TableColumn<ServicioTecnologico, Especialidad> colTipo;

    @FXML private ComboBox<String> cmbTipoServicio;
    @FXML private TextField txtCodigo;
    @FXML private TextField txtNombre;
    @FXML private TextField txtDescripcion;
    @FXML private Button btnCrearServicio;

    @FXML private CheckBox chkAtencionPrioritaria;
    @FXML private CheckBox chkSoporte30Dias;
    @FXML private CheckBox chkAtencionDomicilio;

    @FXML private Label lblCostoBase;
    @FXML private Label lblIncrementos;
    @FXML private Label lblCostoFinal;

    @FXML private Button btnGuardarPersonalizacion;
    @FXML private Button btnVolver;


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
        this.servicioController = new ServicioController(Empresa.getInstance());

        cmbTipoServicio.getItems().addAll(
                "Mantenimiento Preventivo",
                "Mantenimiento Correctivo",
                "Instalación de Redes",
                "Recuperación de Información"
        );

        initDataBinding();
        listenerSelection();
        actualizarTabla();

        chkAtencionPrioritaria.setOnAction(e -> recalcularCostosUI());
        chkSoporte30Dias.setOnAction(e -> recalcularCostosUI());
        chkAtencionDomicilio.setOnAction(e -> recalcularCostosUI());

        btnVolver.setOnAction(event -> cerrarVentana());
        btnCrearServicio.setOnAction(this::handleCrearServicio);
        btnGuardarPersonalizacion.setOnAction(this::handleAplicarComplementos);
    }

    private void initDataBinding() {
        colCodigo.setCellValueFactory(data -> new SimpleIntegerProperty(data.getValue().getCodigo()).asObject());
        colNombre.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getNombre()));
        // AQUÍ ESTÁ LA CLAVE: Mostramos el costo total calculado (Base + Complementos) en la tabla
        colCostoBase.setCellValueFactory(data -> new SimpleObjectProperty<>(data.getValue().calcularCosto()));
        colTipo.setCellValueFactory(data -> new SimpleObjectProperty<>(data.getValue().getEspecialidad()));
    }

    private void listenerSelection() {
        tableServicios.getSelectionModel().selectedItemProperty().addListener((obs, anterior, nuevo) -> {
            this.servicioSeleccionado = nuevo;
            recalcularCostosUI();
        });
    }

    /**
     * actualiza la tabla
     */
    private void actualizarTabla() {
        listaServicios.clear();
        listaServicios.addAll(servicioController.obtenerCatalogoServicios());
        tableServicios.setItems(listaServicios);
    }

    /**
     * permite calcular el costo del total
     */
    private void recalcularCostosUI() {
        if (servicioSeleccionado == null) return;

        double base = servicioSeleccionado.getCostoBase();
        lblCostoBase.setText("$" + base);

        ServicioTecnologico decorado = servicioController.aplicarDecoradores(
                servicioSeleccionado,
                chkAtencionPrioritaria.isSelected(),
                chkSoporte30Dias.isSelected(),
                chkAtencionDomicilio.isSelected()
        );

        double total = decorado.calcularCosto();
        double incrementos = total - base;

        lblIncrementos.setText("$" + incrementos);
        lblCostoFinal.setText("$" + total);
    }

    /**
     * permite crear el servicio
     * @param event
     */
    @FXML
    private void handleCrearServicio(ActionEvent event) {
        try {
            String tipo = cmbTipoServicio.getValue();
            int codigo = Integer.parseInt(txtCodigo.getText());
            String nombre = (txtNombre != null && !txtNombre.getText().isEmpty()) ? txtNombre.getText() : tipo;
            String desc = (txtDescripcion != null && !txtDescripcion.getText().isEmpty()) ? txtDescripcion.getText() : "Servicio estándar";

            if (tipo == null) {
                mostrarError("Seleccione un tipo de servicio.");
                return;
            }

            String resultado = servicioController.crearServicio(tipo, codigo, nombre, desc);
            mostrarInformacion(resultado);
            actualizarTabla();
            limpiarFormulario();

        } catch (NumberFormatException e) {
            mostrarError("El código debe ser un número entero válido.");
        }
    }

    /**
     * permite añadirle al servicio los complementos
     * @param event
     */
    @FXML
    private void handleAplicarComplementos(ActionEvent event) {
        if (servicioSeleccionado != null) {
            ServicioTecnologico servicioDecorado = servicioController.aplicarDecoradores(
                    servicioSeleccionado,
                    chkAtencionPrioritaria.isSelected(),
                    chkSoporte30Dias.isSelected(),
                    chkAtencionDomicilio.isSelected()
            );

            servicioController.registrarServicioPersonalizado(servicioDecorado);
            actualizarTabla();

            mostrarInformacion("¡Servicio personalizado creado con éxito! Costo final: $" + servicioDecorado.calcularCosto() + ". Ya está disponible en el registro de solicitudes.");
            chkAtencionPrioritaria.setSelected(false);
            chkSoporte30Dias.setSelected(false);
            chkAtencionDomicilio.setSelected(false);
        } else {
            mostrarError("Seleccione un servicio de la tabla.");
        }
    }

    /**
     * elimina los espacios seleccionados
     */
    private void limpiarFormulario() {
        txtCodigo.clear();
        if (txtNombre != null) txtNombre.clear();
        if (txtDescripcion != null) txtDescripcion.clear();
        cmbTipoServicio.getSelectionModel().clearSelection();
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