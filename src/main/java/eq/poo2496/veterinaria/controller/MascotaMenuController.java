//Autor: Medrano Reyes Julio Cesar
//Descricion: define un controlador de JavaFX para la vista principal de una aplicación de veterinaria y cambiar entre las vistas de mascotas y clientes al hacer clic en los botones correspondientes
package eq.poo2496.veterinaria.controller;

import eq.poo2496.veterinaria.util.SpringFXMLLoader;
import javafx.fxml.FXML;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component //Indica que esta clase es un componente de Spring
public class MascotaMenuController {
    @FXML
    private Pane dataPane; // Contenedor de datos de la interfaz de usuario
    // Método para abrir la vista de registro de mascotas
    @FXML
    public void RegistroMascota() throws IOException {
        SpringFXMLLoader loader = new SpringFXMLLoader(); // Método para abrir la vista de devolución de mascotas
        loader.loadView("/eq/poo2496/veterinaria/formularioMascota.fxml", dataPane); // Carga la vista de registro de mascotas en el contenedor de datos
    }
    // Método para abrir la vista de adopción de mascotas
    @FXML
    public void Adopcion() throws IOException {
        SpringFXMLLoader loader = new SpringFXMLLoader(); // Crea una instancia de SpringFXMLLoader
        loader.loadView("/eq/poo2496/veterinaria/formularioAdopcion.fxml", dataPane); // Carga la vista de adopción de mascotas en el contenedor de datos
    }
    // Método para abrir la vista de devolución de mascotas
    @FXML
    public void Devolucion() throws IOException {
        SpringFXMLLoader loader = new SpringFXMLLoader(); // Crea una instancia de SpringFXMLLoader
        loader.loadView("/eq/poo2496/veterinaria/formularioDevolucion.fxml", dataPane); // Carga la vista de devolución de mascotas en el contenedor de datos
    }


}
