package eq.poo2496.veterinaria.controller;

import eq.poo2496.veterinaria.entity.Paquete;
import eq.poo2496.veterinaria.service.Services;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.stereotype.Component;

@Component
public class ConsultaPaqueteController extends Services {
    @FXML
    public TableView<Paquete> paquete;
    @FXML
    public TableColumn<Paquete, Long> idPaquete;
    @FXML
    public TableColumn<Paquete, String> nombrePaquete;
    @FXML
    public TableColumn<Paquete, Double> precioPaquete;

    public void initialize(){
        idPaquete.setCellValueFactory(new PropertyValueFactory<>("id"));
        nombrePaquete.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        precioPaquete.setCellValueFactory(param ->
                new SimpleDoubleProperty(param.getValue().getPrecio()).asObject()
        );

        paquete.setItems(FXCollections.observableArrayList(paqueteS.findAll()));
    }
}
