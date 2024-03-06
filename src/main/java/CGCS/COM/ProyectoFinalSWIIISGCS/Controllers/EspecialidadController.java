package CGCS.COM.ProyectoFinalSWIIISGCS.Controllers;

import CGCS.COM.ProyectoFinalSWIIISGCS.Domain.Especialidad;
import CGCS.COM.ProyectoFinalSWIIISGCS.Services.EspecialidadService;
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
 * Clase controladora para gestionar entidades Especialidad.
 */
@RestController
@RequestMapping("api/v1/especialidades")
public class EspecialidadController {

    @Autowired
    private EspecialidadService especialidadService;

    /**
     * Endpoint para listar todas las Especialidades.
     *
     * @return ResponseEntity con una lista de Especialidades o un mensaje de error.
     * @throws IllegalOperationException si ocurre una operación ilegal.
     */
    @GetMapping
    public ResponseEntity<?> listarEspecialidades() throws IllegalOperationException {
        List<Especialidad> especialidades = especialidadService.listarEspecialidades();
        return ResponseEntity.ok(GlobalResponse.ok(especialidades));
    }

    /**
     * Endpoint para registrar una nueva Especialidad.
     *
     * @param especialidad    Objeto Especialidad a registrar.
     * @param bindingResult   BindingResult para validar el cuerpo de la solicitud.
     * @return ResponseEntity con la Especialidad registrada o errores de validación.
     * @throws IllegalOperationException si ocurre una operación ilegal.
     */
    @PostMapping
    public ResponseEntity<?> registrarEspecialidad(@Valid @RequestBody Especialidad especialidad, BindingResult bindingResult) throws IllegalOperationException {
        if (bindingResult.hasErrors()) {
            Map<String, String> errores = ValidationUtil.getValidationErrors(bindingResult);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errores);
        } else {
            Especialidad nuevaEspecialidad = especialidadService.registrarEspecialidad(especialidad);
            return ResponseEntity.ok(GlobalResponse.ok(nuevaEspecialidad));
        }
    }

    /**
     * Endpoint para obtener una Especialidad específica por ID.
     *
     * @param id ID de la Especialidad a recuperar.
     * @return ResponseEntity con la Especialidad recuperada o un mensaje de error.
     * @throws IllegalOperationException si ocurre una operación ilegal.
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerEspecialidad(@PathVariable Long id) throws IllegalOperationException {
        Optional<Especialidad> optionalEspecialidad = especialidadService.buscarEspecialidadPorId(id);
        if (optionalEspecialidad.isPresent()) {
            Especialidad especialidad = optionalEspecialidad.get();
            return ResponseEntity.ok(GlobalResponse.ok(especialidad));
        } else {
            return ResponseEntity.ok(GlobalResponse.error("No se encontró la especialidad con el ID proporcionado"));
        }
    }

    /**
     * Endpoint para eliminar una Especialidad específica por ID.
     *
     * @param id ID de la Especialidad a eliminar.
     * @return ResponseEntity con un mensaje de éxito o un mensaje de error.
     * @throws IllegalOperationException si ocurre una operación ilegal.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarEspecialidad(@PathVariable Long id) throws IllegalOperationException {
        especialidadService.eliminarEspecialidad(id);
        return ResponseEntity.ok(GlobalResponse.ok("Especialidad eliminada correctamente"));
    }

    /**
     * Endpoint para actualizar una Especialidad específica por ID.
     *
     * @param id              ID de la Especialidad a actualizar.
     * @param especialidad    Objeto Especialidad actualizado.
     * @param bindingResult   BindingResult para validar el cuerpo de la solicitud.
     * @return ResponseEntity con la Especialidad actualizada o errores de validación.
     * @throws IllegalOperationException si ocurre una operación ilegal.
     */
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarEspecialidad(@PathVariable Long id, @Valid @RequestBody Especialidad especialidad, BindingResult bindingResult) throws IllegalOperationException {
        if (bindingResult.hasErrors()) {
            Map<String, String> errores = ValidationUtil.getValidationErrors(bindingResult);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errores);
        } else {
            Especialidad especialidadActualizada = especialidadService.modificarEspecialidad(id, especialidad);
            return ResponseEntity.ok(GlobalResponse.ok(especialidadActualizada));
        }
    }
}
