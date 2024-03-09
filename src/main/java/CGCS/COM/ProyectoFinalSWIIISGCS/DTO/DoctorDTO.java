package CGCS.COM.ProyectoFinalSWIIISGCS.DTO;

import CGCS.COM.ProyectoFinalSWIIISGCS.Domain.Cita;
import CGCS.COM.ProyectoFinalSWIIISGCS.Domain.Especialidad;
import lombok.Data;
import java.util.List;
import java.util.Set;
@Data
public class DoctorDTO {

    private Long idDoctor;
    private String nombres;
    private String apellidos;
    private String dni;
    private String telefono;
    private String email;
    private List<Cita> citas;
    private Set<Especialidad> especialidades;
}
