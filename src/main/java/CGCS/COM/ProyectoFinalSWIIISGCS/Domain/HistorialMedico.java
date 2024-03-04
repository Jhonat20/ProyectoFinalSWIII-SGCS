package CGCS.COM.ProyectoFinalSWIIISGCS.Domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Data
public class HistorialMedico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idHistorialMedico;

    @NotBlank(message = "El diagnóstico es obligatorio")
    @Size(max = 500, message = "El diagnostico deve tener como maximo 500 caracteres")
    private String diagnostico;

    @Size(max = 500, message = "Las alergias deben tener como máximo 500 caracteres")
    private String alergias;

    @Size(max = 500, message = "Las cirugías deben tener como máximo 500 caracteres")
    private String cirugias;

    @Size(max = 500, message = "Las enfermedades previas deben tener como máximo 500 caracteres")
    private String enfermedadesPrevias;

    @Size(max = 500, message = "Los medicamentos deben tener como máximo 500 caracteres")
    private String medicamentos;

    // Aquí podrías añadir más campos y validaciones según las necesidades específicas del dominio

}
