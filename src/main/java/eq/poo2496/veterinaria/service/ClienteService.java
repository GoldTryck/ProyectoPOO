package eq.poo2496.veterinaria.service;

import eq.poo2496.veterinaria.entity.Cliente;
import eq.poo2496.veterinaria.entity.Mascota;
import eq.poo2496.veterinaria.entity.Persona;
import eq.poo2496.veterinaria.repository.ClienteRepository;
import eq.poo2496.veterinaria.util.Utility;
import jakarta.transaction.Transactional;
import javafx.scene.control.Alert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private MascotaService mascotaService;

    public List<Cliente> getAll() {
        return clienteRepository.findAll();
    }

    public Cliente byIdAndMascota(Long id) {
        Optional<Cliente> optionalCliente = clienteRepository.findByIdWhereMascotaIsNull(id);
        return optionalCliente.orElse(null);
    }

    public Cliente clienteByMascota(Mascota mascota){
        try{
            return clienteRepository.findByMascota(mascota);
        }catch (Exception e){
            String msg = e.getMessage();
            Utility.showDialog("DB ERROR", msg, Alert.AlertType.ERROR);
            return null;
        }
    }

    @Transactional
    public Cliente persistCliente(Cliente cliente) {
        Mascota mascota = cliente.getMascota();
        if (mascota != null && mascota.getNumeroMascota() != null) {
            Mascota attachedMascota = mascotaService.getById(mascota.getNumeroMascota());
            cliente.setMascota(attachedMascota);
        }

        if (clienteRepository.existsByCurp(cliente.getCurp())) {
            throw new IllegalStateException("Cliente con CURP " + cliente.getCurp() + " ya existe.");
        }
        try {
            return clienteRepository.save(cliente);
        } catch (DataIntegrityViolationException e) {
            throw new IllegalStateException("Error al guardar el cliente: " + e.getMessage(), e);
        }
    }




    public List<Cliente> getByMascota() {
        return clienteRepository.getByMascota();
    }

    public void updateCliente(Cliente clienteSelected) {
        clienteRepository.updateMascotaById(clienteSelected.getId(), clienteSelected.getMascota());
    }
    public void removeMascota(Cliente clienteSelected) {
        clienteRepository.deleteMascotaById(clienteSelected.getId());
    }

    public Cliente getById(Long id) {
        return clienteRepository.findById(id).orElse(null);
    }
}
