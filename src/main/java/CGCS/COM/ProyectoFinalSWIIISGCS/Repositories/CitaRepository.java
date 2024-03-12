/**
 * @file: CitaRepository.java
 * @created: (Fecha de Creación)
 */

package CGCS.COM.ProyectoFinalSWIIISGCS.Repositories;

import CGCS.COM.ProyectoFinalSWIIISGCS.Domain.Cita;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Repositorio JPA para la entidad Cita.
 */
public interface CitaRepository extends JpaRepository<Cita, Long> {

    // Se pueden agregar métodos adicionales según las necesidades de la aplicación

}
