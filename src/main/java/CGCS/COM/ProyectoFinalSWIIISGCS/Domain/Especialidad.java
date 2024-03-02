package CGCS.COM.ProyectoFinalSWIIISGCS.Domain;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Data
@Entity
@Table(name = "especialidades")
public class Especialidad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEspecialidad;

    @Column(length = 255, nullable = false)
    private String nombre;

    @Column(columnDefinition = "TEXT")
    private String descripcion;

}
