package CGCS.COM.ProyectoFinalSWIIISGCS.Services;

import CGCS.COM.ProyectoFinalSWIIISGCS.Domain.HistorialMedico;
import CGCS.COM.ProyectoFinalSWIIISGCS.Repositories.HistorialMedicoRepository;

import CGCS.COM.ProyectoFinalSWIIISGCS.exception.IllegalOperationException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class HistorialMedicoServiceImp implements HistorialMedicoService {

    @Autowired
    private HistorialMedicoRepository histMedRep;

    @Override
    @Transactional
    public List<HistorialMedico> listarHistorialMedico() {
        return histMedRep.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<HistorialMedico> BuscarPorId(Long id) {
        Optional<HistorialMedico> historialMedicoOptional = histMedRep.findById(id);
        if (historialMedicoOptional.isPresent()) {
            return historialMedicoOptional;
        } else {
            return Optional.empty();
        }
    }

    @Override
    public HistorialMedico Grabar(HistorialMedico historialMedico) {
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

        return histMedRep.save(historialMedico);
    }

    @Override
    @Transactional
    public HistorialMedico Actualizar(Long id, HistorialMedico historialMedico)  {
        Optional<HistorialMedico> historialMedicoOptional = obtenerHistorialMedicoExistente(id);
        if (historialMedicoOptional.isPresent()) {
            HistorialMedico historialMedicoExistente = historialMedicoOptional.get();
            actualizarCamposHistorialMedico(historialMedicoExistente, historialMedico);
            return histMedRep.save(historialMedicoExistente);
        } else {
            throw new EntityNotFoundException();
        }
    }

    @Override
    @Transactional
    public void Eliminar(Long id) {
        Optional<HistorialMedico> historialMedicoOptional = histMedRep.findById(id);
        if (historialMedicoOptional.isPresent()) {
            HistorialMedico historialMedico = historialMedicoOptional.get();
            histMedRep.delete(historialMedico);
        } else {
            throw new EntityNotFoundException();
        }
    }

    @Override
    @Transactional
    public HistorialMedico actualizarParcial(Long id, Map<String, Object> cambios) {
        Optional<HistorialMedico> historialMedicoOptional = histMedRep.findById(id);
        if (historialMedicoOptional.isEmpty()) {
            throw new EntityNotFoundException();
        }

        HistorialMedico historialMedico = historialMedicoOptional.get();
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
                    throw new IllegalArgumentException();
            }
        });

        return histMedRep.save(historialMedico);
    }

    private Optional<HistorialMedico> obtenerHistorialMedicoExistente(Long id) {
        return histMedRep.findById(id);
    }

    private void actualizarCamposHistorialMedico(HistorialMedico historialMedicoExistente, HistorialMedico historialMedico) {
        if (historialMedico.getDiagnostico().isEmpty() ||
                historialMedico.getAlergias().isEmpty() ||
                historialMedico.getCirugias().isEmpty() ||
                historialMedico.getEnfermedadesPrevias().isEmpty() ||
                historialMedico.getMedicamentos().isEmpty()) {
            // Manejar el caso en el que algún campo requerido esté vacío
        }
        historialMedicoExistente.setDiagnostico(historialMedico.getDiagnostico());
        historialMedicoExistente.setAlergias(historialMedico.getAlergias());
        historialMedicoExistente.setCirugias(historialMedico.getCirugias());
        historialMedicoExistente.setEnfermedadesPrevias(historialMedico.getEnfermedadesPrevias());
        historialMedicoExistente.setMedicamentos(historialMedico.getMedicamentos());
    }
}
