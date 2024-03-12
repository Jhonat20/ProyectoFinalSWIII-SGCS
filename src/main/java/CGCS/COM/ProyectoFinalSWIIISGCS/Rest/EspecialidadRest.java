/**
 * @file: EspecialidadRest.java
 * @created: [Fecha de creación]
 */

package CGCS.COM.ProyectoFinalSWIIISGCS.Rest;

import CGCS.COM.ProyectoFinalSWIIISGCS.DTO.EspecialidadDTO;
import CGCS.COM.ProyectoFinalSWIIISGCS.Domain.Especialidad;
import CGCS.COM.ProyectoFinalSWIIISGCS.Services.EspecialidadService;
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
 * Controlador REST para las operaciones relacionadas con las especialidades.
 */
@RestController
@RequestMapping("/api/v1/especialidad")
public class EspecialidadRest {

    @Autowired
    private EspecialidadService especialidadService;

    @Autowired
    private ModelMapper modelMapper;

    /**
     * Endpoint para listar todas las especialidades.
     *
     * @return ResponseEntity con la respuesta.
     * @throws IllegalOperationException Si se produce una operación ilegal.
     */
    @GetMapping
    public ResponseEntity<?> listarEspecialidades() throws IllegalOperationException {
        List<Especialidad> especialidades = especialidadService.listarEspecialidades();
        List<EspecialidadDTO> especialidadDTOs = especialidades.stream()
                .map(especialidad -> modelMapper.map(especialidad, EspecialidadDTO.class))
                .collect(Collectors.toList());

        return ResponseEntity.ok(GlobalResponse.ok(especialidadDTOs));
    }

    /**
     * Endpoint para obtener una especialidad por su ID.
     *
     * @param id ID de la especialidad.
     * @return ResponseEntity con la respuesta.
     * @throws IllegalOperationException Si se produce una operación ilegal.
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerEspecialidadPorId(@PathVariable Long id) throws IllegalOperationException {
        Optional<Especialidad> especialidad = especialidadService.buscarEspecialidadPorId(id);
        EspecialidadDTO especialidadDTO = modelMapper.map(especialidad, EspecialidadDTO.class);

        ApiResponse<EspecialidadDTO> response = new ApiResponse<>(true, "Especialidad obtenida con éxito", especialidadDTO);
        return ResponseEntity.ok(response);
    }

    /**
     * Endpoint para guardar una nueva especialidad.
     *
     * @param especialidadDTO DTO de la especialidad a guardar.
     * @return ResponseEntity con la respuesta.
     * @throws IllegalOperationException Si se produce una operación ilegal.
     */
    @PostMapping
    public ResponseEntity<?> guardarEspecialidad(@RequestBody EspecialidadDTO especialidadDTO) throws IllegalOperationException {
        Especialidad especialidad = modelMapper.map(especialidadDTO, Especialidad.class);
        especialidadService.registrarEspecialidad(especialidad);

        EspecialidadDTO savedEspecialidadDTO = modelMapper.map(especialidad, EspecialidadDTO.class);
        ApiResponse<EspecialidadDTO> response = new ApiResponse<>(true, "Especialidad guardada con éxito", savedEspecialidadDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    /**
     * Endpoint para actualizar una especialidad existente.
     *
     * @param id             ID de la especialidad a actualizar.
     * @param especialidadDTO DTO con los nuevos datos de la especialidad.
     * @return ResponseEntity con la respuesta.
     * @throws IllegalOperationException Si se produce una operación ilegal.
     */
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<EspecialidadDTO>> actualizarEspecialidad(@PathVariable Long id, @RequestBody EspecialidadDTO especialidadDTO) throws IllegalOperationException {
        Especialidad especialidad = modelMapper.map(especialidadDTO, Especialidad.class);
        especialidadService.modificarEspecialidad(id, especialidad);

        EspecialidadDTO updatedEspecialidadDTO = modelMapper.map(especialidad, EspecialidadDTO.class);
        ApiResponse<EspecialidadDTO> response = new ApiResponse<>(true, "Especialidad actualizada con éxito", updatedEspecialidadDTO);
        return ResponseEntity.ok(response);
    }

    /**
     * Endpoint para eliminar una especialidad por su ID.
     *
     * @param id ID de la especialidad a eliminar.
     * @return ResponseEntity con la respuesta.
     * @throws IllegalOperationException Si se produce una operación ilegal.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarEspecialidad(@PathVariable Long id) throws IllegalOperationException {
        especialidadService.eliminarEspecialidad(id);
        ApiResponse<String> response = new ApiResponse<>(true, "Especialidad eliminada con éxito", null);
        return ResponseEntity.ok(response);
    }
}