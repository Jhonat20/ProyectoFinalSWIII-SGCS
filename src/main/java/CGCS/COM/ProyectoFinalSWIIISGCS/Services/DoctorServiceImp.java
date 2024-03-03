package CGCS.COM.ProyectoFinalSWIIISGCS.Services;

import CGCS.COM.ProyectoFinalSWIIISGCS.Domain.Doctor;
import CGCS.COM.ProyectoFinalSWIIISGCS.Repositories.DoctorRepository;
import CGCS.COM.ProyectoFinalSWIIISGCS.exception.IllegalOperationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class DoctorServiceImp implements DoctorService {

    @Autowired
    public DoctorRepository doctorRepository;
    @Override
    public List<Doctor> listarDoctores() {
        return doctorRepository.findAll();
    }

    @Override
    public Doctor registrarDoctor(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Doctor> obtenerDoctorPorId(Long id) throws IllegalOperationException {
        return doctorRepository.findById(id);
    }

}