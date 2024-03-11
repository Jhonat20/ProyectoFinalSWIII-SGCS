package CGCS.COM.ProyectoFinalSWIIISGCS.DTO;

import CGCS.COM.ProyectoFinalSWIIISGCS.Domain.Cita;
import CGCS.COM.ProyectoFinalSWIIISGCS.Domain.Especialidad;
import CGCS.COM.ProyectoFinalSWIIISGCS.Domain.Horario;
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
    private Horario horario;
    private List<Cita> citas;
    private Set<Especialidad> especialidades;
}
