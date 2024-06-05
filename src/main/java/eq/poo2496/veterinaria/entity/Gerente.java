/*Author: Larios Ponce Hector
Description: Este código define la entidad Gerente en una aplicación de veterinaria. La clase Gerente hereda de la clase
Persona, lo que significa que tiene todos los atributos y métodos de una persona además de los específicos de un gerente.*/
package eq.poo2496.veterinaria.entity;

// Importaciones necesarias para JPA y Lombok
import eq.poo2496.veterinaria.enums.Sucursal;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data // La anotación @Data de Lombok genera automáticamente getters, setters, toString, equals y hashCode para la clase
@Entity // Indica que esta clase es una entidad de JPA y se mapeará a una tabla en la base de datos
@Table(name = "tGerente") // Define el nombre de la tabla en la base de datos

// Lombok genera un método equals y hashCode basado solo en los campos explícitamente incluidos.
// En este caso, solo se incluirá el campo 'id' de la clase base Persona.
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
// Permite realizar un override en la definición del atributo 'id' heredado de la clase Persona
@AttributeOverride(name = "id", column = @Column(name = "numeroGerente"))
public class Gerente extends Persona {

    // Define una columna llamada 'sucursal' en la tabla tGerente.
    // El valor de la columna no puede ser nulo y debe ser único en la tabla.
    @Column(name = "sucursal", nullable = false, unique = true)
    private Sucursal sucursal;
}

