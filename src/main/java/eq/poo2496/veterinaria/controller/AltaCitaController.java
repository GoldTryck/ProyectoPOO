package eq.poo2496.veterinaria.controller;

import eq.poo2496.veterinaria.entity.Cliente;
import eq.poo2496.veterinaria.enums.TipoCita;
import eq.poo2496.veterinaria.util.Utility;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import org.springframework.stereotype.Component;


@Component
public class AltaCitaController extends personaController {

    @FXML
    public Pane citaRadioButtons;
    @FXML
    public DatePicker fc;
    @FXML
    public Spinner<Integer> hc;
    @FXML
    public Button register;
    @FXML
    public Button dispButton;

    public void initialize() {
        Utility.loadRadioButtons(citaRadioButtons, TipoCita.class);
        mapTable();
        fillTable();
    }


    @Override
    void validateFields() {

    }

    @Override
    void registerButton() {

    }
    private void fillTable(){
        persona.getItems().clear();
        persona.setItems(FXCollections.observableArrayList(clienteS.getAll()));

    }
}
