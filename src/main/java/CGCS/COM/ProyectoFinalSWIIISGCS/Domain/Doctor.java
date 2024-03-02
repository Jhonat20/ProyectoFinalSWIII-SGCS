package CGCS.COM.ProyectoFinalSWIIISGCS.Domain;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Data
@Entity
@Table(name = "doctores")
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDoctor;

    @Column(length = 255, nullable = false)
    private String nombre;

    @Column(length = 255, nullable = false)
    private String apellido;

    //  @ManyToOne
    @JoinColumn(name = "idEspecialidad")
    private Especialidad especialidad;

    @Column(length = 20, nullable = false, unique = true)
    private String dni;

    @Column(length = 20)
    private String telefono;

    @Column(length = 255, nullable = false, unique = true)
    private String email;

    //  @OneToOne(mappedBy = "doctor", cascade = CascadeType.ALL)
    private Horario horario;

    //@OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL)
    private Set<Cita> citas;
}
