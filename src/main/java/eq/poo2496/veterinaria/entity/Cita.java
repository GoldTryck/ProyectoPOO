package eq.poo2496.veterinaria.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Table(name = "tCita")
@Entity
public class Cita {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long numeroCita;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false, unique = true)
    private Date fechaHora;

    @ManyToOne(optional = false)
    @JoinColumn(name = "numeroCliente")
    private Cliente cliente;

    @ManyToOne(optional = false)
    @JoinColumn(name = "numeroMascota")
    private Mascota mascota;

    @ManyToOne
    @JoinColumn(name = "numeroVeterinario")
    private Veterinario veterinario;

    @ManyToOne
    @JoinColumn(name = "numeroAsistente")
    private Asistente asistente;

    @Column(nullable = false)
    private String descripcionServicio;

    @ManyToMany
    @JoinTable(
            name = "citaPaquete",
            joinColumns = @JoinColumn(name = "numeroCita"),
            inverseJoinColumns = @JoinColumn(name = "idPaquete")
    )
    private List<Paquete> paquetes;
}