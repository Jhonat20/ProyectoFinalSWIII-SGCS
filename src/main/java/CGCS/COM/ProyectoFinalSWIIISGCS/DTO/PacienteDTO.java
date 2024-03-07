package CGCS.COM.ProyectoFinalSWIIISGCS.DTO;

import CGCS.COM.ProyectoFinalSWIIISGCS.Domain.Cita;
import CGCS.COM.ProyectoFinalSWIIISGCS.Domain.HistorialMedico;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @file: PacienteDTO
 * @author: (c)jhons
 * @created: 06/03/2024 22:04
 */

@Data
public class PacienteDTO {
    private Long idPaciente;
    private String nombre;
    private String apellido;
    private String dni;
    private String telefono;
    private String email;
    private Date fechaNacimiento;
    private String direccion;
    private String grupoSanguineo;
    private List<Cita> citas;
    private HistorialMedico historialMedico;

}
