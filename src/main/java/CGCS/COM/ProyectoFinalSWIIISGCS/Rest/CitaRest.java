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
 * @file: CitaRest
 * @author: (c) AngelArribasplata
 * @created: 7/03/2024
 */
@RestController
@RequestMapping("/api/v1/cita")
public class CitaRest {
    @Autowired
    private CitaService citaService;
    @Autowired
    private ModelMapper modelMapper;
    @GetMapping
    public ResponseEntity<?> listarCitas() throws IllegalOperationException {
        List<Cita> citas = citaService.listarCitas(); // Llamada al método listarCitas del servicio

        List<CitaDTO> citaDTOs = citas.stream()
                .map(cita -> modelMapper.map(cita, CitaDTO.class))
                .collect(Collectors.toList());

        // Utilizando GlobalResponse.ok() para crear una respuesta exitosa
        return ResponseEntity.ok(GlobalResponse.ok(citaDTOs));
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerCitaPorId(@PathVariable Long id) throws IllegalOperationException {
        Optional<Cita> cita = citaService.obtenerCitaPorId(id); // Método para buscar una cita por su ID
        CitaDTO citaDTO = modelMapper.map(cita, CitaDTO.class); // Mapeo a DTO

        ApiResponse<CitaDTO> response = new ApiResponse<>(true, "Cita obtenida con éxito", citaDTO);
        return ResponseEntity.ok(response);
    }
    @PostMapping
    public ResponseEntity<?> guardarCita(@RequestBody CitaDTO citaDTO) throws IllegalOperationException {
        Cita cita = modelMapper.map(citaDTO, Cita.class);
        citaService.registrarCita(cita);

        CitaDTO savedCitaDTO = modelMapper.map(cita, CitaDTO.class);
        ApiResponse<CitaDTO> response = new ApiResponse<>(true, "Cita guardada con éxito", savedCitaDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<CitaDTO>> actualizarCita(@PathVariable Long id, @RequestBody CitaDTO citaDTO) throws IllegalOperationException {
        Cita cita = modelMapper.map(citaDTO, Cita.class);
        citaService.actualizarCita(id, cita);

        CitaDTO updatedCitaDTO = modelMapper.map(cita, CitaDTO.class);
        ApiResponse<CitaDTO> response = new ApiResponse<>(true, "Cita actualizada con éxito", updatedCitaDTO);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarCita(@PathVariable Long id) throws IllegalOperationException {
        citaService.eliminarCita(id);
        ApiResponse<String> response = new ApiResponse<>(true, "Cita eliminada con éxito", null);
        return ResponseEntity.ok(response);
    }
}
