package eq.poo2496.veterinaria.service;

import eq.poo2496.veterinaria.entity.Servicio;
import eq.poo2496.veterinaria.entity.ServicioIndividual;
import eq.poo2496.veterinaria.repository.ServicioIndividualRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicioIndividualService {

    @Autowired
    ServicioIndividualRepository servicioIndividualRepository;

    public void saveServico(ServicioIndividual servicioIndividual) {
        servicioIndividualRepository.save(servicioIndividual);
    }

    public List<ServicioIndividual> findAll() {
        return servicioIndividualRepository.findAll();
    }
}
