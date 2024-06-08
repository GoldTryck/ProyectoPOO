package eq.poo2496.veterinaria.service;

import eq.poo2496.veterinaria.entity.Gerente;
import eq.poo2496.veterinaria.repository.GerenteRepository;
import javafx.fxml.FXML;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GerenteService {
    @Autowired
    private GerenteRepository gerenteRepository;

    public Gerente persistGerente(Gerente gerente) {
        return gerenteRepository.save(gerente);
    }

    public List<Gerente> filterByNombre(String nombre) {
        return gerenteRepository.findByNombre(nombre);
    }

    public List<Gerente> filterByAp(String ap) {
        return gerenteRepository.findByApellidoPaterno(ap);
    }

    public List<Gerente> filterByAm(String am) {
        return gerenteRepository.findByApellidoMaterno(am);
    }

    public List<Gerente> filterByCurp(String curp) {
        return gerenteRepository.findByCurp(curp);
    }

    public void deleteGerente(Gerente gerente) {
        gerenteRepository.delete(gerente);
    }
}
