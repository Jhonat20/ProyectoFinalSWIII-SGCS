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

/**
 * Controlador para la gestión de citas en la aplicación.
 * Proporciona endpoints para operaciones CRUD sobre citas.
 */
@RestController
@RequestMapping("api/v1/citas")
public class CitaController {

    @Autowired
    private CitaService citaService; // Servicio para operaciones de citas.

    /**
     * Lista todas las citas disponibles.
     *
     * @param apiVersion Versión de la API solicitada por el cliente.
     * @return ResponseEntity con la lista de citas.
     * @throws IllegalOperationException Si ocurre alguna excepción durante la operación.
     */
    @GetMapping
    public ResponseEntity<?> listarCitas(@RequestHeader(value = "API-Version", defaultValue = "v0.1.0") String apiVersion) throws IllegalOperationException {
        List<Cita> citas = citaService.listarCitas();
        return ResponseEntity.ok(GlobalResponse.ok(citas));
    }

    /**
     * Registra una nueva cita, validando previamente los datos de entrada.
     *
     * @param cita Datos de la cita a registrar.
     * @param bindingResult Resultado de la validación.
     * @return ResponseEntity con la cita registrada o errores de validación.
     * @throws IllegalOperationException Si ocurre alguna excepción durante el registro.
     */
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

    /**
     * Obtiene una cita específica por su ID.
     *
     * @param id ID de la cita a obtener.
     * @return ResponseEntity con la cita encontrada o un mensaje de error si no existe.
     * @throws IllegalOperationException Si ocurre alguna excepción durante la búsqueda.
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerCita(@PathVariable Long id) throws IllegalOperationException {
        Optional<Cita> optionalCita = citaService.obtenerCitaPorId(id);
        if (optionalCita.isPresent()) {
            return ResponseEntity.ok(GlobalResponse.ok(optionalCita.get()));
        } else {
            return ResponseEntity.ok(GlobalResponse.error("No se encontró la cita con el ID proporcionado"));
        }
    }

    /**
     * Elimina una cita por su ID.
     *
     * @param id ID de la cita a eliminar.
     * @return ResponseEntity con mensaje de confirmación.
     * @throws IllegalOperationException Si la cita no existe o no se puede eliminar.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarCita(@PathVariable Long id) throws IllegalOperationException {
        citaService.eliminarCita(id);
        return ResponseEntity.ok(GlobalResponse.ok("Cita eliminada correctamente"));
    }

    /**
     * Actualiza los datos de una cita existente.
     *
     * @param id ID de la cita a actualizar.
     * @param cita Datos actualizados de la cita.
     * @param bindingResult Resultado de la validación.
     * @return ResponseEntity con la cita actualizada o errores de validación.
     * @throws IllegalOperationException Si ocurre alguna excepción durante la actualización.
     */
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
