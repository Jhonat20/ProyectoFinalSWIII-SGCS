package CGCS.COM.ProyectoFinalSWIIISGCS.Domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
@Table(name = "administrativos")
public class Administrativo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAdministrativo;

    @NotBlank(message = "Los nombres son obligatorio")
    @Size(max = 255, message = "Los nombres debe tener como máximo 255 caracteres")
    @Column(length = 255, nullable = false)
    private String nombres;

    @NotBlank(message = "Los apellidos son obligatorios")
    @Size(max = 255, message = "Los apellidos deben tener como máximo 255 caracteres")
    @Column(length = 255, nullable = false)
    private String apellidos;

    @NotBlank(message = "El DNI es obligatorio")
    @Size(max = 8, message = "El DNI debe tener como máximo 8 caracteres")
    @Column(length = 8, nullable = false, unique = true)
    private String dni;

    @Size(max = 20, message = "El teléfono debe tener como máximo 20 caracteres")
    @Column(length = 20)
    private String telefono;

    @NotBlank(message = "El email es obligatorio")
    @Email(message = "El email debe tener un formato válido")
    @Size(max = 255, message = "El email debe tener como máximo 255 caracteres")
    @Column(length = 255, nullable = false, unique = true)
    private String email;

    // Atributos adicionales específicos del personal administrativo

    @Column(length = 255)
    private String cargo;

    @Column(length = 255)
    private String departamento;
}
