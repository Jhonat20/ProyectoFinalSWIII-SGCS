package CGCS.COM.ProyectoFinalSWIIISGCS.Services;

import CGCS.COM.ProyectoFinalSWIIISGCS.Domain.Paciente;
import CGCS.COM.ProyectoFinalSWIIISGCS.exception.IllegalOperationException;

import java.util.List;
import java.util.Optional;

public interface PacienteService {

    List<Paciente> listarPacientes() throws IllegalOperationException;

    Paciente registrarPaciente(Paciente paciente) throws IllegalOperationException;

    Optional<Paciente> obtenerPacientePorId(Long id) throws IllegalOperationException;

    void eliminarPaciente(Long id) throws IllegalOperationException;

    Paciente actualizarPaciente(Long id, Paciente paciente) throws IllegalOperationException;



}
