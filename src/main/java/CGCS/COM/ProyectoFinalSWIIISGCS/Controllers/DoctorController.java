package CGCS.COM.ProyectoFinalSWIIISGCS.Controllers;

import CGCS.COM.ProyectoFinalSWIIISGCS.Domain.Doctor;
import CGCS.COM.ProyectoFinalSWIIISGCS.Services.DoctorService;

import CGCS.COM.ProyectoFinalSWIIISGCS.Validation.ValidationUtil;
import CGCS.COM.ProyectoFinalSWIIISGCS.exception.IllegalOperationException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("api/v1/doctores")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    @GetMapping
    public List<Doctor> listarDoctores() throws IllegalOperationException {
        return doctorService.listarDoctores();
    }

    @PostMapping
    public ResponseEntity<?> registarDoctor(@Valid @RequestBody Doctor doctor, BindingResult bindingResult) throws IllegalOperationException {
        if (bindingResult.hasErrors()) {
            Map<String, String> errores = ValidationUtil.getValidationErrors(bindingResult);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errores);
        } else {
            Doctor nuevoDoctor = doctorService.registrarDoctor(doctor);
            String mensaje = "Doctor agregado exitosamente";

            Map<String, Object> response = new HashMap<>();
            response.put("mensaje", mensaje);
            response.put("doctor", nuevoDoctor);

            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        }
    }

}
