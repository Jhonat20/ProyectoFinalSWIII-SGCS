package CGCS.COM.ProyectoFinalSWIIISGCS.DTO;

import CGCS.COM.ProyectoFinalSWIIISGCS.Domain.Paciente;
import lombok.Data;

/**
 * La clase HistorialMedicoDTO representa un objeto de transferencia de datos (DTO) para la entidad HistorialMedico.
 * Contiene los datos necesarios para realizar operaciones de transferencia de información sobre el historial médico de un paciente.
 */
@Data
public class HistorialMedicoDTO {

    /** Identificador único del historial médico */
    private Long idHistorialMedico;

    /** Diagnóstico del historial médico */
    private String diagnostico;

    /** Alergias del paciente según el historial médico */
    private String alergias;

    /** Cirugías previas registradas en el historial médico */
    private String cirugias;

    /** Enfermedades previas registradas en el historial médico */
    private String enfermedadesPrevias;

    /** Medicamentos actuales según el historial médico */
    private String medicamentos;

    /** Paciente asociado al historial médico */
    private Paciente paciente;
}
