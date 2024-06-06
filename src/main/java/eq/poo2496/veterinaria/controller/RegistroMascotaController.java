    package eq.poo2496.veterinaria.controller;

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



    @Component // Indica que esta clase es un componente de Spring
    public class RegistroMascotaController {

        @Autowired // Inyecta el servicio de Mascota para interactuar con los datos de mascotas
        private MascotaService mascotaS;
        @FXML // Elementos de la interfaz de usuario definidos en el archivo FXML
        private TextField nombreField; // Campo de texto para ingresar el nombre de la mascota
        @FXML
        private TextField razaField; // Campo de texto para ingresar la raza de la mascota
        @FXML
        private ListView<CheckBox> vacunasListView; // Lista de casillas de verificación para seleccionar vacunas
        @FXML
        private VBox statusRadio; // Contenedor vertical para los botones de radio de estado
        private ToggleGroup statusGroup = new ToggleGroup(); // Grupo de botones de radio para el estado de la mascota

        public void initialize() {  // Método llamado al inicializar el controlador
            Utility.cbList(vacunasListView, Vacuna.class); // Utilidad para cargar elementos de una enumeración en una lista de casillas de verificación
            statusGroup = Utility.loadRadioButtons(statusRadio, StatusMascota.class);// Utilidad para cargar elementos de una enumeración en un grupo de botones de radio y asignarlo al contenedor
        }

        @FXML
        public void registerButton(){ // Método llamado al hacer clic en el botón de registro
            // Obtiene el nombre, la raza y las vacunas ingresadas por el usuario
            String nombre = nombreField.getText();
            String raza = razaField.getText();
            List<String> vacunas = Utility.getSelectedCB(vacunasListView);
            // Verifica si los campos obligatorios están vacíos
            if (nombre.isEmpty() || raza.isEmpty() || statusGroup.getSelectedToggle() == null) {
                Utility.showDialog("Error", "Por favor ingrese nombre, raza y status de la mascota", Alert.AlertType.WARNING);
                return;
            }
            String status = ((RadioButton) statusGroup.getSelectedToggle()).getText();// Obtiene el estado de la mascota seleccionado por el usuario
            Mascota saved = mascotaS.registrarMascota(nombre, raza, vacunas, status);// Registra la mascota con los datos proporcionados
            String numMascota = "Mascota registrada exitosamente con numero de mascota: " + saved.getNumeroMascota().toString();
            Utility.showDialog("Registro Exitoso", numMascota, Alert.AlertType.INFORMATION);
        }

    }
