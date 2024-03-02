package CGCS.COM.ProyectoFinalSWIIISGCS.Domain;
import jakarta.persistence.*;
import lombok.Data;

import java.sql.Time;

@Data
@Entity
@Table(name = "horarios")
public class Horario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idHorario;

    //@OneToOne
    @JoinColumn(name = "idDoctor")
    private Doctor doctor;

    @Temporal(TemporalType.TIME)
    private Time horaInicio;
    @Temporal(TemporalType.TIME)
    private Time horaFin;

    private String diaTrabjo;

    private boolean disponible;
}
