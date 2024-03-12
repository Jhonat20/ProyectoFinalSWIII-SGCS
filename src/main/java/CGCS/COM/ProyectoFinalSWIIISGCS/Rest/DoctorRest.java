/**
 * @file: DoctorRest.java
 * @created: [Fecha de creación]
 */

package CGCS.COM.ProyectoFinalSWIIISGCS.Rest;

import CGCS.COM.ProyectoFinalSWIIISGCS.DTO.DoctorDTO;
import CGCS.COM.ProyectoFinalSWIIISGCS.Domain.Doctor;
import CGCS.COM.ProyectoFinalSWIIISGCS.exception.IllegalOperationException;
import CGCS.COM.ProyectoFinalSWIIISGCS.responses.ApiResponse;
import CGCS.COM.ProyectoFinalSWIIISGCS.responses.GlobalResponse;
import org.modelmapper.ModelMapper;
import CGCS.COM.ProyectoFinalSWIIISGCS.Services.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Controlador REST para las operaciones relacionadas con los doctores.
 */
@RestController
@RequestMapping("/api/v1/doctor")
public class DoctorRest {

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private ModelMapper modelMapper;

    /**
     * Endpoint para listar todos los doctores.
     *
     * @return ResponseEntity con la respuesta.
     * @throws IllegalOperationException Si se produce una operación ilegal.
     */
    @GetMapping
    public ResponseEntity<?> listarDoctores() throws IllegalOperationException {
        List<Doctor> doctores = doctorService.listarDoctores();
        List<DoctorDTO> doctorDTOs = doctores.stream()
                .map(doctor -> modelMapper.map(doctor, DoctorDTO.class))
                .collect(Collectors.toList());

        return ResponseEntity.ok(GlobalResponse.ok(doctorDTOs));
    }

    /**
     * Endpoint para obtener un doctor por su ID.
     *
     * @param id ID del doctor.
     * @return ResponseEntity con la respuesta.
     * @throws IllegalOperationException Si se produce una operación ilegal.
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerDoctorPorId(@PathVariable Long id) throws IllegalOperationException {
        Optional<Doctor> doctor = doctorService.obtenerDoctorPorId(id);
        DoctorDTO doctorDTO = modelMapper.map(doctor, DoctorDTO.class);

        ApiResponse<DoctorDTO> response = new ApiResponse<>(true, "Doctor obtenido con éxito", doctorDTO);
        return ResponseEntity.ok(response);
    }

    /**
     * Endpoint para guardar un nuevo doctor.
     *
     * @param doctorDTO DTO del doctor a guardar.
     * @return ResponseEntity con la respuesta.
     * @throws IllegalOperationException Si se produce una operación ilegal.
     */
    @PostMapping
    public ResponseEntity<?> guardarDoctor(@RequestBody DoctorDTO doctorDTO) throws IllegalOperationException {
        Doctor doctor = modelMapper.map(doctorDTO, Doctor.class);
        doctorService.registrarDoctor(doctor);

        DoctorDTO savedDoctorDTO = modelMapper.map(doctor, DoctorDTO.class);
        ApiResponse<DoctorDTO> response = new ApiResponse<>(true, "Doctor guardado con éxito", savedDoctorDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    /**
     * Endpoint para actualizar un doctor existente.
     *
     * @param id        ID del doctor a actualizar.
     * @param doctorDTO DTO con los nuevos datos del doctor.
     * @return ResponseEntity con la respuesta.
     * @throws IllegalOperationException Si se produce una operación ilegal.
     */
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<DoctorDTO>> actualizarDoctor(@PathVariable Long id, @RequestBody DoctorDTO doctorDTO) throws IllegalOperationException {
        Doctor doctor = modelMapper.map(doctorDTO, Doctor.class);
        doctorService.actualizarDoctor(id, doctor);

        DoctorDTO updatedDoctorDTO = modelMapper.map(doctor, DoctorDTO.class);
        ApiResponse<DoctorDTO> response = new ApiResponse<>(true, "Doctor actualizado con éxito", updatedDoctorDTO);
        return ResponseEntity.ok(response);
    }

    /**
     * Endpoint para eliminar un doctor por su ID.
     *
     * @param id ID del doctor a eliminar.
     * @return ResponseEntity con la respuesta.
     * @throws IllegalOperationException Si se produce una operación ilegal.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarDoctor(@PathVariable Long id) throws IllegalOperationException {
        doctorService.eliminarDoctor(id);
        ApiResponse<String> response = new ApiResponse<>(true, "Doctor eliminado con éxito", null);
        return ResponseEntity.ok(response);
    }
}