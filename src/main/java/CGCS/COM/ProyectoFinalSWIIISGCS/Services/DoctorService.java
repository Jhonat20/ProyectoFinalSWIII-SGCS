/**
 * @file: DoctorService.java
 * @created: Fecha de creación
 * @author:
 */

package CGCS.COM.ProyectoFinalSWIIISGCS.Services;

import CGCS.COM.ProyectoFinalSWIIISGCS.Domain.Doctor;
import CGCS.COM.ProyectoFinalSWIIISGCS.Domain.Horario;
import CGCS.COM.ProyectoFinalSWIIISGCS.exception.IllegalOperationException;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * Interfaz que define los servicios para la entidad Doctor.
 */
public interface DoctorService {

    /**
     * Obtiene la lista de todos los doctores.
     *
     * @return Lista de doctores.
     * @throws IllegalOperationException Si hay una operación ilegal.
     */
    List<Doctor> listarDoctores() throws IllegalOperationException;

    /**
     * Registra un nuevo doctor.
     *
     * @param doctor Doctor a registrar.
     * @return Doctor registrado.
     * @throws IllegalOperationException Si hay una operación ilegal.
     */
    Doctor registrarDoctor(Doctor doctor) throws IllegalOperationException;

    /**
     * Obtiene un doctor por su ID.
     *
     * @param id ID del doctor.
     * @return Doctor encontrado (si existe).
     * @throws IllegalOperationException Si hay una operación ilegal.
     */
    Optional<Doctor> obtenerDoctorPorId(Long id) throws IllegalOperationException;

    /**
     * Elimina un doctor por su ID.
     *
     * @param id ID del doctor a eliminar.
     * @throws IllegalOperationException Si hay una operación ilegal.
     */
    void eliminarDoctor(Long id) throws IllegalOperationException;

    /**
     * Actualiza un doctor por su ID.
     *
     * @param id     ID del doctor a actualizar.
     * @param doctor Datos actualizados del doctor.
     * @return Doctor actualizado.
     * @throws IllegalOperationException Si hay una operación ilegal.
     */
    Doctor actualizarDoctor(Long id, Doctor doctor) throws IllegalOperationException;

    /**
     * Asigna una cita a un doctor.
     *
     * @param doctorId ID del doctor.
     * @param citaId   ID de la cita a asignar.
     * @return Doctor actualizado con la cita asignada.
     * @throws IllegalOperationException Si hay una operación ilegal.
     */
    Doctor asignarCitaDoctor(Long doctorId, Long citaId) throws IllegalOperationException;

    /**
     * Asigna una especialidad a un doctor.
     *
     * @param doctorId       ID del doctor.
     * @param especialidadId ID de la especialidad a asignar.
     * @return Doctor actualizado con la especialidad asignada.
     * @throws IllegalOperationException Si hay una operación ilegal.
     */
    Doctor asignarEspecialidadDoctor(Long doctorId, Long especialidadId) throws IllegalOperationException;

    /**
     * Desasigna una cita de un doctor.
     *
     * @param doctorId ID del doctor.
     * @param citaId   ID de la cita a desasignar.
     * @return Doctor actualizado sin la cita asignada.
     * @throws IllegalOperationException Si hay una operación ilegal.
     */
    Doctor desasignarCitaDoctor(Long doctorId, Long citaId) throws IllegalOperationException;
}