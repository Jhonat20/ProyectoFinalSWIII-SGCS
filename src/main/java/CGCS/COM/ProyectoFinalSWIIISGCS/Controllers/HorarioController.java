package CGCS.COM.ProyectoFinalSWIIISGCS.Controllers;

import CGCS.COM.ProyectoFinalSWIIISGCS.Domain.Horario;
import CGCS.COM.ProyectoFinalSWIIISGCS.Services.HorarioService;
import CGCS.COM.ProyectoFinalSWIIISGCS.exception.IllegalOperationException;
import CGCS.COM.ProyectoFinalSWIIISGCS.responses.GlobalResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/horario")
public class HorarioController {

    @Autowired
    private HorarioService horarioService;

    @GetMapping
    public ResponseEntity<?> listarHorarios() {
        List<Horario> horarios = horarioService.listarHorario();
        if (horarios.isEmpty()) {
            // Si no hay horarios disponibles, devuelve un mensaje informativo
            return ResponseEntity.ok(GlobalResponse.error("No hay información disponible para mostrar"));
        } else {
            // Si hay horarios disponibles, devuelve la lista de horarios
            return ResponseEntity.ok(GlobalResponse.ok(horarios));
        }
    }


    @PostMapping
    public ResponseEntity<?> guardarHorario(@Valid @RequestBody Horario horario, BindingResult bindingResult)throws IllegalOperationException {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(GlobalResponse.error("Error en los datos proporcionados"));
        } else {
            Horario nuevoHorario = horarioService.guardadHorario(horario);
            return ResponseEntity.ok(GlobalResponse.ok(nuevoHorario));
        }

    }

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
}
