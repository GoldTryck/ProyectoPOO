package eq.poo2496.veterinaria.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "tUsuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUsuario;

    @Column(name = "nombre", nullable = false, length = 40)
    private String nombre;

    @Column(name = "primerApellido", nullable = false, length = 40)
    private String primerApellido;

    @Column(name = "segundoApellido", nullable = true, columnDefinition = "VARCHAR(40) DEFAULT NULL")
    private String segundoApellido;
}
