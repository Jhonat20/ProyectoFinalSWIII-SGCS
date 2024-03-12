package CGCS.COM.ProyectoFinalSWIIISGCS.Rest;

import CGCS.COM.ProyectoFinalSWIIISGCS.DTO.EspecialidadDTO;
import CGCS.COM.ProyectoFinalSWIIISGCS.Domain.Especialidad;
import CGCS.COM.ProyectoFinalSWIIISGCS.Services.DoctorService;
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
 * @file: EspecialidadRest
 * @author: Angel Arribasplata
 * @created: 7/03/2024
 */
@RestController
@RequestMapping("/api/v1/especialidad")
public class EspecialidadRest {
    @Autowired
    private EspecialidadService especialidadService;
    @Autowired
    private ModelMapper modelMapper;
    @GetMapping
    public ResponseEntity<?> listarEspecialidades() throws IllegalOperationException {
        List<Especialidad> especialidades = especialidadService.listarEspecialidades(); // Llamada al método listarEspecialidades del servicio

        List<EspecialidadDTO> especialidadDTOs = especialidades.stream()
                .map(especialidad -> modelMapper.map(especialidad, EspecialidadDTO.class))
                .collect(Collectors.toList());

        // Utilizando GlobalResponse.ok() para crear una respuesta exitosa
        return ResponseEntity.ok(GlobalResponse.ok(especialidadDTOs));
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerEspecialidadPorId(@PathVariable Long id) throws IllegalOperationException {
        Optional<Especialidad> especialidad = especialidadService.buscarEspecialidadPorId(id); // Método para buscar una especialidad por su ID
        EspecialidadDTO especialidadDTO = modelMapper.map(especialidad, EspecialidadDTO.class); // Mapeo a DTO

        ApiResponse<EspecialidadDTO> response = new ApiResponse<>(true, "Especialidad obtenida con éxito", especialidadDTO);
        return ResponseEntity.ok(response);
    }
    @PostMapping
    public ResponseEntity<?> guardarEspecialidad(@RequestBody EspecialidadDTO especialidadDTO) throws IllegalOperationException {
        Especialidad especialidad = modelMapper.map(especialidadDTO, Especialidad.class);
        especialidadService.registrarEspecialidad(especialidad);

        EspecialidadDTO savedEspecialidadDTO = modelMapper.map(especialidad, EspecialidadDTO.class);
        ApiResponse<EspecialidadDTO> response = new ApiResponse<>(true, "Especialidad guardada con éxito", savedEspecialidadDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<EspecialidadDTO>> actualizarEspecialidad(@PathVariable Long id, @RequestBody EspecialidadDTO especialidadDTO) throws IllegalOperationException {
        Especialidad especialidad = modelMapper.map(especialidadDTO, Especialidad.class);
        especialidadService.modificarEspecialidad(id, especialidad);

        EspecialidadDTO updatedEspecialidadDTO = modelMapper.map(especialidad, EspecialidadDTO.class);
        ApiResponse<EspecialidadDTO> response = new ApiResponse<>(true, "Especialidad actualizada con éxito", updatedEspecialidadDTO);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarEspecialidad(@PathVariable Long id) throws IllegalOperationException {
        especialidadService.eliminarEspecialidad(id);
        ApiResponse<String> response = new ApiResponse<>(true, "Especialidad eliminada con éxito", null);
        return ResponseEntity.ok(response);
    }
}
