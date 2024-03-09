package CGCS.COM.ProyectoFinalSWIIISGCS.ImpHateoas.Cita;

import CGCS.COM.ProyectoFinalSWIIISGCS.Domain.Doctor;
import CGCS.COM.ProyectoFinalSWIIISGCS.Domain.Paciente;
import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

/**
 * @file: CitaModel
 * @author: (c) Jhon Bravo
 * @created: 09/03/2024 8:07
 */
@Data
public class CitaModel extends RepresentationModel<CitaModel> {
    private Long idCita;
    private LocalDate fecha;
    private LocalTime hora;
    private String descripcion;
    private String estado;

}
