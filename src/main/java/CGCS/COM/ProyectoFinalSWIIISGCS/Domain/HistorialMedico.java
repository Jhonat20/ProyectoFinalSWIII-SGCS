package CGCS.COM.ProyectoFinalSWIIISGCS.Domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class HistorialMedico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idHistorialMedico;
    private String diagnostico;
    private String alergias;
    private String cirugias;
    private String enfermedadesPrevias;
    private String medicamentos;
}
