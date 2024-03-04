package CGCS.COM.ProyectoFinalSWIIISGCS.Controllers;

import CGCS.COM.ProyectoFinalSWIIISGCS.Domain.Administrativo;
import CGCS.COM.ProyectoFinalSWIIISGCS.Services.AdministrativoService;
import CGCS.COM.ProyectoFinalSWIIISGCS.Validation.ValidationUtil;
import CGCS.COM.ProyectoFinalSWIIISGCS.exception.IllegalOperationException;
import CGCS.COM.ProyectoFinalSWIIISGCS.responses.GlobalResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Controlador para la gestión de Administrativos en la aplicación.
 * Proporciona endpoints para operaciones CRUD sobre Administrativo.
 */
@RestController
@RequestMapping("api/v1/Administrativo")
public class AdministrativoController {
    @Autowired
    private AdministrativoService AdministrativoService; // Servicio para operaciones de Administrativo.

    /**
     * Lista todas los Administrativos disponibles.
     *
     * @param apiVersion Versión de la API solicitada por el cliente.
     * @return ResponseEntity con la lista de Administrativos.
     * @throws IllegalOperationException Si ocurre alguna excepción durante la operación.
     */
    @GetMapping
    public ResponseEntity<?> listarAdministrativo(@RequestHeader(value = "API-Version", defaultValue = "v0.1.0") String apiVersion) throws IllegalOperationException {
        List<Administrativo> Administrativo = AdministrativoService.listarAdministrativo();
        return ResponseEntity.ok(GlobalResponse.ok(Administrativo));
    }

    /**
     * Registra un nuevo Administrativo, validando previamente los datos de entrada.
     *
     * @param Administrativo Datos del Administrativo a registrar.
     * @param bindingResult Resultado de la validación.
     * @return ResponseEntity con el Administrativo registrado o errores de validación.
     * @throws IllegalOperationException Si ocurre alguna excepción durante el registro.
     */
    @PostMapping
    public ResponseEntity<?> registrarAdministrativo(@Valid @RequestBody Administrativo Administrativo, BindingResult bindingResult) throws IllegalOperationException {
        if (bindingResult.hasErrors()) {
            Map<String, String> errores = ValidationUtil.getValidationErrors(bindingResult);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errores);
        } else {
            Administrativo nuevoAdministrativo = AdministrativoService.registrarAdministrativo(Administrativo);
            return ResponseEntity.ok(GlobalResponse.ok(nuevoAdministrativo));
        }
    }

    /**
     * Obtiene un Administrativo específicado por su ID.
     *
     * @param id ID del Administrativo a obtener.
     * @return ResponseEntity con el Administrativo encontrada o un mensaje de error si no existe.
     * @throws IllegalOperationException Si ocurre alguna excepción durante la búsqueda.
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerAdministrativo(@PathVariable Long id) throws IllegalOperationException {
        Optional<Administrativo> optionalAdministrativo = AdministrativoService.obtenerAdministrativoPorId(id);
        if (optionalAdministrativo.isPresent()) {
            return ResponseEntity.ok(GlobalResponse.ok(optionalAdministrativo.get()));
        } else {
            return ResponseEntity.ok(GlobalResponse.error("No se encontró el Administrativo con el ID proporcionado"));
        }
    }

    /**
     * Elimina una cita por su ID.
     *
     * @param id ID de la cita a eliminar.
     * @return ResponseEntity con mensaje de confirmación.
     * @throws IllegalOperationException Si la cita no existe o no se puede eliminar.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarAdministrativo(@PathVariable Long id) throws IllegalOperationException {
        AdministrativoService.eliminarAdministrativo(id);
        return ResponseEntity.ok(GlobalResponse.ok("Administrativo eliminado correctamente"));
    }

    /**
     * Actualiza los datos de un Administrativo existente.
     *
     * @param id ID de la cita a actualizar.
     * @param Administrativo Datos actualizados de Administrativo.
     * @param bindingResult Resultado de la validación.
     * @return ResponseEntity con el Administrativo actualizada o errores de validación.
     * @throws IllegalOperationException Si ocurre alguna excepción durante la actualización.
     */
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarAdministrativo(@PathVariable Long id, @Valid @RequestBody Administrativo Administrativo, BindingResult bindingResult) throws IllegalOperationException {
        if (bindingResult.hasErrors()) {
            Map<String, String> errores = ValidationUtil.getValidationErrors(bindingResult);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errores);
        } else {
            Administrativo AdministrativoActualizado = AdministrativoService.actualizarAdministrativo(id, Administrativo);
            return ResponseEntity.ok(GlobalResponse.ok(AdministrativoActualizado));
        }
    }
}
