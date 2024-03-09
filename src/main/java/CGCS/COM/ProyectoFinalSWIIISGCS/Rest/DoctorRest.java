package CGCS.COM.ProyectoFinalSWIIISGCS.Rest;

import CGCS.COM.ProyectoFinalSWIIISGCS.DTO.DoctorDTO;
import CGCS.COM.ProyectoFinalSWIIISGCS.Domain.Doctor;
import CGCS.COM.ProyectoFinalSWIIISGCS.exception.IllegalOperationException;
import CGCS.COM.ProyectoFinalSWIIISGCS.responses.ApiResponse;
import CGCS.COM.ProyectoFinalSWIIISGCS.responses.GlobalResponse;
import org.modelmapper.ModelMapper;
import CGCS.COM.ProyectoFinalSWIIISGCS.Services.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @file: DoctorRest
 * @author: (c)Jhon Bravo
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
    public ResponseEntity<?> listarDoctores() throws IllegalOperationException {
        List<Doctor> doctores = doctorService.listarDoctores();
        List<DoctorDTO> doctorDTOs = doctores.stream()
                .map(doctor -> modelMapper.map(doctor, DoctorDTO.class))
                .collect(Collectors.toList());

        // Utilizando GlobalResponse.ok() para crear una respuesta exitosa
        return ResponseEntity.ok(GlobalResponse.ok(doctorDTOs));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerDoctorPorId(@PathVariable Long id) throws IllegalOperationException {
        Optional<Doctor> doctor = doctorService.obtenerDoctorPorId(id); // Método para buscar un doctor por su ID
        DoctorDTO doctorDTO = modelMapper.map(doctor, DoctorDTO.class); // Mapeo a DTO

        ApiResponse<DoctorDTO> response = new ApiResponse<>(true, "Doctor obtenido con éxito", doctorDTO);
        return ResponseEntity.ok(response);
    }
}

