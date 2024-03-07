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

@Entity
@Data
public class Horario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idHorario;

    @NotNull(message = "El campo 'dia' no puede ser nulo")
    @FutureOrPresent(message = "El 'dia' debe ser la fecha de hoy o futura")
    private LocalDate dia;

    @NotNull(message = "El campo hora de inicio no puede ser nulo")
    private LocalTime horaInicio;

    @NotNull(message = "El campo hora final no puede ser nulo")
    private LocalTime horaFin;


    @OneToMany(mappedBy = "horario")
    @JsonBackReference
    private List<Doctor> doctores;

    // Validación personalizada
    @AssertTrue(message = "La hora de inicio debe ser anterior a la hora de fin")
    public boolean isHoraInicioBeforeHoraFin() {
        return horaInicio.isBefore(horaFin);
    }
}
