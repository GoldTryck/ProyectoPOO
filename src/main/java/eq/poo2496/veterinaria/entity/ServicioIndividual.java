package eq.poo2496.veterinaria.entity;

import lombok.Data;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Data
@Entity
@Table(name = "tServicioIndividual")
public class ServicioIndividual extends Servicio {

}
