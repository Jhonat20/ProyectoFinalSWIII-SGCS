package CGCS.COM.ProyectoFinalSWIIISGCS.Services;

import CGCS.COM.ProyectoFinalSWIIISGCS.Domain.Paciente;
import CGCS.COM.ProyectoFinalSWIIISGCS.Repositories.PacienteRepository;
import CGCS.COM.ProyectoFinalSWIIISGCS.exception.IllegalOperationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PacienteServiceImp implements PacienteService {

    @Autowired
    private PacienteRepository pacienteRepository;

    @Override
    public List<Paciente> listarPacientes() throws IllegalOperationException {
        return pacienteRepository.findAll();
    }

    @Override
    public Paciente registrarPaciente(Paciente paciente) throws IllegalOperationException {
        return pacienteRepository.save(paciente);
    }

    @Override
    public Optional<Paciente> obtenerPacientePorId(Long id) throws IllegalOperationException {
        return pacienteRepository.findById(id);
    }

    @Override
    public void eliminarPaciente(Long id) throws IllegalOperationException {
        Optional<Paciente> optionalPaciente = pacienteRepository.findById(id);
        if (optionalPaciente.isPresent()) {
            pacienteRepository.delete(optionalPaciente.get());
        } else {
            throw new IllegalOperationException("No se encontró el paciente con el ID proporcionado: " + id);
        }
    }

    @Override
    public Paciente actualizarPaciente(Long id, Paciente paciente) throws IllegalOperationException {
        Optional<Paciente> optionalPaciente = pacienteRepository.findById(id);
        if (optionalPaciente.isPresent()) {
            Paciente pacienteExistente = optionalPaciente.get();
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
}

