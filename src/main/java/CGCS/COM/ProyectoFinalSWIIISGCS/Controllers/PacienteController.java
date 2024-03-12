package CGCS.COM.ProyectoFinalSWIIISGCS.Controllers;

import CGCS.COM.ProyectoFinalSWIIISGCS.Domain.Paciente;
import CGCS.COM.ProyectoFinalSWIIISGCS.ImpHateoas.Paciente.PacienteModel;
import CGCS.COM.ProyectoFinalSWIIISGCS.ImpHateoas.Paciente.PacienteModelAssembler;
import CGCS.COM.ProyectoFinalSWIIISGCS.Services.PacienteService;
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
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("api/v1/Paciente")
public class PacienteController {

    @Autowired
    private PacienteService pacienteService;

    @Autowired
    private PacienteModelAssembler pacienteModelAssembler;

    @GetMapping
    public ResponseEntity<?> listarPacientes() throws IllegalOperationException {
        List<Paciente> pacientes = pacienteService.listarPacientes();
        List<EntityModel<PacienteModel>> pacienteModels = new ArrayList<>();
        for (Paciente paciente : pacientes) {
            PacienteModel pacienteModel = pacienteModelAssembler.toModel(paciente);
            Link selfLink = linkTo(methodOn(PacienteController.class).obtenerPaciente(paciente.getIdPaciente())).withSelfRel();
            pacienteModel.add(selfLink);
            EntityModel<PacienteModel> entityModel = EntityModel.of(pacienteModel);
            pacienteModels.add(entityModel);
        }
        Link allPacientesLink = linkTo(methodOn(PacienteController.class).listarPacientes()).withSelfRel();
        CollectionModel<EntityModel<PacienteModel>> collectionModel = CollectionModel.of(pacienteModels, allPacientesLink);
        return ResponseEntity.ok(collectionModel);

    }

    @PostMapping
    public ResponseEntity<?> registrarPaciente(@Valid @RequestBody Paciente paciente, BindingResult bindingResult) throws IllegalOperationException {
        if (bindingResult.hasErrors()) {
            Map<String, String> errores = ValidationUtil.getValidationErrors(bindingResult);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errores);
        } else {
            Paciente nuevoPaciente = pacienteService.registrarPaciente(paciente);
            PacienteModel pacienteModel = pacienteModelAssembler.toModel(nuevoPaciente);
            Link selfLink = linkTo(methodOn(PacienteController.class).obtenerPaciente(nuevoPaciente.getIdPaciente())).withSelfRel();
            pacienteModel.add(selfLink);
            Link allPacientesLink = linkTo(methodOn(PacienteController.class).listarPacientes()).withRel("Ver lista Pacientes");
            pacienteModel.add(allPacientesLink);
            return ResponseEntity.ok(pacienteModel);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerPaciente(@PathVariable Long id) throws IllegalOperationException {
        Optional<Paciente> optionalPaciente = pacienteService.obtenerPacientePorId(id);
        if (optionalPaciente.isPresent()) {
            PacienteModel pacienteModel = pacienteModelAssembler.toModel(optionalPaciente.get());
            Link selfLink = linkTo(methodOn(PacienteController.class).obtenerPaciente(id)).withSelfRel();
            Link allPacientesLink = linkTo(methodOn(PacienteController.class).listarPacientes()).withRel("Ver lista Pacientes");
            pacienteModel.add(allPacientesLink);
            pacienteModel.add(selfLink);
            return ResponseEntity.ok(pacienteModel);
        } else {
            return ResponseEntity.ok(GlobalResponse.error("No se encontró el paciente con el ID proporcionado"));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarPaciente(@PathVariable Long id) throws IllegalOperationException {
        Optional<Paciente> optionalPaciente = pacienteService.obtenerPacientePorId(id);
        if (optionalPaciente.isPresent()) {
            pacienteService.eliminarPaciente(id);
            return ResponseEntity.ok(GlobalResponse.ok("Paciente eliminado correctamente"));
        } else {
            PacienteModel pacienteModel = pacienteModelAssembler.toModel(optionalPaciente.get());
            Link allPacientesLink = linkTo(methodOn(PacienteController.class).listarPacientes()).withRel("Ver lista Pacientes");
            pacienteModel.add(allPacientesLink);
            return ResponseEntity.ok(GlobalResponse.error("No se encontró el paciente con el ID proporcionado"));

        }
         }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarPaciente(@PathVariable Long id, @Valid @RequestBody Paciente paciente, BindingResult bindingResult) throws IllegalOperationException {
        if (bindingResult.hasErrors()) {
            Map<String, String> errores = ValidationUtil.getValidationErrors(bindingResult);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errores);
        } else {
            Paciente pacienteActualizado = pacienteService.actualizarPaciente(id, paciente);
            PacienteModel pacienteModel = pacienteModelAssembler.toModel(pacienteActualizado);
            Link selfLink = linkTo(methodOn(PacienteController.class).obtenerPaciente(id)).withSelfRel();
            pacienteModel.add(selfLink);
            return ResponseEntity.ok(pacienteModel);
        }
    }





    @PutMapping("/{idPaciente}/agregarCita/{idCita}")
    public ResponseEntity<?> agregarCitaAPaciente(@PathVariable Long idPaciente, @PathVariable Long idCita) throws IllegalOperationException {
        pacienteService.agregarCitaAPaciente(idPaciente, idCita);
        return ResponseEntity.ok(GlobalResponse.ok("Cita agregada al paciente correctamente"));
    }

    //agregarHistorialMedicoAPaciente
    @PutMapping("/{idPaciente}/agregarHistorialMedico/{idHistorialMedico}")
    public ResponseEntity<?> agregarHistorialMedicoAPaciente(@PathVariable Long idPaciente, @PathVariable Long idHistorialMedico) throws IllegalOperationException {
        pacienteService.agregarHistorialMedicoAPaciente(idPaciente, idHistorialMedico);
        return ResponseEntity.ok(GlobalResponse.ok("Historial médico agregado al paciente correctamente"));
    }


}
