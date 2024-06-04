package eq.poo2496.veterinaria.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Data
@Entity
@Table(name = "tPaquete")
public class Paquete {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPaquete;

    @Column(name = "nombrePaquete", nullable = false)
    private String nombre;

    @ElementCollection
    @CollectionTable(name = "tServicios", joinColumns = @JoinColumn(name = "idPaquete"))
    @Column(name = "servicio")
    private List<String> servicios;

    @Column(name = "precio", nullable = false)
    private double precio;

}