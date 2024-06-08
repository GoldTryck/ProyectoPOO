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
public class AdopcionController extends Services {
    @FXML
    private TableView<Mascota> mtable;
    @FXML
    private TableView<Cliente> ctable;
    @FXML
    private TableColumn<Cliente, Long> numeroCliente;
    @FXML
    private TableColumn<Cliente, String> nombre;
    @FXML
    private TableColumn<Cliente, String> apellidoPaterno;
    @FXML
    private TableColumn<Cliente, String> apellidoMaterno;
    @FXML
    private TableColumn<Mascota, Long> numMascota;
    @FXML
    private TableColumn<Mascota, String> mNombre;
    @FXML
    private TableColumn<Mascota, String> mRaza;
    @FXML
    private TableColumn<Mascota, String> mStatus;
    @FXML
    private TextField numeroB;
    @FXML
    private Button buscar;
    public Button adoptar;


    public void initialize() {
        numMascota.setCellValueFactory(new PropertyValueFactory<>("numeroMascota"));
        mNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        mRaza.setCellValueFactory(new PropertyValueFactory<>("raza"));
        mStatus.setCellValueFactory(new PropertyValueFactory<>("status"));

        numeroCliente.setCellValueFactory(new PropertyValueFactory<>("id"));
        nombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        apellidoPaterno.setCellValueFactory(new PropertyValueFactory<>("apellidoPaterno"));
        apellidoMaterno.setCellValueFactory(new PropertyValueFactory<>("apellidoMaterno"));
        loadMascotas();
        loadCliente();
        adoptar.setOnAction(event -> adptButton());
    }

    private void loadMascotas() {
        List<Mascota> mascotas = mascotaS.getByStatus("ADOPCION");
        List<Mascota> mdev = mascotaS.getByStatus("DEVOLUCION");
        mascotas.addAll(mdev);
        ObservableList<Mascota> mascotaObservableList = FXCollections.observableArrayList(mascotas);
        mtable.setItems(mascotaObservableList);
    }
    private void loadCliente() {
        List<Cliente> clientes = clienteS.getByMascota();
        ObservableList<Cliente> clienteObservableList = FXCollections.observableArrayList(clientes);
        ctable.setItems(clienteObservableList);
    }
    public void adptButton() {
        Mascota selected = mtable.getSelectionModel().getSelectedItem();
        Cliente clienteSelected = ctable.getSelectionModel().getSelectedItem();
        if (selected != null && clienteSelected != null) {
            selected.setStatus("ADOPTADO");
            mascotaS.updateStatus(selected);
            clienteSelected.setMascota(selected);
            clienteS.updateCliente(clienteSelected);
            Utility.showDialog("Adopcion", "Mascota adoptada", javafx.scene.control.Alert.AlertType.INFORMATION);
            loadMascotas();
            loadCliente();
            return;
        }
        Utility.showDialog("Adopcion", "Seleccione una mascota y un cliente", javafx.scene.control.Alert.AlertType.WARNING);
        loadCliente();
    }
}
