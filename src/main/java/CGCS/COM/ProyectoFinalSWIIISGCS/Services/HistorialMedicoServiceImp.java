package CGCS.COM.ProyectoFinalSWIIISGCS.Services;

import CGCS.COM.ProyectoFinalSWIIISGCS.Domain.HistorialMedico;
import CGCS.COM.ProyectoFinalSWIIISGCS.Repositories.HistorialMedicoRepository;
import CGCS.COM.ProyectoFinalSWIIISGCS.exception.ErrorMessage;
import CGCS.COM.ProyectoFinalSWIIISGCS.exception.IllegalOperationException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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
    @Transactional (readOnly = true)
    public Optional<HistorialMedico> BuscarPorId(Long id) throws EntityNotFoundException {
        return histMedRep.findById(id);
    }


    @Override
    public HistorialMedico Grabar(HistorialMedico historialMedico) throws IllegalOperationException {
        if (historialMedico.getDiagnostico().isEmpty() ||
                historialMedico.getAlergias().isEmpty() ||
                historialMedico.getCirugias().isEmpty() ||
                historialMedico.getEnfermedadesPrevias().isEmpty() ||
                historialMedico.getMedicamentos().isEmpty()) {

            throw new IllegalOperationException(ErrorMessage.HISTORIAL_MEDICO_INVALID);
        }
        return histMedRep.save(historialMedico);
    }

    @Override
    @Transactional
    public HistorialMedico Actualizar(Long id, HistorialMedico historialMedico) throws EntityNotFoundException, IllegalOperationException {
       // Obtener la entidad HistorialMedico existente
        HistorialMedico historialMedicoExistente = obtenerHistorialMedicoExistente(id);
        // Actualizar los campos de historialMedicoExistente con los valores proporcionados en el parámetro historialMedico
        actualizarCamposHistorialMedico(historialMedicoExistente, historialMedico);
        // Guardar la entidad HistorialMedico actualizada
        return histMedRep.save(historialMedicoExistente);
    }


    @Override
    @Transactional
    public void Eliminar(Long id)  throws EntityNotFoundException, IllegalOperationException  {
        // Validar que el historial médico exista en la base de datos
        HistorialMedico historialMedico = histMedRep.findById(id).orElseThrow(
                () -> new EntityNotFoundException(ErrorMessage.HISTORIAL_MEDICO_NOT_FOUND)
        );

        histMedRep.delete(historialMedico);
    }



        //***************METODOS DE EXRACCION Y ACTUALIZACION***********

    // Método para obtener la entidad HistorialMedico existente
    private HistorialMedico obtenerHistorialMedicoExistente(Long id) throws EntityNotFoundException {
        Optional<HistorialMedico> entidadHistorialMedico = histMedRep.findById(id);
        if (entidadHistorialMedico.isEmpty())
            throw new EntityNotFoundException(ErrorMessage.HISTORIAL_MEDICO_NOT_FOUND);

        return entidadHistorialMedico.get();
    }


    // Método para actualizar los campos de historialMedicoExistente con los valores proporcionados en el parámetro historialMedico
    private void actualizarCamposHistorialMedico(HistorialMedico historialMedicoExistente, HistorialMedico historialMedico) throws IllegalOperationException {
        // Verificar si alguno de los campos requeridos está vacío
        if (historialMedico.getDiagnostico().isEmpty() ||
                historialMedico.getAlergias().isEmpty() ||
                historialMedico.getCirugias().isEmpty() ||
                historialMedico.getEnfermedadesPrevias().isEmpty() ||
                historialMedico.getMedicamentos().isEmpty()) {

            throw new IllegalOperationException(ErrorMessage.HISTORIAL_MEDICO_INVALID);
        }

        // Actualizar los campos de historialMedicoExistente con los valores proporcionados en el parámetro historialMedico
        historialMedicoExistente.setDiagnostico(historialMedico.getDiagnostico());
        historialMedicoExistente.setAlergias(historialMedico.getAlergias());
        historialMedicoExistente.setCirugias(historialMedico.getCirugias());
        historialMedicoExistente.setEnfermedadesPrevias(historialMedico.getEnfermedadesPrevias());
        historialMedicoExistente.setMedicamentos(historialMedico.getMedicamentos());
    }

}



