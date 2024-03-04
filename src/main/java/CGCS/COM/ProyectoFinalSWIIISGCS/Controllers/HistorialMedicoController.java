package CGCS.COM.ProyectoFinalSWIIISGCS.Controllers;

import CGCS.COM.ProyectoFinalSWIIISGCS.Domain.HistorialMedico;
import CGCS.COM.ProyectoFinalSWIIISGCS.Services.HistorialMedicoService;
import CGCS.COM.ProyectoFinalSWIIISGCS.Validation.ValidationUtil;
import CGCS.COM.ProyectoFinalSWIIISGCS.exception.IllegalOperationException;
import CGCS.COM.ProyectoFinalSWIIISGCS.responses.CustomVerificacionResponse;
import CGCS.COM.ProyectoFinalSWIIISGCS.responses.GlobalResponse;
import CGCS.COM.ProyectoFinalSWIIISGCS.responses.SuccessResponse;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.NoResultException;
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
@RequestMapping("/api/v1/HistorialMedico")
public class HistorialMedicoController {

    @Autowired
    private HistorialMedicoService historialMedicoService;

    @GetMapping
    public ResponseEntity<?> listarHistorialMedico() {
       List<HistorialMedico> historialMedicos = historialMedicoService.listarHistorialMedico();
            if (historialMedicos.isEmpty()) {
                return ResponseEntity.ok(GlobalResponse.error("No hay información disponible para mostrar"));
            } else {
                return ResponseEntity.ok(GlobalResponse.ok(historialMedicos));
            }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerHistorialMedico(@PathVariable Long id) throws IllegalOperationException {
        Optional<HistorialMedico> optionalHistorialMedico = historialMedicoService.BuscarPorId(id);
        if(optionalHistorialMedico.isPresent()) {
            HistorialMedico historialMedico = optionalHistorialMedico.get();
            return ResponseEntity.ok(GlobalResponse.ok(historialMedico));
        } else {
            return ResponseEntity.ok(GlobalResponse.error("No se encontró el historial médico con el ID proporcionado"));
        }
    }

    @PostMapping
    public ResponseEntity<?> guardarHistorialMedico(@Valid @RequestBody HistorialMedico historialMedico, BindingResult bindingResult) throws IllegalOperationException {
        if (bindingResult.hasErrors()) {
            Map<String, String> errores = ValidationUtil.getValidationErrors(bindingResult);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errores);
        }else {
            HistorialMedico nuevoHistorialMedico = historialMedicoService.Grabar(historialMedico);
            return ResponseEntity.ok(GlobalResponse.ok(nuevoHistorialMedico));
        }
    }

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

    @PatchMapping("/{id}")
    public ResponseEntity<?> actualizarParcialHistorialMedico(@PathVariable Long id, @RequestBody Map<String, Object> cambios) throws IllegalOperationException{
        Optional<HistorialMedico> historialMedicoOptional = historialMedicoService.BuscarPorId(id);
        if(historialMedicoOptional.isPresent()){
            HistorialMedico historialMedico = historialMedicoOptional.get();
            historialMedicoService.actualizarParcial(id, cambios);
            return ResponseEntity.ok(GlobalResponse.ok(historialMedico));
        } else {
            return ResponseEntity.ok(GlobalResponse.error("No se encontró el historial médico con el ID proporcionado"));
        }
    }



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
