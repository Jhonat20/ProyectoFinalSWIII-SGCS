package CGCS.COM.ProyectoFinalSWIIISGCS.Rest;

import CGCS.COM.ProyectoFinalSWIIISGCS.DTO.HistorialMedicoDTO;
import CGCS.COM.ProyectoFinalSWIIISGCS.Domain.HistorialMedico;
import CGCS.COM.ProyectoFinalSWIIISGCS.Services.HistorialMedicoService;
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
 * @file: HistorialMedicoRest
 * @author: Angel Arribasplata
 * @created: 7/03/2024
 */
@RestController
@RequestMapping("/api/v1/historialMedico")
public class HistorialMedicoRest {
    @Autowired
    private HistorialMedicoService historialMedicoService;
    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<?> listarHistorialesMedicos() throws IllegalOperationException {
        List<HistorialMedico> historialesMedicos = historialMedicoService.listarHistorialMedico(); // Llamada al método listarHistorialesMedicos del servicio

        List<HistorialMedicoDTO> historialMedicoDTOs = historialesMedicos.stream()
                .map(historialMedico -> modelMapper.map(historialMedico, HistorialMedicoDTO.class))
                .collect(Collectors.toList());

        // Utilizando GlobalResponse.ok() para crear una respuesta exitosa
        return ResponseEntity.ok(GlobalResponse.ok(historialMedicoDTOs));
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerHistorialMedicoPorId(@PathVariable Long id) throws IllegalOperationException {
        Optional<HistorialMedico> historialMedico = historialMedicoService.BuscarPorId(id); // Método para buscar un historial médico por su ID
        HistorialMedicoDTO historialMedicoDTO = modelMapper.map(historialMedico, HistorialMedicoDTO.class); // Mapeo a DTO

        ApiResponse<HistorialMedicoDTO> response = new ApiResponse<>(true, "Historial médico obtenido con éxito", historialMedicoDTO);
        return ResponseEntity.ok(response);
    }
    @PostMapping
    public ResponseEntity<?> guardarHistorialMedico(@RequestBody HistorialMedicoDTO historialMedicoDTO) throws IllegalOperationException {
        HistorialMedico historialMedico = modelMapper.map(historialMedicoDTO, HistorialMedico.class);
        historialMedicoService.Grabar(historialMedico);

        HistorialMedicoDTO savedHistorialMedicoDTO = modelMapper.map(historialMedico, HistorialMedicoDTO.class);
        ApiResponse<HistorialMedicoDTO> response = new ApiResponse<>(true, "Historial médico guardado con éxito", savedHistorialMedicoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<HistorialMedicoDTO>> actualizarHistorialMedico(@PathVariable Long id, @RequestBody HistorialMedicoDTO historialMedicoDTO) throws IllegalOperationException {
        HistorialMedico historialMedico = modelMapper.map(historialMedicoDTO, HistorialMedico.class);
        historialMedicoService.Actualizar(id, historialMedico);

        HistorialMedicoDTO updatedHistorialMedicoDTO = modelMapper.map(historialMedico, HistorialMedicoDTO.class);
        ApiResponse<HistorialMedicoDTO> response = new ApiResponse<>(true, "Historial médico actualizado con éxito", updatedHistorialMedicoDTO);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarHistorialMedico(@PathVariable Long id) throws IllegalOperationException {
        historialMedicoService.Eliminar(id);
        ApiResponse<String> response = new ApiResponse<>(true, "Historial médico eliminado con éxito", null);
        return ResponseEntity.ok(response);
    }
}

