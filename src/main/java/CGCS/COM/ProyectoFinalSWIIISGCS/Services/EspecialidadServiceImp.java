package CGCS.COM.ProyectoFinalSWIIISGCS.Services;

import CGCS.COM.ProyectoFinalSWIIISGCS.Domain.Especialidad;
import CGCS.COM.ProyectoFinalSWIIISGCS.Repositories.EspecialidadRepository;
import CGCS.COM.ProyectoFinalSWIIISGCS.exception.IllegalOperationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Implementación del servicio para la gestión de especialidades médicas.
 */
@Service
public class EspecialidadServiceImp implements EspecialidadService {

    @Autowired
    private EspecialidadRepository especialidadRepository;

    @Override
    public List<Especialidad> listarEspecialidades() throws IllegalOperationException {
        return (List<Especialidad>) especialidadRepository.findAll();
    }

    @Override
    public Especialidad registrarEspecialidad(Especialidad especialidad) throws IllegalOperationException {
        return especialidadRepository.save(especialidad);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Especialidad> buscarEspecialidadPorId(Long id) throws IllegalOperationException {
        return especialidadRepository.findById(id);
    }

    @Override
    public void eliminarEspecialidad(Long id) throws IllegalOperationException {
        Optional<Especialidad> optionalEspecialidad = especialidadRepository.findById(id);
        if (optionalEspecialidad.isPresent()) {
            especialidadRepository.delete(optionalEspecialidad.get());
        } else {
            throw new IllegalOperationException("No se encontró la especialidad con el ID proporcionado: " + id);
        }
    }

    @Override
    public Especialidad modificarEspecialidad(Long id, Especialidad especialidad) throws IllegalOperationException {
        Optional<Especialidad> optionalEspecialidad = especialidadRepository.findById(id);
        if (optionalEspecialidad.isPresent()) {
            Especialidad especialidadExistente = optionalEspecialidad.get();
            especialidadExistente.setNombre(especialidad.getNombre());
            // Actualiza otros campos según sea necesario
            return especialidadRepository.save(especialidadExistente);
        } else {
            throw new IllegalOperationException("No se encontró la especialidad con el ID proporcionado: " + id);
        }
    }
}
