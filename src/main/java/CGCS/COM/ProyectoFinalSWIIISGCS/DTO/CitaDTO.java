package CGCS.COM.ProyectoFinalSWIIISGCS.DTO;

import CGCS.COM.ProyectoFinalSWIIISGCS.Domain.Doctor;
import CGCS.COM.ProyectoFinalSWIIISGCS.Domain.Paciente;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * La clase CitaDTO representa un objeto de transferencia de datos (DTO) para la entidad Cita.
 * Contiene los datos necesarios para realizar operaciones de transferencia de información sobre una cita.
 */
@Data
public class CitaDTO {

    /** Identificador único de la cita */
    private Long idCita;

    /** Fecha de la cita */
    private LocalDate fecha;

    /** Hora de la cita */
    private LocalTime hora;

    /** Descripción de la cita */
    private String descripcion;

    /** Estado de la cita */
    private String estado;

    /** Doctor asociado a la cita */
    private Doctor doctor;

    /** Paciente asociado a la cita */
    private Paciente paciente;
}
