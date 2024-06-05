/*Author: Larios Ponce Hector
Description: Este código define la entidad Veterinario, que representa a un veterinario y hereda de la clase Persona.
La tabla en la base de datos se llamará "tVeterinario"  */

package eq.poo2496.veterinaria.entity;

import jakarta.persistence.AttributeOverride; // Importa la anotación AttributeOverride de JPA
import jakarta.persistence.Column; // Importa la anotación Column de JPA
import jakarta.persistence.Entity; // Importa la anotación Entity de JPA
import jakarta.persistence.Table; // Importa la anotación Table de JPA
import lombok.Data; // Importa la anotación @Data de Lombok
import lombok.EqualsAndHashCode; // Importa la anotación @EqualsAndHashCode de Lombok

@Data // Anotación de Lombok para generar automáticamente getters, setters, toString, equals y hashCode
@Entity // Indica que esta clase es una entidad JPA
@Table(name = "tVeterinario") // Especifica el nombre de la tabla en la base de datos
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true) // Anotación de Lombok para generar automáticamente
                                                                    // el método equals y hashCode, llamando al de la
                                                                    // clase padre y solo incluyendo los campos explícitamente marcados
@AttributeOverride(name = "id", column = @Column(name = "numeroVeterinario")) // Anotación de JPA para cambiar la
                                                                              // definición de la columna "id" heredada
                                                                              // de la clase padre Persona
public class Veterinario extends Persona { // Clase que representa un veterinario y hereda de Persona

    @Column(name = "cedula", length = 10, nullable = false, unique = true) // Especifica la columna correspondiente a la
                                                                           // cédula del veterinario
    private String cedula; // Número de cédula del veterinario
}
