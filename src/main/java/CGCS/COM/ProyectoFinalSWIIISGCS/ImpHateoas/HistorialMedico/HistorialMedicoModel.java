/**
 * @file: HistorialMedicoModel.java
 * @author: (c) Jhon Bravo
 * @created: 09/03/2024 10:18
 */

package CGCS.COM.ProyectoFinalSWIIISGCS.ImpHateoas.HistorialMedico;

import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

/**
 * Modelo de HATEOAS para la entidad HistorialMedico.
 */
@Data
public class HistorialMedicoModel extends RepresentationModel<HistorialMedicoModel> {

    /** Identificador único del historial médico */
    private Long idHistorialMedico;

    /** Diagnóstico del historial médico */
    private String diagnostico;

    /** Alergias registradas en el historial médico */
    private String alergias;

    /** Cirugías realizadas según el historial médico */
    private String cirugias;

    /** Enfermedades previas registradas en el historial médico */
    private String enfermedadesPrevias;

    /** Medicamentos asociados al historial médico */
    private String medicamentos;
}
