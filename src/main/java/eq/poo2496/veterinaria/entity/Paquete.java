/*Author: Larios Ponce Hector
Description: , este código proporciona una estructura de datos para representar los paquetes de servicios ofrecidos por
una veterinaria, con la capacidad de persistir esta información en una base de datos relacional.*/
package eq.poo2496.veterinaria.entity;

import jakarta.persistence.*; // Importaciones de JPA (Java Persistence API)
import lombok.Data; // Importación de Lombok para la generación de métodos getter, setter, equals, hashCode y toString
                    // automáticamente
import java.util.List; // Importación de la clase List del paquete java.util

@Data // Anotación de Lombok para la generación de métodos getter, setter, equals, hashCode y toString automáticamente
@Entity // Anotación para indicar que esta clase es una entidad JPA
@Table(name = "tPaquete") // Nombre de la tabla en la base de datos
public class Paquete {

    @Id // Indica que este atributo es la clave primaria de la entidad
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Estrategia de generación de valor para la clave primaria
    private Long idPaquete; // Identificador único del paquete

    @Column(name = "nombrePaquete", nullable = false) // Mapeo del atributo "nombre" a una columna en la tabla "tPaquete"
    private String nombre; // Nombre del paquete

    @ElementCollection // Indica que el atributo "servicios" es una colección de elementos
    @CollectionTable(name = "tServicios", joinColumns = @JoinColumn(name = "idPaquete")) // Define la tabla intermedia
                                                                                        // para la relación
    @Column(name = "servicio") // Mapeo del atributo "servicios" a una columna en la tabla "tServicios"
    private List<String> servicios; // Lista de servicios incluidos en el paquete

    @Column(name = "precio", nullable = false) // Mapeo del atributo "precio" a una columna en la tabla "tPaquete"
    private double precio; // Precio del paquete

}