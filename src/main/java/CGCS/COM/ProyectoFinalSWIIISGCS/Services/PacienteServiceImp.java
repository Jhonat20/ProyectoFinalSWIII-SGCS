package CGCS.COM.ProyectoFinalSWIIISGCS.Services;

import CGCS.COM.ProyectoFinalSWIIISGCS.Domain.Cita;
import CGCS.COM.ProyectoFinalSWIIISGCS.Domain.Paciente;
import CGCS.COM.ProyectoFinalSWIIISGCS.Repositories.CitaRepository;
import CGCS.COM.ProyectoFinalSWIIISGCS.Repositories.PacienteRepository;
import CGCS.COM.ProyectoFinalSWIIISGCS.exception.IllegalOperationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

/**
 * Servicio para la gestión de pacientes, implementando la interfaz PacienteService.
 * Ofrece funcionalidades para listar, registrar, obtener por ID, eliminar y actualizar pacientes.
 */
@Service
public class PacienteServiceImp implements PacienteService {

    @Autowired
    private PacienteRepository pacienteRepository; // Repositorio para el acceso a datos de pacientes.

    @Autowired
    private CitaRepository citaRepository;//Repositorio para el acceso a datos de citas
    /**
     * Lista todos los pacientes registrados en el sistema.
     *
     * @return Una lista de objetos Paciente.
     * @throws IllegalOperationException Si ocurre un error durante la operación.
     */
    @Override
    public List<Paciente> listarPacientes() throws IllegalOperationException {
        return pacienteRepository.findAll();
    }

    /**
     * Registra un nuevo paciente en el sistema.
     *
     * @param paciente Objeto Paciente a registrar.
     * @return El objeto Paciente registrado con su ID asignado.
     * @throws IllegalOperationException Si ocurre un error durante la operación.
     */
    @Override
    public Paciente registrarPaciente(Paciente paciente) throws IllegalOperationException {
        return pacienteRepository.save(paciente);
    }

    /**
     * Obtiene los detalles de un paciente por su ID.
     *
     * @param id ID del paciente a buscar.
     * @return Un Optional que contiene el objeto Paciente si se encontró, o vacío en caso contrario.
     * @throws IllegalOperationException Si ocurre un error durante la operación o el paciente no se encuentra.
     */
    @Override
    public Optional<Paciente> obtenerPacientePorId(Long id) throws IllegalOperationException {
        return pacienteRepository.findById(id);
    }

    /**
     * Elimina un paciente del sistema por su ID.
     *
     * @param id ID del paciente a eliminar.
     * @throws IllegalOperationException Si el paciente no existe o no se puede eliminar.
     */
    @Override
    public void eliminarPaciente(Long id) throws IllegalOperationException {
        Optional<Paciente> optionalPaciente = pacienteRepository.findById(id);
        if (optionalPaciente.isPresent()) {
            pacienteRepository.delete(optionalPaciente.get());
        } else {
            throw new IllegalOperationException("No se encontró el paciente con el ID proporcionado: " + id);
        }
    }

    /**
     * Actualiza los datos de un paciente existente.
     *
     * @param id ID del paciente a actualizar.
     * @param paciente Objeto Paciente con los datos actualizados.
     * @return El objeto Paciente actualizado.
     * @throws IllegalOperationException Si el paciente no existe o no se puede actualizar.
     */
    @Override
    public Paciente actualizarPaciente(Long id, Paciente paciente) throws IllegalOperationException {
        Optional<Paciente> optionalPaciente = pacienteRepository.findById(id);
        if (optionalPaciente.isPresent()) {
            Paciente pacienteExistente = optionalPaciente.get();
            // Actualiza los datos del paciente con la información proporcionada.
            pacienteExistente.setNombre(paciente.getNombre());
            pacienteExistente.setApellido(paciente.getApellido());
            pacienteExistente.setDni(paciente.getDni());
            pacienteExistente.setTelefono(paciente.getTelefono());
            pacienteExistente.setEmail(paciente.getEmail());
            pacienteExistente.setDireccion(paciente.getDireccion());
            pacienteExistente.setFechaNacimiento(paciente.getFechaNacimiento());
            pacienteExistente.setGrupoSanguineo(paciente.getGrupoSanguineo());
            return pacienteRepository.save(pacienteExistente);
        } else {
            throw new IllegalOperationException("No se encontró el paciente con el ID proporcionado: " + id);
        }
    }

    @Override
    public void agregarCitaAPaciente(long idPaciente, long idCita) {
        Optional<Paciente> pacienteOptional = pacienteRepository.findById(idPaciente);
        Optional<Cita> citaOptional = citaRepository.findById(idCita);
        if (pacienteOptional.isPresent() && citaOptional.isPresent()) {
            Paciente paciente = pacienteOptional.get();
           Cita cita = citaOptional.get();
            cita.setPaciente(paciente);
            citaRepository.save(cita);
        } else {
            throw new NoSuchElementException("La cita o paciente  no existen");
        }
    }
}
