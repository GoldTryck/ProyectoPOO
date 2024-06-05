/*Author: Larios Ponce Hector
Description: este código define un servicio para agendar citas en una veterinaria. El servicio se encarga de verificar
la disponibilidad de los veterinarios y asistentes, cargar las entidades necesarias desde la base de datos y finalmente
crear y guardar una nueva cita.*/

package eq.poo2496.veterinaria.service;

// Importaciones necesarias para el funcionamiento del controlador
import eq.poo2496.veterinaria.entity.*;
import eq.poo2496.veterinaria.exceptions.CitaOcupadaException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.Date;
import java.util.List;

public class CitaService {

    // Crear una instancia de EntityManagerFactory usando la unidad de persistencia definida
    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("miUnidadDePersistencia");

    // Método para agendar una cita
    public Cita agendarCita(Date fechaHora, Long numeroCliente, Long numeroMascota,
                            Long numeroVeterinario, Long numeroAsistente, String descripcionServicio,
                            List<Long> idPaquetes) throws CitaOcupadaException {
        EntityManager em = emf.createEntityManager(); // Crear EntityManager para interactuar con la base de datos
        try {
            em.getTransaction().begin(); // Iniciar una transacción

            // Obtener todas las citas en el día y hora especificados
            List<Cita> citasEnHorario = em.createQuery("SELECT c FROM Cita c WHERE c.fechaHora = :fechaHora", Cita.class)
                    .setParameter("fechaHora", fechaHora)
                    .getResultList();

            // Verificar si el veterinario tiene una cita en el horario especificado
            if (numeroVeterinario != null) {
                for (Cita cita : citasEnHorario) {
                    if (cita.getVeterinario() != null && cita.getVeterinario().getId().equals(numeroVeterinario)) {
                        throw new CitaOcupadaException("El veterinario seleccionado ya tiene una cita en este horario.");
                    }
                }
            }

            // Verificar si el asistente tiene una cita en el horario especificado
            if (numeroAsistente != null) {
                for (Cita cita : citasEnHorario) {
                    if (cita.getAsistente() != null && cita.getAsistente().getId().equals(numeroAsistente)) {
                        throw new CitaOcupadaException("El asistente seleccionado ya tiene una cita en este horario.");
                    }
                }
            }

            // Cargar las entidades desde la base de datos
            Cliente cliente = em.find(Cliente.class, numeroCliente);
            Mascota mascota = em.find(Mascota.class, numeroMascota);
            Veterinario veterinario = numeroVeterinario != null ? em.find(Veterinario.class, numeroVeterinario) : null;
            Asistente asistente = numeroAsistente != null ? em.find(Asistente.class, numeroAsistente) : null;
            List<Paquete> paquetes = em.createQuery("SELECT p FROM Paquete p WHERE p.id IN :ids", Paquete.class)
                    .setParameter("ids", idPaquetes)
                    .getResultList();

            // Realizar el pago
            //nuevaCita.realizarPago();

            // Crear y guardar la nueva cita
            Cita nuevaCita = new Cita();
            nuevaCita.setFechaHora(fechaHora);
            nuevaCita.setCliente(cliente);
            nuevaCita.setMascota(mascota);
            nuevaCita.setVeterinario(veterinario);
            nuevaCita.setAsistente(asistente);
            nuevaCita.setDescripcionServicio(descripcionServicio);
            nuevaCita.setPaquetes(paquetes);

            em.persist(nuevaCita); // Persistir la nueva cita en la base de datos



            em.getTransaction().commit(); // Confirmar la transacción
            return nuevaCita; // Devolver la cita creada
        } catch (Exception e) {
            em.getTransaction().rollback(); // Revertir la transacción en caso de error
            throw e; // Volver a lanzar la excepción
        } finally {
            em.close(); // Cerrar el EntityManager
        }
    }
}

