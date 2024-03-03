package CGCS.COM.ProyectoFinalSWIIISGCS.Domain;

import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity
@Table(name = "pacientes")
public class Paciente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPaciente;

    @Column(length = 255, nullable = false)
    private String nombre;

    @Column(length = 255, nullable = false)
    private String apellido;

    @Column(length = 20, nullable = false, unique = true)
    private String dni;

    @Column(length = 20)
    private String telefono;

    @Column(length = 255, nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private Date fechaNacimiento;

    @ManyToOne
    @JoinColumn(name = "idSexo")
    private Sexo sexo;

    @OneToOne(mappedBy = "paciente")
    private HistoriaClinica historiaClinica;

    @OneToMany(mappedBy = "paciente", cascade = CascadeType.ALL)
    private Set<Cita> citas;

}
