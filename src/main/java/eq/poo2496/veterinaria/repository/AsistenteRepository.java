package eq.poo2496.veterinaria.repository;

import eq.poo2496.veterinaria.entity.Asistente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AsistenteRepository extends JpaRepository<Asistente, Long> {
}
