package CGCS.COM.ProyectoFinalSWIIISGCS.ImpHateoas.Cita;

import CGCS.COM.ProyectoFinalSWIIISGCS.Controllers.CitaController;
import CGCS.COM.ProyectoFinalSWIIISGCS.Domain.Cita;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

/**
 * @file: CitaModelAssembler
 * @author: (c) Jhon Bravo
 * @created: 09/03/2024 8:09
 */
@Component
public class CitaModelAssembler extends RepresentationModelAssemblerSupport<Cita, CitaModel>{

public CitaModelAssembler() {
        super(CitaController.class, CitaModel.class);
        }

        @Override
        public CitaModel toModel(Cita cita) {
        CitaModel citaModel = new CitaModel();
        citaModel.setIdCita(cita.getIdCita());
        citaModel.setFecha(cita.getFecha());
        citaModel.setHora(cita.getHora());
        citaModel.setDescripcion(cita.getDescripcion());
        citaModel.setEstado(cita.getEstado());
        return citaModel;
        }
}
