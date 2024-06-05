/*Author: Larios Ponce Hector
Description: Este código define la entidad Tarjeta que se utilizará para mapear objetos de tarjeta a la base de datos.
La tabla en la base de datos se llamará "tTarjeta"*/
package eq.poo2496.veterinaria.entity;

import jakarta.persistence.*; // Importa las anotaciones de JPA
import lombok.Data; // Importa la anotación @Data de Lombok

import java.util.Date; // Importa la clase Date de Java

@Data // Anotación de Lombok para generar automáticamente getters, setters, toString, equals y hashCode
@Entity // Indica que esta clase es una entidad JPA
@Table(name = "tTarjeta") // Especifica el nombre de la tabla en la base de datos
public class Tarjeta {
    @Id // Indica que este campo es la clave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Indica que el valor de esta clave primaria se generará automáticamente
    private Long idTarjeta; // Identificador único de la tarjeta

    @Column(name = "numeroTarjeta", nullable = false, unique = true) // Especifica que esta propiedad está mapeada a una
                                                                     // columna en la base de datos
    private Long numeroTarjeta; // Número único de la tarjeta

    @Temporal(TemporalType.DATE) // Indica que solo se almacenará la fecha (sin la hora)
    @Column(name = "fechaVencimiento", nullable = false) // Especifica la columna correspondiente a la fecha de vencimiento
    private Date fechaVencimiento; // Fecha de vencimiento de la tarjeta

    @Column(name = "cvc", nullable = false) // Especifica la columna correspondiente al código de seguridad (CVC)
    private short cvc; // Código de seguridad de la tarjeta
}
