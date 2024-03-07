package CGCS.COM.ProyectoFinalSWIIISGCS.Domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

/**
 * La clase Horario representa el horario de trabajo de un doctor en el sistema.
 * Contiene información sobre el día, la hora de inicio y la hora de finalización del horario.
 */
@Entity
@Data
public class Horario {

    /** Identificador único del horario */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idHorario;

    /** Día del horario */
    @NotNull(message = "El campo 'dia' no puede ser nulo")
    @FutureOrPresent(message = "El 'dia' debe ser la fecha de hoy o futura")
    private LocalDate dia;

    /** Hora de inicio del horario */
    @NotNull(message = "El campo hora de inicio no puede ser nulo")
    private LocalTime horaInicio;

    /** Hora de finalización del horario */
    @NotNull(message = "El campo hora final no puede ser nulo")
    private LocalTime horaFin;

    /** Lista de doctores asociados con este horario */
    @OneToMany(mappedBy = "horario")
    @JsonBackReference
    private List<Doctor> doctores;

    /** Validación personalizada para asegurar que la hora de inicio es anterior a la hora de fin */
    @AssertTrue(message = "La hora de inicio debe ser anterior a la hora de fin")
    public boolean isHoraInicioBeforeHoraFin() {
        return horaInicio.isBefore(horaFin);
    }
}
