package eq.poo2496.veterinaria.repository;

import eq.poo2496.veterinaria.entity.Asistente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AsistenteRepository extends JpaRepository<Asistente, Long> {

    List<Asistente> findByNombre(String nombre);

    List<Asistente> findByApellidoPaterno(String ap);

    List<Asistente> findByApellidoMaterno(String am);

    List<Asistente> findByCurp(String curp);
}
