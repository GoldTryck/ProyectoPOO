package eq.poo2496.veterinaria.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Data
@Entity
@Table(name = "tMascota")
public class Mascota {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long numeroMascota;

    @Column(name = "nombre", length = 40, nullable = false)
    private String nombre;

    @Column(name = "raza", length = 40, nullable = false)
    private String raza;

    @Column(name = "status", nullable = false,
            columnDefinition = "VARCHAR(11) CHECK (status IN ('CLIENTE', 'ADOPCION', 'DEVOLUCION', 'ADOPTADO'))")
    private String status;

    @ElementCollection
    @CollectionTable(name = "tVacunas", joinColumns = @JoinColumn(name = "numeroMascota"))
    @Column(name = "vacuna")
    private List<String> vacunasAplicadas;
}
