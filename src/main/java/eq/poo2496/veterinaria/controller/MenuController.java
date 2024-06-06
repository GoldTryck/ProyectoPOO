//Autor: Medrano Reyes Julio Cesar
//Descripcion: define una clase abstracta MenuController que sirve como base para otros controladores que tiene paneles comunes que se utilizan en múltiples vistas y proporciona un método de inicialización que puede ser utilizado para cualquier inicialización común
package eq.poo2496.veterinaria.controller;

import eq.poo2496.veterinaria.util.SpringFXMLLoader;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component // Indica que esta clase es un componente de Spring
public abstract class MenuController {
    // Define los paneles comunes a todos los controladores
    @FXML
    protected Pane headerPane; // Panel para el encabezado
    @FXML
    protected Pane menuPane; // Panel para el menú
    @FXML
    protected Pane dataPane; // Panel para los datos


    @FXML
    public void initialize() {
        // Puedes poner aquí cualquier inicialización común a todos los controladores.
    }
}
