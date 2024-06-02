package eq.poo2496.veterinaria.controller;

import eq.poo2496.veterinaria.util.SpringFXMLLoader;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.HBox;

import javafx.scene.layout.VBox;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.util.Objects;

import static eq.poo2496.veterinaria.Main.context;

@Component
public class MainController {

    @FXML
    public VBox primaryPane;
    @FXML
    public HBox dataPane;

    @FXML
    private void MascotaView() throws IOException {
        SpringFXMLLoader v = new SpringFXMLLoader();
        v.loadView("/eq/poo2496/veterinaria/mascotaView.fxml", primaryPane);
    }


    @FXML
    public void endApp() {
        System.exit(0);
    }
}
