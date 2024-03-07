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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
            List<Doctor> doctores = doctorService.listarDoctores();
            List<DoctorDTO> doctorDTOs = doctores.stream()
                    .map(doctor -> modelMapper.map(doctor, DoctorDTO.class))
                    .collect(Collectors.toList());

            // Utilizando GlobalResponse.ok() para crear una respuesta exitosa
            return ResponseEntity.ok(GlobalResponse.ok(doctorDTOs));
        } catch (Exception e) {
            // Utilizando GlobalResponse.error() para crear una respuesta de error
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(GlobalResponse.error("Error interno del servidor"));
        }
    }

}
