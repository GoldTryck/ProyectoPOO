//Autor: Medrano Reyes Julio Cesar
// Descripcion: Se define un controlador de JavaFX para la vista principal de una aplicación de veterinaria y define un controlador de JavaFX para la vista principal de una aplicación de veterinaria
package eq.poo2496.veterinaria.controller;

import eq.poo2496.veterinaria.util.SpringFXMLLoader;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import org.springframework.stereotype.Component;


@Component // Spring Component
public class MainController {

    @FXML
    public Pane headerPane;
    @FXML
    public Pane dataPane;
    @FXML
    private Pane sidePane;

    public void initialize() {
        try {
            SpringFXMLLoader.loadView("/eq/poo2496/veterinaria/_sideBar.fxml", sidePane);
            SpringFXMLLoader.loadView("/eq/poo2496/veterinaria/_header.fxml", headerPane);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void cargaMenu(String fxml) {
        try {
            dataPane.getChildren().clear();
            SpringFXMLLoader.loadView(fxml, headerPane);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void cargaData(String fxml) {
        try {
            SpringFXMLLoader.loadView(fxml, dataPane);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
