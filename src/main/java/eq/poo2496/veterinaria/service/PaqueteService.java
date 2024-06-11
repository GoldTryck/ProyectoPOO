package eq.poo2496.veterinaria.service;

import eq.poo2496.veterinaria.entity.Paquete;
import eq.poo2496.veterinaria.entity.Servicio;
import eq.poo2496.veterinaria.entity.ServicioIndividual;
import eq.poo2496.veterinaria.repository.PaqueteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaqueteService {
    @Autowired
    PaqueteRepository paqueteRepository;

    public void save(String nombre, List<ServicioIndividual> servicios) {
        Paquete paquete = new Paquete();
        paquete.setNombre(nombre);
        paquete.setServicios(servicios);
        paqueteRepository.save(paquete);
    }

    public List<Paquete> findAll() {
        return paqueteRepository.findAllWithServicios();
    }

}
