package CGCS.COM.ProyectoFinalSWIIISGCS.DTO;

import CGCS.COM.ProyectoFinalSWIIISGCS.Domain.Doctor;
import lombok.Data;

import java.util.Set;

@Data
public class EspecialidadDTO {
    private Long idEspecialidad;
    private String nombre;
    private String descripcion;
    private Set<Doctor> doctores;
}
