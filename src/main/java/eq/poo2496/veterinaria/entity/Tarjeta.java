package eq.poo2496.veterinaria.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "tTarjeta")
public class Tarjeta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTarjeta;

    @Column(name = "numeroTarjeta", nullable = false, unique = true)
    private Long numeroTarjeta;

    @Temporal(TemporalType.DATE)
    @Column(name = "fechaVencimiento", nullable = false)
    private Date fechaVencimiento;

    @Column(name = "cvc", nullable = false)
    private short cvc;
}
