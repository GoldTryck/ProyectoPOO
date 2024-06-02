package eq.poo2496.veterinaria.controller;

import eq.poo2496.veterinaria.Service.MascotaService;
import eq.poo2496.veterinaria.entity.Mascota;
import eq.poo2496.veterinaria.enums.*;
import eq.poo2496.veterinaria.util.Utility;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;



@Component
public class RegistroMascotaController {

    @Autowired
    private MascotaService mascotaS;
    @FXML
    private TextField nombreField;
    @FXML
    private TextField razaField;
    @FXML
    private ListView<CheckBox> vacunasListView;
    @FXML
    private VBox statusRadio;
    private ToggleGroup statusGroup = new ToggleGroup();

    public void initialize() {
        Utility.cbList(vacunasListView, Vacuna.class);
        statusGroup = Utility.loadRadioButtons(statusRadio, StatusMascota.class);
    }

    @FXML
    public void registerButton(){
        String nombre = nombreField.getText();
        String raza = razaField.getText();
        List<String> vacunas = Utility.getSelectedCB(vacunasListView);

        if (nombre.isEmpty() || raza.isEmpty() || statusGroup.getSelectedToggle() == null) {
            Utility.showDialog("Error", "Por favor ingrese nombre, raza y status de la mascota", Alert.AlertType.WARNING);
            return;
        }
        String status = ((RadioButton) statusGroup.getSelectedToggle()).getText();
        Mascota saved = mascotaS.registrarMascota(nombre, raza, vacunas, status);
        String numMascota = "Mascota registrada exitosamente con numero de mascota: " + saved.getNumeroMascota().toString();
        Utility.showDialog("Registro Exitoso", numMascota, Alert.AlertType.INFORMATION);
    }

}
