/**
 * @file: AdministrativoService.java
 * @created: Fecha de creación
 * @author:
 */

package CGCS.COM.ProyectoFinalSWIIISGCS.Services;

import CGCS.COM.ProyectoFinalSWIIISGCS.Domain.Administrativo;
import CGCS.COM.ProyectoFinalSWIIISGCS.exception.IllegalOperationException;

import java.util.List;
import java.util.Optional;

/**
 * Interfaz que define los servicios para la entidad Administrativo.
 */
public interface AdministrativoService {

    /**
     * Obtiene la lista de todos los administrativos.
     *
     * @return Lista de administrativos.
     * @throws IllegalOperationException Si hay una operación ilegal.
     */
    List<Administrativo> listarAdministrativo() throws IllegalOperationException;

    /**
     * Registra un nuevo administrativo.
     *
     * @param administrativo Administrativo a registrar.
     * @return Administrativo registrado.
     * @throws IllegalOperationException Si hay una operación ilegal.
     */
    Administrativo registrarAdministrativo(Administrativo administrativo) throws IllegalOperationException;

    /**
     * Obtiene un administrativo por su ID.
     *
     * @param id ID del administrativo.
     * @return Administrativo encontrado (si existe).
     * @throws IllegalOperationException Si hay una operación ilegal.
     */
    Optional<Administrativo> obtenerAdministrativoPorId(Long id) throws IllegalOperationException;

    /**
     * Elimina un administrativo por su ID.
     *
     * @param id ID del administrativo a eliminar.
     * @throws IllegalOperationException Si hay una operación ilegal.
     */
    void eliminarAdministrativo(Long id) throws IllegalOperationException;

    /**
     * Actualiza un administrativo por su ID.
     *
     * @param id             ID del administrativo a actualizar.
     * @param administrativo Datos actualizados del administrativo.
     * @return Administrativo actualizado.
     * @throws IllegalOperationException Si hay una operación ilegal.
     */
    Administrativo actualizarAdministrativo(Long id, Administrativo administrativo) throws IllegalOperationException;
}
