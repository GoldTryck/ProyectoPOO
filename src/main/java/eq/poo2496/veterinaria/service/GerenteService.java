package eq.poo2496.veterinaria.service;

import eq.poo2496.veterinaria.entity.Gerente;
import eq.poo2496.veterinaria.repository.GerenteRepository;
import javafx.fxml.FXML;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GerenteService {
    @Autowired
    private GerenteRepository gerenteRepository;

    public Gerente persistGerente(Gerente gerente) {
        return gerenteRepository.save(gerente);
    }
}
