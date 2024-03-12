/**
 * @file: PacienteRest.java
 * @created: [Fecha de creación]
 */

package CGCS.COM.ProyectoFinalSWIIISGCS.Rest;

import CGCS.COM.ProyectoFinalSWIIISGCS.DTO.PacienteDTO;
import CGCS.COM.ProyectoFinalSWIIISGCS.Domain.Paciente;
import CGCS.COM.ProyectoFinalSWIIISGCS.Services.PacienteService;
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
 * Controlador REST para las operaciones relacionadas con los pacientes.
 */
@RestController
@RequestMapping("/api/v1/paciente")
public class PacienteRest {

    @Autowired
    private PacienteService pacienteService;

    @Autowired
    private ModelMapper modelMapper;

    /**
     * Endpoint para listar todos los pacientes.
     *
     * @return ResponseEntity con la respuesta.
     */
    @GetMapping
    public ResponseEntity<?> listarPacientes() {
        try {
            List<Paciente> pacientes = pacienteService.listarPacientes();
            List<PacienteDTO> pacienteDTOs = pacientes.stream()
                    .map(paciente -> modelMapper.map(paciente, PacienteDTO.class))
                    .collect(Collectors.toList());

            return ResponseEntity.ok(GlobalResponse.ok(pacienteDTOs));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(GlobalResponse.error("Error interno del servidor"));
        }
    }

    /**
     * Endpoint para obtener un paciente por su ID.
     *
     * @param id ID del paciente.
     * @return ResponseEntity con la respuesta.
     * @throws IllegalOperationException Si se produce una operación ilegal.
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerPacientePorId(@PathVariable Long id) throws IllegalOperationException {
        Optional<Paciente> paciente = pacienteService.obtenerPacientePorId(id);
        PacienteDTO pacienteDTO = modelMapper.map(paciente, PacienteDTO.class);

        ApiResponse<PacienteDTO> response = new ApiResponse<>(true, "Paciente obtenido con éxito", pacienteDTO);
        return ResponseEntity.ok(response);
    }

    /**
     * Endpoint para guardar un nuevo paciente.
     *
     * @param pacienteDTO DTO del paciente a guardar.
     * @return ResponseEntity con la respuesta.
     * @throws IllegalOperationException Si se produce una operación ilegal.
     */
    @PostMapping
    public ResponseEntity<?> guardarPaciente(@RequestBody PacienteDTO pacienteDTO) throws IllegalOperationException {
        Paciente paciente = modelMapper.map(pacienteDTO, Paciente.class);
        pacienteService.registrarPaciente(paciente);

        PacienteDTO savedPacienteDTO = modelMapper.map(paciente, PacienteDTO.class);
        ApiResponse<PacienteDTO> response = new ApiResponse<>(true, "Paciente guardado con éxito", savedPacienteDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    /**
     * Endpoint para actualizar un paciente existente.
     *
     * @param id          ID del paciente a actualizar.
     * @param pacienteDTO DTO con los nuevos datos del paciente.
     * @return ResponseEntity con la respuesta.
     * @throws IllegalOperationException Si se produce una operación ilegal.
     */
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<PacienteDTO>> actualizarPaciente(@PathVariable Long id, @RequestBody PacienteDTO pacienteDTO) throws IllegalOperationException {
        Paciente paciente = modelMapper.map(pacienteDTO, Paciente.class);
        pacienteService.actualizarPaciente(id, paciente);

        PacienteDTO updatedPacienteDTO = modelMapper.map(paciente, PacienteDTO.class);
        ApiResponse<PacienteDTO> response = new ApiResponse<>(true, "Paciente actualizado con éxito", updatedPacienteDTO);
        return ResponseEntity.ok(response);
    }

    /**
     * Endpoint para eliminar un paciente por su ID.
     *
     * @param id ID del paciente a eliminar.
     * @return ResponseEntity con la respuesta.
     * @throws IllegalOperationException Si se produce una operación ilegal.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarPaciente(@PathVariable Long id) throws IllegalOperationException {
        pacienteService.eliminarPaciente(id);
        ApiResponse<String> response = new ApiResponse<>(true, "Paciente eliminado con éxito", null);
        return ResponseEntity.ok(response);
    }
}
