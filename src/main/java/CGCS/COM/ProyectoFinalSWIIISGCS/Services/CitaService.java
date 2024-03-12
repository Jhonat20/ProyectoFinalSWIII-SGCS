/**
 * @file: CitaService.java
 * @created: Fecha de creación
 * @author:
 */

package CGCS.COM.ProyectoFinalSWIIISGCS.Services;

import CGCS.COM.ProyectoFinalSWIIISGCS.Domain.Cita;
import CGCS.COM.ProyectoFinalSWIIISGCS.exception.IllegalOperationException;

import java.util.List;
import java.util.Optional;

/**
 * Interfaz que define los servicios para la entidad Cita.
 */
public interface CitaService {

    /**
     * Obtiene la lista de todas las citas.
     *
     * @return Lista de citas.
     * @throws IllegalOperationException Si hay una operación ilegal.
     */
    List<Cita> listarCitas() throws IllegalOperationException;

    /**
     * Registra una nueva cita.
     *
     * @param cita Cita a registrar.
     * @return Cita registrada.
     * @throws IllegalOperationException Si hay una operación ilegal.
     */
    Cita registrarCita(Cita cita) throws IllegalOperationException;

    /**
     * Obtiene una cita por su ID.
     *
     * @param id ID de la cita.
     * @return Cita encontrada (si existe).
     * @throws IllegalOperationException Si hay una operación ilegal.
     */
    Optional<Cita> obtenerCitaPorId(Long id) throws IllegalOperationException;

    /**
     * Elimina una cita por su ID.
     *
     * @param id ID de la cita a eliminar.
     * @throws IllegalOperationException Si hay una operación ilegal.
     */
    void eliminarCita(Long id) throws IllegalOperationException;

    /**
     * Actualiza una cita por su ID.
     *
     * @param id   ID de la cita a actualizar.
     * @param cita Datos actualizados de la cita.
     * @return Cita actualizada.
     * @throws IllegalOperationException Si hay una operación ilegal.
     */
    Cita actualizarCita(Long id, Cita cita) throws IllegalOperationException;

    /**
     * Obtiene la lista de citas para un paciente específico.
     *
     * @param id ID del paciente.
     * @return Lista de citas del paciente.
     * @throws IllegalOperationException Si hay una operación ilegal.
     */
    List<Cita> listarCitasPorPaciente(Long id) throws IllegalOperationException;

    /**
     * Obtiene la lista de citas para un doctor específico.
     *
     * @param id ID del doctor.
     * @return Lista de citas del doctor.
     * @throws IllegalOperationException Si hay una operación ilegal.
     */
    List<Cita> listarCitasPorDoctor(Long id) throws IllegalOperationException;
}
