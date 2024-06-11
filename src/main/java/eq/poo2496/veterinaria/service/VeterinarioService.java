package eq.poo2496.veterinaria.service;

import eq.poo2496.veterinaria.entity.Veterinario;
import eq.poo2496.veterinaria.repository.VeterinarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VeterinarioService  {
    @Autowired
    VeterinarioRepository veterinarioRepository;

    public Veterinario persistVeterinario(Veterinario veterinario) {
        return veterinarioRepository.save(veterinario);
    }

    public List<Veterinario> filterByNombre(String nombre) {
        return veterinarioRepository.findByNombre(nombre);
    }

    public List<Veterinario> filterByAp(String ap){
        return veterinarioRepository.findByApellidoPaterno(ap);
    }

    public List<Veterinario> filterByAm(String am) {
        return veterinarioRepository.findByApellidoMaterno(am);
    }

    public List<Veterinario> filterByCurp(String curp) {
        return veterinarioRepository.findByCurp(curp);
    }

    public void deleteVeterinario(Veterinario veterinario) {
        veterinarioRepository.delete(veterinario);
    }

    public List<Veterinario> getAll() {
        return veterinarioRepository.findAll();
    }

    public Veterinario getById(Long id) {
        return veterinarioRepository.findById(id).orElse(null);
    }
}
