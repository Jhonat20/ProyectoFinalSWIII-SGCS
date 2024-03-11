package CGCS.COM.ProyectoFinalSWIIISGCS.Controllers;

import CGCS.COM.ProyectoFinalSWIIISGCS.Domain.Especialidad;
import CGCS.COM.ProyectoFinalSWIIISGCS.ImpHateoas.Especialidad.EspecialidadModel;
import CGCS.COM.ProyectoFinalSWIIISGCS.ImpHateoas.Especialidad.EspecialidadModelAssembler;
import CGCS.COM.ProyectoFinalSWIIISGCS.Services.EspecialidadService;
import CGCS.COM.ProyectoFinalSWIIISGCS.Validation.ValidationUtil;
import CGCS.COM.ProyectoFinalSWIIISGCS.exception.IllegalOperationException;
import CGCS.COM.ProyectoFinalSWIIISGCS.responses.ApiResponseHateos;
import CGCS.COM.ProyectoFinalSWIIISGCS.responses.GlobalResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * Clase controladora para gestionar entidades Especialidad.
 */
@RestController
@RequestMapping("api/v1/Especialidad")
public class EspecialidadController {

    @Autowired
    private EspecialidadService especialidadService;

    @Autowired
    private EspecialidadModelAssembler especialidadModelAssembler;

    /**
     * Endpoint para listar todas las Especialidades.
     *
     * @return ResponseEntity con una lista de Especialidades o un mensaje de error.
     * @throws IllegalOperationException si ocurre una operación ilegal.
     */

    @GetMapping(headers = "Api-Version=v0.1.0")
    public ResponseEntity<?> listarEspecialidadesv1(@RequestHeader("Api-Version") String apiVersion) throws IllegalOperationException {
        List<Especialidad> especialidades = especialidadService.listarEspecialidades();
       return ResponseEntity.ok(GlobalResponse.ok(especialidades));
    }


    @GetMapping(headers = "Api-Version=v0.2.0")
    public ResponseEntity<?> listarEspecialidades(@RequestHeader("Api-Version") String apiVersion) throws IllegalOperationException {
        List<Especialidad> especialidades = especialidadService.listarEspecialidades();
        List<EntityModel<EspecialidadModel>> especialidadModels = new ArrayList<>();
        for (Especialidad especialidad : especialidades) {
            EspecialidadModel especialidadModel = especialidadModelAssembler.toModel(especialidad);
            Link selfLink = linkTo(methodOn(EspecialidadController.class).obtenerEspecialidad(apiVersion,especialidad.getIdEspecialidad())).withSelfRel().expand(apiVersion);
            EntityModel<EspecialidadModel> especialidadEntityModel = EntityModel.of(especialidadModel, selfLink);
            especialidadModels.add(especialidadEntityModel);
        }
        CollectionModel<EntityModel<EspecialidadModel>> collectionModel = CollectionModel.of(especialidadModels);
        Link link = linkTo(methodOn(EspecialidadController.class).listarEspecialidades(apiVersion)).withSelfRel().expand(apiVersion);
        collectionModel.add(link);
        ApiResponseHateos response = ApiResponseHateos.ok(collectionModel, "Especialidades listadas con éxito");
        return ResponseEntity.ok(response);
    }

