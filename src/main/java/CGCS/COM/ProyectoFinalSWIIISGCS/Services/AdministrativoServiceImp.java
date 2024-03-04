package CGCS.COM.ProyectoFinalSWIIISGCS.Services;

import CGCS.COM.ProyectoFinalSWIIISGCS.Domain.Administrativo;
import CGCS.COM.ProyectoFinalSWIIISGCS.Repositories.AdministrativoRepository;
import CGCS.COM.ProyectoFinalSWIIISGCS.exception.IllegalOperationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Servicio para la gestión de Administrativos, implementando la interfaz AdministrativoService.
 * Proporciona funcionalidades para listar, registrar, obtener, eliminar y actualizar Administrativos.
 */
@Service
public class AdministrativoServiceImp implements AdministrativoService{
    @Autowired
    private AdministrativoRepository AdministrativoRepository; // Repositorio para acceso a datos de Administrativo.

    /**
     * Lista todas las Administrativos disponibles.
     *
     * @return lista de Administrativos.
     */
    @Override
    public List<Administrativo> listarAdministrativo() {
        return AdministrativoRepository.findAll();
    }

    /**
     * Registra un nuevo Administrativo en el sistema.
     *
     * @param Administrativo La Administrativo a registrar.
     * @return eñ Administrativo registrada con su ID asignado.
     */
    @Override
    public Administrativo registrarAdministrativo(Administrativo Administrativo) {
        return AdministrativoRepository.save(Administrativo);
    }

    /**
     * Obtiene un Administrativo por su ID.
     *
     * @param id El ID del Administrativo a obtener.
     * @return Un Optional conteniendo el Administrativo si se encontró, o vacío si no.
     * @throws IllegalOperationException Si la operación no se puede realizar.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Administrativo> obtenerAdministrativoPorId(Long id) throws IllegalOperationException {
        return AdministrativoRepository.findById(id);
    }

    /**
     * Elimina un Administrativo del sistema por su ID.
     *
     * @param id El ID del Administrativo a eliminar.
     * @throws IllegalOperationException Si el Administrativo no existe o no se puede eliminar.
     */
    @Override
    public void eliminarAdministrativo(Long id) throws IllegalOperationException {
        Optional<Administrativo> optionalAdministrativo = AdministrativoRepository.findById(id);
        if (optionalAdministrativo.isPresent()) {
            AdministrativoRepository.delete(optionalAdministrativo.get());
        } else {
            throw new IllegalOperationException("No se encontró el Administrativo con el ID proporcionado: " + id);
        }
    }

    /**
     * Actualiza los datos de un Administrativo existente.
     *
     * @param id El ID del Administrativo a actualizar.
     * @param Administrativo El Administrativo con los datos actualizados.
     * @return el Administrativo actualizado.
     * @throws IllegalOperationException Si el Administrativo no existe o no se puede actualizar.
     */
    @Override
    public Administrativo actualizarAdministrativo(Long id, Administrativo Administrativo) throws IllegalOperationException {
        Optional<Administrativo> optionalAdministrativo = AdministrativoRepository.findById(id);
        if (optionalAdministrativo.isPresent()) {
            Administrativo AdministrativoExistente = optionalAdministrativo.get();
            AdministrativoExistente.setNombres(Administrativo.getNombres());
            AdministrativoExistente.setApellidos(Administrativo.getApellidos());
            AdministrativoExistente.setEmail(Administrativo.getEmail());
            AdministrativoExistente.setTelefono(Administrativo.getTelefono());

            // Actualiza otros campos según sea necesario.
            return AdministrativoRepository.save(AdministrativoExistente);
        } else {
            throw new IllegalOperationException("No se encontró el Administrativo con el ID proporcionado: " + id);
        }
    }
}
