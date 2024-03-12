/**
 * @file: EspecialidadRepository.java
 * @created: (Fecha de Creación)
 */

package CGCS.COM.ProyectoFinalSWIIISGCS.Repositories;

import CGCS.COM.ProyectoFinalSWIIISGCS.Domain.Especialidad;
import org.springframework.data.repository.CrudRepository;

/**
 * Repositorio CRUD para la entidad Especialidad.
 */
public interface EspecialidadRepository extends CrudRepository<Especialidad, Long> {

    // Puede incluir métodos adicionales según las necesidades de la aplicación

}
