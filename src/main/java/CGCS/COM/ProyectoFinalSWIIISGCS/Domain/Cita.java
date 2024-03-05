package CGCS.COM.ProyectoFinalSWIIISGCS.Domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "citas")
public class Cita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cita")
    private Long idCita;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_hora", nullable = false)
    @FutureOrPresent(message = "La fecha de la cita debe ser en el presente o en el futuro")
    private Date fechaHora;

    @NotBlank(message = "La descripción es obligatoria")
    @Column(nullable = false)
    private String descripcion;

    @NotBlank(message = "El estado de la cita es obligatorio")
    @Column(name = "estado", nullable = false)
    private String estado;


    @ManyToOne
    @JoinColumn(name = "id_doctor")
    private Doctor doctor;


    @ManyToOne
    @JoinColumn(name = "id_paciente")
    private Paciente paciente;
}
