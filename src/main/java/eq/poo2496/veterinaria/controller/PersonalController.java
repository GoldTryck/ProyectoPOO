package eq.poo2496.veterinaria.controller;

import eq.poo2496.veterinaria.entity.*;
import eq.poo2496.veterinaria.enums.*;
import eq.poo2496.veterinaria.util.Utility;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import org.springframework.stereotype.Component;

@Component
public class PersonalController extends personaController {

    public TableView<Sucursal> tsucursal;
    public TableColumn<Sucursal, String> snombre;
    public TableColumn<Sucursal, String> szona;
    public Label vcedula;

    private RadioButton sel;
    private ToggleGroup radioGroup = new ToggleGroup();

    public void initialize() {
        setRadiogroup();
        fillSucursalT();
        isValidCURP();
        isValidText(nmbr);
        isValidText(ap);
        isValidText(am);
        isValidFn();
        isValidCedula();
        tableListener(tsucursal);
        register.setOnAction(event -> registerButton());
    }

    @Override
    void validateFields() {
        boolean isValid =
                nmbr.getStyle().equals(validated) &&
                        ap.getStyle().equals(validated) &&
                        am.getStyle().equals(validated) &&
                        curp.getStyle().equals(validated) &&
                        fn.getStyle().equals(validated) &&
                        (!cedula.isVisible() || cedula.getStyle().equals(validated)) &&
                        (!tsucursal.isVisible() || !tsucursal.getSelectionModel().getSelectedItems().isEmpty()) &&
                        radioGroup.getSelectedToggle() != null;

        register.setDisable(!isValid);
    }

    @Override
    void registerButton() {
        nombreS = nmbr.getText();
        apS = ap.getText();
        amS = am.getText();
        curpS = curp.getText();
        fnD = getFn();

        sel = (RadioButton) radioGroup.getSelectedToggle();

        if (sel != null) {
            switch (sel.getText()) {
                case "VETERINARIO":
                    cedulaS = cedula.getText();
                    Veterinario savedVet = saveVeterinario();
                    Utility.showDialog("EXITO",
                            "Veterinario registrado con id: " + savedVet.getId(),
                            Alert.AlertType.INFORMATION);
                    break;
                case "GERENTE":
                    sucursal = tsucursal.getSelectionModel().getSelectedItem();
                    Gerente savedGerente = saveGerente();
                    Utility.showDialog("EXITO",
                            "Gerente registrado con id: " + savedGerente.getId(),
                            Alert.AlertType.INFORMATION);
                    break;
                default:
                    Asistente savedAsistente = saveAsistente();
                    Utility.showDialog("EXITO",
                            "Asistente registrado con id: " + savedAsistente.getId(),
                            Alert.AlertType.INFORMATION);
                    break;
            }
        } else {
            // Handle the case where no radio button is selected, if necessary
        }
    }

    private void setRadiogroup() {
        radioGroup = Utility.loadRadioButtons(radioPane, TipoPersona.class);
        radioPane.getChildren().removeLast();

        radioGroup.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            sel = (RadioButton) newValue;
            if (sel != null) {
                switch (sel.getText()) {
                    case "VETERINARIO":
                        cedula.setVisible(true);
                        vcedula.setVisible(true);
                        tsucursal.setVisible(false);
                        break;
                    case "GERENTE":
                        cedula.setVisible(false);
                        vcedula.setVisible(false);
                        tsucursal.setVisible(true);
                        break;
                    default:
                        cedula.setVisible(false);
                        vcedula.setVisible(false);
                        tsucursal.setVisible(false);
                        break;
                }
            }
            validateFields();
        });
    }

    private void fillSucursalT() {
        snombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        szona.setCellValueFactory(new PropertyValueFactory<>("zona"));
        ObservableList<Sucursal> sucursals = FXCollections.observableArrayList(Sucursal.values());
        tsucursal.setItems(sucursals);
    }
}