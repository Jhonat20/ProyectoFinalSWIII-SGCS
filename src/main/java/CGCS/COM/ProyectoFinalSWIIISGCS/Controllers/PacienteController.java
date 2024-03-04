package CGCS.COM.ProyectoFinalSWIIISGCS.Controllers;

import CGCS.COM.ProyectoFinalSWIIISGCS.Domain.Paciente;
import CGCS.COM.ProyectoFinalSWIIISGCS.Services.PacienteService;
import CGCS.COM.ProyectoFinalSWIIISGCS.Validation.ValidationUtil;
import CGCS.COM.ProyectoFinalSWIIISGCS.exception.IllegalOperationException;
import CGCS.COM.ProyectoFinalSWIIISGCS.responses.GlobalResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/pacientes")
public class PacienteController {

    @Autowired
    private PacienteService pacienteService;

    @GetMapping
    public ResponseEntity<?> listarPacientes(@RequestHeader(value = "API-Version", defaultValue = "v0.1.0") String apiVersion) throws IllegalOperationException {
        List<Paciente> pacientes = pacienteService.listarPacientes();
        HttpHeaders headers = new HttpHeaders();
        headers.add("API-Version", apiVersion);
        return ResponseEntity.ok(GlobalResponse.ok(pacientes));
    }

    @PostMapping
    public ResponseEntity<?> registrarPaciente(@Valid @RequestBody Paciente paciente, BindingResult bindingResult) throws IllegalOperationException {
        if (bindingResult.hasErrors()) {
            Map<String, String> errores = ValidationUtil.getValidationErrors(bindingResult);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errores);
        } else {
            Paciente nuevoPaciente = pacienteService.registrarPaciente(paciente);
            return ResponseEntity.ok(GlobalResponse.ok(nuevoPaciente));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerPaciente(@PathVariable Long id) throws IllegalOperationException {
        Optional<Paciente> optionalPaciente = pacienteService.obtenerPacientePorId(id);
        if (optionalPaciente.isPresent()) {
            Paciente paciente = optionalPaciente.get();
            return ResponseEntity.ok(GlobalResponse.ok(paciente));
        } else {
            return ResponseEntity.ok(GlobalResponse.error("No se encontró el paciente con el ID proporcionado"));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarPaciente(@PathVariable Long id) throws IllegalOperationException {
        pacienteService.eliminarPaciente(id);
        return ResponseEntity.ok(GlobalResponse.ok("Paciente eliminado correctamente"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarPaciente(@PathVariable Long id, @Valid @RequestBody Paciente paciente, BindingResult bindingResult) throws IllegalOperationException {
        if (bindingResult.hasErrors()) {
            Map<String, String> errores = ValidationUtil.getValidationErrors(bindingResult);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errores);
        } else {
            Paciente pacienteActualizado = pacienteService.actualizarPaciente(id, paciente);
            return ResponseEntity.ok(GlobalResponse.ok(pacienteActualizado));
        }
    }
}

