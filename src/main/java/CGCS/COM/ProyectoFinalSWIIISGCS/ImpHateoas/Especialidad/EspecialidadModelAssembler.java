package CGCS.COM.ProyectoFinalSWIIISGCS.ImpHateoas.Especialidad;

import CGCS.COM.ProyectoFinalSWIIISGCS.Controllers.EspecialidadController;
import CGCS.COM.ProyectoFinalSWIIISGCS.Domain.Especialidad;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

/**
 * @file: EspecialidadModelAssembler
 * @author: (c) Jhon Bravo
 * @created: 09/03/2024 20:51
 */
@Component
public class EspecialidadModelAssembler extends RepresentationModelAssemblerSupport<Especialidad, EspecialidadModel>{

   public EspecialidadModelAssembler() {
       super(EspecialidadController.class, EspecialidadModel.class);
   }

  @Override
    public EspecialidadModel toModel(Especialidad especialidad) {
        EspecialidadModel especialidadModel = new EspecialidadModel();
        especialidadModel.setIdEspecialidad(especialidad.getIdEspecialidad());
        especialidadModel.setNombre(especialidad.getNombre());
        especialidadModel.setDescripcion(especialidad.getDescripcion());
        return especialidadModel;
    }

}
