//Autor: Medrano Reyes Julio Cesar
//Descripción:  facilita la carga de vistas desde archivos FXML y su integración con el marco
//de trabajo Spring. El objetivo principal de esta clase es cargar una interfaz gráfica de
//usuario definida en un archivo FXML
package eq.poo2496.veterinaria.util;//Esta línea declara el paquete al que pertenece esta clase.

import javafx.fxml.FXMLLoader; //se utiliza para cargar archivos FXML, que definen las interfaces de usuario.
import javafx.scene.layout.Pane; //es un tipo de contenedor de JavaFX que puede contener otros nodos.
import java.io.IOException; //se utiliza para manejar excepciones relacionadas con la entrada/salida.
import java.net.URL; //se utiliza para manejar direcciones URL
import static eq.poo2496.veterinaria.Main.context; // es una referencia estática al contexto de Spring en la clase Main, que permite acceder a los beans de Spring.

public class SpringFXMLLoader {
    public void loadView(String filePath, Pane child) throws IOException{//(de tipo String), que es la ruta del archivo FXML que se quiere cargar y child (de tipo Pane), que es el contenedor donde se cargará la vista.
        URL url = getClass().getResource(filePath); //Esto permite localizar el archivo FXML en el sistema de archivos o en el classpath.

        FXMLLoader loaded = new FXMLLoader(url); //es la clase responsable de cargar y construir la interfaz definida en el archivo FXML.
        child.getChildren().clear(); //Se limpian todos los elementos hijos del contenedor child para asegurarse de que esté vacío antes de cargar la nueva vista.
        loaded.setControllerFactory(context::getBean); // Esto permite que FXMLLoader use los controladores gestionados por Spring, facilitando la inyección de dependencias.
        child.getChildren().add(loaded.load()); //Se carga la vista desde el archivo FXML y se agrega al contenedor child
    }
}

