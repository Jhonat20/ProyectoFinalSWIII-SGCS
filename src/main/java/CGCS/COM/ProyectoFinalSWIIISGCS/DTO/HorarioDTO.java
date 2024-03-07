package CGCS.COM.ProyectoFinalSWIIISGCS.DTO;

import CGCS.COM.ProyectoFinalSWIIISGCS.Domain.Doctor;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
public class HorarioDTO {
    private Long idHorario;
    private LocalDate dia;
    private LocalTime horaInicio;
    private LocalTime horaFin;
    private List<Doctor> doctores;
    public boolean isHoraInicioBeforeHoraFin() {
        return horaInicio.isBefore(horaFin);
    }
}
