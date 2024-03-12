/**
 * @file: DoctorModelAssembler.java
 * @author: (c) Jhon Bravo
 * @created: 08/03/2024 21:45
 */

package CGCS.COM.ProyectoFinalSWIIISGCS.ImpHateoas.Doctor;

import CGCS.COM.ProyectoFinalSWIIISGCS.Controllers.DoctorController;
import CGCS.COM.ProyectoFinalSWIIISGCS.Domain.Doctor;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

/**
 * Clase encargada de convertir entidades Doctor en modelos DoctorModel para su representación HATEOAS.
 */
@Component
public class DoctorModelAssembler extends RepresentationModelAssemblerSupport<Doctor, DoctorModel> {

    /**
     * Constructor que configura el ensamblador indicando el controlador de destino y el tipo de modelo.
     */
    public DoctorModelAssembler() {
        super(DoctorController.class, DoctorModel.class);
    }

    /**
     * Convierte una entidad Doctor en un modelo DoctorModel.
     *
     * @param doctor La entidad Doctor a convertir.
     * @return El modelo DoctorModel generado.
     */
    @Override
    public DoctorModel toModel(Doctor doctor) {
        DoctorModel doctorModel = new DoctorModel();
        doctorModel.setIdDoctor(doctor.getIdDoctor());
        doctorModel.setNombres(doctor.getNombres());
        doctorModel.setApellidos(doctor.getApellidos());
        doctorModel.setDni(doctor.getDni());
        doctorModel.setTelefono(doctor.getTelefono());
        doctorModel.setEmail(doctor.getEmail());
        return doctorModel;
    }
}
