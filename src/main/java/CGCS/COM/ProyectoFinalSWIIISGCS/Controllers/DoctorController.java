package CGCS.COM.ProyectoFinalSWIIISGCS.Controllers;

import CGCS.COM.ProyectoFinalSWIIISGCS.Domain.Cita;
import CGCS.COM.ProyectoFinalSWIIISGCS.Domain.Doctor;
import CGCS.COM.ProyectoFinalSWIIISGCS.Domain.Horario;
import CGCS.COM.ProyectoFinalSWIIISGCS.ImpHateoas.Doctor.DoctorModel;
import CGCS.COM.ProyectoFinalSWIIISGCS.ImpHateoas.Doctor.DoctorModelAssembler;
import CGCS.COM.ProyectoFinalSWIIISGCS.Services.CitaService;
import CGCS.COM.ProyectoFinalSWIIISGCS.Services.DoctorService;

import CGCS.COM.ProyectoFinalSWIIISGCS.Validation.ValidationUtil;
import CGCS.COM.ProyectoFinalSWIIISGCS.exception.IllegalOperationException;
import CGCS.COM.ProyectoFinalSWIIISGCS.responses.GlobalResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.*;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * Clase controladora para gestionar entidades de Doctor.
 * Maneja las solicitudes HTTP relacionadas con las operaciones de Doctor.
 *
 * @author CGCS.COM.ProyectoFinalSWIIISGCS.Controllers
 * @version 1.0
 * @since 2024-03-03
 */
