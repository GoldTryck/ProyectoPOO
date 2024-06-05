// Declaración del paquete en el que se encuentra este controlador
package eq.poo2496.veterinaria.controller;

// Importaciones necesarias para que el código funcione correctamente
import eq.poo2496.veterinaria.service.MascotaService;
import eq.poo2496.veterinaria.entity.Cliente;
import eq.poo2496.veterinaria.entity.Mascota;
import eq.poo2496.veterinaria.util.Utility;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AdopcionController { // Define la clase AdopcionController.
    @Autowired
    private MascotaService mascotaS; // Declara una variable para el servicio de mascotas.
    @FXML
    private TableView<Mascota> mtable; // Declara variables para la tabla de Mascota.

    @FXML
    private TableView<Cliente> ctable; // Declara variables para la tabla de Cliente.
    @FXML
    private TableColumn<Mascota, Long> numMascota; // Declara la variable numMascota para las columnas
    @FXML                                          // de la tabla de Mascota.
    private TableColumn<Mascota, String> mNombre; // Declara la variable nNombre para las columnas
    @FXML                                         // de la tabla de Mascota.
    private TableColumn<Mascota, String> mRaza; // Declara la variable mRaza para las columnas
    @FXML                                       // de la tabla de Mascota.
    private TableColumn<Mascota, String> mStatus; // Declara la variable mStatus para las columnas
                                                  // de la tabla de Mascota.
    public void initialize() { // Método que se ejecuta al inicializar el controlador.
        numMascota.setCellValueFactory(new PropertyValueFactory<>("numeroMascota")); // Configura la columna
        // numMascota para que muestre el valor de la propiedad "numeroMascota" de los objetos Mascota.
        mNombre.setCellValueFactory(new PropertyValueFactory<>("nombre")); // Configura la columna mNombre para que
        // muestre el valor de la propiedad "nombre" de los objetos Mascota
        mRaza.setCellValueFactory(new PropertyValueFactory<>("raza")); // Configura la columna mRaza para que muestre
        // el valor de la propiedad "raza" de los objetos Mascota.
        mStatus.setCellValueFactory(new PropertyValueFactory<>("status")); // Configura la columna mRaza para que
        // muestre el valor de la propiedad "raza" de los objetos Mascota.
        loadMascotas(); // Carga las mascotas en la tabla.
    }

    // Método para cargar las mascotas con estado "ADOPCION" y "DEVOLUCION" en la tabla.
    private void loadMascotas() {
        List<Mascota> mascotas = mascotaS.getByStatus("ADOPCION"); // Obtiene la lista de mascotas en adopción.
        List<Mascota> mdev = mascotaS.getByStatus("DEVOLUCION"); // Obtiene la lista de mascotas devueltas.
        mascotas.addAll(mdev); // Agrega las mascotas devueltas a la lista de mascotas en adopción.
        ObservableList<Mascota> mascotaObservableList = FXCollections.observableArrayList(mascotas); // Crea una lista
        // observable a partir de la lista de mascotas.
        mtable.setItems(mascotaObservableList); // Establece la lista observable como los ítems de la tabla de mascotas.
    }
    public void adptButton() { // Método que se ejecuta cuando se presiona el botón de adopción.
        Mascota selected = mtable.getSelectionModel().getSelectedItem(); // Obtiene la mascota seleccionada en la tabla.
        if (selected != null) {
            selected.setStatus("ADOPTADO"); // Si se seleccionó una mascota, cambia su estado a "ADOPTADO".
            mascotaS.updateStatus(selected); // Actualiza el estado de la mascota en el servicio.
            // Muestra un diálogo informando que la mascota ha sido adoptada.
            Utility.showDialog("Adopcion", "Mascota adoptada", javafx.scene.control.Alert.AlertType.INFORMATION);
            loadMascotas();
            return; // Vuelve a cargar las mascotas en la tabla.
        }
        // Si no se seleccionó ninguna mascota, muestra un diálogo de advertencia.
        Utility.showDialog("Adopcion", "Seleccione una mascota", javafx.scene.control.Alert.AlertType.WARNING);
    }
}
