/*Author: Larios Ponce Hector
Description:  proporciona una estructura de datos para representar a las personas que interactúan con el sistema de
gestión de la veterinaria, con la capacidad de persistir esta información en una base de datos relacional.*/

package eq.poo2496.veterinaria.entity;
import jakarta.persistence.*; // Importaciones de JPA (Java Persistence API)
import lombok.Data; // Importación de Lombok para la generación de métodos getter, setter, equals, hashCode y
                    // toString automáticamente
import java.util.Date; // Importación de la clase Date del paquete java.util

@Data // Lombok annotation to generate getters and setters
//@Entity // JPA annotation to specify that this class is an entity
@MappedSuperclass // JPA annotation to specify that this class is a superclass
//@Table(name = "tPersona") // JPA annotation to specify the table name
public class Persona {

    @Id // Indica que este atributo es la clave primaria de la entidad
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Estrategia de generación de valor para la clave primaria
    private Long id; // Identificador único de la persona

    @Column(name = "nombre", length = 40, nullable = false)  // Mapeo del atributo "nombre" a una columna en la tabla
    private String nombre; // Nombre de la persona

    @Column(name = "apellidoPaterno", length = 40, nullable = false) // Mapeo del atributo "apellidoPaterno" a una
                                                                     // columna en la tabla
    private String apellidoPaterno; // Apellido paterno de la persona

    @Column(name = "apeellidoMaterno", length = 40) // Mapeo del atributo "apellidoMaterno" a una columna en la tabla
    private String apellidoMaterno; // Apellido materno de la persona

    @Temporal(TemporalType.DATE) // Tipo de temporalidad para la fecha de nacimiento
    @Column(name = "fechaNacimiento", nullable = false) // Mapeo del atributo "fechaNacimiento" a una columna en la tabla
    private Date fechaNacimiento; // Fecha de nacimiento de la persona

    @Column(name = "curp", length = 18, unique = true, nullable = false) // Mapeo del atributo "curp" a una columna en
                                                                         // la tabla, indicando que debe ser único
    private String curp; // Clave Única de Registro de Población (CURP) de la persona
}
