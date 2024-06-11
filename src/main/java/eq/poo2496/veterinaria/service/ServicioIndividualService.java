package eq.poo2496.veterinaria.service;

import eq.poo2496.veterinaria.entity.ServicioIndividual;
import eq.poo2496.veterinaria.repository.ServicioIndividualRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public Optional<ServicioIndividual> getById(long id) {
        return servicioIndividualRepository.findById(id);
    }
}
