package CGCS.COM.ProyectoFinalSWIIISGCS.DTO;

import CGCS.COM.ProyectoFinalSWIIISGCS.Domain.Doctor;
import CGCS.COM.ProyectoFinalSWIIISGCS.Domain.Paciente;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

@Data
public class CitaDTO {
    private Long idCita;
    private LocalDate fecha;
    private LocalTime hora;
    private String descripcion;
    private String estado;
    private Doctor doctor;
    private Paciente paciente;
}
