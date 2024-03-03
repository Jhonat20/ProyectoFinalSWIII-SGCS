package CGCS.COM.ProyectoFinalSWIIISGCS.Controllers;


import CGCS.COM.ProyectoFinalSWIIISGCS.Domain.HistorialMedico;
import CGCS.COM.ProyectoFinalSWIIISGCS.Services.HistorialMedicoService;
import CGCS.COM.ProyectoFinalSWIIISGCS.exception.ErrorResponse;
import CGCS.COM.ProyectoFinalSWIIISGCS.exception.IllegalOperationException;
import CGCS.COM.ProyectoFinalSWIIISGCS.responses.GlobalResponse;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
    public ResponseEntity<?> obtenerPorId(@PathVariable Long id) {
        Optional <HistorialMedico> historialMedico = historialMedicoService.BuscarPorId(id);
        if (historialMedico.isPresent()) {
            HistorialMedico historialMedicoEncontrado = historialMedico.get();
            return ResponseEntity.ok(historialMedicoEncontrado);
        } else {
            return ResponseEntity.ok(GlobalResponse.error("No se encontró el historial médico con el ID proporcionado"));
        }
    }

    @PostMapping
    public ResponseEntity<?> guardar(@RequestBody HistorialMedico historialMedico) {
        try {
            historialMedico = historialMedicoService.Grabar(historialMedico);
            return ResponseEntity.status(HttpStatus.CREATED).body(historialMedico);
        } catch(IllegalOperationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorResponse("Error interno del servidor"));
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar (@PathVariable Long id, @RequestBody HistorialMedico historialMedico) {
        try {
            historialMedico = historialMedicoService.Actualizar(id, historialMedico);
            return ResponseEntity.ok(historialMedico);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse(e.getMessage()));
        } catch (IllegalOperationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorResponse("Error interno del servidor"));
        }
    }

}
