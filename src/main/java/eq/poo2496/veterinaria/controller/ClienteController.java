/* Author: Rosa Esmeralda Flores Harrison
Descrition: Este código define un controlador de JavaFX para una aplicación de veterinaria que gestiona
clientes y sus mascotas. Permite buscar una mascota por su ID, y si la mascota no está adoptada y no tiene
un cliente asociado, la muestra en una tabla. Configura las columnas de la tabla para mostrar el nombre,
la raza y el estado de la mascota. Además, restringe la entrada del campo de texto para el ID de la mascota
a solo dígitos.*/
package eq.poo2496.veterinaria.controller;

//Son las clases y bibliotecas necesarias para que el controlador funcione correctamente
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

    // Inyecta el servicio de Cliente para interactuar con los datos de clientes
    @Autowired
    private ClienteService clienteS;
    // Inyecta el servicio de Mascota para interactuar con los datos de mascotas
    @Autowired
    private MascotaService mascotaS;
    // Elementos de la interfaz de usuario definidos en el archivo FXML
    @FXML
    private TextField cnombre; // Campo de texto para el nombre del cliente
    @FXML
    private TextField cap; // Campo de texto para el apellido paterno del cliente
    @FXML
    private TextField cam; // Campo de texto para el apellido materno del cliente
    @FXML
    private TextField ccurp; // Campo de texto para el CURP del cliente
    @FXML
    private DatePicker cfn; // Selector de fecha para la fecha de nacimiento del cliente
    @FXML
    private TextField cmascota; // Campo de texto para el ID de la mascota
    @FXML
    private TableView<Mascota> tmascota; // Tabla para mostrar la mascota
    @FXML
    private TableColumn<Mascota, String> mnombre; // Columna para el nombre de la mascota
    @FXML
    private TableColumn<Mascota, String> mraza; // Columna para la raza de la mascota
    @FXML
    private TableColumn<Mascota, String> mstatus; // Columna para el estado de la mascota

    private static Mascota mascota; // Variable estática para almacenar la mascota encontrada
    @FXML
    private void findMascota() {
        // Busca una mascota por su ID, que se introduce en el campo de texto cmascota
        mascota = mascotaS.getById(Long.parseLong(cmascota.getText()));
        if(mascota != null){
            // Busca el cliente asociado a la mascota
            Cliente cliente = clienteS.clienteByMascota(mascota);
            // Verifica si la mascota no está adoptada y no tiene un cliente asociado
            if (cliente == null && !mascota.getStatus().equals("ADOPTADO")) {
                // Añade la mascota encontrada a la tabla
                ObservableList<Mascota> mascotaObservableList = FXCollections.observableArrayList();
                mascotaObservableList.add(mascota);
                tmascota.setItems(mascotaObservableList);
                return;
            }
        }
        // Muestra un diálogo de error si la mascota no está disponible
        Utility.showDialog("Mascota no encontrada","La mascota no esta disponible", Alert.AlertType.ERROR);
    }

    @FXML

    public void initialize() {
        // Configura las columnas de la tabla con las propiedades correspondientes de la clase Mascota
        mnombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        mraza.setCellValueFactory(new PropertyValueFactory<>("raza"));
        mstatus.setCellValueFactory(new PropertyValueFactory<>("status"));

        // Añade un filtro de eventos al campo de texto cmascota para permitir solo la entrada de dígitos
        cmascota.addEventFilter(KeyEvent.KEY_TYPED, event -> {
            String c = event.getCharacter();
            if (!c.matches("[0-9]")) {
                event.consume(); // Consume el evento si el carácter no es un dígito
            }
        });
    }


}
