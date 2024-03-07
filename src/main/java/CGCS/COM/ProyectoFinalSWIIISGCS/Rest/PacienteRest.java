package CGCS.COM.ProyectoFinalSWIIISGCS.Rest;

import CGCS.COM.ProyectoFinalSWIIISGCS.DTO.PacienteDTO;
import CGCS.COM.ProyectoFinalSWIIISGCS.Domain.Paciente;
import CGCS.COM.ProyectoFinalSWIIISGCS.Services.PacienteService;
import CGCS.COM.ProyectoFinalSWIIISGCS.responses.GlobalResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @file: PacienteRest
 * @author: (c)jhons
 * @created: 07/03/2024 2:15
 */

@RestController
@RequestMapping("/api/v1/paciente")
public class PacienteRest {

@Autowired
    private PacienteService pacienteService;

@Autowired
    private ModelMapper modelMapper;

@GetMapping
    public ResponseEntity<?> listarPacientes() {
        try {
            List<Paciente> pacientes = pacienteService.listarPacientes();
            List<PacienteDTO> pacienteDTOs = pacientes.stream()
                    .map(paciente -> modelMapper.map(paciente, PacienteDTO.class))
                    .collect(Collectors.toList());

            // Utilizando GlobalResponse.ok() para crear una respuesta exitosa
            return ResponseEntity.ok(GlobalResponse.ok(pacienteDTOs));
        } catch (Exception e) {
            // Utilizando GlobalResponse.error() para crear una respuesta de error
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(GlobalResponse.error("Error interno del servidor"));
        }
    }

}
