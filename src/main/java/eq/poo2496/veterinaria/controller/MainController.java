/*Author: Larios Ponce Hector
Description: Este código es un controlador JavaFX para una aplicación de veterinaria. Proporciona métodos para cargar
vistas de mascotas y clientes desde archivos FXML, así como un método para cerrar la aplicación. Además, utiliza Spring
Framework para la gestión de componentes.*/

package eq.poo2496.veterinaria.controller;

// Importaciones necesarias para el funcionamiento del controlador
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
public class MainController { // Define la clase MainController en el paquete eq.poo2496.veterinaria.controller

    @FXML
    public VBox primaryPane; // Declara un campo primaryPane de tipo VBox para el contenedor principal de la interfaz
                             // de usuario
    @FXML
    public HBox dataPane; // Declara un campo dataPane de tipo HBox para un contenedor de datos en la interfaz de usuario
    SpringFXMLLoader loader = new SpringFXMLLoader(); // Crea una instancia de SpringFXMLLoader para cargar archivos FXML
    @FXML
    private void MascotaView() throws IOException { // Método privado que carga la vista de la mascota desde un archivo
                                                    // FXML y la coloca en primaryPane
        loader.loadView("/eq/poo2496/veterinaria/mascotaView.fxml", primaryPane);
    }
    @FXML
    private void ClienteView() throws IOException { // Método privado que carga la vista del cliente desde un archivo
                                                    // FXML y la coloca en primaryPane
        loader.loadView("/eq/poo2496/veterinaria/clienteView.fxml", primaryPane);
    }


    @FXML
    public void endApp() {
        System.exit(0);
    } // Método público que termina la aplicación
}
