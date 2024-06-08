package eq.poo2496.veterinaria.repository;

import eq.poo2496.veterinaria.entity.Gerente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GerenteRepository extends JpaRepository<Gerente, Long> {

    List<Gerente> findByNombre(String nombre);

    List<Gerente> findByApellidoPaterno(String ap);

    List<Gerente> findByApellidoMaterno(String am);

    List<Gerente> findByCurp(String curp);
}
