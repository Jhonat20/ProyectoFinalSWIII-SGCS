package CGCS.COM.ProyectoFinalSWIIISGCS.Services;

import CGCS.COM.ProyectoFinalSWIIISGCS.Domain.HistorialMedico;
import CGCS.COM.ProyectoFinalSWIIISGCS.exception.IllegalOperationException;
import jakarta.persistence.EntityNotFoundException;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface HistorialMedicoService {

    List<HistorialMedico> listarHistorialMedico();
    Optional<HistorialMedico> BuscarPorId(Long id) throws IllegalOperationException;
    HistorialMedico Grabar(HistorialMedico historialMedico)  throws IllegalOperationException;
    HistorialMedico Actualizar(Long id, HistorialMedico historialMedico) throws IllegalOperationException, EntityNotFoundException;
    HistorialMedico actualizarParcial(Long id, Map<String, Object> cambios);
    void Eliminar(Long id) throws EntityNotFoundException, IllegalOperationException;

}
