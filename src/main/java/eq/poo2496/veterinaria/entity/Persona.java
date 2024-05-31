package eq.poo2496.veterinaria.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data // Lombok annotation to generate getters and setters
//@Entity // JPA annotation to specify that this class is an entity
@MappedSuperclass // JPA annotation to specify that this class is a superclass
//@Table(name = "tPersona") // JPA annotation to specify the table name
public class Persona {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", length = 40, nullable = false)
    private String nombre;

    @Column(name = "apellidoPaterno", length = 40, nullable = false)
    private String apellidoPaterno;

    @Column(name = "apeellidoMaterno", length = 40)
    private String apellidoMaterno;

    @Temporal(TemporalType.DATE)
    @Column(name = "fechaNacimiento", nullable = false)
    private Date fechaNacimiento;

    @Column(name = "curp", length = 18, unique = true, nullable = false)
    private String curp;
}
