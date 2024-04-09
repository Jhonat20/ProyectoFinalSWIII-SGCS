package CGCS.COM.ProyectoFinalSWIIISGCS.Domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Objects;
import java.util.Set;

/**
 * La clase Especialidad representa una especialidad médica en el sistema.
 * Contiene información sobre el nombre y la descripción de la especialidad.
 */
@Data
@Entity
@Table(name = "especialidades")
public class Especialidad {

    /** Identificador único de la especialidad */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEspecialidad;

    /** Nombre de la especialidad */
    @NotBlank(message = "El nombre de la especialidad es obligatorio")
    @Size(max = 255, message = "El nombre de la especialidad debe tener como máximo 255 caracteres")
    @Column(length = 255, nullable = false, unique = true)
    private String nombre;

    /** Descripción de la especialidad */
    @NotBlank(message = "La descripción de la especialidad es obligatoria")
    @Size(max = 500, message = "La descripción de la especialidad debe tener como máximo 500 caracteres")
    @Column(length = 500, nullable = false)
    private String descripcion;

    /** Conjunto de médicos que tienen esta especialidad */
    @ManyToMany(mappedBy = "especialidades")
    @JsonIgnore
    private Set<Doctor> doctores;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Especialidad)) return false;
        Especialidad especialidad = (Especialidad) o;
        return Objects.equals(getIdEspecialidad(), especialidad.getIdEspecialidad());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIdEspecialidad());
    }

}