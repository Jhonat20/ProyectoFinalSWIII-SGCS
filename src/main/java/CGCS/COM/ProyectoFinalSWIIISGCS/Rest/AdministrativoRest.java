package CGCS.COM.ProyectoFinalSWIIISGCS.Rest;

import CGCS.COM.ProyectoFinalSWIIISGCS.DTO.AdministrativoDTO;
import CGCS.COM.ProyectoFinalSWIIISGCS.Domain.Administrativo;
import CGCS.COM.ProyectoFinalSWIIISGCS.Services.AdministrativoService;
import CGCS.COM.ProyectoFinalSWIIISGCS.Services.DoctorService;
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

}
