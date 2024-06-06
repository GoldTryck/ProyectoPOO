/*Autor: Julio Cesar Medrano Reyes
Descripcion: Este código define una entidad de base de datos llamada Mascota, que representa una mascota en la
veterinaria. La tabla correspondiente en la base de datos se llama tMascota. La entidad contiene campos como número de
mascota, nombre, raza, estado y vacunas aplicadas a la mascota. Utiliza diversas anotaciones de Jakarta
* */
package eq.poo2496.veterinaria.entity;

import jakarta.persistence.*; // Importa las anotaciones de Jakarta Persistence
import lombok.Data; // Importa la anotación Data de Lombok para generar getters, setters, equals, hashCode y toString
import java.util.List; // Importa la interfaz List de Java para manejar colecciones de elementos

@Data // Anotación de Lombok para generar getters, setters, equals, hashCode y toString
@Entity // Anotación de Jakarta Persistence para marcar la clase como una entidad de base de datos
@Table(name = "tMascota") // Especifica el nombre de la tabla de la base de datos
public class Mascota { // Define la clase Mascota, que representa una mascota en la veterinaria

    @Id // Marca el campo como la clave primaria de la tabla
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Genera automáticamente valores para la clave primaria
    private Long numeroMascota; // Número único que identifica a la mascota

    @Column(name = "nombre", length = 40, nullable = false) // Especifica las propiedades de la columna en la base de datos
    private String nombre; // Nombre de la mascota

    @Column(name = "raza", length = 40, nullable = false) // Especifica las propiedades de la columna en la base de datos
    private String raza; // Raza de la mascota

    @Column(name = "status", nullable = false, // Especifica las propiedades de la columna en la base de datos
            columnDefinition = "VARCHAR(11) CHECK (status IN ('CLIENTE', 'ADOPCION', 'DEVOLUCION', 'ADOPTADO'))") // Define una restricción de columna
    private String status; // Estado de la mascota

    @ElementCollection // Indica que la propiedad es una colección de elementos
    @CollectionTable(name = "tVacunas", joinColumns = @JoinColumn(name = "numeroMascota")) // Especifica los detalles de la tabla para la colección
    @Column(name = "vacuna") // Especifica las propiedades de la columna en la base de datos
    private List<String> vacunasAplicadas; // Lista de vacunas aplicadas a la mascota
}
