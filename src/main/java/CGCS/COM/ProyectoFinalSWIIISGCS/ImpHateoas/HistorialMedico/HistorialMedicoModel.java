package CGCS.COM.ProyectoFinalSWIIISGCS.ImpHateoas.HistorialMedico;

import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

/**
 * @file: HistorialMedicoModel
 * @author: (c) Jhon Bravo
 * @created: 09/03/2024 10:18
 */

@Data
public class HistorialMedicoModel extends RepresentationModel<HistorialMedicoModel> {
    private Long idHistorialMedico;
    private String diagnostico;
    private String alergias;
    private String cirugias;
    private String enfermedadesPrevias;
    private String medicamentos;
}
