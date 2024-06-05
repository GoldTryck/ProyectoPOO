/*Author: Larios Ponce Hector
Description: define una clase llamada Cita que representa una cita en una veterinaria. Utiliza anotaciones de JPA para
mapear la clase a una tabla en la base de datos y Lombok para generar métodos getter, setter, equals y hashCode de forma
automática. Define varios campos para almacenar información sobre la cita, como la fecha y hora, el cliente, la mascota,
el veterinario, el asistente, la descripción del servicio y los paquetes asociados a la cita.*/

package eq.poo2496.veterinaria.entity;

// Importaciones necesarias para las anotaciones de JPA y Lombok
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data // Anotación de Lombok para generar automáticamente los métodos getter, setter, equals, hashCode, y toString
@Table(name = "tCita") // Especificación del nombre de la tabla en la base de datos
@Entity // Anotación para indicar que esta clase es una entidad JPA
public class Cita { // Definición de la clase Cita
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long numeroCita; // Identificador de la cita, generado automáticamente

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false, unique = true)
    private Date fechaHora; // Fecha y hora de la cita

    @ManyToOne(optional = false)
    @JoinColumn(name = "numeroCliente")
    private Cliente cliente; // Cliente asociado a la cita

    @ManyToOne(optional = false)
    @JoinColumn(name = "numeroMascota")
    private Mascota mascota; // Mascota asociada a la cita

    @ManyToOne
    @JoinColumn(name = "numeroVeterinario")
    private Veterinario veterinario; // Veterinario asociado a la cita

    @ManyToOne
    @JoinColumn(name = "numeroAsistente")
    private Asistente asistente; // Asistente asociado a la cita

    @Column(nullable = false)
    private String descripcionServicio; // Descripción del servicio de la cita

    @ManyToMany
    @JoinTable(
            name = "citaPaquete",
            joinColumns = @JoinColumn(name = "numeroCita"),
            inverseJoinColumns = @JoinColumn(name = "idPaquete")
    )
    private List<Paquete> paquetes;// Lista de paquetes asociados a la cita
}