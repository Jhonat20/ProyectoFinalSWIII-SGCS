/**
 * @file: AdministrativoRepository.java
 * @created: (Fecha de Creación)
 */

package CGCS.COM.ProyectoFinalSWIIISGCS.Repositories;

import CGCS.COM.ProyectoFinalSWIIISGCS.Domain.Administrativo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Repositorio JPA para la entidad Administrativo.
 */
public interface AdministrativoRepository extends JpaRepository<Administrativo, Long> {

    /**
     * Busca un administrativo por su dirección de correo electrónico.
     *
     * @param email La dirección de correo electrónico del administrativo a buscar.
     * @return Un objeto Optional que puede contener el administrativo encontrado, o vacío si no se encuentra.
     */
    Optional<Administrativo> findOneByEmail(String email);
}
