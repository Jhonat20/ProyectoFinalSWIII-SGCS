package CGCS.COM.ProyectoFinalSWIIISGCS.Services;

import CGCS.COM.ProyectoFinalSWIIISGCS.Domain.Cita;
import CGCS.COM.ProyectoFinalSWIIISGCS.Domain.Doctor;
import CGCS.COM.ProyectoFinalSWIIISGCS.Domain.Especialidad;
import CGCS.COM.ProyectoFinalSWIIISGCS.Domain.Horario;
import CGCS.COM.ProyectoFinalSWIIISGCS.Repositories.CitaRepository;
import CGCS.COM.ProyectoFinalSWIIISGCS.Repositories.DoctorRepository;
import CGCS.COM.ProyectoFinalSWIIISGCS.Repositories.EspecialidadRepository;
import CGCS.COM.ProyectoFinalSWIIISGCS.Repositories.HorarioRepository;
import CGCS.COM.ProyectoFinalSWIIISGCS.exception.IllegalOperationException;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class DoctorServiceImp implements DoctorService {

    @Autowired
    public DoctorRepository doctorRepository;
    @Autowired
    public CitaRepository citaRepository;

    @Autowired
    public EspecialidadRepository especialidadRepository;

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
            doctorExistente.setDni(doctor.getDni());
            doctorExistente.setTelefono(doctor.getTelefono());
            doctorExistente.setEmail(doctor.getEmail());
            return doctorRepository.save(doctorExistente);
        } else {
            throw new IllegalOperationException("No se encontró el doctor con el ID proporcionado: " + id);
        }
    }



    @Override
    public Doctor asignarCitaDoctor(Long doctorId, Long citaId) throws IllegalOperationException {
        // Obtener el doctor por su ID
        Optional<Doctor> optionalDoctor = doctorRepository.findById(doctorId);
        if (optionalDoctor.isPresent()) {
            Doctor doctor = optionalDoctor.get();

            // Obtener la cita por su ID
            Optional<Cita> optionalCita = citaRepository.findById(citaId);
            if (optionalCita.isPresent()) {
                Cita cita = optionalCita.get();

                // Verificar si la cita ya está asignada a otro doctor
                if (cita.getDoctor() != null) {
                    throw new IllegalOperationException("La cita ya está asignada a otro doctor");
                }

                // Asignar la cita al doctor
                doctor.agregarCita(cita);

                // Guardar el doctor actualizado en la base de datos
                return doctorRepository.save(doctor);
            } else {
                throw new IllegalOperationException("No se encontró la cita con el ID proporcionado: " + citaId);
            }

        } else {
            throw new IllegalOperationException("No se encontró el doctor con el ID proporcionado: " + doctorId);
        }
    }

    @Override
    public Doctor asignarEspecialidadDoctor(Long doctorId, Long especialidadId) throws IllegalOperationException {
        if (doctorId == null || especialidadId == null) {
            throw new IllegalArgumentException("Los IDs del doctor y la especialidad no pueden ser nulos");
        }

        Optional<Doctor> optionalDoctor = doctorRepository.findById(doctorId);
        Optional<Especialidad> optionalEspecialidad = especialidadRepository.findById(especialidadId);

        if (!optionalDoctor.isPresent() && !optionalEspecialidad.isPresent()) {
            throw new IllegalOperationException("No se proporcionaron IDs válidos para el doctor y la especialidad");
        } else if (!optionalDoctor.isPresent()) {
            throw new IllegalOperationException("No se encontró el doctor con el ID proporcionado");
        } else if (!optionalEspecialidad.isPresent()) {
            throw new IllegalOperationException("No se encontró la especialidad con el ID proporcionado");
        }

        Doctor doctor = optionalDoctor.get();
        Especialidad especialidad = optionalEspecialidad.get();

        doctor.getEspecialidades().add(especialidad);
        especialidad.getDoctores().add(doctor);

        doctorRepository.save(doctor);
        return doctor;
    }

    @Override
    public Doctor desasignarCitaDoctor(Long doctorId, Long citaId) throws IllegalOperationException {
        Optional<Doctor> optionalDoctor = doctorRepository.findById(doctorId);
        if (optionalDoctor.isPresent()) {
            Doctor doctor = optionalDoctor.get();

            // Obtener la cita por su ID
            Optional<Cita> optionalCita = citaRepository.findById(citaId);
            if (optionalCita.isPresent()) {
                Cita cita = optionalCita.get();

                // Desasignar la cita del doctor
                doctor.getCitas().remove(cita);
                cita.setDoctor(null);

                // Guardar el doctor actualizado en la base de datos
                return doctorRepository.save(doctor);
            } else {
                throw new IllegalOperationException("No se encontró la cita con el ID proporcionado: " + citaId);
            }
        } else {
            throw new IllegalOperationException("No se encontró el doctor con el ID proporcionado: " + doctorId);
        }
    }





//    //*********************************Metodo para eliminar horarios pasados**************************
//    @PostConstruct
//    public void eliminarHorariosPasados() {
//        // Obtén todos los doctores
//        List<Doctor> doctores = doctorRepository.findAll();
//
//        // Para cada doctor
//        for (Doctor doctor : doctores) {
//            // Obtén los horarios del doctor
//            List<Horario> horarios = doctor.getHorario();
//
//            // Crea una lista para almacenar los horarios que se van a eliminar
//            List<Horario> horariosAEliminar = new ArrayList<>();
//
//            // Para cada horario
//            for (Horario horario : horarios) {
//                // Si la fecha del horario es anterior a la fecha actual
//                if (horario.getDia().isBefore(LocalDate.now())) {
//                    // Añade el horario a la lista de horarios a eliminar
//                    horariosAEliminar.add(horario);
//                }
//            }
//
//            // Desasigna los horarios del doctor
//            horarios.removeAll(horariosAEliminar);
//            doctor.setHorario(horarios);
//            doctorRepository.save(doctor);
//
//            // Ahora puedes eliminar los horarios
//            for (Horario horario : horariosAEliminar) {
//                horarioRepository.delete(horario);
//            }
//        }
//    }



}