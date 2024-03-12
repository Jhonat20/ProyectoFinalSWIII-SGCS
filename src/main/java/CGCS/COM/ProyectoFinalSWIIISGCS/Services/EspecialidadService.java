package CGCS.COM.ProyectoFinalSWIIISGCS.Services;

import CGCS.COM.ProyectoFinalSWIIISGCS.Domain.Especialidad;
import CGCS.COM.ProyectoFinalSWIIISGCS.exception.IllegalOperationException;

import java.util.List;
import java.util.Optional;

/**
 * Servicio para la gestión de especialidades médicas.
 */
public interface EspecialidadService {

    /**
     * Obtiene la lista de todas las especialidades.
     *
     * @return Lista de especialidades.
     * @throws IllegalOperationException Si se produce una operación ilegal.
     */
    List<Especialidad> listarEspecialidades() throws IllegalOperationException;

    /**
     * Registra una nueva especialidad.
     *
     * @param especialidad La especialidad a registrar.
     * @return La especialidad registrada.
     * @throws IllegalOperationException Si se produce una operación ilegal.
     */
    Especialidad registrarEspecialidad(Especialidad especialidad) throws IllegalOperationException;

    /**
     * Busca una especialidad por su identificador único.
     *
     * @param id El identificador único de la especialidad.
     * @return Optional que contiene la especialidad encontrada, o vacío si no se encuentra.
     * @throws IllegalOperationException Si se produce una operación ilegal.
     */
    Optional<Especialidad> buscarEspecialidadPorId(Long id) throws IllegalOperationException;

    /**
     * Elimina una especialidad por su identificador único.
     *
     * @param id El identificador único de la especialidad a eliminar.
     * @throws IllegalOperationException Si se produce una operación ilegal.
     */
    void eliminarEspecialidad(Long id) throws IllegalOperationException;

    /**
     * Modifica una especialidad existente por su identificador único.
     *
     * @param id          El identificador único de la especialidad a modificar.
     * @param especialidad La nueva información de la especialidad.
     * @return La especialidad modificada.
     * @throws IllegalOperationException Si se produce una operación ilegal.
     */
    Especialidad modificarEspecialidad(Long id, Especialidad especialidad) throws IllegalOperationException;
}
