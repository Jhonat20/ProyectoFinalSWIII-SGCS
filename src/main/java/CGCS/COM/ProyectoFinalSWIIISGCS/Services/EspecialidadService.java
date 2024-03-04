package CGCS.COM.ProyectoFinalSWIIISGCS.Services;

import CGCS.COM.ProyectoFinalSWIIISGCS.Domain.Especialidad;
import CGCS.COM.ProyectoFinalSWIIISGCS.exception.IllegalOperationException;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;
import java.util.Optional;

public interface EspecialidadService {

    List<Especialidad> listarEspecialidades() throws IllegalOperationException;

    Especialidad registrarEspecialidad(Especialidad especialidad) throws IllegalOperationException;

    Optional<Especialidad> buscarEspecialidadPorId(Long id) throws IllegalOperationException;

    void eliminarEspecialidad(Long id) throws IllegalOperationException;

    Especialidad modificarEspecialidad(Long id, Especialidad especialidad) throws IllegalOperationException;
}
