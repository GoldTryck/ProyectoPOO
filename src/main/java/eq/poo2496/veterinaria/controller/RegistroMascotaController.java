package eq.poo2496.veterinaria.controller;

import eq.poo2496.veterinaria.entity.Cliente;
import eq.poo2496.veterinaria.entity.Mascota;
import eq.poo2496.veterinaria.enums.*;
import eq.poo2496.veterinaria.service.Services;
import eq.poo2496.veterinaria.util.Utility;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import org.springframework.stereotype.Component;
import java.util.*;



@Component
public class RegistroMascotaController extends Services {

    public TextField campoNombre;
    public TextField campoRaza;
    public TextField idCliente;
    public ListView<CheckBox> vacunasListView;
    public Pane statusRadio;
    public Button buscarC;
    public Button register;
    public Pane busqueda;


    public TableView<Cliente>  tcliente;
    public TableColumn<Cliente, Long> id;
    public TableColumn<Cliente, String> cnombre;
    public TableColumn<Cliente, String> cap;
    public TableColumn<Cliente, String> cam;

    RadioButton sel;
    private ToggleGroup statusGroup = new ToggleGroup();



    public void initialize() {
        Utility.cbList(vacunasListView, Vacuna.class);
        tcliente.setVisible(false);
        busqueda.setVisible(false);
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        cnombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        cap.setCellValueFactory(new PropertyValueFactory<>("apellidoPaterno"));
        cam.setCellValueFactory(new PropertyValueFactory<>("apellidoMaterno"));
        setRadiogroup();
        register.setOnAction(event -> registerButton());
        buscarC.setOnAction(event -> buscaCliente());
        idCliente.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                idCliente.setText(oldValue);
            }
        });
    }

    @FXML
    public void registerButton(){
        String nombre = campoNombre.getText();
        String raza = campoRaza.getText();
        List<String> vacunas = Utility.selectedCB(vacunasListView);

        if (nombre.isEmpty()
                || raza.isEmpty()
                || statusGroup.getSelectedToggle() == null
                || ((sel.getText().equals("CLIENTE") || sel.getText().equals("ADOPTADO"))
                && tcliente.getSelectionModel().getSelectedItem() == null)) {
            Utility.showDialog("Error",
                    "Por favor ingrese todos los datos de la mascota",
                    Alert.AlertType.WARNING);
            return;
        }

        String status = ((RadioButton) statusGroup.getSelectedToggle()).getText();
        Mascota saved = mascotaS.registrarMascota(nombre, raza, vacunas, status);
        if(sel.getText().equals("CLIENTE") || sel.getText().equals("ADOPTADO")){
            Cliente cliente = tcliente.getSelectionModel().getSelectedItem();
            cliente.setMascota(saved);
            clienteS.updateCliente(cliente);
        }
        String numMascota = "Mascota registrada exitosamente con numero de mascota: " + saved.getNumeroMascota().toString();
        Utility.showDialog("Registro Exitoso", numMascota, Alert.AlertType.INFORMATION);
        limpiarCampos();
    }

    private void setRadiogroup(){
        statusGroup = Utility.loadRadioButtons(statusRadio, StatusMascota.class);
        statusGroup.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            sel = (RadioButton) newValue;
            if(sel.getText().equals("CLIENTE") || sel.getText().equals("ADOPTADO")) {
                busqueda.setVisible(true);
                tcliente.setVisible(true);
            } else {
                busqueda.setVisible(false);
                tcliente.setVisible(false);
            }
        });
    }
    private void buscaCliente(){
        Long id = Long.valueOf(idCliente.getText());
        Cliente cliente = clienteS.byIdAndMascota(id);
        if(cliente == null){
            Utility.showDialog("Error",
                    "El cliente ya tiene una mascota o no esta registrado",
                    Alert.AlertType.WARNING);
            return;
        }
        tcliente.getItems().setAll(cliente);
        System.out.println(cliente.getApellidoMaterno());
    }
    private void limpiarCampos(){
        campoNombre.clear();
        campoRaza.clear();
        idCliente.clear();
        vacunasListView.getSelectionModel().clearSelection();
        tcliente.getItems().clear();
    }
    private void clienteOAdoptado(){

    }
}
