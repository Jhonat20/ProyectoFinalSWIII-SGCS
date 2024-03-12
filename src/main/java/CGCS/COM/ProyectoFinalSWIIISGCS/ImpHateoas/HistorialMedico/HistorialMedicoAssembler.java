/**
 * @file: HistorialMedicoAssembler.java
 * @author: (c) Jhon Bravo
 * @created: 09/03/2024 10:19
 */

package CGCS.COM.ProyectoFinalSWIIISGCS.ImpHateoas.HistorialMedico;

import CGCS.COM.ProyectoFinalSWIIISGCS.Controllers.HistorialMedicoController;
import CGCS.COM.ProyectoFinalSWIIISGCS.Domain.HistorialMedico;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

/**
 * Ensamblador de modelo para la entidad HistorialMedico, utilizado en HATEOAS.
 */
@Component
public class HistorialMedicoAssembler extends RepresentationModelAssemblerSupport<HistorialMedico, HistorialMedicoModel> {

    /**
     * Constructor que configura el ensamblador para trabajar con el controlador HistorialMedicoController.
     */
    public HistorialMedicoAssembler() {
        super(HistorialMedicoController.class, HistorialMedicoModel.class);
    }

    /**
     * Convierte una instancia de la entidad HistorialMedico en un modelo de HistorialMedicoModel.
     *
     * @param historialMedico La entidad HistorialMedico.
     * @return El modelo HistorialMedicoModel correspondiente.
     */
    @Override
    public HistorialMedicoModel toModel(HistorialMedico historialMedico) {
        HistorialMedicoModel historialMedicoModel = new HistorialMedicoModel();
        historialMedicoModel.setIdHistorialMedico(historialMedico.getIdHistorialMedico());
        historialMedicoModel.setDiagnostico(historialMedico.getDiagnostico());
        historialMedicoModel.setAlergias(historialMedico.getAlergias());
        historialMedicoModel.setCirugias(historialMedico.getCirugias());
        historialMedicoModel.setEnfermedadesPrevias(historialMedico.getEnfermedadesPrevias());
        historialMedicoModel.setMedicamentos(historialMedico.getMedicamentos());
        return historialMedicoModel;
    }
}
