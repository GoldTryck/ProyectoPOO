package eq.poo2496.veterinaria.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(name = "tCliente")
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@AttributeOverride(name = "id", column = @Column(name = "numeroCliente"))
public class Cliente extends Persona {


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "numeroMascota", referencedColumnName = "numeroMascota", unique = true, nullable = true)
    private Mascota mascota;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idTarjeta", referencedColumnName = "idTarjeta")
    private Tarjeta tarjeta;
}
