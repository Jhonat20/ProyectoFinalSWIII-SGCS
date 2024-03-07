package CGCS.COM.ProyectoFinalSWIIISGCS.Ensamblardor_Hateoas.Doctor;

import CGCS.COM.ProyectoFinalSWIIISGCS.Domain.Doctor;
import org.springframework.hateoas.RepresentationModel;

/**
 * @file: DoctorResource
 * @author: (c)jhons
 * @created: 07/03/2024 17:41
 */
public class DoctorResource extends RepresentationModel<DoctorResource> {

    private final Doctor doctor;

    public DoctorResource(Doctor doctor) {
        this.doctor = doctor;
        // Aquí puedes agregar enlaces adicionales si es necesario
    }

    // Agrega getters y setters según sea necesario para el doctor
}
