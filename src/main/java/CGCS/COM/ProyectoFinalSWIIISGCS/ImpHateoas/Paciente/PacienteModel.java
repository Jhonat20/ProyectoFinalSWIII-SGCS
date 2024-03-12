/**
 * @file: PacienteModel.java
 * @author: (c) Jhon Bravo
 * @created: 11/03/2024 23:24
 */

package CGCS.COM.ProyectoFinalSWIIISGCS.ImpHateoas.Paciente;

import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

import java.util.Date;

/**
 * Modelo de HATEOAS para la entidad Paciente.
 */
@Data
public class PacienteModel extends RepresentationModel<PacienteModel> {

    /** Identificador único del paciente */
    private Long idPaciente;

    /** Nombre del paciente */
    private String nombre;

    /** Apellido del paciente */
    private String apellido;

    /** Número de identificación del paciente (DNI) */
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
}
