package CGCS.COM.ProyectoFinalSWIIISGCS.ImpHateoas.HistorialMedico;

import CGCS.COM.ProyectoFinalSWIIISGCS.Controllers.HistorialMedicoController;
import CGCS.COM.ProyectoFinalSWIIISGCS.Domain.HistorialMedico;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

/**
 * @file: HistorialMedicoAssembler
 * @author: (c) Jhon Bravo
 * @created: 09/03/2024 10:19
 */
@Component
public class HistorialMedicoAssembler extends RepresentationModelAssemblerSupport<HistorialMedico, HistorialMedicoModel> {

public HistorialMedicoAssembler() {
        super(HistorialMedicoController.class, HistorialMedicoModel.class);
    }

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
