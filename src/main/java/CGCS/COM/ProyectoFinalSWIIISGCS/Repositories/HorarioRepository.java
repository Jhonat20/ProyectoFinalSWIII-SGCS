/**
 * @file: HorarioRepository.java
 * @created: (Fecha de Creación)
 */

package CGCS.COM.ProyectoFinalSWIIISGCS.Repositories;

import CGCS.COM.ProyectoFinalSWIIISGCS.Domain.Horario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

/**
 * Repositorio JPA para la entidad Horario.
 */
public interface HorarioRepository extends JpaRepository<Horario, Long> {

    /**
     * Busca un horario por día, hora de inicio y hora de fin.
     *
     * @param dia        Día del horario.
     * @param horaInicio Hora de inicio del horario.
     * @param horaFin    Hora de fin del horario.
     * @return Un objeto Optional que puede contener el Horario si se encuentra, o vacío si no.
     */
    Optional<Horario> findByDiaAndHoraInicioAndHoraFin(LocalDate dia, LocalTime horaInicio, LocalTime horaFin);

    /**
     * Busca todos los horarios para un día específico.
     *
     * @param dia Día para el cual se buscan los horarios.
     * @return Una lista de Horarios para el día especificado.
     */
    List<Horario> findByDia(LocalDate dia);

    // Puede incluir métodos adicionales según las necesidades de la aplicación

}
