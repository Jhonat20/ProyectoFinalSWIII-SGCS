package CGCS.COM.ProyectoFinalSWIIISGCS.Controllers;

import CGCS.COM.ProyectoFinalSWIIISGCS.DTO.DoctorDTO;
import CGCS.COM.ProyectoFinalSWIIISGCS.Domain.Doctor;
import CGCS.COM.ProyectoFinalSWIIISGCS.responses.ApiResponse;
import CGCS.COM.ProyectoFinalSWIIISGCS.responses.GlobalResponse;
import org.modelmapper.ModelMapper;
import CGCS.COM.ProyectoFinalSWIIISGCS.Services.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @file: DoctorRest
 * @author: (c)jhons
 * @created: 06/03/2024 23:50
 */

@RestController
@RequestMapping("/api/v1/doctor")
public class DoctorRest {

    @Autowired
    private DoctorService doctorService;
    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<?> listarDoctores() {
        try {
            List<Doctor> doctor = doctorService.listarDoctores();
            List<DoctorDTO> doctorDTOs = doctor.stream()
                    .map(this::convertToDto)
                    .collect(Collectors.toList());

            return ResponseEntity.ok(GlobalResponse.ok(doctorDTOs));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(GlobalResponse.error("Error interno del servidor"));
        }

    }
    // Método para convertir Doctor a DoctorDTO
    private DoctorDTO convertToDto(Doctor doctor) {
        return modelMapper.map(doctor, DoctorDTO.class);
    }

    // Método para convertir DoctorDTO a Doctor
    private Doctor convertToEntity(DoctorDTO doctorDTO) {
        return modelMapper.map(doctorDTO, Doctor.class);
    }
    @PostMapping
    public ResponseEntity<?> crearDoctor(@RequestBody DoctorDTO doctorDTO) {
        try {
            // Convertir DoctorDTO a Doctor
            Doctor doctor = convertToEntity(doctorDTO);

            // Guardar el doctor
            Doctor nuevoDoctor = doctorService.registrarDoctor(doctor);

            // Convertir el nuevo doctor a DoctorDTO y devolverlo en la respuesta
            DoctorDTO nuevoDoctorDTO = convertToDto(nuevoDoctor);
            return ResponseEntity.ok(GlobalResponse.ok(nuevoDoctorDTO));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(GlobalResponse.error("Error interno del servidor"));
        }
    }



}

