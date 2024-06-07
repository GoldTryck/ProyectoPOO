package eq.poo2496.veterinaria.service;

import eq.poo2496.veterinaria.entity.Cliente;
import eq.poo2496.veterinaria.entity.Mascota;
import eq.poo2496.veterinaria.repository.ClienteRepository;
import eq.poo2496.veterinaria.util.Utility;
import jakarta.transaction.Transactional;
import javafx.scene.control.Alert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    public Cliente saveCliente(Cliente cliente) {
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


    public static boolean isValidCURP(String curp) {
        String CURP_REGEX = "^[A-Z]{1}[AEIOU]{1}[A-Z]{2}\\d{2}(0[1-9]|1[0-2])(0[1-9]|[12]\\d|3[01])[HM](AS|BC|BS|CC|CL|CM|CS|CH|DF|DG|GT|GR|HG|JC|MC|MN|MS|NT|NL|OC|PL|QT|QR|SP|SL|SR|TC|TS|TL|VZ|YN|ZS|NE)[B-DF-HJ-NP-TV-Z]{3}[A-Z\\d]{1}[0-9]{1}$";

        Pattern pattern = Pattern.compile(CURP_REGEX);
        Matcher matcher = pattern.matcher(curp);
        return matcher.matches();
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
}
