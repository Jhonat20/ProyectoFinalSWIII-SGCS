package CGCS.COM.ProyectoFinalSWIIISGCS.DTO;

import CGCS.COM.ProyectoFinalSWIIISGCS.Domain.Cita;
import CGCS.COM.ProyectoFinalSWIIISGCS.Domain.Especialidad;
import CGCS.COM.ProyectoFinalSWIIISGCS.Domain.Horario;

import java.util.List;
import java.util.Set;

public class DoctorDTO {
    private Long idDoctor;
    private String nombres;
    private String apellidos;
    private String dni;
    private String telefono;
    private String email;
   // private List<Cita> citas;
    private Horario horario;
    //private Set<Especialidad> especialidades;
}
