package CGCS.COM.ProyectoFinalSWIIISGCS.Services;

import CGCS.COM.ProyectoFinalSWIIISGCS.Domain.Administrativo;
import CGCS.COM.ProyectoFinalSWIIISGCS.Domain.Cita;
import CGCS.COM.ProyectoFinalSWIIISGCS.exception.IllegalOperationException;

import java.util.List;
import java.util.Optional;

public interface AdministrativoService {
        List<Administrativo> listarAdministrativo() throws IllegalOperationException;

    Administrativo registrarAdministrativo(Administrativo Administrativo) throws IllegalOperationException;

        Optional<Administrativo> obtenerAdministrativoPorId(Long id) throws IllegalOperationException;

        void eliminarAdministrativo(Long id) throws IllegalOperationException;

        Administrativo actualizarAdministrativo(Long id, Administrativo Administrativo) throws IllegalOperationException;
}
