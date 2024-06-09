package eq.poo2496.veterinaria.repository;

import eq.poo2496.veterinaria.entity.ServicioIndividual;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServicioIndividualRepository extends JpaRepository<ServicioIndividual, Long>{
}
