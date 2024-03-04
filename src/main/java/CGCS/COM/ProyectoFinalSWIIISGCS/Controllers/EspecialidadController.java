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

@RestController
@RequestMapping("api/v1/especialidades")
public class EspecialidadController {

    @Autowired
    private EspecialidadService especialidadService;

    @GetMapping
    public ResponseEntity<?> listarEspecialidades(@RequestHeader(value = "API-Version", defaultValue = "v0.1.0") String apiVersion) throws IllegalOperationException {
        List<Especialidad> especialidades = especialidadService.listarEspecialidades();
        HttpHeaders headers = new HttpHeaders();
        headers.add("API-Version", apiVersion);
        return ResponseEntity.ok(GlobalResponse.ok(especialidades));
    }

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

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarEspecialidad(@PathVariable Long id) throws IllegalOperationException {
        especialidadService.eliminarEspecialidad(id);
        return ResponseEntity.ok(GlobalResponse.ok("Especialidad eliminada correctamente"));
    }

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
