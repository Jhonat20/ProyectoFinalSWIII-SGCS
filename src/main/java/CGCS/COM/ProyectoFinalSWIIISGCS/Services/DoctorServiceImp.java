package CGCS.COM.ProyectoFinalSWIIISGCS.Services;

import CGCS.COM.ProyectoFinalSWIIISGCS.Domain.Doctor;
import CGCS.COM.ProyectoFinalSWIIISGCS.Domain.Horario;
import CGCS.COM.ProyectoFinalSWIIISGCS.Repositories.DoctorRepository;
import CGCS.COM.ProyectoFinalSWIIISGCS.Repositories.HorarioRepository;
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
    @Autowired
    public HorarioRepository horarioRepository;
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
    //Edward Moya
    @Override
    public void eliminarDoctor(Long id) throws IllegalOperationException {
        Optional<Doctor> optionalDoctor = doctorRepository.findById(id);
        if (optionalDoctor.isPresent()) {
            doctorRepository.delete(optionalDoctor.get());
        } else {
            throw new IllegalOperationException("No se encontró el doctor con el ID proporcionado: " + id);
        }
    }

    @Override
    public Doctor actualizarDoctor(Long id, Doctor doctor) throws IllegalOperationException {
        Optional<Doctor> optionalDoctor = doctorRepository.findById(id);
        if (optionalDoctor.isPresent()) {
            Doctor doctorExistente = optionalDoctor.get();
            doctorExistente.setNombres(doctor.getNombres());
            doctorExistente.setApellidos(doctor.getApellidos());
            // Actualiza otros campos según sea necesario
            return doctorRepository.save(doctorExistente);
        } else {
            throw new IllegalOperationException("No se encontró el doctor con el ID proporcionado: " + id);
        }
    }

}