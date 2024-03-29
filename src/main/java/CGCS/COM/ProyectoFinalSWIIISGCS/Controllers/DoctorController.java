package CGCS.COM.ProyectoFinalSWIIISGCS.Controllers;

import CGCS.COM.ProyectoFinalSWIIISGCS.Domain.Cita;
import CGCS.COM.ProyectoFinalSWIIISGCS.Domain.Doctor;
import CGCS.COM.ProyectoFinalSWIIISGCS.Domain.Especialidad;
import CGCS.COM.ProyectoFinalSWIIISGCS.Domain.Horario;
import CGCS.COM.ProyectoFinalSWIIISGCS.ImpHateoas.Cita.CitaModelAssembler;
import CGCS.COM.ProyectoFinalSWIIISGCS.ImpHateoas.Doctor.DoctorModel;
import CGCS.COM.ProyectoFinalSWIIISGCS.ImpHateoas.Doctor.DoctorModelAssembler;
import CGCS.COM.ProyectoFinalSWIIISGCS.Services.CitaService;
import CGCS.COM.ProyectoFinalSWIIISGCS.Services.DoctorService;
import CGCS.COM.ProyectoFinalSWIIISGCS.Services.EspecialidadService;
import CGCS.COM.ProyectoFinalSWIIISGCS.Validation.ValidationUtil;
import CGCS.COM.ProyectoFinalSWIIISGCS.exception.IllegalOperationException;
import CGCS.COM.ProyectoFinalSWIIISGCS.responses.GlobalResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.*;
import org.springframework.hateoas.*;
import org.springframework.http.*;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.*;
import java.util.*;

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

    // Inyección de dependencias de los servicios y ensambladores necesarios
    @Autowired
    private DoctorService doctorService;
    @Autowired
    private CitaService citaService;
    @Autowired
    private EspecialidadService especialidadService;
    @Autowired
    private DoctorModelAssembler doctorModelAssembler;
    @Autowired
    private CitaModelAssembler citaModelAssembler;

    /**
     * Obtiene la lista de doctores.
     *
     * @return ResponseEntity con una lista de doctores y encabezados apropiados.
     * @throws IllegalOperationException Si ocurre una operación ilegal durante la solicitud.
     */
    @GetMapping()
    public ResponseEntity<?> listarDoctores() throws IllegalOperationException {
        // Obtiene la lista de doctores del servicio
        List<Doctor> doctores = doctorService.listarDoctores();
        List<EntityModel<DoctorModel>> doctorModels = new ArrayList<>();

        // Transforma los doctores en modelos HATEOAS
        for (Doctor doctor : doctores) {
            DoctorModel doctorModel = doctorModelAssembler.toModel(doctor);

            // Agrega enlaces HATEOAS
            Link selfLink = linkTo(methodOn(DoctorController.class).obtenerDoctor(doctor.getIdDoctor())).withSelfRel();
            Link citasDoctorLink = linkTo(methodOn(CitaController.class).listarCitasPorDoctor(doctor.getIdDoctor())).withRel("Ver citas del Doctor");
            doctorModel.add(selfLink, citasDoctorLink);

            EntityModel<DoctorModel> entityModel = EntityModel.of(doctorModel);
            doctorModels.add(entityModel);
        }

        // Agrega enlace HATEOAS para ver todos los doctores
        Link allDoctorsLink = linkTo(methodOn(DoctorController.class).listarDoctores()).withSelfRel();
        CollectionModel<EntityModel<DoctorModel>> collectionModel = CollectionModel.of(doctorModels, allDoctorsLink);
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
        // Verifica errores de validación y responde con mensajes de error si es necesario
        if (bindingResult.hasErrors()) {
            Map<String, String> errores = ValidationUtil.getValidationErrors(bindingResult);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errores);
        } else {
            // Registra el nuevo doctor y responde con el modelo HATEOAS del doctor registrado
            Doctor nuevoDoctor = doctorService.registrarDoctor(doctor);
            DoctorModel doctorModel = doctorModelAssembler.toModel(nuevoDoctor);
            Link allDoctorsLink = linkTo(methodOn(DoctorController.class).listarDoctores()).withRel("Ver lista Doctores");
            doctorModel.add(allDoctorsLink);
            return ResponseEntity.ok(doctorModel);
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
        // Obtiene el doctor opcional por ID
        Optional<Doctor> doctorOptional = doctorService.obtenerDoctorPorId(id);

        // Verifica la existencia del doctor y responde con un mensaje de error si no existe
        if (!doctorOptional.isPresent()) {
            return ResponseEntity.ok(GlobalResponse.error("No se encontró el doctor con el ID A eliminar"));
        } else {
            // Elimina el doctor y responde con enlace HATEOAS para ver todos los doctores
            doctorService.eliminarDoctor(id);
            Link allDoctorsLink = linkTo(methodOn(DoctorController.class).listarDoctores()).withRel("Eliminado correctamente, Ver todos los Doctors");
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
    public ResponseEntity<?> actualizarDoctor(@PathVariable Long id, @RequestBody @Valid Doctor doctor, BindingResult bindingResult) throws IllegalOperationException {
        // Verifica errores de validación y responde con mensajes de error si es necesario
        if (bindingResult.hasErrors()) {
            Map<String, String> errores = ValidationUtil.getValidationErrors(bindingResult);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errores);
        }

        // Obtiene el doctor opcional por ID y responde con un mensaje de error si no existe
        Optional<Doctor> doctorOptional = doctorService.obtenerDoctorPorId(id);
        if (!doctorOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontró el doctor con el ID proporcionado");
        }

        // Actualiza el doctor y responde con el modelo HATEOAS del doctor actualizado
        Doctor doctorActualizado = doctorService.actualizarDoctor(id, doctor);
        DoctorModel doctorModel = doctorModelAssembler.toModel(doctorActualizado);
        Link allDoctorsLink = linkTo(methodOn(DoctorController.class).listarDoctores()).withRel("Ver lista Doctores");
        doctorModel.add(allDoctorsLink);
        return ResponseEntity.ok(doctorModel);
    }
    /**
     * Asigna una cita a un doctor según su horario y cantidad de citas disponibles.
     *
     * @param doctorId ID del doctor.
     * @param citaId   ID de la cita a asignar.
     * @return ResponseEntity con el doctor actualizado o mensajes de error.
     * @throws IllegalOperationException Si ocurre una operación ilegal durante la solicitud.
     */
    @PutMapping("/{doctorId}/citas/{citaId}")
    public ResponseEntity<?> asignarCitaDoctor(@PathVariable Long doctorId, @PathVariable Long citaId) throws IllegalOperationException {
        // Verifica la cantidad de citas disponibles para el doctor
        int cantidadCitas = 20;
        Optional<Doctor> doctorOptional = doctorService.obtenerDoctorPorId(doctorId);
        Doctor verdoctor = doctorOptional.get();
        Optional<Cita> citaOptional = citaService.obtenerCitaPorId(citaId);

        // Responde con un mensaje de error si no hay citas disponibles
        if (verdoctor.getCitas().size() >= cantidadCitas) {
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

        // Si el horario del doctor está en el pasado, devuelve un error
        LocalDate fechaActual = LocalDate.now();
        if (horario.getDia().isBefore(fechaActual)) {
            return ResponseEntity.ok(GlobalResponse.error("El doctor no tiene un horario futuro"));
        }

        LocalDateTime horaCita = LocalDateTime.of(horario.getDia(), horario.getHoraInicio());

        // Actualiza las citas existentes
        for (Cita cita : verdoctor.getCitas()) {
            cita.setFecha(horaCita.toLocalDate());
            cita.setHora(horaCita.toLocalTime());
            horaCita = horaCita.plus(intervalo);
        }

        // Asigna nuevas citas si es necesario
        for (int i = verdoctor.getCitas().size(); i < cantidadCitas; i++) {
            Cita cita1 = new Cita();
            cita1.setFecha(horaCita.toLocalDate());
            cita1.setHora(horaCita.toLocalTime());
            verdoctor.getCitas().add(cita1);
            horaCita = horaCita.plus(intervalo);
        }

        // Asigna la cita al doctor y responde con el modelo HATEOAS del doctor actualizado
        Doctor doctor = doctorService.asignarCitaDoctor(doctorId, citaId);
        DoctorModel doctorModel = doctorModelAssembler.toModel(doctor);
        Link allDoctorsLink = linkTo(methodOn(DoctorController.class).listarDoctores()).withRel("Ver lista Doctores");
        Link citasDoctorLink = linkTo(methodOn(CitaController.class).listarCitasPorDoctor(doctorId)).withRel("Ver citas del Doctor");
        doctorModel.add(citasDoctorLink);
        doctorModel.add(allDoctorsLink);
        return ResponseEntity.ok(doctorModel);
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
        // Obtiene el doctor opcional por ID
        Optional<Doctor> optionalDoctor = doctorService.obtenerDoctorPorId(id);

        // Responde con un mensaje de error si no se encuentra el doctor
        if (optionalDoctor.isPresent()) {
            Doctor doctor = optionalDoctor.get();
            DoctorModel doctorModel = doctorModelAssembler.toModel(doctor);
            Link allDoctorsLink = linkTo(methodOn(DoctorController.class).listarDoctores()).withRel("Ver lista Doctores");
            doctorModel.add(allDoctorsLink);
            Link citasDoctorLink = linkTo(methodOn(CitaController.class).listarCitasPorDoctor(id)).withRel("Ver citas del Doctor");
            doctorModel.add(citasDoctorLink);
            return ResponseEntity.ok(doctorModel);
        } else {
            return ResponseEntity.ok(GlobalResponse.error("No se encontró el doctor con el ID proporcionado"));
        }
    }

    /**
     * Asigna una especialidad a un doctor.
     *
     * @param doctorId      ID del doctor.
     * @param especialidadId ID de la especialidad a asignar.
     * @return ResponseEntity con el doctor actualizado o mensajes de error.
     * @throws IllegalOperationException Si ocurre una operación ilegal durante la solicitud.
     */
    @PutMapping("/{doctorId}/especialidades/{especialidadId}")
    public ResponseEntity<?> asignarEspecialidadDoctor(@PathVariable Long doctorId, @PathVariable Long especialidadId) throws IllegalOperationException {
        // Verifica que los IDs no sean nulos
        if (doctorId == null || especialidadId == null) {
            throw new IllegalArgumentException("Los IDs del doctor y la especialidad no pueden ser nulos");
        }

        // Obtiene el doctor opcional por ID y responde con un mensaje de error si no existe
        Optional<Doctor> optionalDoctor = doctorService.obtenerDoctorPorId(doctorId);
        if (!optionalDoctor.isPresent()) {
            throw new IllegalOperationException("No se encontró el doctor con el ID proporcionado");
        }

        // Obtiene el doctor y la especialidad y realiza la asignación
        Doctor doctor = optionalDoctor.get();
        Optional<Especialidad> optionalEspecialidad = especialidadService.buscarEspecialidadPorId(especialidadId);
        if (!optionalEspecialidad.isPresent()) {
            throw new IllegalOperationException("No se encontró la especialidad con el ID proporcionado");
        }
        Especialidad especialidad = optionalEspecialidad.get();
        doctor.getEspecialidades().add(especialidad);
        especialidad.getDoctores().add(doctor);

        // Actualiza el doctor y responde con el modelo HATEOAS del doctor actualizado
        doctorService.actualizarDoctor(doctorId, doctor);
        DoctorModel doctorModel = doctorModelAssembler.toModel(doctor);
        Link allDoctorsLink = linkTo(methodOn(DoctorController.class).listarDoctores()).withRel("Ver lista Doctores");
        doctorModel.add(allDoctorsLink);
        return ResponseEntity.ok(doctorModel);
    }
    // Resto del código...

    /**
     * Desasigna una cita a un doctor.
     *
     * @param doctorId ID del doctor.
     * @param citaId   ID de la cita a desasignar.
     * @return ResponseEntity con el doctor actualizado o mensajes de error.
     * @throws IllegalOperationException Si ocurre una operación ilegal durante la solicitud.
     */
    @DeleteMapping("/{doctorId}/citas/{citaId}")
    public ResponseEntity<?> desasignarCitaDoctor(@PathVariable Long doctorId, @PathVariable Long citaId) throws IllegalOperationException {
        // Desasigna la cita al doctor y responde con el doctor actualizado
        Doctor doctor = doctorService.desasignarCitaDoctor(doctorId, citaId);
        return ResponseEntity.ok(doctor);
    }
}