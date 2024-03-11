package CGCS.COM.ProyectoFinalSWIIISGCS.Services;

import CGCS.COM.ProyectoFinalSWIIISGCS.Domain.Cita;
import CGCS.COM.ProyectoFinalSWIIISGCS.exception.IllegalOperationException;

import java.util.List;
import java.util.Optional;

public interface CitaService {

    List<Cita> listarCitas() throws IllegalOperationException;

    Cita registrarCita(Cita cita) throws IllegalOperationException;

    Optional<Cita> obtenerCitaPorId(Long id) throws IllegalOperationException;

    void eliminarCita(Long id) throws IllegalOperationException;

    Cita actualizarCita(Long id, Cita cita) throws IllegalOperationException;

    List <Cita> listarCitasPorPaciente(Long id) throws IllegalOperationException;
    List <Cita> listarCitasPorDoctor(Long id) throws IllegalOperationException;

}