@RestController
@RequestMapping("api/v1/Doctor")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private CitaService citaService;
    @Autowired
    private DoctorModelAssembler doctorModelAssembler;


    /**
     * Obtiene la lista de doctores.
     *
     * @return ResponseEntity con una lista de doctores y encabezados apropiados.
     * @throws IllegalOperationException Si ocurre una operación ilegal durante la solicitud.
     */
    @GetMapping
    public ResponseEntity<?> listarDoctores() throws IllegalOperationException {
        List<Doctor> doctores = doctorService.listarDoctores();
        List<EntityModel<DoctorModel>> doctorModels = new ArrayList<>();
        for (Doctor doctor : doctores) {
            DoctorModel doctorModel = doctorModelAssembler.toModel(doctor);
            Link selfLink = linkTo(methodOn(DoctorController.class).obtenerDoctor(doctor.getIdDoctor())).withSelfRel();
            EntityModel<DoctorModel> entityModel = EntityModel.of(doctorModel, selfLink);
            doctorModels.add(entityModel);
        }

        CollectionModel<EntityModel<DoctorModel>> collectionModel = CollectionModel.of(doctorModels);
        Link link = linkTo(methodOn(DoctorController.class).listarDoctores()).withSelfRel();
        collectionModel.add(link);

        return ResponseEntity.ok(collectionModel);
    }


    /**
     * Registra un nuevo doctor.
     *
     * @param doctor         Objeto Doctor a registrar, validado mediante Jakarata Bean Validation.
     * @param bindingResult  Objeto BindingResult para verificar errores de validación.
     * @return ResponseEntity con el nuevo doctor registrado o errores de validación.
     * @throws IllegalOperationException Si ocurre una operación ilegal durante la solicitud.
     */
    @PostMapping
    public ResponseEntity<?> registrarDoctor(@Valid @RequestBody Doctor doctor, BindingResult bindingResult) throws IllegalOperationException {
        if (bindingResult.hasErrors()) {
            Map<String, String> errores = ValidationUtil.getValidationErrors(bindingResult);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errores);
        } else {
            Doctor nuevoDoctor = doctorService.registrarDoctor(doctor);
            DoctorModel doctorModel = doctorModelAssembler.toModel(nuevoDoctor);
            // Añadir un enlace a la lista completa de doctores
            Link allDoctorsLink = linkTo(methodOn(DoctorController.class).listarDoctores()).withRel("Ver lista Doctores");
            doctorModel.add(allDoctorsLink);
            return ResponseEntity.ok(doctorModel);
        }
    }

    /**
     * Obtiene un doctor específico por ID.
     *
     * @param id ID del doctor.
     * @return ResponseEntity con el doctor solicitado o un mensaje de error si no se encuentra.
     * @throws IllegalOperationException Si ocurre una operación ilegal durante la solicitud.
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerDoctor(@PathVariable Long id) throws IllegalOperationException {
        Optional<Doctor> optionalDoctor = doctorService.obtenerDoctorPorId(id);
        if (optionalDoctor.isPresent()) {
            Doctor doctor = optionalDoctor.get();
            DoctorModel doctorModel = doctorModelAssembler.toModel(doctor);
            // Añadir un enlace a la lista completa de doctores
            Link allDoctorsLink = linkTo(methodOn(DoctorController.class).listarDoctores()).withRel("Ver lista Doctores");
            doctorModel.add(allDoctorsLink);
            return ResponseEntity.ok(doctorModel);
        } else {
            return ResponseEntity.ok(GlobalResponse.error("No se encontró el doctor con el ID proporcionado"));
        }
    }


    /**
     * Elimina un doctor por ID.
     *
     * @param id ID del doctor.
     * @return ResponseEntity indicando la eliminación exitosa o un mensaje de error.
     * @throws IllegalOperationException Si ocurre una operación ilegal durante la solicitud.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarDoctor(@PathVariable Long id) throws IllegalOperationException {
        Optional <Doctor> doctorOptional = doctorService.obtenerDoctorPorId(id);
        if (!doctorOptional.isPresent()){
            return ResponseEntity.ok(GlobalResponse.error("No se encontró el doctor con el ID A eliminar"));
        }else {
            doctorService.eliminarDoctor(id);
            Link allDoctorsLink = linkTo(methodOn(DoctorController.class).listarDoctores()).withRel("allDoctors");
            return ResponseEntity.ok(allDoctorsLink);
        }
    }

    /**
     * Actualiza un doctor existente por ID.
     *
     * @param id             ID del doctor.
     * @param doctor         Objeto Doctor actualizado, validado mediante Jakarata Bean Validation.
     * @return ResponseEntity con el doctor actualizado o errores de validación.
     * @throws IllegalOperationException Si ocurre una operación ilegal durante la solicitud.
     */
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarDoctor(@PathVariable Long id, @RequestBody Doctor doctor, BindingResult bindingResult) throws IllegalOperationException {
       Optional <Doctor> doctorOptional = doctorService.obtenerDoctorPorId(id);
       if (!doctorOptional.isPresent()){
           if (bindingResult.hasErrors()) {
                Map<String, String> errores = ValidationUtil.getValidationErrors(bindingResult);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errores);
           }else {
                Doctor doctorActualizado = doctorService.actualizarDoctor(id, doctor);
                DoctorModel doctorModel = doctorModelAssembler.toModel(doctorActualizado);
                 return ResponseEntity.ok(doctorModel);
           }
       }else {
           return ResponseEntity.ok(GlobalResponse.error("No se encontró el doctor con el ID proporcionado"));
       }
    }


    @PutMapping("/{doctorId}/citas/{citaId}")
    public ResponseEntity<?> asignarCitaDoctor(@PathVariable Long doctorId, @PathVariable Long citaId) throws IllegalOperationException {
        int cantidadCitas = 20;
        Optional<Doctor> doctorOptional = doctorService.obtenerDoctorPorId(doctorId);
        Doctor verdoctor = doctorOptional.get();
        Optional<Cita> citaOptional = citaService.obtenerCitaPorId(citaId);

        if(verdoctor.getCitas().size() >= cantidadCitas){
            return ResponseEntity.ok(GlobalResponse.error("No hay citas disponibles para este doctor"));
        }

        // Obtiene el horario del doctor
        Horario horario = verdoctor.getHorario();
        Duration duracionHorario = Duration.between(horario.getHoraInicio(), horario.getHoraFin());
        Duration intervalo;

        // Si el horario es mayor a 6 horas, se considera la hora de almuerzo
        if (duracionHorario.toHours() > 6) {
            duracionHorario = duracionHorario.minusHours(1);
        }
        intervalo = duracionHorario.dividedBy(cantidadCitas);

        LocalDateTime horaCita = LocalDateTime.of(horario.getDia(), horario.getHoraInicio());

        // Calcula la fecha y hora de la cita basándose en el horario del doctor y el número de citas que ya tiene
        for (int i = 0; i < cantidadCitas; i++) {
            // Salta la hora de almuerzo
            if (horaCita.toLocalTime().equals(LocalTime.of(13, 0))) {
                horaCita = horaCita.plusHours(1);
            }

            // Asigna la fecha y hora a cada cita
            Cita cita1 = new Cita();
            cita1.setFecha(horaCita.toLocalDate());  // Usa la fecha del horario
            cita1.setHora(horaCita.toLocalTime());

            // Avanza al siguiente intervalo de tiempo
            horaCita = horaCita.plus(intervalo);

            // Asigna la cita al doctor y guarda los cambios
            verdoctor.getCitas().add(cita1);
        }

        Doctor doctor = doctorService.asignarCitaDoctor(doctorId, citaId);
        DoctorModel doctorModel = doctorModelAssembler.toModel(doctor);
        return ResponseEntity.ok(doctorModel);
    }



}