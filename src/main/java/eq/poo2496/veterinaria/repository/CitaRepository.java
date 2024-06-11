package eq.poo2496.veterinaria.repository;

import eq.poo2496.veterinaria.entity.Asistente;
import eq.poo2496.veterinaria.entity.Cita;
import eq.poo2496.veterinaria.entity.Cliente;
import eq.poo2496.veterinaria.entity.Veterinario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Date;

@Repository
public interface CitaRepository extends JpaRepository<Cita, Long> {
    boolean existsByClienteAndFechaHora(Cliente cliente, LocalDateTime fechaHora);

    boolean existsByVeterinarioAndFechaHora(Veterinario veterinario, LocalDateTime fechaHora);

    boolean existsByAsistenteAndFechaHora(Asistente asistente, LocalDateTime fechaHora);
}
