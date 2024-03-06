package CGCS.COM.ProyectoFinalSWIIISGCS.Services;

import CGCS.COM.ProyectoFinalSWIIISGCS.Domain.Cita;
import CGCS.COM.ProyectoFinalSWIIISGCS.Repositories.CitaRepository;
import CGCS.COM.ProyectoFinalSWIIISGCS.exception.IllegalOperationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Servicio para la gestión de citas, implementando la interfaz CitaService.
 * Proporciona funcionalidades para listar, registrar, obtener, eliminar y actualizar citas.
 */
@Service
public class CitaServiceImp implements CitaService {

    @Autowired
    private CitaRepository citaRepository; // Repositorio para acceso a datos de citas.

    /**
     * Lista todas las citas disponibles.
     *
     * @return lista de citas.
     */
    @Override
    public List<Cita> listarCitas() {
        return citaRepository.findAll();
    }

    /**
     * Registra una nueva cita en el sistema.
     *
     * @param cita La cita a registrar.
     * @return La cita registrada con su ID asignado.
     */
    @Override
    public Cita registrarCita(Cita cita) {
        return citaRepository.save(cita);
    }

    /**
     * Obtiene una cita por su ID.
     *
     * @param id El ID de la cita a obtener.
     * @return Un Optional conteniendo la cita si se encontró, o vacío si no.
     * @throws IllegalOperationException Si la operación no se puede realizar.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Cita> obtenerCitaPorId(Long id) throws IllegalOperationException {
        return citaRepository.findById(id);
    }

    /**
     * Elimina una cita del sistema por su ID.
     *
     * @param id El ID de la cita a eliminar.
     * @throws IllegalOperationException Si la cita no existe o no se puede eliminar.
     */
    @Override
    public void eliminarCita(Long id) throws IllegalOperationException {
        Optional<Cita> optionalCita = citaRepository.findById(id);
        if (optionalCita.isPresent()) {
            citaRepository.delete(optionalCita.get());
        } else {
            throw new IllegalOperationException("No se encontró la cita con el ID proporcionado: " + id);
        }
    }

    /**
     * Actualiza los datos de una cita existente.
     *
     * @param id El ID de la cita a actualizar.
     * @param cita La cita con los datos actualizados.
     * @return La cita actualizada.
     * @throws IllegalOperationException Si la cita no existe o no se puede actualizar.
     */
    @Override
    public Cita actualizarCita(Long id, Cita cita) throws IllegalOperationException {
        Optional<Cita> optionalCita = citaRepository.findById(id);
        if (optionalCita.isPresent()) {
            Cita citaExistente = optionalCita.get();
            citaExistente.setDescripcion(cita.getDescripcion());
            citaExistente.setFechaHora(cita.getFechaHora());
            citaExistente.setEstado(cita.getEstado());
            // Actualiza otros campos según sea necesario.
            return citaRepository.save(citaExistente);
        } else {
            throw new IllegalOperationException("No se encontró la cita con el ID proporcionado: " + id);
        }
    }
}
