package eq.poo2496.veterinaria.entity;

// Importaciones necesarias para las anotaciones de JPA y Lombok
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data // Anotación de Lombok para generar automáticamente los métodos getter, setter, equals, hashCode, y toString
@Entity // Anotación para indicar que esta clase es una entidad JPA
@Table(name = "tAsistente") // Especificación del nombre de la tabla en la base de datos
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true) // Anotación de Lombok para generar automáticamente
                                                                    // los métodos equals y hashCode
@AttributeOverride(name = "id", column = @Column(name = "numeroAsistente")) // Anotación para especificar que se desea
                                                                            // sobrescribir el atributo de la clase base
public class Asistente extends Persona{ // Definición de la clase Asistente, que extiende de la clase Persona
}
