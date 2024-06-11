package eq.poo2496.veterinaria.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "tCita")
public class Cita {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long numeroCita;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private LocalDateTime fechaHora;

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
            name = "citaServicioIndividual",
            joinColumns = @JoinColumn(name = "idCita"),
            inverseJoinColumns = @JoinColumn(name = "idServicioIndividual")
    )
    private List<ServicioIndividual> serviciosIndividuales = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "citaPaquete",
            joinColumns = @JoinColumn(name = "idCita"),
            inverseJoinColumns = @JoinColumn(name = "idPaquete")
    )
    private List<Paquete> paquetes = new ArrayList<>();

    @Column(name = "precioTotal")
    private double precioTotal;

    @Transient
    public List<Servicio> getAllServicios() {
        List<Servicio> allServicios = new ArrayList<>();
        allServicios.addAll(serviciosIndividuales);
        allServicios.addAll(paquetes);
        return allServicios;
    }
}