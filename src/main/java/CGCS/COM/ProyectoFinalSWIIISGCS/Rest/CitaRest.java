/**
 * @file: CitaRest.java
 * @created: [Fecha de creación]
 */

package CGCS.COM.ProyectoFinalSWIIISGCS.Rest;

import CGCS.COM.ProyectoFinalSWIIISGCS.DTO.CitaDTO;
import CGCS.COM.ProyectoFinalSWIIISGCS.Domain.Cita;
import CGCS.COM.ProyectoFinalSWIIISGCS.Services.CitaService;
import CGCS.COM.ProyectoFinalSWIIISGCS.Services.DoctorService;
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
 * Controlador REST para las operaciones relacionadas con las citas.
 */
@RestController
@RequestMapping("/api/v1/cita")
public class CitaRest {

    @Autowired
    private CitaService citaService;

    @Autowired
    private ModelMapper modelMapper;

    /**
     * Endpoint para listar todas las citas.
     *
     * @return ResponseEntity con la respuesta.
     * @throws IllegalOperationException Si se produce una operación ilegal.
     */
    @GetMapping
    public ResponseEntity<?> listarCitas() throws IllegalOperationException {
        List<Cita> citas = citaService.listarCitas();

        List<CitaDTO> citaDTOs = citas.stream()
                .map(cita -> modelMapper.map(cita, CitaDTO.class))
                .collect(Collectors.toList());

        return ResponseEntity.ok(GlobalResponse.ok(citaDTOs));
    }

    /**
     * Endpoint para obtener una cita por su ID.
     *
     * @param id ID de la cita.
     * @return ResponseEntity con la respuesta.
     * @throws IllegalOperationException Si se produce una operación ilegal.
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerCitaPorId(@PathVariable Long id) throws IllegalOperationException {
        Optional<Cita> cita = citaService.obtenerCitaPorId(id);
        CitaDTO citaDTO = modelMapper.map(cita, CitaDTO.class);

        ApiResponse<CitaDTO> response = new ApiResponse<>(true, "Cita obtenida con éxito", citaDTO);
        return ResponseEntity.ok(response);
    }

    /**
     * Endpoint para guardar una nueva cita.
     *
     * @param citaDTO DTO de la cita a guardar.
     * @return ResponseEntity con la respuesta.
     * @throws IllegalOperationException Si se produce una operación ilegal.
     */
    @PostMapping
    public ResponseEntity<?> guardarCita(@RequestBody CitaDTO citaDTO) throws IllegalOperationException {
        Cita cita = modelMapper.map(citaDTO, Cita.class);
        citaService.registrarCita(cita);

        CitaDTO savedCitaDTO = modelMapper.map(cita, CitaDTO.class);
        ApiResponse<CitaDTO> response = new ApiResponse<>(true, "Cita guardada con éxito", savedCitaDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    /**
     * Endpoint para actualizar una cita existente.
     *
     * @param id      ID de la cita a actualizar.
     * @param citaDTO DTO con los nuevos datos de la cita.
     * @return ResponseEntity con la respuesta.
     * @throws IllegalOperationException Si se produce una operación ilegal.
     */
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<CitaDTO>> actualizarCita(@PathVariable Long id, @RequestBody CitaDTO citaDTO) throws IllegalOperationException {
        Cita cita = modelMapper.map(citaDTO, Cita.class);
        citaService.actualizarCita(id, cita);

        CitaDTO updatedCitaDTO = modelMapper.map(cita, CitaDTO.class);
        ApiResponse<CitaDTO> response = new ApiResponse<>(true, "Cita actualizada con éxito", updatedCitaDTO);
        return ResponseEntity.ok(response);
    }

    /**
     * Endpoint para eliminar una cita por su ID.
     *
     * @param id ID de la cita a eliminar.
     * @return ResponseEntity con la respuesta.
     * @throws IllegalOperationException Si se produce una operación ilegal.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarCita(@PathVariable Long id) throws IllegalOperationException {
        citaService.eliminarCita(id);
        ApiResponse<String> response = new ApiResponse<>(true, "Cita eliminada con éxito", null);
        return ResponseEntity.ok(response);
    }
}
