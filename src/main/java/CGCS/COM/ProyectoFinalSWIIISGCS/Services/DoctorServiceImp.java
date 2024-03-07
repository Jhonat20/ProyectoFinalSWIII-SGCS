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

    @Override
    public Doctor asignarHorario(Long doctorId, Long horarioId) throws IllegalOperationException {
        Optional<Doctor> optionalDoctor = doctorRepository.findById(doctorId);
        if (optionalDoctor.isPresent()) {
            Doctor doctor = optionalDoctor.get();
            // Obtener el horario usando su ID
            Optional<Horario> optionalHorario = horarioRepository.findById(horarioId);
            if (optionalHorario.isPresent()) {
                Horario horario = optionalHorario.get();
                // Validar si el doctor ya tiene un horario asignado para ese día
                if (doctor.getHorario() != null && doctor.getHorario().getDia().equals(horario.getDia())) {
                    throw new IllegalOperationException("El doctor ya tiene un horario asignado para ese día: " + doctor.getHorario().getDia());
                }
                // Asignar el horario al doctor
                doctor.setHorario(horario);
                // Guardar el doctor actualizado en la base de datos
                return doctorRepository.save(doctor);
            } else {
                throw new IllegalOperationException("No se encontró el horario con el ID proporcionado: " + horarioId);
            }
        } else {
            throw new IllegalOperationException("No se encontró el doctor con el ID proporcionado: " + doctorId);
        }
    }

}