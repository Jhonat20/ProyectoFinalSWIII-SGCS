package CGCS.COM.ProyectoFinalSWIIISGCS.Controllers;

import CGCS.COM.ProyectoFinalSWIIISGCS.Domain.Cita;
import CGCS.COM.ProyectoFinalSWIIISGCS.Services.CitaService;
import CGCS.COM.ProyectoFinalSWIIISGCS.Validation.ValidationUtil;
import CGCS.COM.ProyectoFinalSWIIISGCS.exception.IllegalOperationException;
import CGCS.COM.ProyectoFinalSWIIISGCS.responses.GlobalResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/citas")
public class CitaController {

    @Autowired
    private CitaService citaService;

    @GetMapping
    public ResponseEntity<?> listarCitas(@RequestHeader(value = "API-Version", defaultValue = "v0.1.0") String apiVersion) throws IllegalOperationException {
        List<Cita> citas = citaService.listarCitas();
        return ResponseEntity.ok(GlobalResponse.ok(citas));
    }

    @PostMapping
    public ResponseEntity<?> registrarCita(@Valid @RequestBody Cita cita, BindingResult bindingResult) throws IllegalOperationException {
        if (bindingResult.hasErrors()) {
            Map<String, String> errores = ValidationUtil.getValidationErrors(bindingResult);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errores);
        } else {
            Cita nuevaCita = citaService.registrarCita(cita);
            return ResponseEntity.ok(GlobalResponse.ok(nuevaCita));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerCita(@PathVariable Long id) throws IllegalOperationException {
        Optional<Cita> optionalCita = citaService.obtenerCitaPorId(id);
        if (optionalCita.isPresent()) {
            Cita cita = optionalCita.get();
            return ResponseEntity.ok(GlobalResponse.ok(cita));
        } else {
            return ResponseEntity.ok(GlobalResponse.error("No se encontró la cita con el ID proporcionado"));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarCita(@PathVariable Long id) throws IllegalOperationException {
        citaService.eliminarCita(id);
        return ResponseEntity.ok(GlobalResponse.ok("Cita eliminada correctamente"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarCita(@PathVariable Long id, @Valid @RequestBody Cita cita, BindingResult bindingResult) throws IllegalOperationException {
        if (bindingResult.hasErrors()) {
            Map<String, String> errores = ValidationUtil.getValidationErrors(bindingResult);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errores);
        } else {
            Cita citaActualizada = citaService.actualizarCita(id, cita);
            return ResponseEntity.ok(GlobalResponse.ok(citaActualizada));
        }
    }
}

