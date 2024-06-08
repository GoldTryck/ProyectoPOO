package eq.poo2496.veterinaria.controller;

import eq.poo2496.veterinaria.entity.*;
import eq.poo2496.veterinaria.util.Utility;
import javafx.collections.FXCollections;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.function.Supplier;

@Component
public class bajaPersonalController extends personaController {
    @FXML
    public Button byNombre;
    @FXML
    public Button byAp;
    @FXML
    public Button byAm;
    @FXML
    public Button byCurp;
    @FXML
    public TableView<Persona> tpersonal;
    @FXML
    public TableColumn<Persona, Long> id;
    @FXML
    public TableColumn<Persona, String> pnombre;
    @FXML
    public TableColumn<Persona, String> pap;
    @FXML
    public TableColumn<Persona, String> pam;

    private final List<Persona> combinedList = new ArrayList<>();

    public void initialize() {
        mapTable();
        isValidText(nombre);
        isValidText(ap);
        isValidText(am);
        isValidCURP();
        tableListener(tpersonal);
        searchLogic();
        register.setOnAction(event -> registerButton());
    }

    private void handleSearch(Supplier<List<Asistente>> asistenteSearch,
                              Supplier<List<Veterinario>> veterinarioSearch,
                              Supplier<List<Gerente>> gerenteSearch) {
        combinedList.clear();

        List<Asistente> asistentes = asistenteSearch.get();
        List<Veterinario> veterinarios = veterinarioSearch.get();
        List<Gerente> gerentes = gerenteSearch.get();

        combinedList.addAll(asistentes);
        combinedList.addAll(veterinarios);
        combinedList.addAll(gerentes);

        tpersonal.setItems(FXCollections.observableArrayList(combinedList));

        clearFields();
    }

    private void mapTable() {
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        pnombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        pap.setCellValueFactory(new PropertyValueFactory<>("apellidoPaterno"));
        pam.setCellValueFactory(new PropertyValueFactory<>("apellidoMaterno"));
    }

    @Override
    void validateFields() {
        byNombre.setDisable(!nombre.getStyle().equals(validated));
        byAp.setDisable(!ap.getStyle().equals(validated));
        byAm.setDisable(!am.getStyle().equals(validated));
        byCurp.setDisable(!curp.getStyle().equals(validated));
        register.setDisable(tpersonal.getSelectionModel().getSelectedItem() == null);
    }

    @Override
    void registerButton() {
        Persona selected = tpersonal.getSelectionModel().getSelectedItem();
        if (selected instanceof Asistente) {
            asistenteS.deleteAsistente((Asistente) selected);
            Utility.showDialog("EXITO",
                    "Asistente eliminado con id: " + selected.getId(),
                    Alert.AlertType.INFORMATION);
        } else if (selected instanceof Veterinario) {
            veterinarioS.deleteVeterinario((Veterinario) selected);
            Utility.showDialog("EXITO",
                    "Veterinario eliminado con id: " + selected.getId(),
                    Alert.AlertType.INFORMATION);
        } else if (selected instanceof Gerente) {
            gerenteS.deleteGerente((Gerente) selected);
            Utility.showDialog("EXITO",
                    "Gerente eliminado con id: " + selected.getId(),
                    Alert.AlertType.INFORMATION);
        }
        tpersonal.getItems().remove(selected);
    }

    private void clearFields() {
        nombre.clear(); nombre.setStyle(null);
        ap.clear(); ap.setStyle(null);
        am.clear(); am.setStyle(null);
        curp.clear(); curp.setStyle(null);
    }
    private void searchLogic(){
        byNombre.setOnAction(event -> handleSearch(() ->
                asistenteS.filterByNombre(nombre.getText()), () ->
                veterinarioS.filterByNombre(nombre.getText()), () ->
                gerenteS.filterByNombre(nombre.getText())));

        byAp.setOnAction(event -> handleSearch(() ->
                asistenteS.filterByAp(ap.getText()), () ->
                veterinarioS.filterByAp(ap.getText()), () ->
                gerenteS.filterByAp(ap.getText())));

        byAm.setOnAction(event -> handleSearch(() ->
                asistenteS.filterByAm(am.getText()), () ->
                veterinarioS.filterByAm(am.getText()), () ->
                gerenteS.filterByAm(am.getText())));

        byCurp.setOnAction(event -> handleSearch(() ->
                asistenteS.filterByCurp(curp.getText()), () ->
                veterinarioS.filterByCurp(curp.getText()), () ->
                gerenteS.filterByCurp(curp.getText())));
    }
}
