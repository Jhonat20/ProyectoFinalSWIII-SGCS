package CGCS.COM.ProyectoFinalSWIIISGCS.Services;

import CGCS.COM.ProyectoFinalSWIIISGCS.Domain.HistorialMedico;
import CGCS.COM.ProyectoFinalSWIIISGCS.Repositories.HistorialMedicoRepository;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Implementación del servicio para manejar operaciones relacionadas con el historial médico.
 */
@Service
public class HistorialMedicoServiceImp implements HistorialMedicoService {

    @Autowired
    private HistorialMedicoRepository histMedRep;

    /**
     * Recupera una lista de todos los historiales médicos.
     *
     * @return Lista de historiales médicos.
     */
    @Override
    @Transactional
    public List<HistorialMedico> listarHistorialMedico() {
        return histMedRep.findAll();
    }

    /**
     * Busca un historial médico por su ID.
     *
     * @param id ID del historial médico a buscar.
     * @return Optional que contiene el historial médico si se encuentra, de lo contrario, Optional vacío.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<HistorialMedico> BuscarPorId(Long id) {
        return histMedRep.findById(id);
    }

    /**
     * Guarda un nuevo historial médico en la base de datos.
     *
     * @param historialMedico El historial médico a guardar.
     * @return El historial médico guardado.
     */
    @Override
    @Transactional
    public HistorialMedico Grabar(HistorialMedico historialMedico) {
        asignarValoresPredeterminados(historialMedico);
        return histMedRep.save(historialMedico);
    }

    /**
     * Actualiza un historial médico existente en la base de datos.
     *
     * @param id              ID del historial médico a actualizar.
     * @param historialMedico El nuevo historial médico con los cambios.
     * @return El historial médico actualizado.
     * @throws EntityNotFoundException Si no se encuentra el historial médico con el ID especificado.
     */
    @Override
    @Transactional
    public HistorialMedico Actualizar(Long id, HistorialMedico historialMedico) {
        Optional<HistorialMedico> historialMedicoOptional = obtenerHistorialMedicoExistente(id);
        if (historialMedicoOptional.isPresent()) {
            HistorialMedico historialMedicoExistente = historialMedicoOptional.get();
            actualizarCamposHistorialMedico(historialMedicoExistente, historialMedico);
            return histMedRep.save(historialMedicoExistente);
        } else {
            throw new EntityNotFoundException("Historial médico no encontrado con ID: " + id);
        }
    }

    /**
     * Elimina un historial médico de la base de datos.
     *
     * @param id ID del historial médico a eliminar.
     * @throws EntityNotFoundException Si no se encuentra el historial médico con el ID especificado.
     */
    @Override
    @Transactional
    public void Eliminar(Long id) {
        histMedRep.findById(id).ifPresentOrElse(histMedRep::delete,
                () -> {
                    throw new EntityNotFoundException("Historial médico no encontrado con ID: " + id);
                });
    }

    /**
     * Actualiza parcialmente un historial médico existente en la base de datos.
     *
     * @param id      ID del historial médico a actualizar.
     * @param cambios Mapa que contiene los cambios a aplicar al historial médico.
     * @return El historial médico actualizado parcialmente.
     * @throws EntityNotFoundException Si no se encuentra el historial médico con el ID especificado.
     */
    @Override
    @Transactional
    public HistorialMedico actualizarParcial(Long id, Map<String, Object> cambios) {
        Optional<HistorialMedico> historialMedicoOptional = histMedRep.findById(id);
        if (historialMedicoOptional.isEmpty()) {
            throw new EntityNotFoundException("Historial médico no encontrado con ID: " + id);
        }

        HistorialMedico historialMedico = historialMedicoOptional.get();
        aplicarCambiosParciales(historialMedico, cambios);
        return histMedRep.save(historialMedico);
    }

    // Métodos privados

    private Optional<HistorialMedico> obtenerHistorialMedicoExistente(Long id) {
        return histMedRep.findById(id);
    }

    private void asignarValoresPredeterminados(HistorialMedico historialMedico) {
        if (historialMedico.getAlergias() == null) {
            historialMedico.setAlergias("Ninguno");
        }
        if (historialMedico.getCirugias() == null) {
            historialMedico.setCirugias("Ninguna");
        }
        if (historialMedico.getEnfermedadesPrevias() == null) {
            historialMedico.setEnfermedadesPrevias("Ninguna");
        }
        if (historialMedico.getMedicamentos() == null) {
            historialMedico.setMedicamentos("Ninguno");
        }
    }

    private void actualizarCamposHistorialMedico(HistorialMedico historialMedicoExistente, HistorialMedico historialMedico) {
        historialMedicoExistente.setDiagnostico(historialMedico.getDiagnostico());
        historialMedicoExistente.setAlergias(historialMedico.getAlergias());
        historialMedicoExistente.setCirugias(historialMedico.getCirugias());
        historialMedicoExistente.setEnfermedadesPrevias(historialMedico.getEnfermedadesPrevias());
        historialMedicoExistente.setMedicamentos(historialMedico.getMedicamentos());
    }

    private void aplicarCambiosParciales(HistorialMedico historialMedico, Map<String, Object> cambios) {
        cambios.forEach((key, value) -> {
            switch (key) {
                case "diagnostico":
                    historialMedico.setDiagnostico((String) value);
                    break;
                case "alergias":
                    historialMedico.setAlergias((String) value);
                    break;
                case "cirugias":
                    historialMedico.setCirugias((String) value);
                    break;
                case "enfermedadesPrevias":
                    historialMedico.setEnfermedadesPrevias((String) value);
                    break;
                case "medicamentos":
                    historialMedico.setMedicamentos((String) value);
                    break;
                default:
                    throw new IllegalArgumentException("Campo desconocido: " + key);
            }
        });
    }
}
