package eq.poo2496.veterinaria.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;


@Data
@Entity
@Table(name = "tPaquete")
public class Paquete extends Servicio {
    @ManyToMany
    @JoinTable(
            name = "tPaqueteServicio",
            joinColumns = @JoinColumn(name = "idPaquete"),
            inverseJoinColumns = @JoinColumn(name = "idServicioIndividual")
    )
    private List<ServicioIndividual> servicios;


    @Transient
    @Override
    public double getPrecio() {
        return servicios.stream().mapToDouble(Servicio::getPrecio).sum();
    }
}