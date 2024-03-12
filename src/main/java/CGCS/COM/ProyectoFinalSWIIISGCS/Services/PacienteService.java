package CGCS.COM.ProyectoFinalSWIIISGCS.Services;

import CGCS.COM.ProyectoFinalSWIIISGCS.Domain.Paciente;
import CGCS.COM.ProyectoFinalSWIIISGCS.exception.IllegalOperationException;

import java.util.List;
import java.util.Optional;

/**
 * Servicio para la gestión de pacientes.
 */
public interface PacienteService {

    /**
     * Lista todos los pacientes disponibles.
     *
     * @return Lista de pacientes.
     * @throws IllegalOperationException Si ocurre una operación ilegal.
     */
    List<Paciente> listarPacientes() throws IllegalOperationException;

    /**
     * Registra un nuevo paciente.
     *
     * @param paciente Paciente a registrar.
     * @return Paciente registrado.
     * @throws IllegalOperationException Si ocurre una operación ilegal.
     */
    Paciente registrarPaciente(Paciente paciente) throws IllegalOperationException;

    /**
     * Obtiene un paciente por su ID.
     *
     * @param id ID del paciente a buscar.
     * @return Paciente encontrado.
     * @throws IllegalOperationException Si ocurre una operación ilegal.
     */
    Optional<Paciente> obtenerPacientePorId(Long id) throws IllegalOperationException;

    /**
     * Elimina un paciente por su ID.
     *
     * @param id ID del paciente a eliminar.
     * @throws IllegalOperationException Si ocurre una operación ilegal.
     */
    void eliminarPaciente(Long id) throws IllegalOperationException;

    /**
     * Actualiza un paciente existente por su ID.
     *
     * @param id       ID del paciente a actualizar.
     * @param paciente Datos actualizados del paciente.
     * @return Paciente actualizado.
     * @throws IllegalOperationException Si ocurre una operación ilegal.
     */
    Paciente actualizarPaciente(Long id, Paciente paciente) throws IllegalOperationException;

    /**
     * Agrega una cita a la lista de citas de un paciente.
     *
     * @param idPaciente ID del paciente.
     * @param idCita     ID de la cita a agregar.
     * @throws IllegalOperationException Si ocurre una operación ilegal.
     */
    void agregarCitaAPaciente(long idPaciente, long idCita) throws IllegalOperationException;

    /**
     * Agrega un historial médico a la lista de historiales médicos de un paciente.
     *
     * @param idPaciente         ID del paciente.
     * @param idHistorialMedico  ID del historial médico a agregar.
     * @throws IllegalOperationException Si ocurre una operación ilegal.
     */
    void agregarHistorialMedicoAPaciente(long idPaciente, long idHistorialMedico) throws IllegalOperationException;
}
