package CGCS.COM.ProyectoFinalSWIIISGCS.DTO;

import CGCS.COM.ProyectoFinalSWIIISGCS.Domain.Doctor;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

/**
 * La clase HorarioDTO representa un objeto de transferencia de datos (DTO) para la entidad Horario.
 * Contiene los datos necesarios para realizar operaciones de transferencia de información sobre el horario de un doctor.
 */
@Data
public class HorarioDTO {

    /** Identificador único del horario */
    private Long idHorario;

    /** Día del horario */
    private LocalDate dia;

    /** Hora de inicio del horario */
    private LocalTime horaInicio;

    /** Hora de fin del horario */
    private LocalTime horaFin;

    /** Lista de doctores asociados al horario */
    private List<Doctor> doctores;

    /**
     * Verifica si la hora de inicio es anterior a la hora de fin en el horario.
     *
     * @return true si la hora de inicio es antes de la hora de fin, false en caso contrario.
     */
    public boolean isHoraInicioBeforeHoraFin() {
        return horaInicio.isBefore(horaFin);
    }
}
