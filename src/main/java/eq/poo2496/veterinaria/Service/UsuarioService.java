package eq.poo2496.veterinaria.Service;

import eq.poo2496.veterinaria.entity.Usuario;
import eq.poo2496.veterinaria.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {
    @Autowired
    UsuarioRepository usuarioRepository;

    public List<Usuario> getUsuarios(){
        return usuarioRepository.findAll();
    }
    public Optional<Usuario> getUsuario(Long id){
        return usuarioRepository.findById(id);
    }
    public void saveUpdate(Usuario usuario){
        usuarioRepository.save(usuario);
    }
    public void deleteUsuario(Long id){
        usuarioRepository.deleteById(id);
    }
}
