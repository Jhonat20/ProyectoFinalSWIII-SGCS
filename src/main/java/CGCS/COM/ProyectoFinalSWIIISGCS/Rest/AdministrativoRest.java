package CGCS.COM.ProyectoFinalSWIIISGCS.Rest;

import CGCS.COM.ProyectoFinalSWIIISGCS.DTO.AdministrativoDTO;
import CGCS.COM.ProyectoFinalSWIIISGCS.Domain.Administrativo;
import CGCS.COM.ProyectoFinalSWIIISGCS.Services.AdministrativoService;
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
 * @file: AdministrativoRest
 * @author: Angel Arribasplata
 * @created: 7/03/2024
 */
@RestController
@RequestMapping("/api/v1/administrativo")
public class AdministrativoRest {
    @Autowired
    private AdministrativoService administrativoService;
    @Autowired
    private ModelMapper modelMapper;
    @GetMapping
    public ResponseEntity<?> listarAdministrativos() throws IllegalOperationException {
        List<Administrativo> administrativos = administrativoService.listarAdministrativo(); // Llamada al método listarAdministrativos del servicio

        List<AdministrativoDTO> administrativoDTOs = administrativos.stream()
                .map(administrativo -> modelMapper.map(administrativo, AdministrativoDTO.class))
                .collect(Collectors.toList());

        // Utilizando GlobalResponse.ok() para crear una respuesta exitosa
        return ResponseEntity.ok(GlobalResponse.ok(administrativoDTOs));
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerAdministrativoPorId(@PathVariable Long id) throws IllegalOperationException {
        Optional<Administrativo> administrativo = administrativoService.obtenerAdministrativoPorId(id); // Método para buscar un administrativo por su ID
        AdministrativoDTO administrativoDTO = modelMapper.map(administrativo, AdministrativoDTO.class); // Mapeo a DTO

        ApiResponse<AdministrativoDTO> response = new ApiResponse<>(true, "Administrativo obtenido con éxito", administrativoDTO);
        return ResponseEntity.ok(response);
    }
    @PostMapping
    public ResponseEntity<?> guardarAdministrativo(@RequestBody AdministrativoDTO administrativoDTO) throws IllegalOperationException {
        Administrativo administrativo = modelMapper.map(administrativoDTO, Administrativo.class);
        administrativoService.registrarAdministrativo(administrativo);

        AdministrativoDTO savedAdministrativoDTO = modelMapper.map(administrativo, AdministrativoDTO.class);
        ApiResponse<AdministrativoDTO> response = new ApiResponse<>(true, "Administrativo guardado con éxito", savedAdministrativoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<AdministrativoDTO>> actualizarAdministrativo(@PathVariable Long id, @RequestBody AdministrativoDTO administrativoDTO) throws IllegalOperationException {
        Administrativo administrativo = modelMapper.map(administrativoDTO, Administrativo.class);
        administrativoService.actualizarAdministrativo(id, administrativo);

        AdministrativoDTO updatedAdministrativoDTO = modelMapper.map(administrativo, AdministrativoDTO.class);
        ApiResponse<AdministrativoDTO> response = new ApiResponse<>(true, "Administrativo actualizado con éxito", updatedAdministrativoDTO);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarAdministrativo(@PathVariable Long id) throws IllegalOperationException {
        administrativoService.eliminarAdministrativo(id);
        ApiResponse<String> response = new ApiResponse<>(true, "Administrativo eliminado con éxito", null);
        return ResponseEntity.ok(response);
    }
}
