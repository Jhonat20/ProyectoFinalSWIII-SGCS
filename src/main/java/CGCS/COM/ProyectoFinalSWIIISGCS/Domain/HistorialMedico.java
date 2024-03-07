package CGCS.COM.ProyectoFinalSWIIISGCS.Domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * La clase HistorialMedico representa el historial médico de un paciente en el sistema.
 * Contiene información sobre el diagnóstico, alergias, cirugías, enfermedades previas y medicamentos.
 */
@Entity
@Data
public class HistorialMedico {

    /** Identificador único del historial médico */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idHistorialMedico;

    /** Diagnóstico del paciente */
    @NotBlank(message = "El diagnóstico es obligatorio")
    @Size(max = 500, message = "El diagnóstico debe tener como máximo 500 caracteres")
    private String diagnostico;

    /** Alergias del paciente */
    @Size(max = 500, message = "Las alergias deben tener como máximo 500 caracteres")
    private String alergias;

    /** Cirugías previas del paciente */
    @Size(max = 500, message = "Las cirugías deben tener como máximo 500 caracteres")
    private String cirugias;

    /** Enfermedades previas del paciente */
    @Size(max = 500, message = "Las enfermedades previas deben tener como máximo 500 caracteres")
    private String enfermedadesPrevias;

    /** Medicamentos actuales del paciente */
    @Size(max = 500, message = "Los medicamentos deben tener como máximo 500 caracteres")
    private String medicamentos;

    /** Paciente asociado al historial médico */
    @OneToOne
    @JsonIgnore
    private Paciente paciente;
}
