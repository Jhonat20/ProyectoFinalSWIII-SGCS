package CGCS.COM.ProyectoFinalSWIIISGCS.Services;

import CGCS.COM.ProyectoFinalSWIIISGCS.Domain.HistorialMedico;
import CGCS.COM.ProyectoFinalSWIIISGCS.exception.IllegalOperationException;
import jakarta.persistence.EntityNotFoundException;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Servicio para la gestión de historiales médicos.
 */
public interface HistorialMedicoService {

    /**
     * Lista todos los historiales médicos disponibles.
     *
     * @return Lista de historiales médicos.
     */
    List<HistorialMedico> listarHistorialMedico();

    /**
     * Busca un historial médico por su ID.
     *
     * @param id ID del historial médico a buscar.
     * @return Historial médico encontrado.
     * @throws IllegalOperationException Si ocurre una operación ilegal.
     */
    Optional<HistorialMedico> BuscarPorId(Long id) throws IllegalOperationException;

    /**
     * Guarda un nuevo historial médico.
     *
     * @param historialMedico Historial médico a guardar.
     * @return Historial médico guardado.
     * @throws IllegalOperationException Si ocurre una operación ilegal.
     */
    HistorialMedico Grabar(HistorialMedico historialMedico) throws IllegalOperationException;

    /**
     * Actualiza un historial médico existente por su ID.
     *
     * @param id              ID del historial médico a actualizar.
     * @param historialMedico Datos actualizados del historial médico.
     * @return Historial médico actualizado.
     * @throws IllegalOperationException Si ocurre una operación ilegal.
     * @throws EntityNotFoundException   Si el historial médico no se encuentra.
     */
    HistorialMedico Actualizar(Long id, HistorialMedico historialMedico) throws IllegalOperationException, EntityNotFoundException;

    /**
     * Actualiza parcialmente un historial médico existente por su ID.
     *
     * @param id      ID del historial médico a actualizar.
     * @param cambios Mapa que contiene los cambios a aplicar.
     * @return Historial médico actualizado parcialmente.
     * @throws IllegalOperationException Si ocurre una operación ilegal.
     */
    HistorialMedico actualizarParcial(Long id, Map<String, Object> cambios);

    /**
     * Elimina un historial médico por su ID.
     *
     * @param id ID del historial médico a eliminar.
     * @throws EntityNotFoundException   Si el historial médico no se encuentra.
     * @throws IllegalOperationException Si ocurre una operación ilegal.
     */
    void Eliminar(Long id) throws EntityNotFoundException, IllegalOperationException;
}
