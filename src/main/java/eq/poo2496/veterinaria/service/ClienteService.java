package eq.poo2496.veterinaria.service;

import eq.poo2496.veterinaria.entity.Cliente;
import eq.poo2496.veterinaria.entity.Mascota;
import eq.poo2496.veterinaria.repository.ClienteRepository;
import eq.poo2496.veterinaria.util.Utility;
import javafx.scene.control.Alert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;

    public Cliente clienteByMascota(Mascota mascota){
        try{
            return clienteRepository.findByMascota(mascota);
        }catch (Exception e){
            String msg = e.getMessage();
            Utility.showDialog("DB ERROR", msg, Alert.AlertType.ERROR);
            return null;
        }
    }
}
