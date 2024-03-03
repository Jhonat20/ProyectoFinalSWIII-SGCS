package CGCS.COM.ProyectoFinalSWIIISGCS.Services;

import CGCS.COM.ProyectoFinalSWIIISGCS.Domain.HistorialMedico;
import CGCS.COM.ProyectoFinalSWIIISGCS.exception.IllegalOperationException;

import java.util.List;
import java.util.Optional;

public interface HistorialMedicoService {

    List<HistorialMedico> listarHistorialMedico();
    Optional<HistorialMedico> BuscarPorId(Long id);
    HistorialMedico Grabar(HistorialMedico historialMedico) throws IllegalOperationException;
    HistorialMedico Actualizar(Long id, HistorialMedico historialMedico) throws IllegalOperationException;
    void Eliminar(Long id) throws IllegalOperationException;



}
