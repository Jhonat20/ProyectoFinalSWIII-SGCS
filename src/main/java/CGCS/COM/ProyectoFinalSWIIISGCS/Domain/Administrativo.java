package CGCS.COM.ProyectoFinalSWIIISGCS.Domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * La clase Administrativo representa a un personal administrativo en el sistema.
 * Contiene información personal del administrativo, como nombres, apellidos, DNI, etc.
 */
@Data
@Entity
@Table(name = "administrativos")
public class Administrativo {

    /** Identificador único del administrativo */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAdministrativo;

    /** Nombres del administrativo */
    @NotBlank(message = "Los nombres son obligatorios")
    @Size(max = 255, message = "Los nombres debe tener como máximo 255 caracteres")
    @Column(length = 255, nullable = false)
    private String nombres;

    /** Apellidos del administrativo */
    @NotBlank(message = "Los apellidos son obligatorios")
    @Size(max = 255, message = "Los apellidos deben tener como máximo 255 caracteres")
    @Column(length = 255, nullable = false)
    private String apellidos;

    /** DNI del administrativo */
    @NotBlank(message = "El DNI es obligatorio")
    @Size(max = 8, message = "El DNI debe tener como máximo 8 caracteres")
    @Column(length = 8, nullable = false, unique = true)
    private String dni;

    /** Teléfono del administrativo */
    @Size(max = 20, message = "El teléfono debe tener como máximo 20 caracteres")
    @Column(length = 20)
    private String telefono;

    /** Correo electrónico del administrativo */
    @NotBlank(message = "El email es obligatorio")
    @Email(message = "El email debe tener un formato válido")
    @Size(max = 255, message = "El email debe tener como máximo 255 caracteres")
    @Column(length = 255, nullable = false, unique = true)
    private String email;

    /** Cargo del administrativo */
    @Column(length = 255)
    private String cargo;
}
