/**
 * @file: AdministrativoRest.java
 * @created: [Fecha de creación]
 */

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
 * Controlador REST para las operaciones relacionadas con los administrativos.
 */
@RestController
@RequestMapping("/api/v1/administrativo")
public class AdministrativoRest {

    @Autowired
    private AdministrativoService administrativoService;

    @Autowired
    private ModelMapper modelMapper;

    /**
     * Endpoint para listar todos los administrativos.
     *
     * @return ResponseEntity con la respuesta.
     * @throws IllegalOperationException Si se produce una operación ilegal.
     */
    @GetMapping
    public ResponseEntity<?> listarAdministrativos() throws IllegalOperationException {
        List<Administrativo> administrativos = administrativoService.listarAdministrativo();

        List<AdministrativoDTO> administrativoDTOs = administrativos.stream()
                .map(administrativo -> modelMapper.map(administrativo, AdministrativoDTO.class))
                .collect(Collectors.toList());

        return ResponseEntity.ok(GlobalResponse.ok(administrativoDTOs));
    }

    /**
     * Endpoint para obtener un administrativo por su ID.
     *
     * @param id ID del administrativo.
     * @return ResponseEntity con la respuesta.
     * @throws IllegalOperationException Si se produce una operación ilegal.
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerAdministrativoPorId(@PathVariable Long id) throws IllegalOperationException {
        Optional<Administrativo> administrativo = administrativoService.obtenerAdministrativoPorId(id);
        AdministrativoDTO administrativoDTO = modelMapper.map(administrativo, AdministrativoDTO.class);

        ApiResponse<AdministrativoDTO> response = new ApiResponse<>(true, "Administrativo obtenido con éxito", administrativoDTO);
        return ResponseEntity.ok(response);
    }

    /**
     * Endpoint para guardar un nuevo administrativo.
     *
     * @param administrativoDTO DTO del administrativo a guardar.
     * @return ResponseEntity con la respuesta.
     * @throws IllegalOperationException Si se produce una operación ilegal.
     */
    @PostMapping
    public ResponseEntity<?> guardarAdministrativo(@RequestBody AdministrativoDTO administrativoDTO) throws IllegalOperationException {
        Administrativo administrativo = modelMapper.map(administrativoDTO, Administrativo.class);
        administrativoService.registrarAdministrativo(administrativo);

        AdministrativoDTO savedAdministrativoDTO = modelMapper.map(administrativo, AdministrativoDTO.class);
        ApiResponse<AdministrativoDTO> response = new ApiResponse<>(true, "Administrativo guardado con éxito", savedAdministrativoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    /**
     * Endpoint para actualizar un administrativo existente.
     *
     * @param id              ID del administrativo a actualizar.
     * @param administrativoDTO DTO con los nuevos datos del administrativo.
     * @return ResponseEntity con la respuesta.
     * @throws IllegalOperationException Si se produce una operación ilegal.
     */
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<AdministrativoDTO>> actualizarAdministrativo(@PathVariable Long id, @RequestBody AdministrativoDTO administrativoDTO) throws IllegalOperationException {
        Administrativo administrativo = modelMapper.map(administrativoDTO, Administrativo.class);
        administrativoService.actualizarAdministrativo(id, administrativo);

        AdministrativoDTO updatedAdministrativoDTO = modelMapper.map(administrativo, AdministrativoDTO.class);
        ApiResponse<AdministrativoDTO> response = new ApiResponse<>(true, "Administrativo actualizado con éxito", updatedAdministrativoDTO);
        return ResponseEntity.ok(response);
    }

    /**
     * Endpoint para eliminar un administrativo por su ID.
     *
     * @param id ID del administrativo a eliminar.
     * @return ResponseEntity con la respuesta.
     * @throws IllegalOperationException Si se produce una operación ilegal.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarAdministrativo(@PathVariable Long id) throws IllegalOperationException {
        administrativoService.eliminarAdministrativo(id);
        ApiResponse<String> response = new ApiResponse<>(true, "Administrativo eliminado con éxito", null);
        return ResponseEntity.ok(response);
    }
}
