package eq.poo2496.veterinaria.controller;

import eq.poo2496.veterinaria.entity.Cliente;
import eq.poo2496.veterinaria.entity.Mascota;
import eq.poo2496.veterinaria.enums.StatusMascota;
import eq.poo2496.veterinaria.util.Utility;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import org.springframework.stereotype.Component;


@Component
public class clienteController extends personaController{

    @FXML
    private TableView<Mascota> tmascota;
    @FXML
    private TableColumn<Mascota, String> mnombre;
    @FXML
    private TableColumn<Mascota, String> mraza;
    @FXML
    private TableColumn<Mascota, String> mstatus;
    @FXML
    public void initialize() {
        mnombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        mraza.setCellValueFactory(new PropertyValueFactory<>("raza"));
        mstatus.setCellValueFactory(new PropertyValueFactory<>("status"));

        isValidCURP();
        isValidText(nmbr);
        isValidText(ap);
        isValidText(am);
        dateListener();

        tmascota.getSelectionModel().
                selectedItemProperty().
                addListener((observable, oldValue, newValue) -> {
                    if (observable != null) {
                        validateFields();
                    }
                });
        cmascota.addEventFilter(KeyEvent.KEY_TYPED, event -> {
            String c = event.getCharacter();
            if (!c.matches("[0-9]")) {
                event.consume();
            }
        });

        register.setOnAction(event -> registerButton());

    }


    public void validateFields(){
        if(nmbr.getStyle().equals(validated)
                && ap.getStyle().equals(validated)
                && am.getStyle().equals(validated)
                && curp.getStyle().equals(validated)
                && fecha.getStyle().equals(validated)
        ){

            register.setDisable(false);
        }else register.setDisable(true);
    }



    @FXML
    public void registerButton() {
        nombreS = nmbr.getText();
        apS = ap.getText();
        amS = am.getText();
        curpS = curp.getText();
        fnD = getFecha();
        mascotaM = tmascota.getSelectionModel().getSelectedItem();

        if(mascotaM != null){
            if (mascotaM.getStatus().equals("ADOPCION") || mascotaM.getStatus().equals("DEVOLUCION")) {
                mascotaM.setStatus("ADOPTADO");
                mascotaS.updateMascota(mascotaM);
            }
            mascotaM.setStatus(StatusMascota.ADOPTADO.toString());
            mascotaS.updateMascota(mascotaM);
        }
        try{
            Cliente saved = saveCliente();
            Utility.showDialog("Registro Exitoso", "Cliente registrado con exito con id: " + saved.getId(), Alert.AlertType.INFORMATION);
            clearFields();
        }catch (Exception e) {
            Utility.showDialog("Error al registrar", e.getMessage(), Alert.AlertType.ERROR);
        }
    }
    @FXML
    private void findMascota() {

        mascotaM = mascotaS.getById(Long.parseLong(cmascota.getText()));
        if(mascotaM != null){
            Cliente cliente = clienteS.clienteByMascota(mascotaM);
            if (cliente == null && !mascotaM.getStatus().equals("ADOPTADO")) {
                ObservableList<Mascota> mascotaMObservableList = FXCollections.observableArrayList();
                mascotaMObservableList.add(mascotaM);
                tmascota.setItems(mascotaMObservableList);
                return;
            }
        }
        Utility.showDialog("Mascota no encontrada","La mascota no esta disponible", Alert.AlertType.ERROR);
    }
    @Override
    public void clearFields(){
        nmbr.clear();
        ap.clear();
        am.clear();
        curp.clear();
        fecha.setValue(null);
        cmascota.clear();
        tmascota.getItems().clear();
    }

}