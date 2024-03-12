package CGCS.COM.ProyectoFinalSWIIISGCS.DTO;

import CGCS.COM.ProyectoFinalSWIIISGCS.Domain.Doctor;
import lombok.Data;

import java.util.Set;

/**
 * La clase EspecialidadDTO representa un objeto de transferencia de datos (DTO) para la entidad Especialidad.
 * Contiene los datos necesarios para realizar operaciones de transferencia de información sobre una especialidad.
 */
@Data
public class EspecialidadDTO {

    /** Identificador único de la especialidad */
    private Long idEspecialidad;

    /** Nombre de la especialidad */
    private String nombre;

    /** Descripción de la especialidad */
    private String descripcion;

    /** Conjunto de doctores asociados a la especialidad */
    private Set<Doctor> doctores;
}
