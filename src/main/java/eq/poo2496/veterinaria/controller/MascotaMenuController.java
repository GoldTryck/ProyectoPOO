/*Author: Larios Ponce Hector
Description: Este código representa un controlador para un menú de opciones en una aplicación de veterinaria desarrollada
en JavaFX. El controlador tiene métodos para manejar eventos relacionados con las diferentes opciones del menú, como
registrar una nueva mascota, gestionar adopciones o procesar devoluciones de mascotas.*/
package eq.poo2496.veterinaria.controller;

import eq.poo2496.veterinaria.util.SpringFXMLLoader; // Importa el cargador personalizado de vistas FXML
import javafx.fxml.FXML; // Importa las anotaciones de JavaFX para manejar FXML
import javafx.scene.layout.HBox; // Importa la clase HBox de JavaFX para manejar el diseño de la GUI
import javafx.scene.layout.Pane; // Importa la clase Pane de JavaFX para manejar el diseño de la GUI
import org.springframework.stereotype.Component; // Importa la anotación @Component de Spring Framework

import java.io.IOException; // Importa la excepción IOException para el manejo de errores de E/S

@Component
public class MascotaMenuController {
    @FXML
    private Pane dataPane; // Declara un atributo dataPane que es un contenedor de tipo Pane

    @FXML
    public void RegistroMascota() throws IOException { // Método para manejar el evento de registro de mascotas
        SpringFXMLLoader loader = new SpringFXMLLoader(); // Crea una instancia del cargador personalizado
        loader.loadView("/eq/poo2496/veterinaria/formularioMascota.fxml", dataPane); // Carga el formulario de
                                                                                            // registro de mascotas
    }
    @FXML
    public void Adopcion() throws IOException {
        SpringFXMLLoader loader = new SpringFXMLLoader(); // Crea una instancia del cargador personalizado
        loader.loadView("/eq/poo2496/veterinaria/formularioAdopcion.fxml", dataPane);// Carga el formulario de
                                                                                            // adopción de mascotas
    }
    @FXML
    // Método para manejar el evento de devolución de mascotas
    public void Devolucion() throws IOException {
        SpringFXMLLoader loader = new SpringFXMLLoader(); // Crea una instancia del cargador personalizado
        loader.loadView("/eq/poo2496/veterinaria/formularioDevolucion.fxml", dataPane); // Carga el formulario de
                                                                                               // devolución de mascotas
    }


}
