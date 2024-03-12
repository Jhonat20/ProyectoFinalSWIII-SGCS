package CGCS.COM.ProyectoFinalSWIIISGCS.Rest;

import CGCS.COM.ProyectoFinalSWIIISGCS.DTO.HorarioDTO;
import CGCS.COM.ProyectoFinalSWIIISGCS.Domain.Horario;
import CGCS.COM.ProyectoFinalSWIIISGCS.Services.HistorialMedicoService;
import CGCS.COM.ProyectoFinalSWIIISGCS.Services.HorarioService;
import CGCS.COM.ProyectoFinalSWIIISGCS.exception.IllegalOperationException;
import CGCS.COM.ProyectoFinalSWIIISGCS.responses.ApiResponse;
import CGCS.COM.ProyectoFinalSWIIISGCS.responses.GlobalResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @file: HorarioRest
 * @author: Angel Arribasplata
 * @created: 7/03/2024
 */
@RestController
@RequestMapping("/api/v1/horario")
public class HorarioRest {
    @Autowired
    private HorarioService horarioService;
    @Autowired
    private ModelMapper modelMapper;
    @GetMapping
    public ResponseEntity<?> listarHorarios() throws IllegalOperationException {
        List<Horario> horarios = horarioService.listarHorario(); // Llamada al método listarHorarios del servicio

        List<HorarioDTO> horarioDTOs = horarios.stream()
                .map(horario -> modelMapper.map(horario, HorarioDTO.class))
                .collect(Collectors.toList());

        // Utilizando GlobalResponse.ok() para crear una respuesta exitosa
        return ResponseEntity.ok(GlobalResponse.ok(horarioDTOs));
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerHorarioPorId(@PathVariable Long id) throws IllegalOperationException {
        Optional<Horario> horario = horarioService.buscarPorId(id); // Método para buscar un horario por su ID
        HorarioDTO horarioDTO = modelMapper.map(horario, HorarioDTO.class); // Mapeo a DTO

        ApiResponse<HorarioDTO> response = new ApiResponse<>(true, "Horario obtenido con éxito", horarioDTO);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<?> guardarHorario(@RequestBody HorarioDTO horarioDTO) throws IllegalOperationException {
        Horario horario = modelMapper.map(horarioDTO, Horario.class);
        horarioService.guardadHorario(horario);

        HorarioDTO savedHorarioDTO = modelMapper.map(horario, HorarioDTO.class);
        ApiResponse<HorarioDTO> response = new ApiResponse<>(true, "Horario guardado con éxito", savedHorarioDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<HorarioDTO>> actualizarHorario(@PathVariable Long id, @RequestBody HorarioDTO horarioDTO) throws IllegalOperationException {
        Horario horario = modelMapper.map(horarioDTO, Horario.class);
        horarioService.actualizarHorario(id, horario);

        HorarioDTO updatedHorarioDTO = modelMapper.map(horario, HorarioDTO.class);
        ApiResponse<HorarioDTO> response = new ApiResponse<>(true, "Horario actualizado con éxito", updatedHorarioDTO);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarHorario(@PathVariable Long id) throws IllegalOperationException {
        horarioService.Eliminar(id);
        ApiResponse<String> response = new ApiResponse<>(true, "Horario eliminado con éxito", null);
        return ResponseEntity.ok(response);
    }
}
