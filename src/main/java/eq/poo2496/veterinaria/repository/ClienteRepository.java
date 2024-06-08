package eq.poo2496.veterinaria.repository;

import eq.poo2496.veterinaria.entity.Cliente;
import eq.poo2496.veterinaria.entity.Mascota;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    Cliente findByMascota(Mascota mascota);
    List<Cliente> findAll();
    @Override
    Optional<Cliente> findById(Long id);

    @Query("SELECT c FROM Cliente c WHERE c.mascota IS NULL")
    List<Cliente> getByMascota();

    @Query("SELECT c FROM Cliente c WHERE c.id = :id AND c.mascota IS NULL")
    Optional<Cliente> findByIdWhereMascotaIsNull(@Param("id") Long id);
    @Transactional
    @Modifying
    @Query("UPDATE Cliente c SET c.mascota = :mascota WHERE c.id = :id")
    void updateMascotaById(@Param("id") Long id, @Param("mascota") Mascota mascota);

    @Transactional
    @Modifying
    @Query("UPDATE Cliente c SET c.mascota = NULL WHERE c.id = :id")
    void deleteMascotaById(@Param("id") Long id);

    boolean existsByCurp(String curp);
}
