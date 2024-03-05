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

    @NotBlank(message = "El nombre del paciente es obligatorio")
    @Column(name = "nombre_paciente", nullable = false)
    private String nombrePaciente;

    @NotBlank(message = "El nombre del doctor es obligatorio")
    @Column(name = "nombre_doctor", nullable = false)
    private String nombreDoctor;

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
    @JoinColumn(name = "id_doctor", nullable = false)
    private Doctor doctor;


    @ManyToOne
    @JoinColumn(name = "id_paciente", nullable = false)
    private Paciente paciente;
}
