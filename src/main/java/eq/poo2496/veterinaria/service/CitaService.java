package eq.poo2496.veterinaria.service;

import eq.poo2496.veterinaria.entity.*;
import eq.poo2496.veterinaria.repository.CitaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CitaService {
    @Autowired
    private CitaRepository citaRepository;

    public Cita validateAndSaveCita(Cita cita) {
        // Verificar conflictos de horario
        boolean clienteConflict = citaRepository.existsByClienteAndFechaHora(cita.getCliente(), cita.getFechaHora());
        boolean veterinarioConflict = cita.getVeterinario() != null && citaRepository.existsByVeterinarioAndFechaHora(cita.getVeterinario(), cita.getFechaHora());
        boolean asistenteConflict = cita.getAsistente() != null && citaRepository.existsByAsistenteAndFechaHora(cita.getAsistente(), cita.getFechaHora());

        if (clienteConflict) {
            throw new RuntimeException("El cliente ya tiene una cita programada en esa fecha y hora.");
        }
        if (veterinarioConflict) {
            throw new RuntimeException("El veterinario ya tiene una cita programada en esa fecha y hora.");
        }
        if (asistenteConflict) {
            throw new RuntimeException("El asistente ya tiene una cita programada en esa fecha y hora.");
        }
        calcularPrecioTotal(cita);
        // Guardar la cita si no hay conflictos
        return citaRepository.save(cita);
    }

    private void calcularPrecioTotal(Cita cita) {
        double precioTotal = 0;
        for (ServicioIndividual servicio : cita.getServiciosIndividuales()) {
            precioTotal += servicio.getPrecio();
        }
        for (Paquete paquete : cita.getPaquetes()) {
            precioTotal += paquete.getPrecio();
        }
        cita.setPrecioTotal(precioTotal);
    }


    public List<Cita> getAll() {
        return citaRepository.findAll();
    }
}

