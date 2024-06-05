/*Author: Larios Ponce Hector
Description: este código define un servicio para agendar citas en una veterinaria. El servicio se encarga de verificar
la disponibilidad de los veterinarios y asistentes, cargar las entidades necesarias desde la base de datos y finalmente
crear y guardar una nueva cita.*/
package eq.poo2496.veterinaria.service;

// Importa las clases necesarias para el funcionamiento del servicio
import eq.poo2496.veterinaria.entity.Cliente;
import eq.poo2496.veterinaria.entity.Mascota;
import eq.poo2496.veterinaria.repository.ClienteRepository;
import eq.poo2496.veterinaria.util.Utility;
import javafx.scene.control.Alert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service // Marca esta clase como un servicio de Spring, para que pueda ser gestionada por el contenedor de Spring
public class ClienteService {
    @Autowired // Inyección de dependencias de Spring para ClienteRepository
    private ClienteRepository clienteRepository;

    public Cliente clienteByMascota(Mascota mascota){ // Método para obtener un Cliente a partir de una Mascota
        try{
            // Intenta encontrar y retornar un Cliente en base a una Mascota usando el repositorio
            return clienteRepository.findByMascota(mascota);
        }catch (Exception e){
            String msg = e.getMessage(); // En caso de excepción, obtiene el mensaje de error
            Utility.showDialog("DB ERROR", msg, Alert.AlertType.ERROR); // Muestra un cuadro de diálogo con el
                                                                             // mensaje de error usando la clase Utility
            return null; // Retorna null en caso de error
        }
    }
}
