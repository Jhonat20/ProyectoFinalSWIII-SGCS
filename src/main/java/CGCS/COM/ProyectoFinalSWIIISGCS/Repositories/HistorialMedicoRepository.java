/**
 * @file: HistorialMedicoRepository.java
 * @created: (Fecha de Creación)
 */

package CGCS.COM.ProyectoFinalSWIIISGCS.Repositories;

import CGCS.COM.ProyectoFinalSWIIISGCS.Domain.HistorialMedico;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repositorio JPA para la entidad HistorialMedico.
 */
public interface HistorialMedicoRepository extends JpaRepository<HistorialMedico, Long> {

    // Puede incluir métodos adicionales según las necesidades de la aplicación

}
