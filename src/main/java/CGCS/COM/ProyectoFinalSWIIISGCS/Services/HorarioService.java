package CGCS.COM.ProyectoFinalSWIIISGCS.Services;

import CGCS.COM.ProyectoFinalSWIIISGCS.Domain.Horario;
import CGCS.COM.ProyectoFinalSWIIISGCS.exception.IllegalOperationException;
import jakarta.persistence.EntityNotFoundException;

import java.util.List;
import java.util.Optional;

public interface HorarioService {
    List<Horario> listarHorario();
    Optional <Horario> buscarPorId(Long id);
    Horario guardadHorario(Horario horario) throws IllegalOperationException;
    Horario actualizarHorario(Long id, Horario horario)throws EntityNotFoundException;
    void Eliminar(Long id);
}
