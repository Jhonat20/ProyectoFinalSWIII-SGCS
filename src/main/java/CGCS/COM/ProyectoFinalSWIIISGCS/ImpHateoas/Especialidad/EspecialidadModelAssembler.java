/**
 * @file: EspecialidadModelAssembler.java
 * @author: (c) Jhon Bravo
 * @created: 09/03/2024 20:51
 */

package CGCS.COM.ProyectoFinalSWIIISGCS.ImpHateoas.Especialidad;

import CGCS.COM.ProyectoFinalSWIIISGCS.Controllers.EspecialidadController;
import CGCS.COM.ProyectoFinalSWIIISGCS.Domain.Especialidad;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

/**
 * Ensamblador de modelo para la entidad Especialidad, utilizado en HATEOAS.
 */
@Component
public class EspecialidadModelAssembler extends RepresentationModelAssemblerSupport<Especialidad, EspecialidadModel>{

    /**
     * Constructor que configura el ensamblador para trabajar con el controlador EspecialidadController.
     */
    public EspecialidadModelAssembler() {
        super(EspecialidadController.class, EspecialidadModel.class);
    }

    /**
     * Convierte una instancia de la entidad Especialidad en un modelo de EspecialidadModel.
     *
     * @param especialidad La entidad Especialidad.
     * @return El modelo EspecialidadModel correspondiente.
     */
    @Override
    public EspecialidadModel toModel(Especialidad especialidad) {
        EspecialidadModel especialidadModel = new EspecialidadModel();
        especialidadModel.setIdEspecialidad(especialidad.getIdEspecialidad());
        especialidadModel.setNombre(especialidad.getNombre());
        especialidadModel.setDescripcion(especialidad.getDescripcion());
        return especialidadModel;
    }
}
