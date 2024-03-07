package CGCS.COM.ProyectoFinalSWIIISGCS.DTO;

import CGCS.COM.ProyectoFinalSWIIISGCS.Domain.Paciente;
import lombok.Data;

@Data
public class HistorialMedicoDTO {
    private Long idHistorialMedico;
    private String diagnostico;
    private String alergias;
    private String cirugias;
    private String enfermedadesPrevias;
    private String medicamentos;
    private Paciente paciente;
}
