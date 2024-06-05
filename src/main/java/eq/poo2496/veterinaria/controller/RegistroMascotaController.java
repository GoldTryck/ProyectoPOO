/*Author: Larios Ponce Hector
Description: Este código es parte de una aplicación de registro de mascotas para una veterinaria. El controlador
RegistroMascotaController maneja la lógica de la interfaz de usuario para registrar nuevas mascotas. Permite a los
usuarios ingresar información como el nombre, la raza, las vacunas y el estado de la mascota. Luego de validar la
entrada del usuario, utiliza un servicio (MascotaService) */
package eq.poo2496.veterinaria.controller;

// Importaciones necesarias para el funcionamiento del controlador
import eq.poo2496.veterinaria.service.MascotaService;
import eq.poo2496.veterinaria.entity.Mascota;
import eq.poo2496.veterinaria.enums.*;
import eq.poo2496.veterinaria.util.Utility;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;



@Component
public class RegistroMascotaController { // Controlador para la interfaz de registro de mascotas

    @Autowired
    private MascotaService mascotaS; // Servicio para gestionar las operaciones relacionadas con las mascotas
    @FXML
    private TextField nombreField; // Campos de texto para el nombre de la mascota
    @FXML
    private TextField razaField; // Campos de texto para la raza de la mascota
    @FXML
    private ListView<CheckBox> vacunasListView; // Lista de casillas de verificación para las vacunas de la mascota
    @FXML
    private VBox statusRadio; // Contenedor para los botones de radio que representan el estado de la mascota
    private ToggleGroup statusGroup = new ToggleGroup(); // Grupo de botones de radio para el estado de la mascota

    public void initialize() { // Método inicializador llamado automáticamente al cargar la interfaz de usuario
        Utility.cbList(vacunasListView, Vacuna.class); // Inicializar la lista de vacunas
        statusGroup = Utility.loadRadioButtons(statusRadio, StatusMascota.class); // Cargar los botones de radio para el
                                                                                  // estado de la mascota
    }

    @FXML
    public void registerButton(){ // Método llamado cuando se hace clic en el botón de registro
        String nombre = nombreField.getText(); // Obtener el nombre seleccionado
        String raza = razaField.getText(); // Obtener la raza seleccionada
        List<String> vacunas = Utility.getSelectedCB(vacunasListView); // Obtener las vacunas seleccionadas

        if (nombre.isEmpty() || raza.isEmpty() || statusGroup.getSelectedToggle() == null) { // Verificar si los campos
                                                                                             // obligatorios están completos

            Utility.showDialog("Error", "Por favor ingrese nombre, raza y status de la mascota", Alert.AlertType.WARNING);
            return; // Mostrar una advertencia si los campos están incompletos
        }
        String status = ((RadioButton) statusGroup.getSelectedToggle()).getText(); // Obtener el estado de la mascota seleccionado
        Mascota saved = mascotaS.registrarMascota(nombre, raza, vacunas, status); // Registrar la mascota utilizando el servicio
                                                                                  // de mascotas
        // Mostrar un mensaje de confirmación con el número de la mascota registrada
        String numMascota = "Mascota registrada exitosamente con numero de mascota: " + saved.getNumeroMascota().toString();
        Utility.showDialog("Registro Exitoso", numMascota, Alert.AlertType.INFORMATION);
    }

}
