/*Author: Larios Ponce Hector
Description: Este código Java define una entidad llamada Mascota que representa a las mascotas en una veterinaria.
La entidad Mascota está mapeada para ser persistida en una base de datos relacional utilizando JPA (Java Persistence
API).*/
package eq.poo2496.veterinaria.entity;

import jakarta.persistence.*; // Importaciones de JPA (Java Persistence API)
import lombok.Data; // Importación de Lombok para la generación de métodos getter, setter, equals, hashCode y toString
                    // automáticamente
import java.util.List; // Importación de la clase List del paquete java.util

@Data // Anotación de Lombok para la generación de métodos getter, setter, equals, hashCode y toString automáticamente
@Entity // Anotación para indicar que esta clase es una entidad JPA
@Table(name = "tMascota") // Nombre de la tabla en la base de datos
public class Mascota {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long numeroMascota; // Indica que este atributo es la clave primaria de la entidad

    @Column(name = "nombre", length = 40, nullable = false)
    private String nombre; // Nombre de la mascota

    @Column(name = "raza", length = 40, nullable = false)
    private String raza; // Raza de la mascota

    @Column(name = "status", nullable = false,
            columnDefinition = "VARCHAR(11) CHECK (status IN ('CLIENTE', 'ADOPCION', 'DEVOLUCION', 'ADOPTADO'))")
    private String status; // Estado de la mascota (CLIENTE, ADOPCION, DEVOLUCION, ADOPTADO)

    @ElementCollection
    @CollectionTable(name = "tVacunas", joinColumns = @JoinColumn(name = "numeroMascota"))
    @Column(name = "vacuna")
    private List<String> vacunasAplicadas; // Indica que el atributo "vacunasAplicadas" es una colección de elementos
}
