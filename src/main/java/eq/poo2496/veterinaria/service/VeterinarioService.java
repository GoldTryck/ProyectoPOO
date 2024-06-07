package eq.poo2496.veterinaria.service;

import eq.poo2496.veterinaria.entity.Veterinario;
import eq.poo2496.veterinaria.repository.VeterinarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VeterinarioService  {
    @Autowired
    VeterinarioRepository veterinarioRepository;

    public Veterinario persistVeterinario(Veterinario veterinario) {
        return veterinarioRepository.save(veterinario);
    }
}
