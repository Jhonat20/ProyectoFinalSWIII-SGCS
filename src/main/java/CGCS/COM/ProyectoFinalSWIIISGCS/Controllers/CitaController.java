package CGCS.COM.ProyectoFinalSWIIISGCS.Controllers;

import CGCS.COM.ProyectoFinalSWIIISGCS.Domain.Cita;
import CGCS.COM.ProyectoFinalSWIIISGCS.Domain.Doctor;
import CGCS.COM.ProyectoFinalSWIIISGCS.ImpHateoas.Cita.CitaModel;
import CGCS.COM.ProyectoFinalSWIIISGCS.ImpHateoas.Cita.CitaModelAssembler;
import CGCS.COM.ProyectoFinalSWIIISGCS.ImpHateoas.Doctor.DoctorModel;
import CGCS.COM.ProyectoFinalSWIIISGCS.Services.CitaService;
import CGCS.COM.ProyectoFinalSWIIISGCS.Services.DoctorService;
import CGCS.COM.ProyectoFinalSWIIISGCS.Validation.ValidationUtil;
import CGCS.COM.ProyectoFinalSWIIISGCS.exception.IllegalOperationException;
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
 * Controlador para la gestión de citas en la aplicación.
 * Proporciona endpoints para operaciones CRUD sobre citas.
 */
@RestController
@RequestMapping("api/v1/Citas")
public class CitaController {

    @Autowired
    private CitaService citaService; // Servicio para operaciones de citas.
    @Autowired
    private CitaModelAssembler citaModelAssembler;

    @Autowired
    private DoctorService doctorService;

    /**
     * Lista todas las citas disponibles.
     *
     * @return ResponseEntity con la lista de citas.
     * @throws IllegalOperationException Si ocurre alguna excepción durante la operación.
     */
    @GetMapping
    public ResponseEntity<?> listarCitas() throws IllegalOperationException {
        List<Cita> citas = citaService.listarCitas();
        List<EntityModel<CitaModel>> citaModels = new ArrayList<>();

        if (citas.isEmpty()) {
            return ResponseEntity.ok(GlobalResponse.error("No hay citas registradas"));
        } else {
            for (Cita cita : citas) {
                CitaModel citaModel = citaModelAssembler.toModel(cita);
                Link selfLink = linkTo(methodOn(CitaController.class).obtenerCita(cita.getIdCita())).withSelfRel();
                citaModel.add(selfLink);
                if (cita.getPaciente() != null) {
                    Link pacienteLink = linkTo(methodOn(PacienteController.class).obtenerPaciente(cita.getPaciente().getIdPaciente())).withRel("Ver Paciente");
                    citaModel.add(pacienteLink);
                }
                if (cita.getDoctor() != null) {
                    Link doctorLink = linkTo(methodOn(DoctorController.class).obtenerDoctor(cita.getDoctor().getIdDoctor())).withRel("Ver Doctor");
                    citaModel.add(doctorLink);
                }
                EntityModel<CitaModel> entityModel = EntityModel.of(citaModel);
                citaModels.add(entityModel);
            }
            CollectionModel<EntityModel<CitaModel>> collectionModel = CollectionModel.of(citaModels);
            Link link = linkTo(methodOn(CitaController.class).listarCitas()).withSelfRel();
            collectionModel.add(link);
            return ResponseEntity.ok(collectionModel);
        }
    }


    @GetMapping("/{doctorId}/citas")
    public ResponseEntity<?> listarCitasPorDoctor(@PathVariable Long doctorId) throws IllegalOperationException {
        Optional<Doctor> optionalDoctor = doctorService.obtenerDoctorPorId(doctorId);
        if (optionalDoctor.isPresent()) {
            List<Cita> citas = citaService.listarCitasPorDoctor(doctorId);
            List<CitaModel> citaModels = new ArrayList<>();
            for (Cita cita : citas) {
                CitaModel citaModel = citaModelAssembler.toModel(cita);
                Link citaLink = linkTo(methodOn(CitaController.class).obtenerCita(cita.getIdCita())).withRel("Ver Cita");
                citaModel.add(citaLink);
                if (optionalDoctor.isPresent()) {
                    Link doctorLink = linkTo(methodOn(DoctorController.class).obtenerDoctor(doctorId)).withRel("Ver Doctor");
                    citaModel.add(doctorLink);
                }
                if (cita.getPaciente() != null) {
                    Link pacienteLink = linkTo(methodOn(PacienteController.class).obtenerPaciente(cita.getPaciente().getIdPaciente())).withRel("Ver Paciente");
                    citaModel.add(pacienteLink);
                }
                citaModels.add(citaModel);
            }
            return ResponseEntity.ok(citaModels);
        } else {
            return ResponseEntity.ok(GlobalResponse.error("No se encontró el doctor con el ID proporcionado"));
        }
    }




