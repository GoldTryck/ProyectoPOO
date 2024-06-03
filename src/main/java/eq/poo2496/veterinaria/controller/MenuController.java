package eq.poo2496.veterinaria.controller;

import eq.poo2496.veterinaria.util.SpringFXMLLoader;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public abstract class MenuController {

    @FXML
    protected Pane headerPane;
    @FXML
    protected Pane menuPane;
    @FXML
    protected Pane dataPane;


    @FXML
    public void initialize() {
        // Puedes poner aquí cualquier inicialización común a todos los controladores.
    }
}
