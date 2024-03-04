package CGCS.COM.ProyectoFinalSWIIISGCS.Services;

import CGCS.COM.ProyectoFinalSWIIISGCS.Domain.Doctor;
import CGCS.COM.ProyectoFinalSWIIISGCS.exception.IllegalOperationException;
import java.util.List;
import java.util.Optional;
public interface DoctorService {

    List<Doctor> listarDoctores() throws IllegalOperationException;

    Doctor registrarDoctor(Doctor doctor) throws IllegalOperationException;

    Optional<Doctor> obtenerDoctorPorId(Long id) throws IllegalOperationException;

    //Edward Moya
    void eliminarDoctor(Long id) throws IllegalOperationException;

    Doctor actualizarDoctor(Long id, Doctor doctor) throws IllegalOperationException;

}
