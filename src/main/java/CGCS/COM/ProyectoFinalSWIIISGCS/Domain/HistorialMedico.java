package CGCS.COM.ProyectoFinalSWIIISGCS.Domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
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


    @OneToOne
    //@JoinColumn(name = "id_paciente", referencedColumnName = "idPaciente")
    @JsonIgnore
    private Paciente paciente;
}
