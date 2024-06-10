/*Autor: Julio Cesar Medrano Reyes
Descripcion:
 */
package eq.poo2496.veterinaria.entity;

import jakarta.persistence.*; // Importa las anotaciones de Jakarta Persistence
import lombok.Data; // Importa la anotación Data de Lombok para generar getters, setters, equals, hashCode y toString
import java.util.List; // Importa la interfaz List de Java para manejar colecciones de elementos

@Data // Anotación de Lombok para generar getters, setters, equals, hashCode y toString
@Entity // Anotación de Jakarta Persistence para marcar la clase como una entidad de base de datos
@Table(name = "tPaquete") // Especifica el nombre de la tabla de la base de datos
public class Paquete { // Define la clase Paquete, que representa un paquete de servicios en la veterinaria

    @Id // Marca el campo como la clave primaria de la tabla
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Genera automáticamente valores para la clave primaria
    private Long idPaquete; // Número único que identifica al paquete

    @Column(name = "nombrePaquete", nullable = false)
    private String nombre; //Nombre del paquete

    @ElementCollection // Indica que la propiedad es una colección de elementos
    @CollectionTable(name = "tServicios", joinColumns = @JoinColumn(name = "idPaquete")) // Especifica los detalles de la tabla para la colección
    @Column(name = "servicio") // Especifica las propiedades de la columna en la base de datos
    private List<String> servicios; // Lista de servicios incluidos en el paquete

    @Column(name = "precio", nullable = false) // Especifica las propiedades de la columna en la base de datos
    private double precio; // Precio del paquete

}