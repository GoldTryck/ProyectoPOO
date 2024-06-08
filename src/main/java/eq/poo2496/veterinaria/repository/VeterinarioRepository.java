package eq.poo2496.veterinaria.repository;

import eq.poo2496.veterinaria.entity.Veterinario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VeterinarioRepository extends JpaRepository<Veterinario, Long>{
    List<Veterinario> findByNombre(String nombre);

    List<Veterinario> findByApellidoPaterno(String ap);

    List<Veterinario> findByApellidoMaterno(String am);

    List<Veterinario> findByCurp(String curp);
}
