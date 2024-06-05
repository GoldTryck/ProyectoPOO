/*Author: Larios Ponce Hector
Description: Este código sirve como controlador para una aplicación de veterinaria. Su propósito principal es manejar la
interacción entre la interfaz de usuario y la lógica de negocio relacionada con clientes y mascotas. Permite buscar
mascotas por ID y mostrarlas en una tabla, así como configurar la visualización de la tabla y filtrar entradas en el
campo de búsqueda*/

package eq.poo2496.veterinaria.controller;

// Importaciones necesarias para el funcionamiento del controlador
import eq.poo2496.veterinaria.entity.Cliente;
import eq.poo2496.veterinaria.entity.Mascota;
import eq.poo2496.veterinaria.service.ClienteService;
import eq.poo2496.veterinaria.service.MascotaService;
import eq.poo2496.veterinaria.util.Utility;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class ClienteController {

    @Autowired
    private ClienteService clienteS; // Servicio para operaciones relacionadas con clientes
    @Autowired
    private MascotaService mascotaS; // Servicio para operaciones relacionadas con mascotas
    @FXML
    private TextField cnombre; // Campo de texto para el nombre del cliente
    @FXML
    private TextField cap; // Campo de texto para el apellido del cliente
    @FXML
    private TextField cam; // Campo de texto para la dirección del cliente
    @FXML
    private TextField ccurp; // Campo de texto para el CURP (Clave Única de Registro de Población) del cliente
    @FXML
    private DatePicker cfn; // Selector de fecha para la fecha de nacimiento del cliente
    @FXML
    private TextField cmascota; // Campo de texto para el ID de la mascota a buscar
    @FXML
    private TableView<Mascota> tmascota; // Tabla para mostrar información de mascotas
    @FXML
    private TableColumn<Mascota, String> mnombre; // Columna para el nombre de la mascota
    @FXML
    private TableColumn<Mascota, String> mraza; // Columna para la raza de la mascota
    @FXML
    private TableColumn<Mascota, String> mstatus; // Columna para el estado de la mascota

    private static Mascota mascota; // Mascota estática usada en la búsqueda
    @FXML
    private void findMascota() { // Método para buscar una mascota

        mascota = mascotaS.getById(Long.parseLong(cmascota.getText())); // Busca la mascota por su ID (obtenido del campo
                                                                        // de texto 'cmascota')
        if(mascota != null){ // Si la mascota existe
            Cliente cliente = clienteS.clienteByMascota(mascota); // Busca el cliente asociado a la mascota
            if (cliente == null && !mascota.getStatus().equals("ADOPTADO")) { // Si no hay cliente asociado y la mascota
                                                                              // no está adoptada
                ObservableList<Mascota> mascotaObservableList = FXCollections.observableArrayList();
                mascotaObservableList.add(mascota); // Crea una lista observable y añade la mascota encontrada
                tmascota.setItems(mascotaObservableList); // Establece la lista en la tabla de mascotas
                return;
            }
        }
        // Si la mascota no se encuentra o no está disponible, muestra un diálogo de error
        Utility.showDialog("Mascota no encontrada","La mascota no esta disponible", Alert.AlertType.ERROR);
    }

    @FXML

    public void initialize() { // Método de inicialización
        // Configuración de las propiedades de las columnas de la tabla de mascotas
        mnombre.setCellValueFactory(new PropertyValueFactory<>("nombre")); // Asigna la propiedad "nombre" de la
                                                                              // clase Mascota a la columna "mnombre"
        mraza.setCellValueFactory(new PropertyValueFactory<>("raza")); // Asigna la propiedad "raza" de la clase
                                                                          // Mascota a la columna "mraza"
        mstatus.setCellValueFactory(new PropertyValueFactory<>("status")); // Asigna la propiedad "status" de la
                                                                              // clase Mascota a la columna "mstatus"

        cmascota.addEventFilter(KeyEvent.KEY_TYPED, event -> { // Agrega un filtro de eventos al campo de texto 'cmascota'
            String c = event.getCharacter(); // Obtiene el carácter ingresado en el evento
            if (!c.matches("[0-9]")) { // Verifica si el carácter no coincide con un dígito del 0 al 9
                event.consume(); // Si el carácter no es un dígito, consume el evento para evitar que se propague
            }
        });
    }


}
