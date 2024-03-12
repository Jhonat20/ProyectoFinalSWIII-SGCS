/**
 * La clase CitaModelAssembler es un ensamblador que convierte entidades de tipo Cita en modelos de tipo CitaModel,
 * en el contexto de HATEOAS (Hypertext As The Engine Of Application State).
 *
 * @file: CitaModelAssembler.java
 * @created: [Fecha de creación]
 */

package CGCS.COM.ProyectoFinalSWIIISGCS.ImpHateoas.Cita;

import CGCS.COM.ProyectoFinalSWIIISGCS.Controllers.CitaController;
import CGCS.COM.ProyectoFinalSWIIISGCS.Domain.Cita;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

/**
 * Clase encargada de ensamblar instancias de Cita en modelos de CitaModel con HATEOAS.
 */
@Component
public class CitaModelAssembler extends RepresentationModelAssemblerSupport<Cita, CitaModel> {

        /**
         * Constructor que configura el ensamblador indicando la clase del controlador y la clase del modelo.
         */
        public CitaModelAssembler() {
                super(CitaController.class, CitaModel.class);
        }

        /**
         * Convierte una entidad Cita en un modelo CitaModel.
         *
         * @param cita La entidad Cita a ser convertida.
         * @return El modelo CitaModel resultante.
         */
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
