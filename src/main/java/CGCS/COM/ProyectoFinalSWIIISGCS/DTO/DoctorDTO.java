package CGCS.COM.ProyectoFinalSWIIISGCS.DTO;

import CGCS.COM.ProyectoFinalSWIIISGCS.Domain.Cita;
import CGCS.COM.ProyectoFinalSWIIISGCS.Domain.Especialidad;
import CGCS.COM.ProyectoFinalSWIIISGCS.Domain.Horario;
import lombok.Data;

import java.util.List;
import java.util.Set;

/**
 * La clase DoctorDTO representa un objeto de transferencia de datos (DTO) para la entidad Doctor.
 * Contiene los datos necesarios para realizar operaciones de transferencia de información sobre un doctor.
 */
@Data
public class DoctorDTO {

    /** Identificador único del doctor */
    private Long idDoctor;

    /** Nombres del doctor */
    private String nombres;

    /** Apellidos del doctor */
    private String apellidos;

    /** DNI del doctor */
    private String dni;

    /** Número de teléfono del doctor */
    private String telefono;

    /** Correo electrónico del doctor */
    private String email;

    /** Horario del doctor */
    private Horario horario;

    /** Lista de citas asociadas al doctor */
    private List<Cita> citas;

    /** Conjunto de especialidades del doctor */
    private Set<Especialidad> especialidades;
}
