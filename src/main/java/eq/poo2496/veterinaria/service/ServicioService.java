package eq.poo2496.veterinaria.service;

import eq.poo2496.veterinaria.entity.Servicio;
import eq.poo2496.veterinaria.repository.ServicioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicioService {
    @Autowired
    ServicioRepository servicioRepository;

    public List<Servicio> findAll() {
        return servicioRepository.findAll();
    }
}
