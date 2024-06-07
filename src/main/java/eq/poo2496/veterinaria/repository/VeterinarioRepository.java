package eq.poo2496.veterinaria.repository;

import eq.poo2496.veterinaria.entity.Veterinario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VeterinarioRepository extends JpaRepository<Veterinario, Long>{
}
