package eq.poo2496.veterinaria.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "tCita")
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

    @ManyToOne
    @JoinColumn(name = "numeroVeterinario")
    private Veterinario veterinario;

    @ManyToOne
    @JoinColumn(name = "numeroAsistente")
    private Asistente asistente;

    @Column(name = "descripcionServicio", length = 100)
    private String descripcionServicio;

    @ManyToMany
    @JoinTable(
            name = "citaServicio",
            joinColumns = @JoinColumn(name = "idCita"),
            inverseJoinColumns = @JoinColumn(name = "idServicio")
    )
    private List<Servicio> servicios;
}