package CGCS.COM.ProyectoFinalSWIIISGCS.Domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * La clase Doctor representa a un médico en el sistema.
 * Contiene información personal del médico, como nombres, apellidos, DNI, etc.
 */
@Data
@Entity
@Table(name = "doctores")
public class Doctor {

    /** Identificador único del médico */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDoctor;

    /** Nombres del médico */
    @NotBlank(message = "Los nombres son obligatorios")
    @Size(max = 255, message = "Los nombres debe tener como máximo 255 caracteres")
    @Column(length = 255, nullable = false)
    private String nombres;

    /** Apellidos del médico */
    @NotBlank(message = "Los apellidos son obligatorios")
    @Size(max = 255, message = "Los apellidos deben tener como máximo 255 caracteres")
    @Column(length = 255, nullable = false)
    private String apellidos;

    /** DNI del médico */
    @NotBlank(message = "El DNI es obligatorio")
    @Size(max = 20, message = "El DNI debe tener como máximo 20 caracteres")
    @Column(length = 20, nullable = false, unique = true)
    private String dni;

    /** Número de teléfono del médico */
    @Size(max = 20, message = "El teléfono debe tener como máximo 20 caracteres")
    @Column(length = 20)
    private String telefono;

    /** Correo electrónico del médico */
    @NotBlank(message = "El email es obligatorio")
    @Email(message = "El email debe tener un formato válido")
    @Size(max = 255, message = "El email debe tener como máximo 255 caracteres")
    @Column(length = 255, nullable = false, unique = true)
    private String email;

    /** Lista de citas asociadas con el médico */
    @OneToMany(mappedBy = "doctor")
    @JsonManagedReference
    private List<Cita> citas;

    /** Horario del médico */
    @ManyToOne
    @JoinColumn(name = "id_horario")
    @JsonIgnore
    private Horario horario;

    /** Conjunto de especialidades del médico */
    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "Doctor_Especialidad",
            joinColumns = { @JoinColumn(name = "id_doctor") },
            inverseJoinColumns = { @JoinColumn(name = "id_especialidad") }
    )
    @JsonIgnore
    private Set<Especialidad> especialidades;

    /**
     * Método para agregar una cita a la lista de citas del médico.
     * Establece el médico como el médico asociado a la cita.
     * @param cita La cita que se va a agregar.
     */
    public void agregarCita(Cita cita) {
        citas.add(cita);
        cita.setDoctor(this);
    }

    /**
     * Compara dos objetos Doctor para determinar si son iguales.
     *
     * @param o Objeto a comparar con el Doctor actual.
     * @return true si los objetos son iguales, false en caso contrario.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Doctor)) return false;
        Doctor doctor = (Doctor) o;
        return Objects.equals(getIdDoctor(), doctor.getIdDoctor());
    }

    /**
     * Genera el código hash para el objeto Doctor.
     *
     * @return Código hash del Doctor.
     */
    @Override
    public int hashCode() {
        return Objects.hash(getIdDoctor());
    }
}