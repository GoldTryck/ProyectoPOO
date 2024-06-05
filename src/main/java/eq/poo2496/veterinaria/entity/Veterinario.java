/*Author: Larios Ponce Hector
Description: */
package eq.poo2496.veterinaria.entity;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(name = "tVeterinario")
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@AttributeOverride(name = "id", column = @Column(name = "numeroVeterinario"))
public class Veterinario extends Persona {

    @Column(name = "cedula", length = 10, nullable = false, unique = true)
    private String cedula;
}
