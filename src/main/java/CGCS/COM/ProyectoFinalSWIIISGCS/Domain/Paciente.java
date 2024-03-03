package CGCS.COM.ProyectoFinalSWIIISGCS.Domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "pacientes")
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_paciente")
    private Long idPaciente;

    @NotBlank(message = "El nombre es obligatorio")
    @Size(max = 255, message = "El nombre debe tener como máximo 255 caracteres")
    @Column(length = 255, nullable = false)
    private String nombre;

    @NotBlank(message = "El apellido es obligatorio")
    @Size(max = 255, message = "El apellido debe tener como máximo 255 caracteres")
    @Column(length = 255, nullable = false)
    private String apellido;

    @NotBlank(message = "El DNI es obligatorio")
    @Size(max = 20, message = "El DNI debe tener como máximo 20 caracteres")
    @Column(name = "dni", length = 20, nullable = false, unique = true)
    private String dni;

    @Size(max = 20, message = "El teléfono debe tener como máximo 20 caracteres")
    @Column(length = 20)
    private String telefono;

    @NotBlank(message = "El email es obligatorio")
    @Email(message = "El email debe tener un formato válido")
    @Size(max = 255, message = "El email debe tener como máximo 255 caracteres")
    @Column(length = 255, nullable = false, unique = true)
    private String email;

    @Temporal(TemporalType.DATE) // Indica que solo se almacenará la fecha sin la hora
    @Column(name = "fecha_nacimiento", nullable = false)
    private Date fechaNacimiento;

    @NotBlank(message = "La dirección es obligatoria")
    @Column(nullable = false)
    private String direccion;

    @NotBlank(message = "El grupo sanguíneo es obligatorio")
    @Size(max = 5, message = "El grupo sanguíneo debe tener como máximo 5 caracteres")
    @Column(name = "grupo_sanguineo", length = 5, nullable = false)
    private String grupoSanguineo;
}
