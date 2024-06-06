//Author: Rosa Esmeralda Flores Harrison
//Description:  entidad contiene campos como número de cita, fecha y hora, cliente, mascota, veterinario, asistente, descripción de servicio y paquetes asociados a la cita
package eq.poo2496.veterinaria.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data // Anotación de Lombok para generar getters, setters, equals, hashCode y toString
@Table(name = "tCita")  // Especifica el nombre de la tabla de la base de datos
@Entity // Anotación de Jakarta Persistence para marcar la clase como una entidad de base de datos
public class Cita { // Marca el campo como la clave primaria de la tabla
    @Id // Marca el campo como la clave primaria de la tabla
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Genera automáticamente valores para la clave primaria
    private long numeroCita; // Número único que identifica la cita

    @Temporal(TemporalType.TIMESTAMP) // Especifica el tipo de temporalidad para el campo de fecha y hora
    @Column(nullable = false, unique = true) // Especifica las propiedades de la columna en la base de datos
    private Date fechaHora; // Fecha y hora de la cita

    @ManyToOne(optional = false) // Establece la relación muchos a uno con la entidad Cliente
    @JoinColumn(name = "numeroCliente") // Especifica el nombre de la columna que actúa como clave foránea
    private Cliente cliente; // Cliente asociado a la cita

    @ManyToOne(optional = false) // Establece la relación muchos a uno con la entidad Mascota
    @JoinColumn(name = "numeroMascota") // Especifica el nombre de la columna que actúa como clave foránea
    private Mascota mascota; // Mascota asociada a la cita

    @ManyToOne
    @JoinColumn(name = "numeroVeterinario") // Especifica el nombre de la columna que actúa como clave foránea
    private Veterinario veterinario; // Veterinario asociado a la cita

    @ManyToOne
    @JoinColumn(name = "numeroAsistente") // Especifica el nombre de la columna que actúa como clave foránea
    private Asistente asistente; // Asistente asociado a la cita

    @Column(nullable = false) // Especifica las propiedades de la columna en la base de datos
    private String descripcionServicio; // Descripción del servicio de la cita

    @ManyToMany
    @JoinTable(
            name = "citaPaquete",
            joinColumns = @JoinColumn(name = "numeroCita"), // Nombre de la columna que hace referencia a esta entidad
            inverseJoinColumns = @JoinColumn(name = "idPaquete")    // Nombre de la columna que hace referencia a la entidad Paquete
    )
    private List<Paquete> paquetes; // Lista de paquetes asociados a la cita
}