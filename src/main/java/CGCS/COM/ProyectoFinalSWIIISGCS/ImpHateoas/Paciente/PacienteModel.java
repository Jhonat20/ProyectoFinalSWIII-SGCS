package CGCS.COM.ProyectoFinalSWIIISGCS.ImpHateoas.Paciente;

import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

import java.util.Date;

/**
 * @file: PacienteModel
 * @author: (c) Jhon Bravo
 * @created: 11/03/2024 23:24
 */
@Data
public class PacienteModel extends RepresentationModel<PacienteModel> {
    private Long idPaciente;
    private String nombre;
    private String apellido;
    private String dni;
    private String telefono;
    private String email;
    private Date fechaNacimiento;
    private String direccion;
    private String grupoSanguineo;
}
