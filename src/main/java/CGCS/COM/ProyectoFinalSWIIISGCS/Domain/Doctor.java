package CGCS.COM.ProyectoFinalSWIIISGCS.Domain;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "doctores")
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDoctor;

    @NotBlank(message = "Los nombres son obligatorio")
    @Size(max = 255, message = "Los nombres debe tener como máximo 255 caracteres")
    @Column(length = 255, nullable = false)
    private String nombres;

    @NotBlank(message = "Los apellidos son obligatorios")
    @Size(max = 255, message = "Los apellidos deben tener como máximo 255 caracteres")
    @Column(length = 255, nullable = false)
    private String apellidos;

    @NotBlank(message = "El DNI es obligatorio")
    @Size(max = 20, message = "El DNI debe tener como máximo 20 caracteres")
    @Column(length = 20, nullable = false, unique = true)
    private String dni;

    @Size(max = 20, message = "El teléfono debe tener como máximo 20 caracteres")
    @Column(length = 20)
    private String telefono;

    @NotBlank(message = "El email es obligatorio")
    @Email(message = "El email debe tener un formato válido")
    @Size(max = 255, message = "El email debe tener como máximo 255 caracteres")
    @Column(length = 255, nullable = false, unique = true)
    private String email;

    @OneToMany(mappedBy = "doctor")
    private List<Cita> citas;

    @OneToOne(cascade = CascadeType.ALL)
    //@JoinColumn(name = "id_horario", referencedColumnName = "idHorario")
    private Horario horario;

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "Doctor_Especialidad",
            joinColumns = { @JoinColumn(name = "id_doctor") },
            inverseJoinColumns = { @JoinColumn(name = "id_especialidad") }
    )
    private Set<Especialidad> especialidades;

}
