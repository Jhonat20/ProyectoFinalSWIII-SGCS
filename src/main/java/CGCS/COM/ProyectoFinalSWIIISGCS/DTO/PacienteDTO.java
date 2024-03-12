package CGCS.COM.ProyectoFinalSWIIISGCS.DTO;

import CGCS.COM.ProyectoFinalSWIIISGCS.Domain.Cita;
import CGCS.COM.ProyectoFinalSWIIISGCS.Domain.HistorialMedico;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * La clase PacienteDTO representa un objeto de transferencia de datos (DTO) para la entidad Paciente.
 * Contiene los datos necesarios para realizar operaciones de transferencia de información sobre un paciente.
 */
@Data
public class PacienteDTO {

    /** Identificador único del paciente */
    private Long idPaciente;

    /** Nombre del paciente */
    private String nombre;

    /** Apellido del paciente */
    private String apellido;

    /** DNI del paciente */
    private String dni;

    /** Número de teléfono del paciente */
    private String telefono;

    /** Correo electrónico del paciente */
    private String email;

    /** Fecha de nacimiento del paciente */
    private Date fechaNacimiento;

    /** Dirección del paciente */
    private String direccion;

    /** Grupo sanguíneo del paciente */
    private String grupoSanguineo;

    /** Lista de citas asociadas al paciente */
    private List<Cita> citas;

    /** Historial médico del paciente */
    private HistorialMedico historialMedico;

}
