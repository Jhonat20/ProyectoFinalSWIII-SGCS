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
            return ResponseEntity.ok(new CustomVerificacionResponse("No hay información disponible para mostrar"));
        } else if(historialMedicos.size() > 0) {
            return ResponseEntity.ok(new SuccessResponse("Historiales médicos encontrados exitosamente", historialMedicos));
        }else {
            return ResponseEntity.ok(GlobalResponse.ok(historialMedicos));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerHistorialMedico(@PathVariable Long id) throws IllegalOperationException {
        Optional<HistorialMedico> optionalHistorialMedico = historialMedicoService.BuscarPorId(id);
        if (optionalHistorialMedico.isPresent()) {
            HistorialMedico historialMedico = optionalHistorialMedico.get();
            return ResponseEntity.ok(new SuccessResponse("Historial médico encontrado exitosamente", historialMedico));
        } else if(optionalHistorialMedico.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new CustomVerificacionResponse("No se encontró el historial médico con el ID N°  ["+id+"] proporcionado"));
        }else {
            return ResponseEntity.ok(GlobalResponse.ok(optionalHistorialMedico));
        }
    }

    @PostMapping
    public ResponseEntity<?> guardarHistorialMedico(@Valid @RequestBody HistorialMedico historialMedico, BindingResult bindingResult) throws IllegalOperationException {
        if (bindingResult.hasErrors()) {
            Map<String, String> errores = ValidationUtil.getValidationErrors(bindingResult);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errores);
        } else if(historialMedico.getIdHistorialMedico() == null || historialMedico.getIdHistorialMedico() == 0){
            HistorialMedico historialMedicoGuardado = historialMedicoService.Grabar(historialMedico);
            return ResponseEntity.status(HttpStatus.CREATED).body(new SuccessResponse("Historial médico guardado exitosamente", historialMedicoGuardado));
        }
        else {
            return ResponseEntity.ok(GlobalResponse.ok(historialMedico));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarHistorialMedico(@PathVariable Long id, @Valid @RequestBody HistorialMedico historialMedico, BindingResult bindingResult) throws IllegalOperationException {
        if (bindingResult.hasErrors()) {
            Map<String, String> errores = ValidationUtil.getValidationErrors(bindingResult);
            return ResponseEntity.ok(GlobalResponse.ok(errores));
        } else if(historialMedico.getIdHistorialMedico() == null || historialMedico.getIdHistorialMedico() == 0){
            HistorialMedico historialMedicoActualizado = historialMedicoService.Actualizar(id, historialMedico);
            return ResponseEntity.ok(new SuccessResponse("Historial médico actualizado exitosamente", historialMedicoActualizado));
        }else {
            return ResponseEntity.ok(GlobalResponse.ok(historialMedico));//Verificar
        }
    }



    @PatchMapping("/{id}")
    public ResponseEntity<?> actualizarParcialHistorialMedico(@PathVariable Long id, @RequestBody Map<String, Object> cambios) {
        Optional<HistorialMedico> historialMedicoOptional = historialMedicoService.BuscarPorId(id);
        if (historialMedicoOptional.isPresent()) {
            HistorialMedico historialMedico = historialMedicoOptional.get();
            HistorialMedico historialMedicoActualizado = historialMedicoService.actualizarParcial(id, cambios);
            return ResponseEntity.ok(new SuccessResponse("Historial médico actualizado exitosamente", historialMedicoActualizado));
        } else if(historialMedicoOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new CustomVerificacionResponse("No se encontró el historial médico con el ID proporcionado"));
        }else {
            return ResponseEntity.ok(GlobalResponse.ok(historialMedicoOptional));
        }
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarHistorialMedico(@PathVariable Long id) throws IllegalOperationException {
        Optional<HistorialMedico> historialMedicoOptional = historialMedicoService.BuscarPorId(id);
        if (historialMedicoOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new CustomVerificacionResponse("No se encontró el historial médico a eliminar"));
        }else if (historialMedicoOptional.isPresent()){
            historialMedicoService.Eliminar(id);
            return ResponseEntity.ok(new CustomVerificacionResponse("Historial médico eliminado exitosamente"));
        }else {
            return ResponseEntity.ok(GlobalResponse.ok(historialMedicoOptional));
        }
    }

}
