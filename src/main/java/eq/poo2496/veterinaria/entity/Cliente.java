/*Author: Larios Ponce Hector
Description: Este código define la entidad Cliente en una aplicación de veterinaria. La clase Cliente hereda de la clase
Persona, lo que significa que tiene todos los atributos y métodos de una persona además de los específicos de un cliente.
Esta clase mapea a la tabla "tCliente" en la base de datos.*/
package eq.poo2496.veterinaria.entity;

// Importaciones necesarias para JPA y Lombok
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data // La anotación @Data de Lombok genera automáticamente getters, setters, toString, equals y hashCode para la clase
@Entity // Indica que esta clase es una entidad de JPA y se mapeará a una tabla en la base de datos
@Table(name = "tCliente") // Define el nombre de la tabla en la base de datos

// Lombok genera un método equals y hashCode basado solo en los campos explícitamente incluidos.
// En este caso, solo se incluirá el campo 'id' de la clase base Persona.
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)

// Permite realizar un override en la definición del atributo 'id' heredado de la clase Persona
@AttributeOverride(name = "id", column = @Column(name = "numeroCliente"))
public class Cliente extends Persona {

    // Relación uno a uno con la entidad Mascota, con una cascada de operaciones que se aplican a la mascota
    @OneToOne(cascade = CascadeType.ALL)
    // Define la columna que actuará como clave foránea en la tabla cliente para la relación con la mascota
    @JoinColumn(name = "numeroMascota", referencedColumnName = "numeroMascota", unique = true, nullable = false)
    private Mascota mascota;

    // Relación uno a uno con la entidad Tarjeta, con una cascada de operaciones que se aplican a la tarjeta
    @OneToOne(cascade = CascadeType.ALL)
    // Define la columna que actuará como clave foránea en la tabla cliente para la relación con la tarjeta
    @JoinColumn(name = "idTarjeta", referencedColumnName = "idTarjeta")
    private Tarjeta tarjeta;
}
