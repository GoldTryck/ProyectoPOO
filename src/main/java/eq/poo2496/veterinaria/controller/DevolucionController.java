package eq.poo2496.veterinaria.controller;

import eq.poo2496.veterinaria.entity.Cliente;
import eq.poo2496.veterinaria.entity.Mascota;
import eq.poo2496.veterinaria.service.Services;
import eq.poo2496.veterinaria.util.Utility;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DevolucionController extends Services {

    @FXML
    private TableView<Mascota> mtable;
    @FXML
    private TableColumn<Mascota, Long> numMascota;
    @FXML
    private TableColumn<Mascota, String> mNombre;
    @FXML
    private TableColumn<Mascota, String> mRaza;
    @FXML
    private TableColumn<Mascota, String> mStatus;
    public Button devolver;

    public void initialize() {
        numMascota.setCellValueFactory(new PropertyValueFactory<>("numeroMascota"));
        mNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        mRaza.setCellValueFactory(new PropertyValueFactory<>("raza"));
        mStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        loadData();
        devolver.setOnAction(event -> devButton());

    }

    protected void loadData() {
        List<Mascota> mascotas = mascotaS.getByStatus("ADOPTADO");
        ObservableList<Mascota> mascotaObservableList = FXCollections.observableArrayList(mascotas);
        mtable.setItems(mascotaObservableList);
    }

    public void devButton() {
        Mascota selected = mtable.getSelectionModel().getSelectedItem();
        Cliente cliente = clienteS.clienteByMascota(selected);
        if (selected != null) {
            selected.setStatus("DEVOLUCION");
            mascotaS.updateStatus(selected);
            clienteS.removeMascota(cliente);
            Utility.showDialog("Devolucion", "Mascota devuelta exitosamente :c", Alert.AlertType.INFORMATION);
            loadData();
            return;
        }
        Utility.showDialog("Devolucion", "Seleccione una mascota para devolver...", Alert.AlertType.WARNING);
    }
}
