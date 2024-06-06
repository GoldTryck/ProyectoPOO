//Autor: Medrano Reyes Julio Cesar
// Descripcion: Se define un controlador de JavaFX para la vista principal de una aplicación de veterinaria y define un controlador de JavaFX para la vista principal de una aplicación de veterinaria
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

@Component // Indica que esta clase es un componente de Spring
public class MainController {

    @FXML
    public VBox primaryPane; // Contenedor principal de la interfaz de usuario
    @FXML
    public HBox dataPane; //// Contenedor de datos de la interfaz de usuario
    SpringFXMLLoader loader = new SpringFXMLLoader(); // Crea una instancia de SpringFXMLLoader para cargar vistas FXML
    @FXML
    private void MascotaView() throws IOException { // Método para cambiar a la vista de mascotas
        loader.loadView("/eq/poo2496/veterinaria/mascotaView.fxml", primaryPane);
    }
    @FXML // Método para cambiar a la vista de clientes
    private void ClienteView() throws IOException {
        loader.loadView("/eq/poo2496/veterinaria/clienteView.fxml", primaryPane);
    }


    @FXML // Método para cerrar la aplicación
    public void endApp() {
        System.exit(0);
    } // Cierra la aplicación con código de salida 0
}
