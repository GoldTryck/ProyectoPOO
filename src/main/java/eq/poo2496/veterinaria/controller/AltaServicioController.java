package eq.poo2496.veterinaria.controller;

import eq.poo2496.veterinaria.entity.ServicioIndividual;
import eq.poo2496.veterinaria.service.Services;
import eq.poo2496.veterinaria.util.Utility;
import javafx.fxml.FXML;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.springframework.stereotype.Component;

@Component
public class AltaServicioController extends Services {
    @FXML
    public Button register;
    @FXML
    public TextField nombreS;
    @FXML
    public TextField precioS;

    private final String validated = "-fx-border-color: green";
    private final String notValidated = "-fx-border-color: red";

    public void initialize(){
        validatePrecio();
        validateNombre();
        register.setOnAction(event -> registerButton());
    }

    private void validatePrecio(){
        precioS.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("^(?!0+(\\.00)?$)\\d+(\\.\\d{2})$")) {
                precioS.setStyle(notValidated);
            }else {
                precioS.setStyle(validated);
            }
            validateFields();
        });
    }
    private void validateNombre(){
        nombreS.textProperty().addListener((observable, oldValue, newValue) -> {
            nombreS.setText(newValue.toUpperCase());
            if (newValue.matches("^[a-zA-Z ]{2,19}$")) {
                nombreS.setStyle("-fx-border-color: green");
            } else {
                nombreS.setStyle("-fx-border-color: red");
            }
            validateFields();
        });
    }

    private void validateFields() {
        register.setDisable(!nombreS.getStyle().equals(validated) || !precioS.getStyle().equals(validated));
    }

    private void registerButton() {
        String nombre = nombreS.getText();
        double precio = Double.parseDouble(precioS.getText());
         ServicioIndividual service = new ServicioIndividual();
         service.setNombre(nombre);
         service.setPrecio(precio);
         servicioIndividualS.saveServico(service);
        Utility.showDialog("EXITO", "Servicio registrado con exito", Alert.AlertType.CONFIRMATION);
    }
}
