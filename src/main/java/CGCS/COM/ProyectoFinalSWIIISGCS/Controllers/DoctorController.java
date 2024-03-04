package CGCS.COM.ProyectoFinalSWIIISGCS.Controllers;

import CGCS.COM.ProyectoFinalSWIIISGCS.Domain.Doctor;
import CGCS.COM.ProyectoFinalSWIIISGCS.Services.DoctorService;

import CGCS.COM.ProyectoFinalSWIIISGCS.Validation.ValidationUtil;
import CGCS.COM.ProyectoFinalSWIIISGCS.exception.IllegalOperationException;
import CGCS.COM.ProyectoFinalSWIIISGCS.responses.GlobalResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.HttpHeaders;
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
    public ResponseEntity<?> listarDoctores() throws IllegalOperationException {
        List<Doctor> doctores = doctorService.listarDoctores();
        return ResponseEntity.ok(GlobalResponse.ok(doctores));
    }

    @PostMapping
    public ResponseEntity<?> registarDoctor(@Valid @RequestBody Doctor doctor, BindingResult bindingResult) throws IllegalOperationException {
        if (bindingResult.hasErrors()) {
            Map<String, String> errores = ValidationUtil.getValidationErrors(bindingResult);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errores);
        } else {
            Doctor nuevoDoctor = doctorService.registrarDoctor(doctor);
            return ResponseEntity.ok(GlobalResponse.ok(nuevoDoctor));

        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerDoctor(@PathVariable Long id) throws IllegalOperationException {
        Optional<Doctor> optionalDoctor = doctorService.obtenerDoctorPorId(id);
        if (optionalDoctor.isPresent()) {
            Doctor doctor = optionalDoctor.get();
            return ResponseEntity.ok(GlobalResponse.ok(doctor));
        } else {
            return ResponseEntity.ok(GlobalResponse.error("No se encontró el doctor con el ID proporcionado"));
        }
    }

    //Edward Moya
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarDoctor(@PathVariable Long id) throws IllegalOperationException {
        doctorService.eliminarDoctor(id);
        return ResponseEntity.ok(GlobalResponse.ok("Doctor eliminado correctamente"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarDoctor(@PathVariable Long id, @Valid @RequestBody Doctor doctor, BindingResult bindingResult) throws IllegalOperationException {
        if (bindingResult.hasErrors()) {
            Map<String, String> errores = ValidationUtil.getValidationErrors(bindingResult);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errores);
        } else {
            Doctor doctorActualizado = doctorService.actualizarDoctor(id, doctor);
            return ResponseEntity.ok(GlobalResponse.ok(doctorActualizado));
        }
    }

}
