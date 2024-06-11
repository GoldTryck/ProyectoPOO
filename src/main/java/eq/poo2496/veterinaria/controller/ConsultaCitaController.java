package eq.poo2496.veterinaria.controller;

import eq.poo2496.veterinaria.entity.Asistente;
import eq.poo2496.veterinaria.entity.Cita;
import eq.poo2496.veterinaria.entity.Cliente;
import eq.poo2496.veterinaria.entity.Veterinario;
import eq.poo2496.veterinaria.service.Services;
import javafx.collections.FXCollections;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ConsultaCitaController extends Services {

    public TableView<Cita> citas;
    public TableColumn<Cita, Long> id;
    public TableColumn<Cita, Cliente> idCliente;
    public TableColumn<Cita, Veterinario> idVeterinario;
    public TableColumn<Cita, Asistente> idAsistente;
    public TableColumn<Cita, LocalDateTime> fechaHora;
    public TableColumn<Cita, Double> precio;
    public TableColumn<Cita, String> descripcion;

    public void initialize() {
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        idCliente.setCellValueFactory(new PropertyValueFactory<>("numeroCliente"));
        idVeterinario.setCellValueFactory(new PropertyValueFactory<>("numeroVeterinario"));
        idAsistente.setCellValueFactory(new PropertyValueFactory<>("numeroAsistente"));
        fechaHora.setCellValueFactory(new PropertyValueFactory<>("fechaHora"));
        precio.setCellValueFactory(new PropertyValueFactory<>("precioTotal"));
        descripcion.setCellValueFactory(new PropertyValueFactory<>("descripcionServicio"));

        citas.setItems(FXCollections.observableList(citaS.getAll()));
    }
}
