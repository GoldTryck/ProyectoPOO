package eq.poo2496.veterinaria.Service;

import eq.poo2496.veterinaria.entity.Persona;
import eq.poo2496.veterinaria.repository.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonaService {
    @Autowired
    PersonaRepository personaRepository;

    public List<Persona> getPersonas(){
        return personaRepository.findAll();
    }
    public Optional<Persona> getPersona(Long id){
        return personaRepository.findById(id);
    }
    public void saveUpdate(Persona persona){
        personaRepository.save(persona);
    }
    public void deletePersona(Long id){
        personaRepository.deleteById(id);
    }

}
