//Autor: Rosa Esmeralda Flores Harrison
//Description: define una entidad de base de datos llamada Asistente, que representa a un asistente en una veterinaria
package eq.poo2496.veterinaria.entity;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data // Anotación de Lombok para generar getters, setters, equals, hashCode y toString
@Entity // Anotación de Jakarta Persistence para marcar la clase como una entidad de base de datos
@Table(name = "tAsistente") // Especifica el nombre de la tabla de la base de datos
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true) // Genera métodos equals y hashCode utilizando la superclase y solo los campos explícitamente incluidos
@AttributeOverride(name = "id", column = @Column(name = "numeroAsistente")) // Sobrescribe la columna 'id' heredada de la superclase 'Persona' con el nombre 'numeroAsistente'
public class Asistente extends Persona{ // Define la clase Asistente, que hereda de la clase Persona
}
