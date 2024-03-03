package CGCS.COM.ProyectoFinalSWIIISGCS.Repositories;

import CGCS.COM.ProyectoFinalSWIIISGCS.Domain.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {
}
