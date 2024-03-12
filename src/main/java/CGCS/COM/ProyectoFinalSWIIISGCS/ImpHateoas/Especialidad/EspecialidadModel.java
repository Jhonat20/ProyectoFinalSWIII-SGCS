/**
 * @file: EspecialidadModel.java
 * @author: (c) Jhon Bravo
 * @created: 09/03/2024 20:51
 */

package CGCS.COM.ProyectoFinalSWIIISGCS.ImpHateoas.Especialidad;

import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

/**
 * Modelo de representación para la entidad Especialidad, utilizado en HATEOAS.
 */
@Data
public class EspecialidadModel extends RepresentationModel<EspecialidadModel> {

    /** Identificador único de la especialidad. */
    private Long idEspecialidad;

    /** Nombre de la especialidad. */
    private String nombre;

    /** Descripción de la especialidad. */
    private String descripcion;
}