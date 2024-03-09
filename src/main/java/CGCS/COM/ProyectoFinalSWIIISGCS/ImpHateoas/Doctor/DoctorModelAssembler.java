package CGCS.COM.ProyectoFinalSWIIISGCS.ImpHateoas.Doctor;


import CGCS.COM.ProyectoFinalSWIIISGCS.Controllers.DoctorController;
import CGCS.COM.ProyectoFinalSWIIISGCS.Domain.Doctor;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

/**
 * @file: DoctorModelAssembler
 * @author: (c) Jhon Bravo
 * @created: 08/03/2024 21:45
 */

@Component
public class DoctorModelAssembler extends RepresentationModelAssemblerSupport<Doctor, DoctorModel> {
    public DoctorModelAssembler() {
        super(DoctorController.class, DoctorModel.class);
    }

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
