package eq.poo2496.veterinaria.entity;

import eq.poo2496.veterinaria.enums.Sucursal;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(name = "tGerente")
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@AttributeOverride(name = "id", column = @Column(name = "numeroGerente"))
public class Gerente extends Persona {

    @Column(name = "sucursal", nullable = false, unique = true)
    private Sucursal sucursal;
}

