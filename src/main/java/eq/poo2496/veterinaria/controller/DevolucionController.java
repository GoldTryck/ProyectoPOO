/*Author: Larios Ponce Hector
Description: se enfoca en la funcionalidad de devolución de mascotas adoptadas. La clase DevolucionController es un
controlador JavaFX que gestiona la interfaz de usuario y la lógica asociada con la devolución de mascotas.*/

package eq.poo2496.veterinaria.controller;

// Importaciones necesarias para el funcionamiento del controlador
import eq.poo2496.veterinaria.service.MascotaService;
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
public class DevolucionController { // Controlador para la funcionalidad de devolución de mascotas

    @Autowired
    MascotaService mascotaS;
    @FXML
    private TableView<Mascota> mtable; //La tabla que mostrará las mascotas
    @FXML
    private TableColumn<Mascota, Long> numMascota; // Columna para el número de la mascota (tipo Long)
    @FXML
    private TableColumn<Mascota, String> mNombre; // Columna para el nombre de la mascota (tipo String)
    @FXML
    private TableColumn<Mascota, String> mRaza; // Columna para la raza de la mascota (tipo String)
    @FXML
    private TableColumn<Mascota, String> mStatus; // Columna para el estado de la mascota (tipo String)

    public void initialize() {
        numMascota.setCellValueFactory(new PropertyValueFactory<>("numeroMascota")); // Asocia la propiedad
                                                                                        // numeroMascota de la clase Mascota
                                                                                        // con esta columna
        mNombre.setCellValueFactory(new PropertyValueFactory<>("nombre")); // Asocia la propiedad nombre de la clase
                                                                              // Mascota con esta columna
        mRaza.setCellValueFactory(new PropertyValueFactory<>("raza")); // Asocia la propiedad raza de la clase
                                                                          // Mascota con esta columna
        mStatus.setCellValueFactory(new PropertyValueFactory<>("status")); // Asocia la propiedad status de la clase
                                                                              // Mascota con esta columna
        loadMascotas(); // Carga las mascotas adoptadas en la tabla
    }

    private void loadMascotas() { // Carga las mascotas adoptadas en la tabla
        List<Mascota> mascotas = mascotaS.getByStatus("ADOPTADO"); // Obtiene la lista de mascotas adoptadas
        // Convierte la lista en un ObservableList
        ObservableList<Mascota> mascotaObservableList = FXCollections.observableArrayList(mascotas);
        mtable.setItems(mascotaObservableList); // Establece el conjunto de ítems de la tabla
    }

    public void devButton() { // Método llamado al presionar el botón de devolución
        Mascota selected = mtable.getSelectionModel().getSelectedItem(); // Obtiene la mascota seleccionada en la tabla
        if (selected != null) { // Si se ha seleccionado una mascota
            selected.setStatus("DEVOLUCION"); // Cambia su estado a "DEVOLUCION"
            mascotaS.updateStatus(selected); // Actualiza el estado en el servicio
            loadMascotas(); // Recarga la lista de mascotas adoptadas
            return;
        }
        // Muestra un diálogo de advertencia si no se seleccionó ninguna mascota
        Utility.showDialog("Devolucion", "Seleccione una mascota para devolver...", javafx.scene.control.Alert.AlertType.WARNING);
    }
}
