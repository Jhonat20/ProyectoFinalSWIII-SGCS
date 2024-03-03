package CGCS.COM.ProyectoFinalSWIIISGCS.Services;

import CGCS.COM.ProyectoFinalSWIIISGCS.exception.IllegalOperationException;
public interface DoctorService {

    List<Doctor> listarDoctores() throws IllegalOperationException;

    Doctor registrarDoctor(Doctor doctor) throws IllegalOperationException;

    Optional<Doctor> obtenerDoctorPorId(Long id) throws IllegalOperationException;
}
