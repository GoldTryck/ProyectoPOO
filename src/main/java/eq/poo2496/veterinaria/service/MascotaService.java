package eq.poo2496.veterinaria.service;

import eq.poo2496.veterinaria.entity.Mascota;
import eq.poo2496.veterinaria.repository.MascotaRepository;
import eq.poo2496.veterinaria.util.Utility;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MascotaService {

    @Autowired
    private MascotaRepository mascotaRepository;

    public Mascota registrarMascota(String nombre, String raza, List<String> vacunas, String status) {
        try{
            Mascota mascota = new Mascota();
            mascota.setNombre(nombre);
            mascota.setRaza(raza);
            mascota.setVacunasAplicadas(vacunas);
            mascota.setStatus(status);
            return mascotaRepository.save(mascota);
        }catch (Exception e){
            String message = e.getMessage();
            Utility.showDialog("Error en la base de datos", message, javafx.scene.control.Alert.AlertType.ERROR);
            return null;
        }
    }

    public List<Mascota> getByStatus(String status) {
        return mascotaRepository.findByStatus(status);
    }
    public Mascota getById(Long id) {
        try{
            return mascotaRepository.findByNumeroMascota(id);
        }catch (Exception e){
            String message = e.getMessage();
            Utility.showDialog("Error en la base de datos", message, javafx.scene.control.Alert.AlertType.ERROR);
            return null;
        }

    }
    public void updateStatus(Mascota mascota){
        try{
            Long id = mascota.getNumeroMascota();
            String status = mascota.getStatus();
            mascotaRepository.updateStatusByNumeroMascota(id, status);
        }catch (Exception e){
            String message = e.getMessage();
            Utility.showDialog("Error en la base de datos", message, javafx.scene.control.Alert.AlertType.ERROR);
            return;
        }

    }

    public void updateMascota(Mascota mascotaM) {
        mascotaRepository.updateStatusByNumeroMascota(mascotaM.getNumeroMascota(), mascotaM.getStatus());
    }
}
