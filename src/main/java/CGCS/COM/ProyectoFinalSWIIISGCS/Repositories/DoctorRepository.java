/**
 * @file: DoctorRepository.java
 * @created: (Fecha de Creación)
 */

package CGCS.COM.ProyectoFinalSWIIISGCS.Repositories;

import CGCS.COM.ProyectoFinalSWIIISGCS.Domain.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repositorio JPA para la entidad Doctor.
 */
public interface DoctorRepository extends JpaRepository<Doctor, Long> {

    // Se pueden agregar métodos adicionales según las necesidades de la aplicación

}
