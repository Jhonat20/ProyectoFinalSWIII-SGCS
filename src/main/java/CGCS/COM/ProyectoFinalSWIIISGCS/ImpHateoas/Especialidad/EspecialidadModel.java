package CGCS.COM.ProyectoFinalSWIIISGCS.ImpHateoas.Especialidad;

import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

/**
 * @file: EspecialidadModel
 * @author: (c) Jhon Bravo
 * @created: 09/03/2024 20:51
 */
@Data
public class EspecialidadModel extends RepresentationModel<EspecialidadModel> {
    private Long idEspecialidad;
    private String nombre;
    private String descripcion;

}
