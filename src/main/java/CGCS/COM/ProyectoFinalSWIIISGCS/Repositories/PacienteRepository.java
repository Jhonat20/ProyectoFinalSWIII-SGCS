/**
 * @file: PacienteRepository.java
 * @created: (Fecha de Creación)
 */

package CGCS.COM.ProyectoFinalSWIIISGCS.Repositories;

import CGCS.COM.ProyectoFinalSWIIISGCS.Domain.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repositorio JPA para la entidad Paciente.
 */
public interface PacienteRepository extends JpaRepository<Paciente, Long> {

    // Puede incluir métodos adicionales según las necesidades de la aplicación

}
