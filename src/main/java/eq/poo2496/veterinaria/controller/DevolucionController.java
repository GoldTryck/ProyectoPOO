package eq.poo2496.veterinaria.controller;


import eq.poo2496.veterinaria.Service.MascotaService;
import eq.poo2496.veterinaria.entity.Mascota;
import eq.poo2496.veterinaria.util.Utility;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DevolucionController {

    @Autowired
    MascotaService mascotaS;
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

    public void initialize() {
        numMascota.setCellValueFactory(new PropertyValueFactory<>("numeroMascota"));
        mNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        mRaza.setCellValueFactory(new PropertyValueFactory<>("raza"));
        mStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        loadMascotas();
    }

    private void loadMascotas() {
        List<Mascota> mascotas = mascotaS.getByStatus("ADOPTADO");
        ObservableList<Mascota> mascotaObservableList = FXCollections.observableArrayList(mascotas);
        mtable.setItems(mascotaObservableList);
    }

    public void devButton() {
        Mascota selected = mtable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            selected.setStatus("DEVOLUCION");
            mascotaS.updateStatus(selected);
            loadMascotas();
            return;
        }
        Utility.showDialog("Devolucion", "Seleccione una mascota para devolver...", javafx.scene.control.Alert.AlertType.WARNING);
    }
}
