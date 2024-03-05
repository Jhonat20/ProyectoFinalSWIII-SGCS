package CGCS.COM.ProyectoFinalSWIIISGCS.Domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Set;

@Data
@Entity
@Table(name = "especialidades")
public class Especialidad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEspecialidad;

    @NotBlank(message = "El nombre de la especialidad es obligatorio")
    @Size(max = 255, message = "El nombre de la especialidad debe tener como máximo 255 caracteres")
    @Column(length = 255, nullable = false, unique = true)
    private String nombre;

    @NotBlank(message = "La descripción de la especialidad es obligatoria")
    @Size(max = 500, message = "La descripción de la especialidad debe tener como máximo 500 caracteres")
    @Column(length = 500, nullable = false)
    private String descripcion;



    @ManyToMany(mappedBy = "especialidades")
    private Set<Doctor> doctores;

}
