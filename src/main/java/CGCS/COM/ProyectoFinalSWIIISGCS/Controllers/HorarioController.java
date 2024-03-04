package CGCS.COM.ProyectoFinalSWIIISGCS.Controllers;

import CGCS.COM.ProyectoFinalSWIIISGCS.Domain.Horario;
import CGCS.COM.ProyectoFinalSWIIISGCS.Services.HorarioService;
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
 * Controlador REST para manejar las operaciones relacionadas con los horarios.
 */
@RestController
@RequestMapping("api/v1/horario")
public class HorarioController {

    @Autowired
    private HorarioService horarioService;

    /**
     * Obtiene una lista de todos los horarios.
     *
     * @return ResponseEntity con la lista de horarios o un mensaje de error si no hay información disponible.
     */
    @GetMapping
    public ResponseEntity<?> listarHorarios() {
        List<Horario> horarios = horarioService.listarHorario();
        if (horarios.isEmpty()) {
            return ResponseEntity.ok(GlobalResponse.error("No hay información disponible para mostrar"));
        } else {
            return ResponseEntity.ok(GlobalResponse.ok(horarios));
        }
    }

    /**
     * Guarda un nuevo horario.
     *
     * @param horario       El horario a guardar.
     * @param bindingResult Resultado de la validación de entrada.
     * @return ResponseEntity con el horario guardado o un mensaje de error si la validación falla.
     * @throws IllegalOperationException Si la validación falla.
     */
    @PostMapping
    public ResponseEntity<?> guardarHorario(@Valid @RequestBody Horario horario, BindingResult bindingResult) throws IllegalOperationException {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(GlobalResponse.error("Error en los datos proporcionados"));
        } else {
            Horario nuevoHorario = horarioService.guardadHorario(horario);
            return ResponseEntity.ok(GlobalResponse.ok(nuevoHorario));
        }
    }

    /**
     * Obtiene un horario por su ID.
     *
     * @param id ID del horario a buscar.
     * @return ResponseEntity con el horario correspondiente o un mensaje de error si no se encuentra.
     * @throws IllegalOperationException Si no se encuentra el horario con el ID proporcionado.
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerHorario(@PathVariable Long id) throws IllegalOperationException {
        Optional<Horario> horario = horarioService.buscarPorId(id);
        if (horario.isPresent()) {
            Horario horarioEncontrado = horario.get();
            return ResponseEntity.ok(GlobalResponse.ok(horarioEncontrado));
        } else {
            return ResponseEntity.ok(GlobalResponse.error("No se encontró el horario con el ID proporcionado"));
        }
    }

    /**
     * Actualiza un horario existente.
     *
     * @param id             ID del horario a actualizar.
     * @param horario El horario actualizado.
     * @param bindingResult  Resultado de la validación de entrada.
     * @return ResponseEntity con el horario actualizado o un mensaje de error si la validación falla o el horario no se encuentra.
     * @throws IllegalOperationException Si la validación falla o el horario no se encuentra.
     */
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarHorario(@PathVariable Long id, @Valid @RequestBody Horario horario, BindingResult bindingResult) throws IllegalOperationException {
        Optional<Horario> horarioOptional = horarioService.buscarPorId(id);
        if (horarioOptional.isPresent()) {
            if (bindingResult.hasErrors()) {
                Map<String, String> errores = ValidationUtil.getValidationErrors(bindingResult);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errores);
            } else {
                Horario horarioActualizado = horarioService.actualizarHorario(id, horario);
                return ResponseEntity.ok(GlobalResponse.ok(horarioActualizado));
            }
        } else {
            return ResponseEntity.ok(GlobalResponse.error("No se encontró el horario con el ID proporcionado"));
        }
    }

    /**
     * Elimina un horario existente.
     *
     * @param id ID del horario a eliminar.
     * @return ResponseEntity con un mensaje de éxito si el horario se elimina correctamente o un mensaje de error si no se encuentra.
     * @throws IllegalOperationException Si el horario no se encuentra.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarHorario(@PathVariable Long id) throws IllegalOperationException {
        Optional<Horario> horarioOptional = horarioService.buscarPorId(id);
        if (horarioOptional.isPresent()) {
            horarioService.Eliminar(id);
            return ResponseEntity.ok(GlobalResponse.ok("Horario eliminado correctamente"));
        } else {
            return ResponseEntity.ok(GlobalResponse.error("No se encontró el horario con el ID proporcionado"));
        }
    }

    /**
     * Actualiza parcialmente un horario existente.
     *
     * @param id      ID del horario a actualizar.
     * @param cambios Mapa con los campos a actualizar y sus nuevos valores.
     * @return ResponseEntity con el horario actualizado parcialmente o un mensaje de error si el horario no se encuentra.
     * @throws IllegalOperationException Si el horario no se encuentra.
     */
    @PatchMapping("/{id}")
    public ResponseEntity<?> actualizarParcialHorario(@PathVariable Long id, @RequestBody Map<String, Object> cambios) throws IllegalOperationException {
        Optional<Horario> horarioOptional = horarioService.buscarPorId(id);
        if (horarioOptional.isPresent()) {
            Horario horario = horarioOptional.get();
            horarioService.actualizarParcial(id, cambios);
            return ResponseEntity.ok(GlobalResponse.ok(horario));
        } else {
            return ResponseEntity.ok(GlobalResponse.error("No se encontró el horario con el ID proporcionado"));
        }
    }
}
