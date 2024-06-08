package eq.poo2496.veterinaria.controller;

import eq.poo2496.veterinaria.entity.*;
import eq.poo2496.veterinaria.enums.TipoPersona;
import eq.poo2496.veterinaria.util.Utility;
import javafx.collections.FXCollections;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Pane;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ConsultaPersonalController extends personaController{

    public Pane radioButtons;
    private RadioButton sel;

    public void initialize() {

        mapTable();
        setRadiogroup();
        fillAll();
    }

    private void fillAll(){
        combinedList.clear();

        List<Asistente> asistentes = asistenteS.getAll();
        List<Veterinario> veterinarios = veterinarioS.getAll();
        List<Gerente> gerentes = gerenteS.getAll();

        combinedList.addAll(asistentes);
        combinedList.addAll(veterinarios);
        combinedList.addAll(gerentes);

        persona.setItems(FXCollections.observableArrayList(combinedList));
    }
    private void fillByCategory(){
        combinedList.clear();

        if (sel.getText().equals("ASISTENTE")){
            List<Asistente> asistentes = asistenteS.getAll();
            persona.setItems(FXCollections.observableArrayList(asistentes));
        } else if (sel.getText().equals("VETERINARIO")){
            List<Veterinario> veterinarios = veterinarioS.getAll();
            persona.setItems(FXCollections.observableArrayList(veterinarios));
        } else if (sel.getText().equals("GERENTE")){
            List<Gerente> gerentes = gerenteS.getAll();
            persona.setItems(FXCollections.observableArrayList(gerentes));
        }
    }
    @Override
    void validateFields() {

    }

    @Override
    void registerButton() {

    }
    private void setRadiogroup(){
        ToggleGroup radioGroup
                = Utility.loadRadioButtons(radioButtons, TipoPersona.class);
        radioButtons.getChildren().removeFirst();
        radioGroup.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            sel = (RadioButton) newValue;
            fillByCategory();
        });

    }
}
