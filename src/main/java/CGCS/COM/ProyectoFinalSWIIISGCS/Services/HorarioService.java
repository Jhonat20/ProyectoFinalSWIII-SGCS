package CGCS.COM.ProyectoFinalSWIIISGCS.Services;

import CGCS.COM.ProyectoFinalSWIIISGCS.Domain.Horario;
import CGCS.COM.ProyectoFinalSWIIISGCS.exception.IllegalOperationException;
import jakarta.persistence.EntityNotFoundException;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface HorarioService {
    List<Horario> listarHorario();

    Optional<Horario> buscarPorId(Long id)throws EntityNotFoundException;

    Horario guardadHorario(Horario horario) throws IllegalOperationException;

    Horario actualizarHorario(Long id, Horario horario) throws EntityNotFoundException, IllegalOperationException;

    void Eliminar(Long id);

    Horario actualizarParcial(Long id, Map<String, Object> cambios) throws IllegalOperationException;

    Horario asignarDoctor(Long horarioId, Long doctorId) throws IllegalOperationException;
}
