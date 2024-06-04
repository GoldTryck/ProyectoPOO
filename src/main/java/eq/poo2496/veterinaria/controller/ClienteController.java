package eq.poo2496.veterinaria.controller;

import eq.poo2496.veterinaria.entity.Cliente;
import eq.poo2496.veterinaria.entity.Mascota;
import eq.poo2496.veterinaria.service.ClienteService;
import eq.poo2496.veterinaria.service.MascotaService;
import eq.poo2496.veterinaria.util.Utility;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class ClienteController {

    @Autowired
    private ClienteService clienteS;
    @Autowired
    private MascotaService mascotaS;
    @FXML
    private TextField cnombre;
    @FXML
    private TextField cap;
    @FXML
    private TextField cam;
    @FXML
    private TextField ccurp;
    @FXML
    private DatePicker cfn;
    @FXML
    private TextField cmascota;
    @FXML
    private TableView<Mascota> tmascota;
    @FXML
    private TableColumn<Mascota, String> mnombre;
    @FXML
    private TableColumn<Mascota, String> mraza;
    @FXML
    private TableColumn<Mascota, String> mstatus;

    private static Mascota mascota;
    @FXML
    private void findMascota() {

        mascota = mascotaS.getById(Long.parseLong(cmascota.getText()));
        if(mascota != null){
            Cliente cliente = clienteS.clienteByMascota(mascota);
            if (cliente == null && !mascota.getStatus().equals("ADOPTADO")) {
                ObservableList<Mascota> mascotaObservableList = FXCollections.observableArrayList();
                mascotaObservableList.add(mascota);
                tmascota.setItems(mascotaObservableList);
                return;
            }
        }
        Utility.showDialog("Mascota no encontrada","La mascota no esta disponible", Alert.AlertType.ERROR);
    }

    @FXML

    public void initialize() {
        mnombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        mraza.setCellValueFactory(new PropertyValueFactory<>("raza"));
        mstatus.setCellValueFactory(new PropertyValueFactory<>("status"));

        cmascota.addEventFilter(KeyEvent.KEY_TYPED, event -> {
            String c = event.getCharacter();
            if (!c.matches("[0-9]")) {
                event.consume();
            }
        });
    }


}
