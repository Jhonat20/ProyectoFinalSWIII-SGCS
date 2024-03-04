package CGCS.COM.ProyectoFinalSWIIISGCS.Controllers;

import CGCS.COM.ProyectoFinalSWIIISGCS.Domain.HistorialMedico;
import CGCS.COM.ProyectoFinalSWIIISGCS.Services.HistorialMedicoService;
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
 * Controlador REST para manejar las operaciones relacionadas con los historiales médicos.
 */
@RestController
@RequestMapping("/api/v1/HistorialMedico")
public class HistorialMedicoController {

    @Autowired
    private HistorialMedicoService historialMedicoService;

    /**
     * Obtiene una lista de todos los historiales médicos.
     *
     * @return ResponseEntity con la lista de historiales médicos o un mensaje de error si no hay información disponible.
     */
    @GetMapping
    public ResponseEntity<?> listarHistorialMedico() {
        List<HistorialMedico> historialMedicos = historialMedicoService.listarHistorialMedico();
        if (historialMedicos.isEmpty()) {
            return ResponseEntity.ok(GlobalResponse.error("No hay información disponible para mostrar"));
        } else {
            return ResponseEntity.ok(GlobalResponse.ok(historialMedicos));
        }
    }

    /**
     * Obtiene un historial médico por su ID.
     *
     * @param id ID del historial médico a buscar.
     * @return ResponseEntity con el historial médico correspondiente o un mensaje de error si no se encuentra.
     * @throws IllegalOperationException Si no se encuentra el historial médico con el ID proporcionado.
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerHistorialMedico(@PathVariable Long id) throws IllegalOperationException {
        Optional<HistorialMedico> optionalHistorialMedico = historialMedicoService.BuscarPorId(id);
        if (optionalHistorialMedico.isPresent()) {
            HistorialMedico historialMedico = optionalHistorialMedico.get();
            return ResponseEntity.ok(GlobalResponse.ok(historialMedico));
        } else {
            return ResponseEntity.ok(GlobalResponse.error("No se encontró el historial médico con el ID proporcionado"));
        }
    }

    /**
     * Guarda un nuevo historial médico.
     *
     * @param historialMedico El historial médico a guardar.
     * @param bindingResult   Resultado de la validación de entrada.
     * @return ResponseEntity con el historial médico guardado o un mensaje de error si la validación falla.
     * @throws IllegalOperationException Si la validación falla.
     */
    @PostMapping
    public ResponseEntity<?> guardarHistorialMedico(@Valid @RequestBody HistorialMedico historialMedico, BindingResult bindingResult) throws IllegalOperationException {
        if (bindingResult.hasErrors()) {
            Map<String, String> errores = ValidationUtil.getValidationErrors(bindingResult);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errores);
        } else {
            HistorialMedico nuevoHistorialMedico = historialMedicoService.Grabar(historialMedico);
            return ResponseEntity.ok(GlobalResponse.ok(nuevoHistorialMedico));
        }
    }

    /**
     * Actualiza un historial médico existente.
     *
     * @param id             ID del historial médico a actualizar.
     * @param historialMedico El historial médico actualizado.
     * @param bindingResult  Resultado de la validación de entrada.
     * @return ResponseEntity con el historial médico actualizado o un mensaje de error si la validación falla o el historial médico no se encuentra.
     * @throws IllegalOperationException Si la validación falla o el historial médico no se encuentra.
     */
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarHistorialMedico(@PathVariable Long id, @Valid @RequestBody HistorialMedico historialMedico, BindingResult bindingResult) throws IllegalOperationException {
        Optional<HistorialMedico> historialMedicoOptional = historialMedicoService.BuscarPorId(id);
        if (historialMedicoOptional.isPresent()) {
            if (bindingResult.hasErrors()) {
                Map<String, String> errores = ValidationUtil.getValidationErrors(bindingResult);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errores);
            } else {
                HistorialMedico historialMedicoActualizado = historialMedicoService.Actualizar(id, historialMedico);
                return ResponseEntity.ok(GlobalResponse.ok(historialMedicoActualizado));
            }
        } else {
            return ResponseEntity.ok(GlobalResponse.error("No se encontró el historial médico con el ID proporcionado"));
        }
    }

    /**
     * Actualiza parcialmente un historial médico existente.
     *
     * @param id      ID del historial médico a actualizar.
     * @param cambios Mapa con los campos a actualizar y sus nuevos valores.
     * @return ResponseEntity con el historial médico actualizado parcialmente o un mensaje de error si el historial médico no se encuentra.
     * @throws IllegalOperationException Si el historial médico no se encuentra.
     */
    @PatchMapping("/{id}")
    public ResponseEntity<?> actualizarParcialHistorialMedico(@PathVariable Long id, @RequestBody Map<String, Object> cambios) throws IllegalOperationException {
        Optional<HistorialMedico> historialMedicoOptional = historialMedicoService.BuscarPorId(id);
        if (historialMedicoOptional.isPresent()) {
            HistorialMedico historialMedico = historialMedicoOptional.get();
            historialMedicoService.actualizarParcial(id, cambios);
            return ResponseEntity.ok(GlobalResponse.ok(historialMedico));
        } else {
            return ResponseEntity.ok(GlobalResponse.error("No se encontró el historial médico con el ID proporcionado"));
        }
    }

    /**
     * Elimina un historial médico existente.
     *
     * @param id ID del historial médico a eliminar.
     * @return ResponseEntity con un mensaje de éxito si el historial médico se elimina correctamente o un mensaje de error si no se encuentra.
     * @throws IllegalOperationException Si el historial médico no se encuentra.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarHistorialMedico(@PathVariable Long id) throws IllegalOperationException {
        Optional<HistorialMedico> historialMedicoOptional = historialMedicoService.BuscarPorId(id);
        if (historialMedicoOptional.isPresent()) {
            historialMedicoService.Eliminar(id);
            return ResponseEntity.ok(GlobalResponse.ok("Historial médico eliminado correctamente"));
        } else {
            return ResponseEntity.ok(GlobalResponse.error("No se encontró el historial médico con el ID proporcionado"));
        }
    }

}
