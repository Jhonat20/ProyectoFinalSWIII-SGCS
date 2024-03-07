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
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
