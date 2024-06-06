//Author: Rosa Esmeralda Flroes Harrison
//Description: La entidad contiene campos como número de cliente, mascota y tarjeta asociada al cliente
package eq.poo2496.veterinaria.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(name = "tCliente")
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true) // Genera métodos equals y hashCode utilizando la superclase y solo los campos explícitamente incluidos
@AttributeOverride(name = "id", column = @Column(name = "numeroCliente")) // Sobrescribe la columna 'id' heredada de la superclase 'Persona' con el nombre 'numeroCliente'
public class Cliente extends Persona {

    @OneToOne(cascade = CascadeType.ALL) // Establece la relación uno a uno con la entidad Mascota
    @JoinColumn(name = "numeroMascota", referencedColumnName = "numeroMascota", unique = true, nullable = false) // Especifica los detalles de la relación y la clave foránea
    private Mascota mascota; // Mascota asociada al cliente

    @OneToOne(cascade = CascadeType.ALL) // Establece la relación uno a uno con la entidad Tarjeta
    @JoinColumn(name = "idTarjeta", referencedColumnName = "idTarjeta") // Especifica los detalles de la relación y la clave foránea
    private Tarjeta tarjeta; // Tarjeta asociada al cliente
}
