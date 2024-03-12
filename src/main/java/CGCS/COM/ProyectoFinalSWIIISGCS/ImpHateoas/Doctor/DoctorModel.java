/**
 * La clase DoctorModel representa un modelo de la entidad Doctor con HATEOAS (Hypertext As The Engine Of Application State).
 *
 * @file: DoctorModel.java
 * @created: 08/03/2024
 */

package CGCS.COM.ProyectoFinalSWIIISGCS.ImpHateoas.Doctor;

import CGCS.COM.ProyectoFinalSWIIISGCS.Controllers.DoctorController;
import CGCS.COM.ProyectoFinalSWIIISGCS.Controllers.EspecialidadController;
import CGCS.COM.ProyectoFinalSWIIISGCS.Controllers.HorarioController;
import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * Clase que representa un modelo de Doctor con HATEOAS.
 */
@Data
public class DoctorModel extends RepresentationModel<DoctorModel> {
    private Long idDoctor;
    private String nombres;
    private String apellidos;
    private String dni;
    private String telefono;
    private String email;
}