package eq.poo2496.veterinaria.service;

import eq.poo2496.veterinaria.entity.Asistente;
import eq.poo2496.veterinaria.repository.AsistenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AsistenteService {
    @Autowired
    public AsistenteRepository asistenteRepository;

    public Asistente persistAsistente(Asistente asistente) {
        return asistenteRepository.save(asistente);
    }


}
