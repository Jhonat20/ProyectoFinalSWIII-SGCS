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

/**
 * Controlador REST para gestionar operaciones relacionadas con pacientes.
 * Maneja la creación, consulta, actualización y eliminación de registros de pacientes.
 */
@RestController
@RequestMapping("api/v1/pacientes")
public class PacienteController {

    @Autowired
    private PacienteService pacienteService; // Servicio para operaciones relacionadas con pacientes.

    /**
     * Lista todos los pacientes registrados.
     * @param apiVersion La versión de la API especificada en el encabezado de la solicitud.
     * @return Una lista de pacientes junto con la versión de la API usada.
     */
    @GetMapping
    public ResponseEntity<?> listarPacientes(@RequestHeader(value = "API-Version", defaultValue = "v0.1.0") String apiVersion) throws IllegalOperationException {
        List<Paciente> pacientes = pacienteService.listarPacientes();
        HttpHeaders headers = new HttpHeaders();
        headers.add("API-Version", apiVersion);
        return ResponseEntity.ok().headers(headers).body(GlobalResponse.ok(pacientes));
    }

    /**
     * Registra un nuevo paciente verificando la validez de los datos proporcionados.
     * @param paciente El paciente a registrar.
     * @param bindingResult Resultado de la validación del objeto paciente.
     * @return El paciente registrado o errores de validación si los hubiera.
     */
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

    /**
     * Obtiene los detalles de un paciente específico por su ID.
     * @param id El ID del paciente a consultar.
     * @return Los detalles del paciente o un mensaje de error si no se encuentra.
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerPaciente(@PathVariable Long id) throws IllegalOperationException {
        Optional<Paciente> optionalPaciente = pacienteService.obtenerPacientePorId(id);
        if (optionalPaciente.isPresent()) {
            return ResponseEntity.ok(GlobalResponse.ok(optionalPaciente.get()));
        } else {
            return ResponseEntity.ok(GlobalResponse.error("No se encontró el paciente con el ID proporcionado"));
        }
    }

    /**
     * Elimina un paciente por su ID.
     * @param id El ID del paciente a eliminar.
     * @return Mensaje de confirmación de la eliminación.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarPaciente(@PathVariable Long id) throws IllegalOperationException {
        pacienteService.eliminarPaciente(id);
        return ResponseEntity.ok(GlobalResponse.ok("Paciente eliminado correctamente"));
    }

    /**
     * Actualiza los datos de un paciente existente.
     * @param id El ID del paciente a actualizar.
     * @param paciente Los nuevos datos del paciente.
     * @param bindingResult Resultado de la validación de los datos del paciente.
     * @return El paciente actualizado o errores de validación si los hubiera.
     */
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
