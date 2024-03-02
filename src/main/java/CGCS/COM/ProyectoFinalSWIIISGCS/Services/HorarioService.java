package CGCS.COM.ProyectoFinalSWIIISGCS.Services;

import CGCS.COM.ProyectoFinalSWIIISGCS.Domain.Horario;
import CGCS.COM.ProyectoFinalSWIIISGCS.exception.IllegalOperationException;
import jakarta.persistence.EntityNotFoundException;

import java.util.List;

public interface HorarioService {
    List<Horario> listarTodos();
    Horario BuscarPorId(Long id);
    Horario Grabar(Horario horario) throws IllegalOperationException;
    Horario Actualizar(Long id, Horario horario)throws EntityNotFoundException;
    void Eliminar(Long id);

}
