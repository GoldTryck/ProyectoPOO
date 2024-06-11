package eq.poo2496.veterinaria.controller;

import eq.poo2496.veterinaria.entity.*;
import eq.poo2496.veterinaria.enums.TipoCita;
import eq.poo2496.veterinaria.util.Utility;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import org.springframework.stereotype.Component;

import java.time.*;
import java.util.List;
import java.util.Optional;


@Component
public class AltaCitaController extends personaController {

    @FXML
    public Pane citaRadioButtons;
    @FXML
    public Spinner<Integer> hora;
    @FXML
    public Button dispButton;
    public Button buscarCliente;
    public Button buscarAsistente;
    public Button buscarVet;
    public Button limpiar;

    public TextField idCliente;
    public HBox asistentePane;
    public TextField idAsistente;
    public HBox veterinarioPane;
    public TextField idVeterinario;
    public TableView<Paquete> paquetes;
    public TableColumn<Paquete, String> paqNombre;
    public TableView<ServicioIndividual> servicios;
    public TableColumn<ServicioIndividual, String> servNombre;

    public Pane controlFecha;
    public Pane controlCliente;
    public Pane serviciosPane;
    public Pane descripcionPane;
    public TextArea descripcion;
    public Pane registerPane;

    private LocalDateTime citaDateTime;
    private RadioButton tipoCita;

    public void initialize() {
        paqNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        servNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));

        loadList();
        ToggleGroup radioButtonsGroup = Utility.loadRadioButtons(citaRadioButtons, TipoCita.class);
        radioButtonsGroup.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            tipoCita = (RadioButton) newValue;
            citaRadioButtons.getChildren().forEach(node -> {
                node.setDisable(true);
            });
            controlFecha.setVisible(true);
            persona.setVisible(true);
            descripcionPane.setVisible(true);
            registerPane.setVisible(true);

        });
        mapTable();
        dateListener();
        fecha.valueProperty().addListener((observable, oldValue, newValue) -> {
            isValidDate(fecha, newValue);
        });
        idCliente.textProperty().addListener((observable, oldValue, newValue) -> {
            buscarCliente.setDisable(!observable.getValue().matches("[0-9]+"));
        });
        buscarCliente.setOnAction(event ->{
            buscarCliente();
            persona.requestFocus();
        });
        idVeterinario.textProperty().addListener((observable, oldValue, newValue) -> {
            buscarVet.setDisable(!observable.getValue().matches("[0-9]+"));
        });
        idAsistente.textProperty().addListener((observable, oldValue, newValue) -> {
            buscarAsistente.setDisable(!observable.getValue().matches("[0-9]+"));
        });
        buscarAsistente.setOnAction(event -> buscarAsistente());
        buscarVet.setOnAction(event -> buscarVeterinario());

        servicios.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        paquetes.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        register.disableProperty().bind(
                Bindings.createBooleanBinding(
                        () -> servicios.getSelectionModel().getSelectedItems().isEmpty() &&
                                paquetes.getSelectionModel().getSelectedItems().isEmpty(),
                        servicios.getSelectionModel().getSelectedItems(),
                        paquetes.getSelectionModel().getSelectedItems()
                )
        );
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(9, 17, 9);
        hora.setValueFactory(valueFactory);
        fecha.valueProperty().addListener((observable, oldValue, newValue) -> updateCitaDateTime());
        hora.valueProperty().addListener((observable, oldValue, newValue) -> updateCitaDateTime());

        register.setOnAction(event -> registerButton());
        limpiar.setOnAction(event -> reload());
    }

    private void updateCitaDateTime() {
        LocalDate fecha = this.fecha.getValue();
        int hora = this.hora.getValue();

        citaDateTime = LocalDateTime.of(fecha, LocalTime.of(hora, 0));
    }


    @Override
    void validateFields() {

    }

    @Override
    void registerButton() {
        Cliente cliente = (Cliente) persona.getItems().getFirst();
        Cita cita = new Cita();
        cita.setCliente(cliente);
        cita.setFechaHora(citaDateTime);
        cita.setDescripcionServicio(descripcion.getText());
        if(tipoCita.getText().equals("MEDICA")){
            Veterinario vet = (Veterinario) persona.getItems().getLast();
            cita.setVeterinario(vet);
            Optional<ServicioIndividual> si = servicioIndividualS.getById(1L);
            cita.setServiciosIndividuales(si.stream().toList());
        }else{
            Asistente asistente = (Asistente) persona.getItems().getLast();
            cita.setAsistente(asistente);
            cita.setServiciosIndividuales(servicios.getSelectionModel().getSelectedItems());
            cita.setPaquetes(paquetes.getSelectionModel().getSelectedItems());
        }
        try {
            citaS.validateAndSaveCita(cita);
            Utility.showDialog("Cita registrada", "La cita fue registrada con éxito", Alert.AlertType.INFORMATION);
        } catch (RuntimeException e) {
            Utility.showDialog("Error", e.getMessage(), Alert.AlertType.ERROR);
        }

    }
    @Override
    public void isValidDate(DatePicker date, LocalDate newValue){
        if(newValue != null && newValue.isAfter(LocalDate.now())){
            date.setStyle(validated);
            fecha.setDisable(true);
            hora.setDisable(false);
            controlCliente.setVisible(true);
        }else{
            date.setStyle(notValidated);
        }
    }

    public void buscarCliente() {
        Long id = Long.valueOf(idCliente.getText());
        Cliente cliente = clienteS.getById(id);
        if (cliente != null) {
            persona.getItems().clear();
            persona.setItems(FXCollections.observableArrayList(cliente));
            buscarCliente.setDisable(true);
            idCliente.setDisable(true);
            if (tipoCita.getText().equals("MEDICA")) {
                veterinarioPane.setVisible(true);
            }else{
                asistentePane.setVisible(true);
            }
        }else {
            Utility.showDialog("Cliente no encontrado", "No se encontró un cliente con el ID proporcionado", Alert.AlertType.ERROR);
        }
    }
    private void buscarVeterinario(){
        Long id = Long.valueOf(idVeterinario.getText());
        Persona veterinario = veterinarioS.getById(id);
        if (veterinario != null) {
            combinedList = persona.getItems();
            combinedList.addAll(veterinario);
            persona.setItems(combinedList);
            buscarVet.setDisable(true);
            idVeterinario.setDisable(true);
            register.setDisable(false);
        }else {
            Utility.showDialog("Veterinario no encontrado", "No se encontró un veterinario con el ID proporcionado", Alert.AlertType.ERROR);
        }
    }
    private void buscarAsistente(){
        Long id = Long.valueOf(idAsistente.getText());
        Persona asistente = asistenteS.getById(id);
        if (asistente != null) {
            combinedList = persona.getItems();
            combinedList.addAll(asistente);
            persona.setItems(combinedList);
            buscarAsistente.setDisable(true);
            idAsistente.setDisable(true);
            serviciosPane.setVisible(true);
        }else {
            Utility.showDialog("Asistente no encontrado", "No se encontró un asistente con el ID proporcionado", Alert.AlertType.ERROR);
        }
    }
    private void reload(){
        main.cargaData("/eq/poo2496/veterinaria/altaCita.fxml");
    }
    private void loadList(){
        List<ServicioIndividual> s = servicioIndividualS.findAll();
        s.removeFirst();
        paquetes.setItems(FXCollections.observableArrayList(paqueteS.findAll()));
        servicios.setItems(FXCollections.observableArrayList(s));
    }

}
