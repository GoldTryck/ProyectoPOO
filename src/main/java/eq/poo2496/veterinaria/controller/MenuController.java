/*Author: Larios Ponce Hector
Description: Este código define una clase abstracta llamada MenuController que actúa como un controlador base para las
vistas de menú en una aplicación de veterinaria. Proporciona campos para acceder a los paneles de la interfaz de usuario
y un método de inicialización que puede ser implementado por las clases hijas para realizar inicializaciones comunes*/
package eq.poo2496.veterinaria.controller;

import eq.poo2496.veterinaria.util.SpringFXMLLoader; // Importa la clase SpringFXMLLoader para cargar archivos FXML
import javafx.fxml.FXML; // Importa las anotaciones FXML de JavaFX
import javafx.scene.layout.Pane; // Importa la clase Pane de JavaFX para manejar diseños de GUI
import org.springframework.beans.factory.annotation.Autowired; // Importa la anotación Autowired de Spring para la
                                                               // inyección de dependencias
import org.springframework.context.ApplicationContext; // Importa la clase ApplicationContext de Spring para el contexto
                                                       // de la aplicación
import org.springframework.stereotype.Component; // Importa la anotación Component de Spring para marcar esta clase
                                                 // como un componente

import java.io.IOException; // Importa IOException para manejar excepciones de E/S

@Component
public abstract class MenuController { // Define una clase abstracta MenuController

    @FXML
    protected Pane headerPane; // Representa un panel de encabezado en la interfaz de usuario
    @FXML
    protected Pane menuPane; // Representa un panel de menú en la interfaz de usuario
    @FXML
    protected Pane dataPane; // Representa un panel de datos en la interfaz de usuario


    @FXML
    public void initialize() { // Método que se llama automáticamente después de que se hayan inyectado todos los campos
                               // FXML y se haya inicializado el controlador
        // Puedes poner aquí cualquier inicialización común a todos los controladores.
    }
}
