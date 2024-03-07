package CGCS.COM.ProyectoFinalSWIIISGCS.Ensamblardor_Hateoas.Doctor;

import CGCS.COM.ProyectoFinalSWIIISGCS.Controllers.DoctorController;
import CGCS.COM.ProyectoFinalSWIIISGCS.Domain.Doctor;
import CGCS.COM.ProyectoFinalSWIIISGCS.exception.IllegalOperationException;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

/**
 * @file: DoctorResourceAssembler
 * @author: (c)jhons
 * @created: 07/03/2024 17:37
 */

@Component
public class DoctorResourceAssembler extends RepresentationModelAssemblerSupport<Doctor, DoctorResource>{
public DoctorResourceAssembler() {
    super(DoctorController.class, DoctorResource.class);
}

@Override
public DoctorResource toModel(Doctor entity) {
    DoctorResource resource = new DoctorResource(entity);
    try {
        resource.add(linkTo(methodOn(DoctorController.class).obtenerDoctor(entity.getIdDoctor())).withSelfRel());
    } catch (IllegalOperationException e) {
        // Manejar la excepción de manera apropiada, por ejemplo, registrándola o devolviendo un mensaje de error
        // Aquí devolvemos un mensaje de error genérico en caso de que ocurra la excepción
        System.err.println("Error al agregar enlace al recurso del doctor: " + e.getMessage());
    }
    return resource;
}

}
