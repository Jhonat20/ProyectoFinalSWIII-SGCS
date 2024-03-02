package CGCS.COM.ProyectoFinalSWIIISGCS.Services;

import CGCS.COM.ProyectoFinalSWIIISGCS.Domain.Doctor;
import CGCS.COM.ProyectoFinalSWIIISGCS.exception.IllegalOperationException;

import java.util.List;

public interface DoctorService {

    List<Doctor> listarDoctores() throws IllegalOperationException;

    Doctor registrarDoctor(Doctor doctor) throws IllegalOperationException;


}
