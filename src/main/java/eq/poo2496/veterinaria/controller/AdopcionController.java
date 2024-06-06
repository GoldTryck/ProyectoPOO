/*
Author: Rosa Esmeralda Flores Harrison
Description: Estas líneas de código configuran las columnas de la tabla mtable para
que muestren los datos de las propiedades numeroMascota, nombre, raza y status de la
clase Mascota.
 */

package eq.poo2496.veterinaria.controller;

//Son las clases y bibliotecas necesarias para que el controlador funcione correctamente
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
//está importando la interfaz List del paquete java.util
import java.util.List;

@Component
public class AdopcionController {
    // Inyecta el servicio de Mascota para interactuar con los datos de mascotas
    @Autowired
    private MascotaService mascotaS;
    // Elementos de la interfaz de usuario definidos en el archivo FXML
    @FXML
    private TableView<Mascota> mtable; // Tabla para mostrar las mascotas
    @FXML
    private TableView<Cliente> ctable; // Tabla para mostrar los clientes (no utilizada en el código)
    @FXML
    private TableColumn<Mascota, Long> numMascota; // Columna para el número de mascota
    @FXML
    private TableColumn<Mascota, String> mNombre; // Columna para el nombre de la mascota
    @FXML
    private TableColumn<Mascota, String> mRaza; // Columna para la raza de la mascota
    @FXML
    private TableColumn<Mascota, String> mStatus; // Columna para el estado de la mascota

    // Método llamado al inicializar el controlador
    public void initialize() {
        // Asigna propiedades a las columnas de la tabla basadas en los atributos de la clase Mascota
        numMascota.setCellValueFactory(new PropertyValueFactory<>("numeroMascota")); // Configura la columna 'numMascota'
                                                                                        // con la propiedad 'numeroMascota'
        mNombre.setCellValueFactory(new PropertyValueFactory<>("nombre")); // Configura la columna 'mNombre' con la
                                                                              // propiedad 'nombre'
        mRaza.setCellValueFactory(new PropertyValueFactory<>("raza")); // Configura la columna 'mRaza' con la propiedad 'raza'
        mStatus.setCellValueFactory(new PropertyValueFactory<>("status")); // Configura la columna 'mStatus' con la propiedad 'status'
        // Carga las mascotas en la tabla
        loadMascotas();
    }
    // Método para cargar las mascotas en la tabla
    private void loadMascotas() {
        // Obtiene listas de mascotas en estado "ADOPCION" y "DEVOLUCION"
        List<Mascota> mascotas = mascotaS.getByStatus("ADOPCION");
        List<Mascota> mdev = mascotaS.getByStatus("DEVOLUCION");
        // Combina ambas listas en una sola
        mascotas.addAll(mdev);
        // Convierte la lista en una lista observable y la asigna a la tabla
        ObservableList<Mascota> mascotaObservableList = FXCollections.observableArrayList(mascotas);
        mtable.setItems(mascotaObservableList);
    }
    // Método llamado al presionar el botón de adopción
    public void adptButton()
    // Obtiene la mascota seleccionada en la tabla
        Mascota selected = mtable.getSelectionModel().getSelectedItem();
        if (selected != null) {
        // Si hay una mascota seleccionada, cambia su estado a "ADOPTADO"
        selected.setStatus("ADOPTADO");
        // Actualiza el estado de la mascota en el servicio
        mascotaS.updateStatus(selected);
        // Muestra un cuadro de diálogo informando que la mascota fue adoptada
        Utility.showDialog("Adopcion", "Mascota adoptada", javafx.scene.control.Alert.AlertType.INFORMATION);
        // Recarga la lista de mascotas
        loadMascotas();
            return;
        }
        // Si no hay una mascota seleccionada, muestra una advertencia
        Utility.showDialog("Adopcion", "Seleccione una mascota", javafx.scene.control.Alert.AlertType.WARNING);
    }
}
