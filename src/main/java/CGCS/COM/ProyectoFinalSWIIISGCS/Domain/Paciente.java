package CGCS.COM.ProyectoFinalSWIIISGCS.Domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * La clase Paciente representa a un paciente en el sistema.
 * Contiene información personal del paciente, como nombre, apellido, DNI, etc.
 */
@Data
@Entity
@Table(name = "pacientes")
public class Paciente {

    /** Identificador único del paciente */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_paciente")
    private Long idPaciente;

    /** Nombre del paciente */
    @NotBlank(message = "El nombre es obligatorio")
    @Size(max = 255, message = "El nombre debe tener como máximo 255 caracteres")
    @Column(length = 255, nullable = false)
    private String nombre;

    /** Apellido del paciente */
    @NotBlank(message = "El apellido es obligatorio")
    @Size(max = 255, message = "El apellido debe tener como máximo 255 caracteres")
    @Column(length = 255, nullable = false)
    private String apellido;

    /** DNI del paciente */
    @NotBlank(message = "El DNI es obligatorio")
    @Size(max = 8, message = "El DNI debe tener como máximo 8 caracteres")
    @Size(min = 8, message = "El DNI debe tener como mínimo 8 caracteres")
    @Pattern(regexp = "\\d+", message = "El DNI debe contener solo números")
    @Column(name = "dni", length = 8, nullable = false, unique = true)
    private String dni;

    /** Teléfono del paciente */
    @Size(max = 9, message = "El teléfono debe tener como máximo 9 caracteres")
    @Column(length = 9)
    private String telefono;

    /** Correo electrónico del paciente */
    @NotBlank(message = "El email es obligatorio")
    @Email(message = "El email debe tener un formato válido")
    @Size(max = 255, message = "El email debe tener como máximo 255 caracteres")
    @Column(length = 255, nullable = false, unique = true)
    private String email;

    /** Fecha de nacimiento del paciente */
    @Temporal(TemporalType.DATE) // Indica que solo se almacenará la fecha sin la hora
    @Column(name = "fecha_nacimiento", nullable = false)
    private Date fechaNacimiento;

    /** Dirección del paciente */
    @NotBlank(message = "La dirección es obligatoria")
    @Column(nullable = false)
    private String direccion;

    /** Grupo sanguíneo del paciente */
    @NotBlank(message = "El grupo sanguíneo es obligatorio")
    @Size(max = 5, message = "El grupo sanguíneo debe tener como máximo 5 caracteres")
    @Column(name = "grupo_sanguineo", length = 5, nullable = false)
    private String grupoSanguineo;

    /** Lista de citas asociadas con el paciente */
    @OneToMany(mappedBy = "paciente")
    @JsonIgnore
    private List<Cita> citas;

    /** Historial médico del paciente */
    @OneToOne(mappedBy = "paciente", cascade = CascadeType.ALL)
    private HistorialMedico historialMedico;
}
