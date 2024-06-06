/*Autor: Julio Cesar Medrano Reyes
Descripcion: Este código define una entidad de base de datos llamada Gerente, que representa un gerente en la
veterinaria. La tabla correspondiente en la base de datos se llama tGerente. La entidad contiene campos como número de
gerente y sucursal asociada al gerente. Utiliza diversas anotaciones de Jakarta Persistence para mapear la entidad a la
base de datos y establecer relaciones entre entidades.
* */
package eq.poo2496.veterinaria.entity;

import eq.poo2496.veterinaria.enums.Sucursal; // Importa el enum Sucursal del paquete veterinaria.enums
import jakarta.persistence.AttributeOverride; // Importa la anotación AttributeOverride de Jakarta Persistence
import jakarta.persistence.Column; // Importa la anotación Column de Jakarta Persistence para definir columnas de la base de datos
import jakarta.persistence.Entity; // Importa la anotación Entity de Jakarta Persistence para definir una entidad de base de datos
import jakarta.persistence.Table; // Importa la anotación Table de Jakarta Persistence para especificar la tabla de la base de datos
import lombok.Data; // Importa la anotación Data de Lombok para generar getters, setters, equals, hashCode y toString
import lombok.EqualsAndHashCode; // Importa la anotación EqualsAndHashCode de Lombok para generar métodos equals y hashCode

@Data // Anotación de Lombok para generar getters, setters, equals, hashCode y toString
@Entity // Anotación de Jakarta Persistence para marcar la clase como una entidad de base de datos
@Table(name = "tGerente") // Especifica el nombre de la tabla de la base de datos
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true) // Genera métodos equals y hashCode utilizando la superclase y solo los campos explícitamente incluidos
@AttributeOverride(name = "id", column = @Column(name = "numeroGerente")) // Sobrescribe la columna 'id' heredada de la superclase 'Persona' con el nombre 'numeroGerente'
public class Gerente extends Persona { // Define la clase Gerente, que representa un gerente en la veterinaria

    @Column(name = "sucursal", nullable = false, unique = true) // Especifica las propiedades de la columna en la base de datos
    private Sucursal sucursal; // Sucursal asociada al gerente
}