    /**
     * Registra una nueva cita, validando previamente los datos de entrada.
     *
     * @param cita Datos de la cita a registrar.
     * @param bindingResult Resultado de la validación.
     * @return ResponseEntity con la cita registrada o errores de validación.
     * @throws IllegalOperationException Si ocurre alguna excepción durante el registro.
     */
    @PostMapping
    public ResponseEntity<?> registrarCita(@Valid @RequestBody Cita cita, BindingResult bindingResult) throws IllegalOperationException {
        if (bindingResult.hasErrors()) {
            Map<String, String> errores = ValidationUtil.getValidationErrors(bindingResult);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errores);
        } else {
            Cita nuevaCita = citaService.registrarCita(cita);
            CitaModel citaModel = citaModelAssembler.toModel(nuevaCita);
            Link AllCitasLink = linkTo(methodOn(CitaController.class).listarCitas()).withRel("Ver lista Citas");
            citaModel.add(AllCitasLink);
            return ResponseEntity.ok((citaModel));
        }
    }

    /**
     * Obtiene una cita específica por su ID.
     *
     * @param id ID de la cita a obtener.
     * @return ResponseEntity con la cita encontrada o un mensaje de error si no existe.
     * @throws IllegalOperationException Si ocurre alguna excepción durante la búsqueda.
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerCita(@PathVariable Long id) throws IllegalOperationException {
        Optional<Cita> optionalCita = citaService.obtenerCitaPorId(id);
        if (optionalCita.isPresent()) {
            Cita cita = optionalCita.get();
            CitaModel citaModel = citaModelAssembler.toModel(cita);
            if (cita.getPaciente() != null) {
                Link pacienteLink = linkTo(methodOn(PacienteController.class).obtenerPaciente(cita.getPaciente().getIdPaciente())).withRel("Ver Paciente");
                citaModel.add(pacienteLink);
            }
            if (cita.getDoctor() != null) {
                Link doctorLink = linkTo(methodOn(DoctorService.class).obtenerDoctorPorId(cita.getDoctor().getIdDoctor())).withRel("Ver Doctor");
                citaModel.add(doctorLink);
            }
            Link AllCitasLink = linkTo(methodOn(CitaController.class).listarCitas()).withRel("Ver lista Citas");
            citaModel.add(AllCitasLink);
            return ResponseEntity.ok(citaModel);

        } else {
            return ResponseEntity.ok(GlobalResponse.error("No se encontró la cita con el ID proporcionado"));
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
    public ResponseEntity<?> eliminarCita(@PathVariable Long id) throws IllegalOperationException {
        citaService.eliminarCita(id);
        Link AllCitasLink = linkTo(methodOn(CitaController.class).listarCitas()).withRel("Ver lista Citas");
        return ResponseEntity.ok(AllCitasLink);
    }

    /**
     * Actualiza los datos de una cita existente.
     *
     * @param id ID de la cita a actualizar.
     * @param cita Datos actualizados de la cita.
     * @param bindingResult Resultado de la validación.
     * @return ResponseEntity con la cita actualizada o errores de validación.
     * @throws IllegalOperationException Si ocurre alguna excepción durante la actualización.
     */
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarCita(@PathVariable Long id, @Valid @RequestBody Cita cita, BindingResult bindingResult) throws IllegalOperationException {
        if (bindingResult.hasErrors()) {
            Map<String, String> errores = ValidationUtil.getValidationErrors(bindingResult);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errores);
        } else {
            Cita citaActualizada = citaService.actualizarCita(id, cita);
            DoctorModel doctorModel = new DoctorModel();
            return ResponseEntity.ok(doctorModel);
        }
    }
}