    /**
     * Endpoint para registrar una nueva Especialidad.
     *
     * @param especialidad    Objeto Especialidad a registrar.
     * @param bindingResult   BindingResult para validar el cuerpo de la solicitud.
     * @return ResponseEntity con la Especialidad registrada o errores de validación.
     * @throws IllegalOperationException si ocurre una operación ilegal.
     */
    @PostMapping(headers = "Api-Version=v0.2.0")
    public ResponseEntity<?> registrarEspecialidad(@Valid @RequestHeader("Api-Version") String apiVersion, @RequestBody Especialidad especialidad, BindingResult bindingResult) throws IllegalOperationException {
        if (bindingResult.hasErrors()) {
            Map<String, String> errores = ValidationUtil.getValidationErrors(bindingResult);
            ApiResponseHateos response = ApiResponseHateos.error("Error en la validación de los datos");
            response.setData(errores);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        } else {
            Especialidad nuevaEspecialidad = especialidadService.registrarEspecialidad(especialidad);
            EspecialidadModel especialidadModel = especialidadModelAssembler.toModel(nuevaEspecialidad);
            Link AllEspecialidadesLink = linkTo(methodOn(EspecialidadController.class).listarEspecialidades(apiVersion)).withRel("Ver todas Especialidades").expand(apiVersion);
            especialidadModel.add(AllEspecialidadesLink);
            ApiResponseHateos response = ApiResponseHateos.ok(especialidadModel, "Especialidad registrada con éxito");
            return ResponseEntity.ok(response);
        }
    }




    /**
     * Endpoint para obtener una Especialidad específica por ID.
     *
     * @param id ID de la Especialidad a recuperar.
     * @return ResponseEntity con la Especialidad recuperada o un mensaje de error.
     * @throws IllegalOperationException si ocurre una operación ilegal.
     */
    @GetMapping(value = "/{id}", headers = "Api-Version=v0.2.0")
    public ResponseEntity<?> obtenerEspecialidad(@RequestHeader("Api-Version") String apiVersion,@PathVariable Long id) throws IllegalOperationException {
        Optional<Especialidad> optionalEspecialidad = especialidadService.buscarEspecialidadPorId(id);
        if (optionalEspecialidad.isPresent()) {
            Especialidad especialidad = optionalEspecialidad.get();
            EspecialidadModel especialidadModel = especialidadModelAssembler.toModel(especialidad);
            Link allEspecialidadesLink = linkTo(methodOn(EspecialidadController.class).listarEspecialidades(apiVersion)).withRel("Ver todas Especialidades").expand(apiVersion);
            especialidadModel.add(allEspecialidadesLink);
            ApiResponseHateos response = ApiResponseHateos.ok(especialidadModel, "Especialidad encontrada con éxito");
            return ResponseEntity.ok(response);
        } else {
            EspecialidadModel especialidadModel = new EspecialidadModel();
            Link allEspecialidadesLink = linkTo(methodOn(EspecialidadController.class).listarEspecialidades(apiVersion)).withRel("Ver todas Especialidades").expand(apiVersion);
            especialidadModel.add(allEspecialidadesLink);
            ApiResponseHateos response = ApiResponseHateos.error("Especialidad no encontrada");
            response.setData(especialidadModel);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }


    /**
     * Endpoint para eliminar una Especialidad específica por ID.
     *
     * @param id ID de la Especialidad a eliminar.
     * @return ResponseEntity con un mensaje de éxito o un mensaje de error.
     * @throws IllegalOperationException si ocurre una operación ilegal.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarEspecialidad(@PathVariable Long id) throws IllegalOperationException {
        especialidadService.eliminarEspecialidad(id);
        return ResponseEntity.ok(GlobalResponse.ok("Especialidad eliminada correctamente"));
    }

    /**
     * Endpoint para actualizar una Especialidad específica por ID.
     *
     * @param id              ID de la Especialidad a actualizar.
     * @param especialidad    Objeto Especialidad actualizado.
     * @param bindingResult   BindingResult para validar el cuerpo de la solicitud.
     * @return ResponseEntity con la Especialidad actualizada o errores de validación.
     * @throws IllegalOperationException si ocurre una operación ilegal.
     */
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarEspecialidad(@PathVariable Long id, @Valid @RequestBody Especialidad especialidad, BindingResult bindingResult) throws IllegalOperationException {
        if (bindingResult.hasErrors()) {
            Map<String, String> errores = ValidationUtil.getValidationErrors(bindingResult);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errores);
        } else {
            Especialidad especialidadActualizada = especialidadService.modificarEspecialidad(id, especialidad);
            return ResponseEntity.ok(GlobalResponse.ok(especialidadActualizada));
        }
    }
}
