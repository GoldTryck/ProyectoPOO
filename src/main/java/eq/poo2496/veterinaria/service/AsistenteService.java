package eq.poo2496.veterinaria.service;

import eq.poo2496.veterinaria.entity.Asistente;
import eq.poo2496.veterinaria.repository.AsistenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AsistenteService {
    @Autowired
    public AsistenteRepository asistenteRepository;

    public Asistente persistAsistente(Asistente asistente) {
        return asistenteRepository.save(asistente);
    }


    public List<Asistente> filterByNombre(String nombre) {
        return asistenteRepository.findByNombre(nombre);
    }

    public List<Asistente> filterByAp(String ap) {
        return asistenteRepository.findByApellidoPaterno(ap);
    }

    public List<Asistente> filterByAm(String am) {
        return asistenteRepository.findByApellidoMaterno(am);
    }

    public List<Asistente> filterByCurp(String curp) {
        return asistenteRepository.findByCurp(curp);
    }

    public void deleteAsistente(Asistente asistente) {
        asistenteRepository.delete(asistente);
    }
}
