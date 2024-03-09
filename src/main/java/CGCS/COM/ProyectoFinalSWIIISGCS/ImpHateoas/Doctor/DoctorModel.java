package CGCS.COM.ProyectoFinalSWIIISGCS.ImpHateoas.Doctor;

import CGCS.COM.ProyectoFinalSWIIISGCS.Controllers.DoctorController;
import CGCS.COM.ProyectoFinalSWIIISGCS.Controllers.EspecialidadController;
import CGCS.COM.ProyectoFinalSWIIISGCS.Controllers.HorarioController;
import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * @file: DoctorModel
 * @author: (c) Jhon Bravo
 * @created: 08/03/2024 19:07
 */

@Data
public class DoctorModel extends RepresentationModel <DoctorModel> {
    private Long idDoctor;
    private String nombres;
    private String apellidos;
    private String dni;
    private String telefono;
    private String email;

}
