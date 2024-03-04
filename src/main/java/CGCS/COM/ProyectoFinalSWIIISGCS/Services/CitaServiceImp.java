package CGCS.COM.ProyectoFinalSWIIISGCS.Services;

import CGCS.COM.ProyectoFinalSWIIISGCS.Domain.Cita;
import CGCS.COM.ProyectoFinalSWIIISGCS.Repositories.CitaRepository;
import CGCS.COM.ProyectoFinalSWIIISGCS.exception.IllegalOperationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CitaServiceImp implements CitaService {

    @Autowired
    public CitaRepository citaRepository;

    @Override
    public List<Cita> listarCitas() {
        return citaRepository.findAll();
    }

    @Override
    public Cita registrarCita(Cita cita) {
        return citaRepository.save(cita);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Cita> obtenerCitaPorId(Long id) throws IllegalOperationException {
        return citaRepository.findById(id);
    }

    @Override
    public void eliminarCita(Long id) throws IllegalOperationException {
        Optional<Cita> optionalCita = citaRepository.findById(id);
        if (optionalCita.isPresent()) {
            citaRepository.delete(optionalCita.get());
        } else {
            throw new IllegalOperationException("No se encontró la cita con el ID proporcionado: " + id);
        }
    }

    @Override
    public Cita actualizarCita(Long id, Cita cita) throws IllegalOperationException {
        Optional<Cita> optionalCita = citaRepository.findById(id);
        if (optionalCita.isPresent()) {
            Cita citaExistente = optionalCita.get();
            citaExistente.setNombrePaciente(cita.getNombrePaciente());
            citaExistente.setNombreDoctor(cita.getNombreDoctor());
            citaExistente.setDescripcion(cita.getDescripcion());
            citaExistente.setFechaHora(cita.getFechaHora());
            citaExistente.setEstado(cita.getEstado());
            // Actualiza otros campos según sea necesario
            return citaRepository.save(citaExistente);
        } else {
            throw new IllegalOperationException("No se encontró la cita con el ID proporcionado: " + id);
        }
    }
}

