/*Author: Flores Harrison Rosa Esmeralda
Description: Este código define un controlador en JavaFX para la devolución de mascotas en una aplicación de veterinaria.
*/

package eq.poo2496.veterinaria.controller;


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
public class DevolucionController {
    // Inyecta el servicio de Mascota para interactuar con los datos de mascotas
    @Autowired
    MascotaService mascotaS;
    // Elementos de la interfaz de usuario definidos en el archivo FXML
    @FXML
    private TableView<Mascota> mtable; // Tabla para mostrar las mascotas
    @FXML
    private TableColumn<Mascota, Long> numMascota;// Columna para el número de la mascota
    @FXML
    private TableColumn<Mascota, String> mNombre; // Columna para el nombre de la mascota
    @FXML
    private TableColumn<Mascota, String> mRaza;  // Columna para la raza de la mascota
    @FXML
    private TableColumn<Mascota, String> mStatus; // Columna para el estado de la mascota

    // Método llamado al inicializar el controlador
    public void initialize() {
        // Configura las fábricas de valores para las columnas de la tabla
        numMascota.setCellValueFactory(new PropertyValueFactory<>("numeroMascota"));
        mNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        mRaza.setCellValueFactory(new PropertyValueFactory<>("raza"));
        mStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        // Carga las mascotas adoptadas en la tabla
        loadMascotas();
    }
    // Método para cargar las mascotas adoptadas en la tabla
    private void loadMascotas() {
        // Obtiene la lista de mascotas adoptadas
        List<Mascota> mascotas = mascotaS.getByStatus("ADOPTADO");
        // Convierte la lista en una lista observable y la asigna a la tabla
        ObservableList<Mascota> mascotaObservableList = FXCollections.observableArrayList(mascotas);
        mtable.setItems(mascotaObservableList);
    }
    // Método llamado al presionar el botón de devolución
    public void devButton() {
        // Obtiene la mascota seleccionada en la tabla
        Mascota selected = mtable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            // Cambia el estado de la mascota a "DEVOLUCION"
            selected.setStatus("DEVOLUCION");
            // Actualiza el estado de la mascota en el servicio
            mascotaS.updateStatus(selected);
            // Recarga la lista de mascotas adoptadas en la tabla
            loadMascotas();
            return;
        }
        // Muestra un diálogo de advertencia si no se selecciona una mascota
        Utility.showDialog("Devolucion", "Seleccione una mascota para devolver...", javafx.scene.control.Alert.AlertType.WARNING);
    }
}
