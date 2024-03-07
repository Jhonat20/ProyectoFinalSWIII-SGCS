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
/**
 * Clase controladora para gestionar entidades de Doctor.
 * Maneja las solicitudes HTTP relacionadas con las operaciones de Doctor.
 *
 * @author CGCS.COM.ProyectoFinalSWIIISGCS.Controllers
 * @version 1.0
 * @since 2024-03-03
 */
@RestController
@RequestMapping("api/v1/doctores")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    /**
     * Obtiene la lista de doctores.
     *
     * @return ResponseEntity con una lista de doctores y encabezados apropiados.
     * @throws IllegalOperationException Si ocurre una operación ilegal durante la solicitud.
     */
    @GetMapping
    public ResponseEntity<?> listarDoctores() throws IllegalOperationException {
        List<Doctor> doctores = doctorService.listarDoctores();
        return ResponseEntity.ok(GlobalResponse.ok(doctores));
    }

    /**
     * Registra un nuevo doctor.
     *
     * @param doctor         Objeto Doctor a registrar, validado mediante Jakarata Bean Validation.
     * @param bindingResult  Objeto BindingResult para verificar errores de validación.
     * @return ResponseEntity con el nuevo doctor registrado o errores de validación.
     * @throws IllegalOperationException Si ocurre una operación ilegal durante la solicitud.
     */
    @PostMapping
    public ResponseEntity<?> registrarDoctor(@Valid @RequestBody Doctor doctor, BindingResult bindingResult) throws IllegalOperationException {
        if (bindingResult.hasErrors()) {
            Map<String, String> errores = ValidationUtil.getValidationErrors(bindingResult);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errores);
        } else {
            Doctor nuevoDoctor = doctorService.registrarDoctor(doctor);
            return ResponseEntity.ok(GlobalResponse.ok(nuevoDoctor));
        }
    }

    /**
     * Obtiene un doctor específico por ID.
     *
     * @param id ID del doctor.
     * @return ResponseEntity con el doctor solicitado o un mensaje de error si no se encuentra.
     * @throws IllegalOperationException Si ocurre una operación ilegal durante la solicitud.
     */
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

    /**
     * Elimina un doctor por ID.
     *
     * @param id ID del doctor.
     * @return ResponseEntity indicando la eliminación exitosa o un mensaje de error.
     * @throws IllegalOperationException Si ocurre una operación ilegal durante la solicitud.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarDoctor(@PathVariable Long id) throws IllegalOperationException {
        doctorService.eliminarDoctor(id);
        return ResponseEntity.ok(GlobalResponse.ok("Doctor eliminado correctamente"));
    }

    /**
     * Actualiza un doctor existente por ID.
     *
     * @param id             ID del doctor.
     * @param doctor         Objeto Doctor actualizado, validado mediante Jakarata Bean Validation.
     * @param bindingResult  Objeto BindingResult para verificar errores de validación.
     * @return ResponseEntity con el doctor actualizado o errores de validación.
     * @throws IllegalOperationException Si ocurre una operación ilegal durante la solicitud.
     */
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


    @PostMapping("/{doctorId}/citas/{citaId}")
    public ResponseEntity<?> asignarCitaDoctor(@PathVariable Long doctorId, @PathVariable Long citaId) throws IllegalOperationException {
        Doctor doctor = doctorService.asignarCitaDoctor(doctorId, citaId);
        return ResponseEntity.ok(GlobalResponse.ok(doctor));
    }

}