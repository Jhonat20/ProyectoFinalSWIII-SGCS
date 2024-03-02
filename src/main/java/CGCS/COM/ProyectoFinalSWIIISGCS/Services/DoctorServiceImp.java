package CGCS.COM.ProyectoFinalSWIIISGCS.Services;

import CGCS.COM.ProyectoFinalSWIIISGCS.Domain.Doctor;
import CGCS.COM.ProyectoFinalSWIIISGCS.Repositories.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
