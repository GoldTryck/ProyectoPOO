package eq.poo2496.veterinaria.controller;

import eq.poo2496.veterinaria.entity.Asistente;
import eq.poo2496.veterinaria.entity.Gerente;
import eq.poo2496.veterinaria.entity.Veterinario;
import eq.poo2496.veterinaria.enums.Empleado;
import eq.poo2496.veterinaria.enums.Sucursal;
import eq.poo2496.veterinaria.util.Utility;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import org.springframework.stereotype.Component;

@Component
public class PersonalController extends personaController{

    public TableView<Sucursal> tsucursal;
    public TableColumn<Sucursal, String> snombre;
    public TableColumn<Sucursal, String> szona;
    public Label vcedula;

    private  RadioButton sel;

    public void initialize() {

            setRadiogroup();
            fillSucursalT();
            isValidCURP();
            isValidText(nombre);
            isValidText(ap);
            isValidText(am);
            isValidFn();
            isValidCedula();
            tableListener(tsucursal);
            registerButton();

    }

    @Override
    void validateFields() {
        if(
                nombre.getStyle().equals(validated)
                && ap.getStyle().equals(validated)
                && am.getStyle().equals(validated)
                && curp.getStyle().equals(validated)
                && fn.getStyle().equals(validated)
                && (!cedula.isVisible() || cedula.getStyle().equals(validated))
                && (!tsucursal.isVisible() || !tsucursal.getSelectionModel().getSelectedItems().isEmpty())
        ){

            register.setDisable(false);
        }else register.setDisable(true);
    }

    @Override
    void registerButton() {
        register.setOnAction(event -> {
            nombreS = nombre.getText();
            apS = ap.getText();
            amS = am.getText();
            curpS = curp.getText();
            fnD = getFn();

            if (sel.getText().equals("VETERINARIO")) {
                cedulaS = cedula.getText();
                Veterinario saved = saveVeterinario();
                Utility.showDialog("EXITO", "Veterinario registrado con id: " + saved.getId(), Alert.AlertType.INFORMATION);

            } else if (sel.getText().equals("GERENTE")) {
                sucursal = tsucursal.getSelectionModel().getSelectedItem();
                Gerente saved = saveGerente();
                Utility.showDialog("EXITO", "Gerente registrado con id: " + saved.getId(), Alert.AlertType.INFORMATION);
            }else{
                Asistente saved = saveAsistente();
                Utility.showDialog("EXITO", "Asistente registrado con id: " + saved.getId(), Alert.AlertType.INFORMATION);
            }

        });

    }
    private void setRadiogroup(){
        ToggleGroup statusGroup = Utility.loadRadioButtons(radiogroup, Empleado.class);
        statusGroup.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            sel = (RadioButton) newValue;
            if(sel.getText().equals("VETERINARIO")) {
                cedula.setVisible(true);
                vcedula.setVisible(true);
                tsucursal.setVisible(false);
            }else if (sel.getText().equals("GERENTE")){
                cedula.setVisible(false);
                vcedula.setVisible(false);
                tsucursal.setVisible(true);
            }else{
                cedula.setVisible(false);
                vcedula.setVisible(false);
                tsucursal.setVisible(false);
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
