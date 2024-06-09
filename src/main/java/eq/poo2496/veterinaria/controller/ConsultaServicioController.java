package eq.poo2496.veterinaria.controller;

import eq.poo2496.veterinaria.entity.Paquete;
import eq.poo2496.veterinaria.entity.Servicio;
import eq.poo2496.veterinaria.entity.ServicioIndividual;
import eq.poo2496.veterinaria.service.Services;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.stereotype.Component;

@Component
public class ConsultaServicioController extends Services {
    @FXML
    public TableView<ServicioIndividual> servicio;
    @FXML
    public TableColumn<ServicioIndividual, Long> idServicio;
    @FXML
    public TableColumn<ServicioIndividual, String> nombreServicio;
    @FXML
    public TableColumn<ServicioIndividual, Double> precioServicio;

    public void initialize(){
        idServicio.setCellValueFactory(new PropertyValueFactory<>("id"));
        nombreServicio.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        precioServicio.setCellValueFactory(new PropertyValueFactory<>("precio"));

        servicio.setItems(FXCollections.observableArrayList(servicioIndividualS.findAll()));
    }
}
