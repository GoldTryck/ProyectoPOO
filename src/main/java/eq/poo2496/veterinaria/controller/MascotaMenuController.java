package eq.poo2496.veterinaria.controller;

import eq.poo2496.veterinaria.util.SpringFXMLLoader;
import javafx.fxml.FXML;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class MascotaMenuController {
    @FXML
    private Pane dataPane;

    @FXML
    public void RegistroMascota() throws IOException {
        SpringFXMLLoader loader = new SpringFXMLLoader();
        loader.loadView("/eq/poo2496/veterinaria/formularioMascota.fxml", dataPane);
    }
    @FXML
    public void Adopcion() throws IOException {
        SpringFXMLLoader loader = new SpringFXMLLoader();
        loader.loadView("/eq/poo2496/veterinaria/formularioAdopcion.fxml", dataPane);
    }
    @FXML
    public void Devolucion() throws IOException {
        SpringFXMLLoader loader = new SpringFXMLLoader();
        loader.loadView("/eq/poo2496/veterinaria/formularioDevolucion.fxml", dataPane);
    }


}
