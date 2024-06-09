package eq.poo2496.veterinaria.repository;

import eq.poo2496.veterinaria.entity.Paquete;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaqueteRepository extends JpaRepository<Paquete, Long> {
    @Query("SELECT p FROM Paquete p JOIN FETCH p.servicios")
    List<Paquete> findAllWithServicios();
}
