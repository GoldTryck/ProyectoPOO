package eq.poo2496.veterinaria.controller;

import eq.poo2496.veterinaria.entity.Servicio;
import eq.poo2496.veterinaria.entity.ServicioIndividual;
import eq.poo2496.veterinaria.service.Services;
import eq.poo2496.veterinaria.util.Utility;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class AltaPaqueteController extends Services {

    @FXML
    public TableView<Servicio> paquete;
    @FXML
    public TableColumn<Servicio, Long> idPaquete;
    @FXML
    public TableColumn<Servicio, String> nombrePaquete;
    @FXML
    public TableColumn<Servicio, Double> precioPaquete;

    @FXML
    public TextField nombre;
    public Button register;

    protected static final String validated = "-fx-border-color: green";
    protected static final String notValidated = "-fx-border-color: red";


    public void initialize(){
        idPaquete.setCellValueFactory(new PropertyValueFactory<>("id"));
        nombrePaquete.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        precioPaquete.setCellValueFactory(new PropertyValueFactory<>("precio"));
        paquete.setItems(FXCollections.observableArrayList(servicioIndividualS.findAll()));

        paquete.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        validateNombre();
        tableListener();
        register.setDisable(true);
        register.setOnAction(event -> registerButton());
    }

    private void validateNombre(){
        nombre.textProperty().addListener((observable, oldValue, newValue) -> {
            nombre.setText(newValue.toUpperCase());
            if (newValue.matches("^[a-zA-Z ]{2,39}$")) {
                nombre.setStyle(validated);
            } else {
                nombre.setStyle(notValidated);
            }
            validateFields();
        });
    }

    private void tableListener(){
        paquete.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue != null){
                validateFields();
            }
        });
    }
    private void validateFields() {
        register.setDisable(!(nombre.getStyle().equals(validated) && paquete.getSelectionModel().getSelectedItem() != null));

    }

    private void registerButton(){
        String pnombre = nombre.getText();
        List<Servicio> serviciosSeleccionados = paquete.getSelectionModel().getSelectedItems();
        List<ServicioIndividual> serviciosIndividuales = convertirAServiciosIndividuales(serviciosSeleccionados);
        paqueteS.save(pnombre, serviciosIndividuales);
        Utility.showDialog("Paquete registrado", "El paquete se ha registrado correctamente", Alert.AlertType.INFORMATION);
    }

    private List<ServicioIndividual> convertirAServiciosIndividuales(List<Servicio> serviciosSeleccionados) {
        List<ServicioIndividual> serviciosIndividuales = new ArrayList<>();
        for (Servicio servicio : serviciosSeleccionados) {

            if (servicio instanceof ServicioIndividual) {
                serviciosIndividuales.add((ServicioIndividual) servicio);
            }
        }
        return serviciosIndividuales;
    }
}
