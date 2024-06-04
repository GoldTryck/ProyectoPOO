package eq.poo2496.veterinaria.repository;

import eq.poo2496.veterinaria.entity.Mascota;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MascotaRepository extends JpaRepository<Mascota, Long> {
    Mascota findByNumeroMascota(Long numeroMascota);
    List<Mascota> findByStatus(String status);
    @Transactional
    @Modifying
    @Query("UPDATE Mascota m SET m.status = :status WHERE m.numeroMascota = :numeroMascota")
    int updateStatusByNumeroMascota(Long numeroMascota, String status);
}
